package ru.miacn.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlanOrm implements Serializable {
	private static final long serialVersionUID = -2580774512165222054L;

	@Id
	private Integer id;

	@Column(name = "group_id")
	private Integer groupId;

	@Column(name = "group_name")
	private String groupName;

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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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