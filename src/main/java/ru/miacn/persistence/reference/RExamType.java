package ru.miacn.persistence.reference;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "r_exam_type")
public class RExamType {
	@Id
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "rExamType")
	private List<RExamMethod> rExamMethods;

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

	public List<RExamMethod> getRExamMethods() {
		return this.rExamMethods;
	}

	public void setRExamMethods(List<RExamMethod> rExamMethods) {
		this.rExamMethods = rExamMethods;
	}

	public RExamMethod addRExamMethod(RExamMethod rExamMethod) {
		getRExamMethods().add(rExamMethod);
		rExamMethod.setRExamType(this);

		return rExamMethod;
	}

	public RExamMethod removeRExamMethod(RExamMethod RExamMethod) {
		getRExamMethods().remove(RExamMethod);
		RExamMethod.setRExamType(null);

		return RExamMethod;
	}
}
