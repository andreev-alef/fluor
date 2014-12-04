package ru.miacn.report;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

public class ReportDecrGroup extends HtmlReport {
	public ReportDecrGroup() {
		super("rep_decr.jasper");
	}
	
	@Override
	public void printReport(OutputStream output) throws JRException, IOException, SQLException, NamingException {
		Map<String, Object> params = new HashMap<>();
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("SELECT id, name FROM r_decr_group")) {
			params.put("p_table_data", new JRResultSetDataSource(rs));
			
			super.printReport(output, params);
		}
	}
}
