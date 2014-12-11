package ru.miacn;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.miacn.persistence.model.Examination;
import ru.miacn.utils.JpaUtils;

@Named
@RequestScoped
public class ExaminationBean implements Serializable {
	private static final long serialVersionUID = 5513014259706142020L;

	@PersistenceContext
	private EntityManager em;
	
	private List<Examination> examinations;
	private int patientId;

	public void loadExam(int patId) {
		setPatientId(patId);
		
    	String sql = ""
    		+ "SELECT * "
    		+ "FROM examination e "
    		+ "WHERE e.patient_id = :id "
			+ "ORDER BY e.dat desc ";
    	Map<String, Object> params = new HashMap<>();
    	
   		params.put("id", getPatientId());
    	
    	setExaminations(JpaUtils.getNativeResultList(em, sql, params, Examination.class));
    }
	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}
