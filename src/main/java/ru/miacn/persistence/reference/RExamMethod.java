package ru.miacn.persistence.reference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "r_exam_methods")
public class RExamMethod {
	@Id
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private RExamType rExamType;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RExamType getRExamType() {
		return this.rExamType;
	}

	public void setRExamType(RExamType rExamType) {
		this.rExamType = rExamType;
	}
}
