package ru.miacn.persistence.model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import ru.miacn.persistence.reference.RUserRole;
import ru.miacn.utils.Md5;

@Entity
@Table(name="users")
public class User {
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

}
