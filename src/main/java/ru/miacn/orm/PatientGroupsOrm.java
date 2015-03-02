package ru.miacn.orm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientGroupsOrm {
	@Id
	private Integer id;
	private Integer socGroup;
	private Integer medGroup;
	private Integer decrGroup;
	private Date datDeath;
	public Integer getSocGroup() {
		return socGroup;
	}
	public void setSocGroup(Integer socGroup) {
		this.socGroup = socGroup;
	}
	public Integer getMedGroup() {
		return medGroup;
	}
	public void setMedGroup(Integer medGroup) {
		this.medGroup = medGroup;
	}
	public Integer getDecrGroup() {
		return decrGroup;
	}
	public void setDecrGroup(Integer decrGroup) {
		this.decrGroup = decrGroup;
	}
	public Date getDatDeath() {
		return datDeath;
	}
	public void setDatDeath(Date datDeath) {
		this.datDeath = datDeath;
	}

}
