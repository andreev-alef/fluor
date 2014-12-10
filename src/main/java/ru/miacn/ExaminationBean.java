package ru.miacn;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.miacn.persistence.model.Examination;
import ru.miacn.persistence.model.Patient;

@Named
@SessionScoped
public class ExaminationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	
	private List<Patient> patientList;
	private List<Examination> examList;
	private int terId;
	
	@PostConstruct
    public void init() {
    }

	public String getTerName(){
		return null;
		
	}
	public List<Examination> getExamList() {
		return examList;
	}

	public void setExamList(List<Examination> examList) {
		this.examList = examList;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public int getTerId() {
		return terId;
	}

	public void setTerId(int terId) {
		this.terId = terId;
	}
	
}
