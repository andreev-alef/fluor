package ru.miacn.persistence.model;

import java.util.Date;

import javax.persistence.Column;
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

@Entity
public class Examination {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dat;

	@Column(name="follow_up")
	private Boolean followUp;

	@ManyToOne
	private Patient patient;

	@OneToOne
	@JoinColumn(name="method_id")
	private RExamMethod rExamMethod;

	@OneToOne
	@JoinColumn(name="type_id")
	private RExamType rExamType;

	@OneToOne
	@JoinColumns({
		@JoinColumn(name="med_city_id", referencedColumnName="ter_id", insertable = false, updatable = false),
		@JoinColumn(name="med_lpu_id", referencedColumnName="lpu_id", insertable = false, updatable = false),
		@JoinColumn(name="med_reg_id", referencedColumnName="reg_id", insertable = false, updatable = false)
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

	public Boolean getFollowUp() {
		return this.followUp;
	}

	public void setFollowUp(Boolean followUp) {
		this.followUp = followUp;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
}