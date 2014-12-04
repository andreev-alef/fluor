package ru.miacn.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;

@Entity
public class Examination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Date dat;
	@Column(name = "follow_up")
	@NotNull
	private Boolean followUp;
	@Column(name = "method_id")
	@NotNull
	private Integer methodId;
	@Column(name = "result_id")
	private Integer resultId;
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "med_city_id", referencedColumnName = "ter_id"),
			@JoinColumn(name = "med_lpu_id", referencedColumnName = "lpu_id"),
			@JoinColumn(name = "med_pol_id", referencedColumnName = "pol_id"),
			@JoinColumn(name = "med_reg_id", referencedColumnName = "reg_id") })
	@NotNull
	private RMedicalOrgPoliclinic rMedicalOrgPoliclinic;

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

	public Integer getMethodId() {
		return this.methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public Integer getResultId() {
		return this.resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public RMedicalOrgPoliclinic getRMedicalOrgPoliclinic() {
		return this.rMedicalOrgPoliclinic;
	}

	public void setRMedicalOrgPoliclinic(RMedicalOrgPoliclinic RMedicalOrgPoliclinic) {
		this.rMedicalOrgPoliclinic = RMedicalOrgPoliclinic;
	}
}
