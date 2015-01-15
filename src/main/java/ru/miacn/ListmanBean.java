package ru.miacn;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ru.miacn.fias.FiasEditor;
import ru.miacn.persistence.model.Patient;
import ru.miacn.utils.JpaUtils;


@Named
@SessionScoped
@Transactional
public class ListmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	
	private String srcFam;
	private String srcIm;
	private String srcOt;
	private Date srcDr;
	private List<Patient> patients;
	
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	@Inject
	private FilterBean fpar;
	@Inject
	private FiasEditor fias;

    @PostConstruct
    public void init() {
    	clearSearch();
    }
    
	public void search() {
    	String sql = ""
    			+ "SELECT * "
//    			+ "(select r.name from examination e LEFT JOIN r_result_type r ON (e.result_id = r.id) where p._ver_parent_id = e.patient_id order by e.dat desc limit 1) as result "
    			+ "FROM patient p "
    			+ "LEFT JOIN r_gender g ON (g.id = p.sex_id) "
    			+ "WHERE p._ver_active = TRUE ";
    	Map<String, Object> params = new HashMap<>();
    	
    	if (!getSrcFam().isEmpty()) {
    		sql += "AND p.last_name ILIKE :last_name ";
    		params.put("last_name", getSrcFam() + "%");
    	}
    	if (!getSrcIm().isEmpty()) {
    		sql += "AND p.first_name ILIKE :first_name ";
    		params.put("first_name", getSrcIm() + "%");
    	}
    	if (!getSrcOt().isEmpty()) {
    		sql += "AND p.father_name ILIKE :father_name ";
    		params.put("father_name", getSrcOt() + "%");
    	}
    	if (getSrcDr() != null) {
    		sql += "AND p.dat_birth = :dat_birth ";
    		params.put("dat_birth", getSrcDr());
    	}
    	sql += "LIMIT 32 ";
    	
    	setPatients(JpaUtils.getNativeResultList(em, sql, params, Patient.class));
    }
    
    public void clearSearch() {
    	setSrcFam("");
    	setSrcIm("");
    	setSrcOt("");
    	setSrcDr(null);
    	
    	search();
    }
    
	public void filter() {
		String sql_join = "";
		String sql_where = "";
		Map<String, Object> params = new HashMap<>();
    	String sql = "SELECT * ";
		sql += "FROM patient p " +
    			"LEFT JOIN r_gender g ON (g.id = p.sex_id) ";
    	if (fpar.getSelectedRegObs() != null ||
   			fpar.getSelectedTerObs() != null ||
   			fpar.getSelectedLpuObs() != null ||
   			fpar.getSelectedRezType() != null ||
   			fpar.getDatStart() != null ||
   			fpar.getDatEnd() != null) {

    		sql_join = "JOIN examination e ON (p._ver_parent_id = e.patient_id) ";

    		if (fpar.getSelectedRegObs() != null) {
        		sql_where += "AND e.med_reg_id = :reg_id ";
        		params.put("reg_id", fpar.getSelectedRegObs().getRegId());
        	}
        	if (fpar.getSelectedTerObs() != null) {
        		sql_where += "AND e.med_city_id = :city_id ";
        		params.put("city_id", fpar.getSelectedTerObs().getId().getTerId());
        	}
        	if (fpar.getSelectedLpuObs() != null) {
        		sql_where += "AND e.med_lpu_id = :lpu_id ";
        		params.put("lpu_id", fpar.getSelectedLpuObs().getId().getLpuId());
        	}
        	if (fpar.getSelectedRezType() != null) {
        		sql_where += "AND e.result_id = :res_id ";
        		params.put("res_id", fpar.getSelectedRezType().getId());
        	}
   			if ((fpar.getDatStart() != null && fpar.getDatEnd() != null) && fpar.getDatEnd().compareTo(fpar.getDatStart()) < 0) {
        		sql_where += "AND e.dat between :dn and :dk ";
        		params.put("dn", fpar.getDatStart());
        		params.put("dk", fpar.getDatEnd());
   			}
    	}

		sql += sql_join;

		sql += "WHERE p._ver_active = TRUE ";

		if (fpar.getSelectedMor() != null) {
    		sql += "AND p.med_reg_id = :med_reg_id ";
    		params.put("med_reg_id", fpar.getSelectedMor().getRegId());
    	}

    	if (fpar.getSelectedMot() != null) {
    		sql += "AND p.med_city_id = :med_city_id ";
    		params.put("med_city_id", fpar.getSelectedMot().getId().getTerId());
    	}

    	if (fpar.getSelectedMom() != null) {
    		sql += "AND p.med_lpu_id = :med_lpu_id ";
    		params.put("med_lpu_id", fpar.getSelectedMom().getId().getLpuId());
    	}

    	if (fpar.getSelectedMop() != null) {
    		sql += "AND p.med_pol_id = :med_pol_id ";
    		params.put("med_pol_id", fpar.getSelectedMop().getId().getPolId());
    	}

    	if (fpar.getSelectedDg() != null) {
    		sql += "AND p.decr_group_id = :decr_group_id ";
    		params.put("decr_group_id", fpar.getSelectedDg().getId());
    	}

    	if (fpar.getSelectedMg() != null) {
    		sql += "AND p.med_group_id = :med_group_id ";
    		params.put("med_group_id", fpar.getSelectedMg().getId());
    	}

    	if (fpar.getSelectedSg() != null) {
    		sql += "AND p.soc_group_id = :soc_group_id ";
    		params.put("soc_group_id", fpar.getSelectedSg().getId());
    	}

    	if(fias.getRegion().getFormalname() != null){
    		sql += "AND p.liv_reg = :reg ";
    		params.put("reg", fias.getRegion().getFormalname());
    	}

    	if(fias.getGorod().getFormalname() != null){
    		sql += "AND p.liv_city = :city ";
    		params.put("city", fias.getGorod().getFormalname());
    	}
    	
    	if(fias.getUlica().getFormalname() != null){
    		sql += "AND p.liv_street = :street ";
    		params.put("street", fias.getUlica().getFormalname());
    	}
    	
    	if(fias.getDom() != null){
    		sql += "AND p.liv_house = :dom ";
    		params.put("dom", fias.getDom());
    	}
    	
    	if(fias.getKorp() != null){
    		sql += "AND p.liv_facility = :fac ";
    		params.put("fac", fias.getKorp());
    	}
    	
    	if(fias.getStr() != null){
    		sql += "AND p.liv_building = :building ";
    		params.put("building", fias.getStr());
    	}
    	
    	if(fias.getKv() != null){
    		sql += "AND p.liv_flat = :flat ";
    		params.put("flat", fias.getKv());
    	}
    	
    	sql += sql_where;
    	sql += "LIMIT 32 ";
    	
    	setPatients(JpaUtils.getNativeResultList(em, sql, params, Patient.class));
    	fpar.clearFilter();
	}

	public String getSrcFam() {
		return srcFam;
	}

	public void setSrcFam(String srcFam) {
		this.srcFam = srcFam;
	}

	public String getSrcIm() {
		return srcIm;
	}

	public void setSrcIm(String srcIm) {
		this.srcIm = srcIm;
	}

	public String getSrcOt() {
		return srcOt;
	}

	public void setSrcOt(String srcOt) {
		this.srcOt = srcOt;
	}

	public Date getSrcDr() {
		return srcDr;
	}

	public void setSrcDr(Date srcDr) {
		this.srcDr = srcDr;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
}
