package ru.miacn.persistence.reference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_ter")
public class RMedicalOrgTer {
	@EmbeddedId
	private RMedicalOrgTerPK id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "reg_id", insertable = false, updatable = false)
	private RMedicalOrgRegion rMedicalOrgRegion;

	public RMedicalOrgTerPK getId() {
		return this.id;
	}

	public void setId(RMedicalOrgTerPK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RMedicalOrgRegion getRMedicalOrgRegion() {
		return this.rMedicalOrgRegion;
	}

	public void setRMedicalOrgRegion(RMedicalOrgRegion rMedicalOrgRegion) {
		this.rMedicalOrgRegion = rMedicalOrgRegion;
	}
}
