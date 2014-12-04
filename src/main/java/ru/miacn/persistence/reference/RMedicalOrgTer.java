package ru.miacn.persistence.reference;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_ter")
public class RMedicalOrgTer {
	@EmbeddedId
	private RMedicalOrgTerPK id;
	private String name;
	@OneToMany(mappedBy = "rMedicalOrgTer")
	private List<RMedicalOrgMain> rMedicalOrgMains;

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

	public List<RMedicalOrgMain> getRMedicalOrgMains() {
		return this.rMedicalOrgMains;
	}

	public void setRMedicalOrgMains(List<RMedicalOrgMain> rMedicalOrgMains) {
		this.rMedicalOrgMains = rMedicalOrgMains;
	}

	public RMedicalOrgMain addRMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		getRMedicalOrgMains().add(rMedicalOrgMain);
		rMedicalOrgMain.setRMedicalOrgTer(this);

		return rMedicalOrgMain;
	}

	public RMedicalOrgMain removeRMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		getRMedicalOrgMains().remove(rMedicalOrgMain);
		rMedicalOrgMain.setRMedicalOrgTer(null);

		return rMedicalOrgMain;
	}
}
