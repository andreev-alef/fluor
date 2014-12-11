package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ru.miacn.persistence.model.Examination;
import ru.miacn.persistence.model.Patient;
import ru.miacn.utils.JpaUtils;

@Named
@SessionScoped
public class ExaminationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	private Integer patientId = 1;
	private Patient patient;
	private List<Examination> examinations;
	
	@PostConstruct
    public void init() {
	}

	public void allExam() {
    	String sql = ""
    		+ "SELECT * "
    		+ "FROM examination e "
/*    		+ "LEFT JOIN r_medical_org_region mor ON (mor.reg_id = e.med_reg_id) "
    		+ "LEFT JOIN r_medical_org_ter mot ON (mot.ter_id = e.med_city_id and mot.reg_id = e.med_reg_id) "
*/    		+ "WHERE e.patient_id = :id "
			+ "ORDER BY e.dat desc ";
    	Map<String, Object> params = new HashMap<>();
   		params.put("id", patientId);
    	
    	setExaminations(JpaUtils.getNativeResultList(em, sql, params, Examination.class));
    }

/*	@SuppressWarnings("unchecked")
	public List<Examination> getAllExamList() {
		if (patientId != 0) {
			Query query = em.createNativeQuery("SELECT * FROM examination r WHERE r.patient_id = :p_id ORDER BY r.dat desc", Examination.class);
			query.setParameter("p_id", patientId);
			setExamList(query.getResultList());
		} else {
			setExamList(new ArrayList<Examination>());
		}
		return examList;
	}
*/
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

}
