package ru.miacn.report;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

public class ReportMedGroup extends JasperReport {
	public ReportMedGroup() {
		super("rep_med");
	}
	
	@Override
	public void printReport(OutputStream output, ReportType type) throws JRException, IOException, SQLException, NamingException, ParseException {
		Map<String, Object> params = new HashMap<>();
		try (Connection conn = getConnection()){
			
			String sql ="select rmg.id, rmg.name, cnt_beg_year, cnt_beg_year cnt_end_year, cnt_plan_pat, cnt_plan_exam, "
					+ "	ofl.flfl,ofl.flren, prisl.isfl, prisl.isren, "
					+ "	((ofl.flfl + ofl.flren)/cnt_plan_pat *100) as v_pl_lic, "
					+ "	((prisl.isfl + prisl.isren)/cnt_plan_exam *100) as v_pl_ex, "
					+ "	((ofl.flfl + ofl.flren)/cnt_beg_year *100) oh_nas, "
//					+ "--Выявлено потологий "
//					+ "	--всего "
					+ "	(	select count (distinct patient_id) "
					+ "		from examination  ex "
					+ "			join patient p on (p._ver_parent_id = ex.patient_id)and (p.med_group_id = rmg.id) "
					+ " 		join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "		where (ex.result_id > 1)and (p._ver_active) and (p.med_reg_id = pl.med_reg_id)and (p.med_city_id = pl.med_city_id) %s "// and (p.med_lpu_id = pl.med_lpu_id) "
					+ "			and (ex.dat between ? and ? ) "
					+ "		group by rmg.id) as vsego, "
//					+ "	-- Активный "
					+ "	(	select count (distinct patient_id) "
					+ "		from examination  ex "
					+ "			join patient p on (p._ver_parent_id = ex.patient_id)and (p.med_group_id = rmg.id) "
					+ "			join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "		where (ex.result_id = 2) and (p._ver_active) and (p.med_reg_id = pl.med_reg_id)and (p.med_city_id = pl.med_city_id) %s "
					+ "			and (ex.dat between ? and ? ) "
					+ "		group by rmg.id) as activ, "
//					+ "	--Неактивный "
					+ "	(	select count (distinct patient_id) "
					+ "		from examination  ex "
					+ "			join patient p on (p._ver_parent_id = ex.patient_id)and (p.med_group_id = rmg.id) "
					+ "			join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "		where (ex.result_id = 3) and (p._ver_active) and (p.med_reg_id = pl.med_reg_id)and (p.med_city_id = pl.med_city_id) %s "
					+ "			and (ex.dat between ? and ? ) "
					+ "		group by rmg.id) as not_activ, "
//					+ " --Прочие "
					+ "	(	select count (distinct patient_id) "
					+ "		from examination  ex "
					+ "			join patient p on (p._ver_parent_id = ex.patient_id)and (p.med_group_id = rmg.id) "
					+ "			join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "		where (ex.result_id > 3) and (p._ver_active) and (p.med_reg_id = pl.med_reg_id)and (p.med_city_id = pl.med_city_id) %s "
					+ "			and (ex.dat between ? and ? ) "
					+ "		group by rmg.id) as proch "
					+ "from r_med_group rmg "
					+ "	left join plan pl on (rmg.id = pl.med_group_id) and (pl.med_reg_id = ?)and (pl.med_city_id = ?)and (pl.med_lpu_id %s ) "
//					+ "-- Осмотр физических лиц "
					+ "	left join ( "
					+ "			select p.med_group_id, "
					+ "				count( (	select distinct patient_id "
					+ "						from examination  ex "
					+ "							join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "								and (p._ver_parent_id = ex.patient_id) "
					+ "						where rem.type_id = 1 and (ex.dat between ? and ? )"
					+ "					)) flfl, "
					+ "				count ((	select distinct patient_id npat "
					+ "						from examination  ex "
					+ "						join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "							and (p._ver_parent_id = ex.patient_id) "
					+ "						where rem.type_id = 2 and (ex.dat between ? and ? )"
					+ "					)) flren "
					+ "			from patient p "
					+ "			where (p._ver_active) and (med_group_id is not null)and (p._ver_active) and (p.med_reg_id = ?)and (p.med_city_id = ?) %s "
					+ "			group by p.med_group_id "
					+ " 		order by p.med_group_id "
					+ "	) ofl on (ofl.med_group_id = rmg.id) "
//					+ "--Проведено исследований "
					+ "	left join ( "
					+ "			select p.med_group_id, "
					+ "				sum((	select count (ex.id ) "
					+ "					from examination  ex "
					+ "						join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "							and (p._ver_parent_id = ex.patient_id) "
					+ "					where rem.type_id = 1 and (ex.dat between ? and ? )"
					+ "					)) isfl, "
					+ "				sum((	select count (ex.id) "
					+ "					from examination  ex "
					+ "						join r_exam_methods rem on (ex.method_id = rem.id) "
					+ "							and (p._ver_parent_id = ex.patient_id) "
					+ "					where rem.type_id = 2 and (ex.dat between ? and ? )"
					+ "				))isren "
					+ "			from patient p "
					+ "			where (p._ver_active) and (med_group_id is not null) and (p._ver_active) and (p.med_reg_id = ?)and (p.med_city_id = ?) %s"
					+ "			group by p.med_group_id "
					+ "			order by p.med_group_id "
					+ "		)prisl on (prisl.med_group_id = rmg.id) "
					+ "order by rmg.id";
			
			String eq = "";
			String lpu ="";
			String gor = "";
			
			if (momId != null){				
				eq = String.format("and (p.med_lpu_id = pl.med_lpu_id)");
				lpu = String.format("and (p.med_lpu_id = ?) ");
				gor = String.format(" = ? ");
			}else{
				gor = String.format(" is null ");
			}
			
			sql = String.format(sql, eq,eq,eq,eq,gor,lpu,lpu);
			
			
			PreparedStatement stm = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			int i =1;
			Date dn = new Date(datStart.getTime());
			Date dk = new Date(datEnd.getTime());

			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);				
			
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);

			stm.setInt(i++, morId);
			stm.setInt(i++, motId);
			if (momId!= null)
				stm.setInt(i++, momId);
			
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			stm.setInt(i++, morId);
			stm.setInt(i++, motId);
			if (momId!= null)
				stm.setInt(i++, momId);
			
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			stm.setDate(i++, dn);
			stm.setDate(i++,dk);
			stm.setInt(i++, morId);
			stm.setInt(i++, motId);
			if (momId!= null)
				stm.setInt(i++, momId);
			
			ResultSet rs = stm.executeQuery();
			params.put("p_table_data", new JRResultSetDataSource(rs));
			params.put("p_beg_date", sdf.format(dn));
			params.put("p_end_date", sdf.format(dk));
			
			
			i=1;
			if (momId!= null)
				sql ="select name from r_medical_org_main where (reg_id = ?) and (ter_id = ?) and (lpu_id =?)";

			else 
				sql = "select name from r_medical_org_ter where (reg_id = ?) and (ter_id = ?)";
							
			PreparedStatement name_lpu = conn.prepareStatement(sql);
			name_lpu.setInt(i++, morId);
			name_lpu.setInt(i++, motId);
			if (momId!= null)
				name_lpu.setInt(i, momId);

			ResultSet rs_namelpu = name_lpu.executeQuery();
			rs_namelpu.next();
			params.put("p_name_lpu",rs_namelpu.getString("name"));
			
			super.printReport(output, params, type);				
		}
	}
}
