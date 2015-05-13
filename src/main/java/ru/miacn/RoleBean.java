package ru.miacn;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class RoleBean implements Serializable {

	private static final long serialVersionUID = 2056742014799493719L;
	
	@Inject
	LoginBean login;
	
	private int getUserRole() {
		int role = login.getAuthedUser().getUserRole().getId();
		
		return role;
	}
	
	public boolean editPermission() {
		if (getUserRole() > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addResultPermission() {
		if (getUserRole() > 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean VerifyAndReportPermission() {
		if (getUserRole() > 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean planPermission() {
		if (getUserRole() > 4) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean uploadFilePermission() {
		if (getUserRole() > 5) {
			return true;
		} else {
			return false;
		}
	}
}
