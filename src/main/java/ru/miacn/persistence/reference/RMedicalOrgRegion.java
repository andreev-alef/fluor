package ru.miacn.persistence.reference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_region")
public class RMedicalOrgRegion {
	@Id
	@Column(name = "reg_id")
	private Integer regId;
	@Column(name = "kod_okato")
	private String kodOkato;
	private String name;
	private String okrname;
	private Integer okrug;
	@OneToMany(mappedBy = "rMedicalOrgRegion")
	private List<RMedicalOrgTer> rMedicalOrgTers;

	public Integer getRegId() {
		return this.regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getKodOkato() {
		return this.kodOkato;
	}

	public void setKodOkato(String kodOkato) {
		this.kodOkato = kodOkato;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOkrname() {
		return this.okrname;
	}

	public void setOkrname(String okrname) {
		this.okrname = okrname;
	}

	public Integer getOkrug() {
		return this.okrug;
	}

	public void setOkrug(Integer okrug) {
		this.okrug = okrug;
	}

	public List<RMedicalOrgTer> getRMedicalOrgTers() {
		return this.rMedicalOrgTers;
	}

	public void setRMedicalOrgTers(List<RMedicalOrgTer> rMedicalOrgTers) {
		this.rMedicalOrgTers = rMedicalOrgTers;
	}

	public RMedicalOrgTer addRMedicalOrgTer(RMedicalOrgTer rMedicalOrgTer) {
		getRMedicalOrgTers().add(rMedicalOrgTer);
		rMedicalOrgTer.setRMedicalOrgRegion(this);

		return rMedicalOrgTer;
	}

	public RMedicalOrgTer removeRMedicalOrgTer(RMedicalOrgTer rMedicalOrgTer) {
		getRMedicalOrgTers().remove(rMedicalOrgTer);
		rMedicalOrgTer.setRMedicalOrgRegion(null);

		return rMedicalOrgTer;
	}

}