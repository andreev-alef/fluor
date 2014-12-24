package ru.miacn.persistence.model;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import ru.miacn.persistence.reference.RCitizen;
import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RGender;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RSocGroup;

@ManagedBean
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
	@OneToOne
	@JoinColumn(name = "citizen_id")
	@NotNull
	private RCitizen citizen;
	@Column(name = "dat_birth")
	@Temporal(TemporalType.DATE)
	@NotNull
	@Past
	private Date datBirth;
	@Column(name = "dat_death")
	@Temporal(TemporalType.DATE)
	private Date datDeath;
	@OneToOne
	@JoinColumn(name = "decr_group_id")
	private RDecrGroup decrGroup;
	@Column(name = "father_name")
	@Size(max = nameFieldSize)
	@FIOAnnotation
	private String fatherName;
	@Column(name = "first_name")
	@NotNull
	@Size(max = nameFieldSize)
	@FIOAnnotation
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	@Size(min=3,max = nameFieldSize)
	@FIOAnnotation
	private String lastName;
	@OneToOne
	@JoinColumn(name = "sex_id")
	@NotNull
	private RGender gender;
	@Embedded
	private Address address;
	@OneToOne
	@JoinColumn(name = "med_group_id")
	private RMedGroup medGroup;
	@OneToOne
	@JoinColumn(name = "soc_group_id")
	private RSocGroup socGroup;
	private String tel;
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "med_city_id", referencedColumnName = "ter_id"),
			@JoinColumn(name = "med_lpu_id", referencedColumnName = "lpu_id"),
			@JoinColumn(name = "med_pol_id", referencedColumnName = "pol_id"),
			@JoinColumn(name = "med_reg_id", referencedColumnName = "reg_id") })
	private RMedicalOrgPoliclinic rMedicalOrgPoliclinic;

	public Patient() {
		citizen =  new RCitizen();
		decrGroup = new RDecrGroup();
		gender = new RGender();
		address = new Address();
		medGroup = new RMedGroup();
		socGroup = new RSocGroup();
		rMedicalOrgPoliclinic = new RMedicalOrgPoliclinic();
	}
	
	public int getNameFieldSize() {
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

	public RCitizen getCitizen() {
		return citizen;
	}

	public void setCitizen(RCitizen citizen) {
		this.citizen = citizen;
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

	public RDecrGroup getDecrGroup() {
		return decrGroup;
	}

	public void setDecrGroup(RDecrGroup decrGroup) {
		this.decrGroup = decrGroup;
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

	public RMedGroup getMedGroup() {
		return medGroup;
	}

	public void setMedGroup(RMedGroup medGroup) {
		this.medGroup = medGroup;
	}

	public RSocGroup getSocGroup() {
		return socGroup;
	}

	public void setSocGroup(RSocGroup socGroup) {
		this.socGroup = socGroup;
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
