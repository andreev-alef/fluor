package ru.miacn.persistence.reference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_policlinic")
public class RMedicalOrgPoliclinic {
	@EmbeddedId
	private RMedicalOrgPoliclinicPK id;
	private Boolean attach;
	private String name;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "lpu_id", referencedColumnName = "lpu_id", insertable = false, updatable = false),
			@JoinColumn(name = "reg_id", referencedColumnName = "reg_id", insertable = false, updatable = false),
			@JoinColumn(name = "ter_id", referencedColumnName = "ter_id", insertable = false, updatable = false) })
	private RMedicalOrgMain rMedicalOrgMain;

	public RMedicalOrgPoliclinicPK getId() {
		return this.id;
	}

	public void setId(RMedicalOrgPoliclinicPK id) {
		this.id = id;
	}

	public Boolean getAttach() {
		return this.attach;
	}

	public void setAttach(Boolean attach) {
		this.attach = attach;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RMedicalOrgMain getRMedicalOrgMain() {
		return this.rMedicalOrgMain;
	}

	public void setRMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		this.rMedicalOrgMain = rMedicalOrgMain;
	}
}
