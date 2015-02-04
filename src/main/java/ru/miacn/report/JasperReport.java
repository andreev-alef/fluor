package ru.miacn.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import ru.miacn.fias.FiasEditor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public abstract class JasperReport {
	private static final String jasperResourcePath = getJasperResourcePath();
	
	public Integer morId;
	public Integer motId;
	public Integer momId;
	public Date datStart;
	public Date datEnd;
	public Integer patId;
	
	private String resName;
	public FiasEditor fias;
	
	public JasperReport(String jasperResourceNamePrefix) {
		resName = jasperResourceNamePrefix;
	}
	
	public String getReportName() {
		return resName;
	}
	
	private static String getJasperResourcePath() {
		Package pkg = JasperReport.class.getPackage();
		String pkgName = pkg.getName();
		String[] parts = pkgName.split("\\.");
		String path = "";
		
		for (int i = 0; i < parts.length; i++) {
			path += "../";
		}
		path += "jasper/";
		
		return path;
	}
	
	public void setParameters(HttpSession session) {
		morId = (session.getAttribute("morId") != null) ? new Integer((Integer) session.getAttribute("morId")) : null;
		motId = (session.getAttribute("motId") != null) ? new Integer((Integer) session.getAttribute("motId")) : null;
		momId = (session.getAttribute("momId") != null) ? new Integer((Integer) session.getAttribute("momId")) : null;
		datStart = (session.getAttribute("datStart") != null) ? new Date(((Date) session.getAttribute("datStart")).getTime()) : null;
		datEnd = (session.getAttribute("datEnd") != null) ? new Date(((Date) session.getAttribute("datEnd")).getTime()) : null;
		patId = (session.getAttribute("patId") != null) ? new Integer((Integer) session.getAttribute("patId")) : null;
	}
	
	protected Connection getConnection() throws NamingException, SQLException {
		InitialContext c = new InitialContext();
		DataSource ds = (DataSource) c.lookup("java:/datasources/fluor-DS");
		
		return ds.getConnection();
	}
	
	public abstract void printReport(OutputStream output, ReportType type) throws JRException, IOException, SQLException, NamingException, ParseException;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printReport(OutputStream servlet, Map<String, Object> params, ReportType type) throws JRException, IOException {
		try (InputStream reportStream = JasperReport.class.getResourceAsStream(jasperResourcePath + resName + ".jasper")) {
			Exporter exporter;
			ExporterOutput output;
			
			switch (type) {
			case html:
				exporter = new HtmlExporter();
				output = new SimpleHtmlExporterOutput(servlet);
				params.put(JRParameter.IS_IGNORE_PAGINATION, true);
				break;
			case pdf:
				exporter = new JRPdfExporter();
				output = new SimpleOutputStreamExporterOutput(servlet);
				break;
			default:
				throw new JRException("Unsupported report type.");
			}
			
			JasperPrint print = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());
			ExporterInput input = new SimpleExporterInput(print);
			
			exporter.setExporterInput(input);
			exporter.setExporterOutput(output);
			
			exporter.exportReport();
		}
	}
}
