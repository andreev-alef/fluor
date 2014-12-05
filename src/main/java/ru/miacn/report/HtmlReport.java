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
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

public abstract class HtmlReport {
	private String resName;
	
	public HtmlReport(String jasperResourceName) {
		resName = jasperResourceName;
	}
	
	protected Connection getConnection() throws NamingException, SQLException {
		InitialContext c = new InitialContext();
		DataSource ds = (DataSource) c.lookup("java:/datasources/fluor-DS");
		
		return ds.getConnection();
	}
	
	public abstract void printReport(OutputStream output) throws JRException, IOException, SQLException, NamingException;
	
	public void printReport(OutputStream output, Map<String, Object> params) throws JRException, IOException {
		params.put(JRParameter.IS_IGNORE_PAGINATION, true);
		try (InputStream reportStream = HtmlReport.class.getResourceAsStream("../../../jasper/" + resName)) {
			JasperPrint print = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());
			HtmlExporter exporter = new HtmlExporter();
			SimpleHtmlReportConfiguration config = new SimpleHtmlReportConfiguration();
			
//			config.setZoomRatio(1.5f);
			exporter.setConfiguration(config);
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(output));
			
			exporter.exportReport();
		}
	}
}
