package ru.miacn.persistence.reference;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="r_verification")
public class RVerification implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String name;

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof RVerification) && (((RVerification) obj).id == this.id);
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