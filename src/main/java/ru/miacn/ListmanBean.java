package ru.miacn;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.miacn.persistence.model.Patient;
import ru.miacn.utils.JpaUtils;


@Named
@SessionScoped
public class ListmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	
	private String srcFam;
	private String srcIm;
	private String srcOt;
	private Date srcDr;
	private List<Patient> patients;
	
	@PersistenceContext
	private EntityManager em;

    @PostConstruct
    public void init() {
    	clearSearch();
    }
    
	public void search() {
    	String sql = ""
    			+ "SELECT * "
    			+ "FROM patient p "
    			+ "JOIN r_gender g ON (g.id = p.sex_id) "
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
