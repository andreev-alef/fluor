package ru.miacn.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "r_medical_org_main")
@IdClass(RefMedicalOrgMainPk.class)
public class RefMedicalOrgMain {
	@Id
	private int reg_id;
	@Id
	private int ter_id;
	@Id
	private int lpu_id;
	private String name;
	private String addr;
	
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
	public int getLpu_id() {
		return lpu_id;
	}
	public void setLpu_id(int lpu_id) {
		this.lpu_id = lpu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
