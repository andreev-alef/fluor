package ru.miacn.persistence.model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RUserRole;
import ru.miacn.utils.Md5;

@Entity
@Table(name="users")
public class User {
	private static final int nameFieldSize = 64;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "login")
	@NotNull
	private String login;
	@Column(name = "password")
	@NotNull
	private String password;
	@ManyToOne
	@JoinColumn(name="role_id")
	private RUserRole userRole;
	@Column(name = "father_name")
	@Size(max = nameFieldSize)
	@FIOAnnotation
	private String fatherName;
	@Column(name = "first_name")
	@NotNull
	@Size(max = nameFieldSize)
	@FIOAnnotation
	private String firstName;
	@Column(name = "last_name")
	@NotNull
	@Size(min=3,max = nameFieldSize)
	@FIOAnnotation
	private String lastName;
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "med_city_id", referencedColumnName = "ter_id"),
			@JoinColumn(name = "med_lpu_id", referencedColumnName = "lpu_id"),
			@JoinColumn(name = "med_reg_id", referencedColumnName = "reg_id") })
	private RMedicalOrgMain rMedicalOrgMain;
	
	public int getNameFieldSize() {
		return nameFieldSize;
	}
	
	public User() {
		rMedicalOrgMain = new RMedicalOrgMain();
		userRole = new RUserRole();
	}
	
	public RUserRole getUserRole() {
		return userRole;
	}
	
	public void setUserRole(RUserRole userRole) {
		this.userRole = userRole;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws NoSuchAlgorithmException {
		this.password = Md5.md5Custom(password);
	}
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RMedicalOrgMain getrMedicalOrgMain() {
		return rMedicalOrgMain;
	}

	public void setrMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		this.rMedicalOrgMain = rMedicalOrgMain;
	}
}
