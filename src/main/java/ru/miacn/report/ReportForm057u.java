package ru.miacn.report;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;
import ru.miacn.fias.FiasElement;

public class ReportForm057u extends JasperReport {
	public ReportForm057u() {
		super("form_057u");
	}
	
	@Override
	public void printReport(OutputStream output, ReportType type) throws JRException, IOException, SQLException, NamingException {
		Map<String, Object> params = new HashMap<>();
		try (Connection conn = getConnection()){	
			String sql = "select (last_name ||' '||first_name||' '||coalesce(father_name, '')) as fio, to_char (dat_birth,'DD.MM.YYYY')dat_birth, "
					+ "	liv_reg_id, liv_reg, liv_city_id, liv_city, liv_street_id, liv_street, liv_house_id, liv_house, liv_facility_id, liv_facility,  "
					+ "	liv_building_id, liv_building, liv_flat_id, liv_flat "
					+ "from patient "
					+ "where id = ?"; 
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, patId);
			
			ResultSet rs = stm.executeQuery();
			rs.next();
			
			FiasElement elem;
			
			if (rs.getObject("liv_reg_id") != null)
				elem = fias.getElementById((UUID) rs.getObject("liv_reg_id"));
			else
				elem = new FiasElement(null, rs.getString("liv_reg"));
			fias.setRegion(elem);
			
			if (rs.getObject("liv_city_id") != null)
				elem = fias.getElementById((UUID) rs.getObject("liv_city_id"));
			else
				elem = new FiasElement(null, rs.getString("liv_city"));
			fias.setGorod(elem);
			
			if (rs.getObject("liv_street_id") != null)
				elem = fias.getElementById((UUID) rs.getObject("liv_street_id"));
			else
				elem = new FiasElement(null, rs.getString("liv_street"));
			fias.setUlica(elem);
			
			fias.setDom(rs.getString("liv_house"));		
			fias.setKorp(rs.getString("liv_facility"));	
			fias.setKv(rs.getString("liv_flat"));
			
			SimpleDateFormat day = new SimpleDateFormat("dd");
			SimpleDateFormat month = new SimpleDateFormat("MMMM");
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			
						
			params.put("fio", rs.getString("fio"));
			params.put("dat_birth", rs.getString("dat_birth"));
			params.put("plive", fias.getAddress());
			params.put("day",day.format(System.currentTimeMillis()));
			params.put("month",month.format(System.currentTimeMillis()).toLowerCase());
			params.put("year",year.format(System.currentTimeMillis()));
			
			super.printReport(output, params, type);
		}
	}
}
