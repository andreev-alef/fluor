package ru.miacn.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import ru.miacn.persistence.model.Address;

@Entity
public class PatientOrm {
	@Id
	private Integer id;
	@Column(name = "_ver_active")
	private Boolean verActive;
	@Column(name = "_ver_creation_date")
	private Date verCreationDate;
	@NotNull
	private Integer patientId;
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
	private String gender;
	@Embedded
	private Address address;
	@Temporal(TemporalType.DATE)
	private Date lastExam;
	private String result;
	private String verification;
	private Integer socGroup;
	private Integer medGroup;
	private Integer decrGroup;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getLastExam() {
		return lastExam;
	}

	public void setLastExam(Date lastExam) {
		this.lastExam = lastExam;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getSocGroup() {
		return socGroup;
	}

	public void setSocGroup(Integer socGroup) {
		this.socGroup = socGroup;
	}

	public Integer getMedGroup() {
		return medGroup;
	}

	public void setMedGroup(Integer medGroup) {
		this.medGroup = medGroup;
	}

	public Integer getDecrGroup() {
		return decrGroup;
	}

	public void setDecrGroup(Integer decrGroup) {
		this.decrGroup = decrGroup;
	}
}
