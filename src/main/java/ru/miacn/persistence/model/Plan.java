package ru.miacn.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RSocGroup;

@Entity
public class Plan implements Serializable {
	private static final long serialVersionUID = -919744857013046269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "med_reg_id")
	private Integer medRegId;

	@Column(name = "med_city_id")
	private Integer medCityId;

	@Column(name = "med_lpu_id")
	private Integer medLpuId;

	private Integer year;

	@OneToOne
	@JoinColumn(name = "soc_group_id")
	private RSocGroup socGroup;

	@OneToOne
	@JoinColumn(name = "med_group_id")
	private RMedGroup medGroup;

	@OneToOne
	@JoinColumn(name = "decr_group_id")
	private RDecrGroup decrGroup;

	@Column(name = "cnt_beg_year")
	private Integer cntBegYear;

	@Column(name = "cnt_plan_pat")
	private Integer cntPlanPat;

	@Column(name = "cnt_plan_exam")
	private Integer cntPlanExam;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMedRegId() {
		return medRegId;
	}

	public void setMedRegId(Integer medRegId) {
		this.medRegId = medRegId;
	}

	public Integer getMedCityId() {
		return medCityId;
	}

	public void setMedCityId(Integer medCityId) {
		this.medCityId = medCityId;
	}

	public Integer getMedLpuId() {
		return medLpuId;
	}

	public void setMedLpuId(Integer medLpuId) {
		this.medLpuId = medLpuId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public RSocGroup getSocGroup() {
		return socGroup;
	}

	public void setSocGroup(RSocGroup socGroup) {
		this.socGroup = socGroup;
	}

	public RMedGroup getMedGroup() {
		return medGroup;
	}

	public void setMedGroup(RMedGroup medGroup) {
		this.medGroup = medGroup;
	}

	public RDecrGroup getDecrGroup() {
		return decrGroup;
	}

	public void setDecrGroup(RDecrGroup decrGroup) {
		this.decrGroup = decrGroup;
	}

	public Integer getCntBegYear() {
		return cntBegYear;
	}

	public void setCntBegYear(Integer cntBegYear) {
		this.cntBegYear = cntBegYear;
	}

	public Integer getCntPlanPat() {
		return cntPlanPat;
	}

	public void setCntPlanPat(Integer cntPlanPat) {
		this.cntPlanPat = cntPlanPat;
	}

	public Integer getCntPlanExam() {
		return cntPlanExam;
	}

	public void setCntPlanExam(Integer cntPlanExam) {
		this.cntPlanExam = cntPlanExam;
	}
}