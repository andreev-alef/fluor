package ru.miacn.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.miacn.persistence.reference.RExamMethod;
import ru.miacn.persistence.reference.RExamType;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RResultType;
import ru.miacn.persistence.reference.RVerification;

@Entity
public class Examination {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dat;

	@ManyToOne
	@JoinColumn(name="patient_id")
	private PatientId patientId;

	@OneToOne
	@JoinColumn(name="method_id")
	private RExamMethod rExamMethod;

	@OneToOne
	@JoinColumn(name="type_id")
	private RExamType rExamType;

	@OneToOne
	@JoinColumn(name="verification_id")
	private RVerification rVerification;

	@OneToOne
	@JoinColumns({
		@JoinColumn(name="med_city_id", referencedColumnName="ter_id"),
		@JoinColumn(name="med_lpu_id", referencedColumnName="lpu_id"),
		@JoinColumn(name="med_reg_id", referencedColumnName="reg_id")
		})
	private RMedicalOrgMain rMedicalOrgMain;

	@OneToOne
	@JoinColumn(name="result_id")
	private RResultType rResultType;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public PatientId getPatientId() {
		return this.patientId;
	}

	public void setPatientId(PatientId patientId) {
		this.patientId = patientId;
	}

	public RExamMethod getRExamMethod() {
		return this.rExamMethod;
	}

	public void setRExamMethod(RExamMethod rExamMethod) {
		this.rExamMethod = rExamMethod;
	}

	public RExamType getRExamType() {
		return this.rExamType;
	}

	public void setRExamType(RExamType rExamType) {
		this.rExamType = rExamType;
	}

	public RMedicalOrgMain getRMedicalOrgMain() {
		return this.rMedicalOrgMain;
	}

	public void setRMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		this.rMedicalOrgMain = rMedicalOrgMain;
	}

	public RResultType getRResultType() {
		return this.rResultType;
	}

	public void setRResultType(RResultType rResultType) {
		this.rResultType = rResultType;
	}
	
	public RVerification getRVerification() {
		return this.rVerification;
	}

	public void setRVerification(RVerification rVerification) {
		this.rVerification = rVerification;
	}
}