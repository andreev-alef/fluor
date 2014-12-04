package ru.miacn.persistence.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RMedicalOrgTerPK implements Serializable {
	private static final long serialVersionUID = -8334882766482579109L;

	@Column(name = "reg_id")
	private Integer regId;
	@Column(name = "ter_id")
	private Integer terId;

	public Integer getRegId() {
		return this.regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public Integer getTerId() {
		return this.terId;
	}

	public void setTerId(Integer terId) {
		this.terId = terId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RMedicalOrgTerPK)) {
			return false;
		}
		RMedicalOrgTerPK castOther = (RMedicalOrgTerPK) other;
		return this.regId.equals(castOther.regId)
				&& this.terId.equals(castOther.terId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.regId.hashCode();
		hash = hash * prime + this.terId.hashCode();

		return hash;
	}
}
