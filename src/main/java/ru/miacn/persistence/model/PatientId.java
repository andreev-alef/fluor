package ru.miacn.persistence.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "patient_id")
public class PatientId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
