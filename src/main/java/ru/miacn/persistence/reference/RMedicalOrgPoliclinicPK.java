package ru.miacn.persistence.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RMedicalOrgPoliclinicPK implements Serializable {
	private static final long serialVersionUID = -1248442919019430945L;

	@Column(name = "reg_id")
	private Integer regId;
	@Column(name = "ter_id")
	private Integer terId;
	@Column(name = "lpu_id")
	private Integer lpuId;
	@Column(name = "pol_id")
	private Integer polId;

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

	public Integer getLpuId() {
		return this.lpuId;
	}

	public void setLpuId(Integer lpuId) {
		this.lpuId = lpuId;
	}

	public Integer getPolId() {
		return this.polId;
	}

	public void setPolId(Integer polId) {
		this.polId = polId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RMedicalOrgPoliclinicPK)) {
			return false;
		}
		RMedicalOrgPoliclinicPK castOther = (RMedicalOrgPoliclinicPK) other;
		return this.regId.equals(castOther.regId)
				&& this.terId.equals(castOther.terId)
				&& this.lpuId.equals(castOther.lpuId)
				&& this.polId.equals(castOther.polId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.regId.hashCode();
		hash = hash * prime + this.terId.hashCode();
		hash = hash * prime + this.lpuId.hashCode();
		hash = hash * prime + this.polId.hashCode();

		return hash;
	}
}
