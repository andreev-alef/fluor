package ru.miacn.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;

@Entity
public class Patient {
	private static final int nameFieldSize = 64;
	
	@Id
	private Integer id;
	@Column(name = "_ver_active")
	@NotNull
	private Boolean verActive;
	@Column(name = "_ver_creation_date")
	@NotNull
	private Date verCreationDate;
	@Column(name = "_ver_parent_id")
	@NotNull
	private Integer verParentId;
	@Column(name = "citizen_id")
	@NotNull
	private Integer citizenId;
	@Column(name = "dat_birth")
	@NotNull
	private Date datBirth;
	@Column(name = "dat_death")
	private Date datDeath;
	@Column(name = "decr_group_id")
	private Integer decrGroupId;
	@Column(name = "father_name")
	@Size(max = nameFieldSize)
	private String fatherName;
	@Column(name = "first_name")
	@NotNull
	@Size(max = nameFieldSize)
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	@Size(max = nameFieldSize)
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "med_group_id")
	private Integer medGroupId;
	@Column(name = "sex_id")
	@NotNull
	private Integer sexId;
	@Column(name = "soc_group_id")
	private Integer socGroupId;
	private String tel;
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "med_city_id", referencedColumnName = "ter_id"),
			@JoinColumn(name = "med_lpu_id", referencedColumnName = "lpu_id"),
			@JoinColumn(name = "med_pol_id", referencedColumnName = "pol_id"),
			@JoinColumn(name = "med_reg_id", referencedColumnName = "reg_id") })
	private RMedicalOrgPoliclinic rMedicalOrgPoliclinic;

	public static int getNameFieldSize() {
		return nameFieldSize;
	}

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

	public Integer getVerParentId() {
		return this.verParentId;
	}

	public void setVerParentId(Integer verParentId) {
		this.verParentId = verParentId;
	}

	public Integer getCitizenId() {
		return this.citizenId;
	}

	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
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

	public Integer getDecrGroupId() {
		return this.decrGroupId;
	}

	public void setDecrGroupId(Integer decrGroupId) {
		this.decrGroupId = decrGroupId;
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

	public Integer getMedGroupId() {
		return this.medGroupId;
	}

	public void setMedGroupId(Integer medGroupId) {
		this.medGroupId = medGroupId;
	}

	public Integer getSexId() {
		return this.sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	public Integer getSocGroupId() {
		return this.socGroupId;
	}

	public void setSocGroupId(Integer socGroupId) {
		this.socGroupId = socGroupId;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public RMedicalOrgPoliclinic getRMedicalOrgPoliclinic() {
		return this.rMedicalOrgPoliclinic;
	}

	public void setRMedicalOrgPoliclinic(RMedicalOrgPoliclinic rMedicalOrgPoliclinic) {
		this.rMedicalOrgPoliclinic = rMedicalOrgPoliclinic;
	}
}
