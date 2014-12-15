package ru.miacn.persistence.reference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "r_decr_group")
public class RDecrGroup {
	@Id
	private Integer id;
	private String name;

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof RDecrGroup) && (((RDecrGroup) obj).id == this.id);
	}
	
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
}
