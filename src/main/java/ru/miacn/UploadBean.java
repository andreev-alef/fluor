package ru.miacn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ru.miacn.persistence.model.Patient;
import ru.miacn.utils.JpaUtils;

@Named
@RequestScoped
public class UploadBean {
	private static final String[] fieldNames = {"фамилия", "имя", "отчество", "дата рождения", "регион", "город", "улица", "дом", "квартира", "код региона прикрепления", "код территории прикрепления", "код лпу прикрепления", "код поликлиники прикрепления", "код региона обследования", "код территории обследования", "код лпу обследования", "дата обследования", "код метода", "код результата"};
	private static final int[] reqPatFields = {0, 1, 3};
	private static final int[] reqExamFields = {13, 14, 15, 16, 17, 18};
	private static final String resourcePath = getResourcePath();
	
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	
	private StreamedContent fileXls;
	private StreamedContent fileXlsx;

	private boolean error = false;
	private int rowNum;
	private DataFormatter formatter = new DataFormatter();
	private int uploadCount;
	private String countMessage;
	private String uploadMessage;
	
	@Transactional
	public void fileUpload(FileUploadEvent event) throws IOException {
		try {
			if (error == false) {
				resetUploadStats();
				
				UploadedFile file = event.getFile();
				Path path = getFilePath(file);
				
				Files.copy(file.getInputstream(), path);
				uploadData(path);
			}
		} catch (Exception e) {
			error = true;
			setErrorMessage(e);
			throw e;
		}
	}
	
	private void resetUploadStats() {
		uploadCount = 0;
		setCountMessage(String.format("Подгружено строк: %d.", uploadCount));
		setUploadMessage("Подгрузка прошла успешно.");
	}
	
	private void setErrorMessage(Exception e) {
		SQLException sql = null;
		Throwable search = e.getCause();
		
		while (search != null) {
			if (search instanceof SQLException) {
				sql = (SQLException) search;
				break;
			}
			search = search.getCause();
		}
		
		if (sql == null)  {
			setUploadMessage("Ошибка: " + e.getLocalizedMessage());
		} else {
			switch (sql.getSQLState()) {
			case "23502":
				setUploadMessage("Ошибка: не указано обязательное поле.");
				break;
			case "23503":
				setUploadMessage("Ошибка: некорректно указан код и/или комибнация кодов.");
				break;
			default:
				setUploadMessage("Ошибка: не удалось записать в базу данных.");
				break;
			}
		}
	}
	
	private Path getFilePath(UploadedFile file) throws UnsupportedEncodingException {
		String fileName = new String(file.getFileName().getBytes("iso-8859-1"), "utf-8");
		int extIdx = fileName.lastIndexOf(".");
		long mills = System.currentTimeMillis();
		String dir = System.getProperty("java.io.tmpdir");
		
		if (extIdx > -1)
			fileName = fileName.substring(0, extIdx) + "_" + mills + fileName.substring(extIdx);
		
		return Paths.get(dir, fileName);
	}
	
	private void uploadData(Path file) throws IOException {
		String extension = file.toString().substring(file.toString().length() - 3);
		try (FileInputStream fis = new FileInputStream(file.toFile());
			Workbook wb = (extension.toLowerCase().equals("xls")) ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);) {
			Sheet sheet = wb.getSheetAt(0);
			boolean isFirst = true;
			
			for (Row row : sheet) {
				rowNum = row.getRowNum();
				if (isFirst) {
					checkStructure(row);
					isFirst = false;
					continue;
				}
				Patient patient = getPatient(row);
				checExamExists(row, patient);
				persistExam(row, patient);
				uploadCount++;
			}
			
			setCountMessage(String.format("Подгружено строк: %d.", uploadCount));
			if (uploadCount == 0)
				setUploadMessage("Нечего подгружать.");
		}
	}
	
	private void checkStructure(Row row) {
		boolean isError = false;
		
		for (int i = 0; i < fieldNames.length; i++) {
			isError |= !getStringCellValue(row, i).equals(fieldNames[i]);
		}
		
		if (isError)
			throw new IllegalArgumentException("Структура документа не соответствует ожидаемой.");
	}
	
	private String getStringCellValue(Row row, int idx) {
		try {
			return formatter.formatCellValue(row.getCell(idx, Row.CREATE_NULL_AS_BLANK));
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Строка: %d, столбец: %s - не удалось прочитать ячейку.", rowNum + 1, getColumnName(idx + 1)));
		}
	}

	private Date getDateCellValue(Row row, int idx) {
		try {
			return row.getCell(idx, Row.CREATE_NULL_AS_BLANK).getDateCellValue();
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Строка: %d, столбец: %s - не удалось прочитать ячейку.", rowNum + 1, getColumnName(idx + 1)));
		}
	}
	
	private Double getNumericCellValue(Row row, int idx) {
		try {
			return row.getCell(idx, Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Строка: %d, столбец: %s - не удалось прочитать ячейку.", rowNum + 1, getColumnName(idx + 1)));
		}
	}
	
	private String getColumnName(int column) {
		String columnString = "";
		int columnNumber = column;
		
		while (columnNumber > 0) {
			int currentLetterNumber = (columnNumber - 1) % 26;
			char currentLetter = (char)(currentLetterNumber + 65);
			columnString += currentLetter;
			columnNumber = (columnNumber - (currentLetterNumber + 1)) / 26;
		}
		
		return columnString;
	}
	
	private Patient getPatient(Row row) {
		checkRequiredFields(row, reqPatFields);
		
		String sql = ""
				+ "SELECT * "
				+ "FROM patient "
				+ "WHERE _ver_active = TRUE ";
		Map<String, Object> params = new HashMap<>();
		
		sql += "AND last_name = :last_name ";
		params.put("last_name", getStringCellValue(row, 0));
		
		sql += "AND first_name = :first_name ";
		params.put("first_name", getStringCellValue(row, 1));
		
		if (!getStringCellValue(row, 2).isEmpty()) {
			sql += "AND father_name = :father_name ";
			params.put("father_name", getStringCellValue(row, 2));
		} else {
			sql += "AND father_name IS NULL ";
		}
		
		sql += "AND dat_birth = :dat_birth ";
		params.put("dat_birth", getDateCellValue(row, 3));
		
		if (!getStringCellValue(row, 4).isEmpty()) {
			sql += "AND liv_reg = :liv_reg ";
			params.put("liv_reg", getStringCellValue(row, 4));
		}
		
		if (!getStringCellValue(row, 5).isEmpty()) {
			sql += "AND liv_city = :liv_city ";
			params.put("liv_city", getStringCellValue(row, 5));
		}
		
		if (!getStringCellValue(row, 6).isEmpty()) {
			sql += "AND liv_street = :liv_street ";
			params.put("liv_street", getStringCellValue(row, 6));
		}
		
		if (!getStringCellValue(row, 7).isEmpty()) {
			sql += "AND liv_house = :liv_house ";
			params.put("liv_house", getStringCellValue(row, 7));
		}
		
		if (!getStringCellValue(row, 8).isEmpty()) {
			sql += "AND liv_flat = :liv_flat ";
			params.put("liv_flat", getStringCellValue(row, 8));
		}
		
		if (!getStringCellValue(row, 9).isEmpty()) {
			sql += "AND med_reg_id = :med_reg_id ";
			params.put("med_reg_id", getNumericCellValue(row, 9).intValue());
		}
		
		if (!getStringCellValue(row, 10).isEmpty()) {
			sql += "AND med_city_id = :med_city_id ";
			params.put("med_city_id", getNumericCellValue(row, 10).intValue());
		}
		
		if (!getStringCellValue(row, 11).isEmpty()) {
			sql += "AND med_lpu_id = :med_lpu_id ";
			params.put("med_lpu_id", getNumericCellValue(row, 11).intValue());
		}
		
		if (!getStringCellValue(row, 12).isEmpty()) {
			sql += "AND med_pol_id = :med_pol_id ";
			params.put("med_pol_id", getNumericCellValue(row, 12).intValue());
		}
		
		try {
			return (Patient) JpaUtils.getNativeQuery(em, sql, params, Patient.class).getSingleResult();
		} catch (NoResultException e) {
			throw new IllegalArgumentException(String.format("Строка %d, пациент не найден.", rowNum + 1));
		} catch (NonUniqueResultException e) {
			throw new IllegalArgumentException(String.format("Строка %d, найдено более одного пациента.", rowNum + 1));
		}
	}
	
	private void checExamExists(Row row, Patient patient) {
		checkRequiredFields(row, reqExamFields);
		
		String sql = ""
				+ "SELECT count(id) "
				+ "FROM examination "
				+ "WHERE patient_id = :patient_id ";
		Map<String, Object> params = new HashMap<>();
		
		params.put("patient_id", patient.getPatientId().getId());
		
		sql += "AND med_reg_id = :med_reg_id ";
		params.put("med_reg_id", getNumericCellValue(row, 13).intValue());
		
		sql += "AND med_city_id = :med_city_id ";
		params.put("med_city_id", getNumericCellValue(row, 14).intValue());
		
		sql += "AND med_lpu_id = :med_lpu_id ";
		params.put("med_lpu_id", getNumericCellValue(row, 15).intValue());
		
		sql += "AND dat = :dat ";
		params.put("dat", getDateCellValue(row, 16));
		
		sql += "AND type_id = :type_id ";
		params.put("type_id", getNumericCellValue(row, 17).intValue());
		
		sql += "AND result_id = :result_id ";
		params.put("result_id", getNumericCellValue(row, 18).intValue());
		
		if (((Number) JpaUtils.getNativeQuery(em, sql, params).getSingleResult()).longValue() > 0)
			throw new IllegalArgumentException(String.format("Строка %d уже существует.", rowNum + 1));
	}
	
	private void persistExam(Row row, Patient patient) {
		String sql = ""
				+ "INSERT "
				+ "INTO examination (%s) "
				+ "VALUES (%s) ";
		String fields = "";
		String values = "";
		Map<String, Object> params = new HashMap<>();
		
		fields += "patient_id, ";
		values += ":patient_id, ";
		params.put("patient_id", patient.getPatientId().getId());
		
		fields += "dat, ";
		values += ":dat, ";
		params.put("dat", getDateCellValue(row, 16));
		
		fields += "med_reg_id, ";
		values += ":med_reg_id, ";
		params.put("med_reg_id", getNumericCellValue(row, 13).intValue());
		
		fields += "med_city_id, ";
		values += ":med_city_id, ";
		params.put("med_city_id", getNumericCellValue(row, 14).intValue());
		
		fields += "med_lpu_id, ";
		values += ":med_lpu_id, ";
		params.put("med_lpu_id", getNumericCellValue(row, 15).intValue());
		
		fields += "type_id, ";
		values += ":type_id, ";
		params.put("type_id", getNumericCellValue(row, 17).intValue());
		
		fields += "result_id, ";
		values += ":result_id, ";
		params.put("result_id", getNumericCellValue(row, 18).intValue());
		
		fields = fields.substring(0, fields.length() - 2);
		values = values.substring(0, values.length() - 2);
		sql = String.format(sql, fields, values);
		
		JpaUtils.getNativeQuery(em, sql, params).executeUpdate();
	}
	
	private void checkRequiredFields(Row row, int[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if (getStringCellValue(row, fields[i]).isEmpty())
				throw new IllegalArgumentException(String.format("Строка %d, поле '%s' обязательно для заполнения.", rowNum + 1, fieldNames[fields[i]]));
		}
	}

	public String getUploadMessage() {
		return uploadMessage;
	}

	public void setUploadMessage(String uploadMessage) {
		this.uploadMessage = uploadMessage;
	}

	public String getCountMessage() {
		return countMessage;
	}

	public void setCountMessage(String countMessage) {
		this.countMessage = countMessage;
	}

	private static String getResourcePath() {
		Package pkg = UploadBean.class.getPackage();
		String pkgName = pkg.getName();
		String[] parts = pkgName.split("\\.");
		String path = "";
		
		for (int i = 0; i < parts.length; i++) {
			path += "../";
		}
		path += "upload-templates/";
		
		return path;
	}
	
	public void openFileFromResource(boolean isXlsx) throws UnsupportedEncodingException {
		String extension = (isXlsx) ? ".xlsx" : ".xls";
		String name = "template" + extension;
		String mime = (isXlsx) ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" : "application/vnd.ms-excel";
		InputStream stream = this.getClass().getResourceAsStream(resourcePath + name);
		DefaultStreamedContent file = new DefaultStreamedContent(stream, mime, new String(name.getBytes("utf-8"), "iso-8859-1"));
		
		if (isXlsx)
			setFileXlsx(file);
		else
			setFileXls(file);
	}
	
	public StreamedContent getFileXls() throws UnsupportedEncodingException {
		if (fileXls == null)
			openFileFromResource(false);
		
		return fileXls;
	}
	
	public void setFileXls(StreamedContent fileXls) {
		this.fileXls = fileXls;
	}
	
	public StreamedContent getFileXlsx() throws UnsupportedEncodingException {
		if (fileXlsx == null)
			openFileFromResource(true);
		
		return fileXlsx;
	}
	
	public void setFileXlsx(StreamedContent fileXlsx) {
		this.fileXlsx = fileXlsx;
	}
}
