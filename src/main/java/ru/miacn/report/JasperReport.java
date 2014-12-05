package ru.miacn.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

public abstract class JasperReport {
	private static final String jasperResourcePath = getJasperResourcePath();
	private String resName;
	
	public JasperReport(String jasperResourceName) {
		resName = jasperResourceName;
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
	
	protected Connection getConnection() throws NamingException, SQLException {
		InitialContext c = new InitialContext();
		DataSource ds = (DataSource) c.lookup("java:/datasources/fluor-DS");
		
		return ds.getConnection();
	}
	
	public abstract void printReport(OutputStream output, ReportType type) throws JRException, IOException, SQLException, NamingException;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void printReport(OutputStream servlet, Map<String, Object> params, ReportType type) throws JRException, IOException {
		try (InputStream reportStream = JasperReport.class.getResourceAsStream(jasperResourcePath + resName)) {
			Exporter exporter;
			ExporterOutput output;
			
			switch (type) {
			case html:
				exporter = new HtmlExporter();
				output = new SimpleHtmlExporterOutput(servlet);
				params.put(JRParameter.IS_IGNORE_PAGINATION, true);
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
