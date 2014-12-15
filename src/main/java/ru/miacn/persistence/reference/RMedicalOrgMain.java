package ru.miacn.persistence.reference;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_main")
public class RMedicalOrgMain {
	@EmbeddedId
	private RMedicalOrgMainPK id;
	private String addr;
	private String name;
	private Boolean oms;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "reg_id", referencedColumnName = "reg_id", insertable = false, updatable = false),
			@JoinColumn(name = "ter_id", referencedColumnName = "ter_id", insertable = false, updatable = false) })
	private RMedicalOrgTer rMedicalOrgTer;
	@OneToMany(mappedBy = "rMedicalOrgMain")
	private List<RMedicalOrgPoliclinic> rMedicalOrgPoliclinics;

	public RMedicalOrgMain() {
		id = new RMedicalOrgMainPK();
		rMedicalOrgTer = new RMedicalOrgTer();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RMedicalOrgMain) {
			RMedicalOrgMainPK pk = ((RMedicalOrgMain) obj).id;
			
			return (pk.getRegId() == id.getRegId())
					&& (pk.getTerId() == id.getTerId())
					&& (pk.getLpuId() == id.getLpuId());
		}
		return false;
	}
	
	public RMedicalOrgMainPK getId() {
		return this.id;
	}

	public void setId(RMedicalOrgMainPK id) {
		this.id = id;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOms() {
		return this.oms;
	}

	public void setOms(Boolean oms) {
		this.oms = oms;
	}

	public RMedicalOrgTer getRMedicalOrgTer() {
		return this.rMedicalOrgTer;
	}

	public void setRMedicalOrgTer(RMedicalOrgTer rMedicalOrgTer) {
		this.rMedicalOrgTer = rMedicalOrgTer;
	}

	public List<RMedicalOrgPoliclinic> getRMedicalOrgPoliclinics() {
		return this.rMedicalOrgPoliclinics;
	}

	public void setRMedicalOrgPoliclinics(
			List<RMedicalOrgPoliclinic> rMedicalOrgPoliclinics) {
		this.rMedicalOrgPoliclinics = rMedicalOrgPoliclinics;
	}

	public RMedicalOrgPoliclinic addRMedicalOrgPoliclinic(
			RMedicalOrgPoliclinic rMedicalOrgPoliclinic) {
		getRMedicalOrgPoliclinics().add(rMedicalOrgPoliclinic);
		rMedicalOrgPoliclinic.setRMedicalOrgMain(this);

		return rMedicalOrgPoliclinic;
	}

	public RMedicalOrgPoliclinic removeRMedicalOrgPoliclinic(
			RMedicalOrgPoliclinic rMedicalOrgPoliclinic) {
		getRMedicalOrgPoliclinics().remove(rMedicalOrgPoliclinic);
		rMedicalOrgPoliclinic.setRMedicalOrgMain(null);

		return rMedicalOrgPoliclinic;
	}
}
