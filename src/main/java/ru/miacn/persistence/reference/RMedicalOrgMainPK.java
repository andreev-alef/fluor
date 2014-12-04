package ru.miacn.persistence.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RMedicalOrgMainPK implements Serializable {
	private static final long serialVersionUID = 4318547409728505705L;

	@Column(name = "reg_id")
	private Integer regId;
	@Column(name = "ter_id")
	private Integer terId;
	@Column(name = "lpu_id")
	private Integer lpuId;

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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RMedicalOrgMainPK)) {
			return false;
		}
		RMedicalOrgMainPK castOther = (RMedicalOrgMainPK) other;
		return this.regId.equals(castOther.regId)
				&& this.terId.equals(castOther.terId)
				&& this.lpuId.equals(castOther.lpuId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.regId.hashCode();
		hash = hash * prime + this.terId.hashCode();
		hash = hash * prime + this.lpuId.hashCode();

		return hash;
	}
}
