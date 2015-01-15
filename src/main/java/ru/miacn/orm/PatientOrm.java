package ru.miacn.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ru.miacn.persistence.model.Address;
import ru.miacn.persistence.model.Examination;
import ru.miacn.persistence.model.PatientId;
import ru.miacn.persistence.reference.RGender;

@Entity
public class PatientOrm {
	@Id
	private Integer id;
	@Column(name = "_ver_active")
	private Boolean verActive;
	@Column(name = "_ver_creation_date")
	private Date verCreationDate;
	@ManyToOne
	@JoinColumn(name = "_ver_parent_id")
	@NotNull
	private PatientId patientId;
	@Column(name = "dat_birth")
	@Temporal(TemporalType.DATE)
	private Date datBirth;
	@Column(name = "dat_death")
	@Temporal(TemporalType.DATE)
	private Date datDeath;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@OneToOne
	@JoinColumn(name = "sex_id")
	private RGender gender;
	@Embedded
	private Address address;
	@OneToOne
	@JoinColumn(name = "last_exam_id")
	private Examination exam;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getVerActive() {
		return this.verActive;
	}

	public void setVerActive(Boolean verActive) {
		this.verActive = verActive;
	}

	public Date getVerCreationDate() {
		return this.verCreationDate;
	}

	public void setVerCreationDate(Date verCreationDate) {
		this.verCreationDate = verCreationDate;
	}

	public PatientId getPatientId() {
		return this.patientId;
	}

	public void setPatientId(PatientId patientId) {
		this.patientId = patientId;
	}

	public Date getDatBirth() {
		return this.datBirth;
	}

	public void setDatBirth(Date datBirth) {
		this.datBirth = datBirth;
	}

	public Date getDatDeath() {
		return this.datDeath;
	}

	public void setDatDeath(Date datDeath) {
		this.datDeath = datDeath;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RGender getGender() {
		return gender;
	}

	public void setGender(RGender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Examination getExam() {
		return exam;
	}

	public void setExam(Examination exam) {
		this.exam = exam;
	}
}
