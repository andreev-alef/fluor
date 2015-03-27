package ru.miacn;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class IdleBean implements Serializable {

	private static final long serialVersionUID = 307631475313423458L;
	
	@Inject
	LoginBean login;
	
	public void onIdle() throws IOException {
		login.logout();
	}
	
	public Integer getSessionTimeout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Integer x = session.getMaxInactiveInterval() * 1000 - 125000;
		return x;
	}
}
