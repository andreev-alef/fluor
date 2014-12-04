package ru.miacn.persistence;

import java.io.Serializable;

public class RefMedicalOrgTerPk implements Serializable {
	private static final long serialVersionUID = 2762711127967132880L;
	private int reg_id;
	private int ter_id;
	
	public int getReg_id() {
		return reg_id;
	}

	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}

	public int getTer_id() {
		return ter_id;
	}

	public void setTer_id(int ter_id) {
		this.ter_id = ter_id;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
