package ru.miacn.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

public class ReportForm057u extends JasperReport {
	public ReportForm057u() {
		super("form_057u");
	}
	
	@Override
	public void printReport(OutputStream output, ReportType type) throws JRException, IOException {
		super.printReport(output, new HashMap<String, Object>(), type);
	}
}
