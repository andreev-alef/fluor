package ru.miacn;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import ru.miacn.persistence.model.User;
import ru.miacn.utils.Md5;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 271781091507093732L;

	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	private List<User> usersList;
	private String login;
	private String password;

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

	public void auth() {
		setUsersList(em.createQuery("SELECT u FROM " + User.class.getName() + " u WHERE u.login= '" + getLogin() + "'", User.class).getResultList());
		if (getUsersList().size() > 0 && getUsersList().get(0).getPassword().equals(getPassword())) {
			
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка входа, введены неверные данные", "Введены неверные данные");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		usersList.clear();
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> list) {
		this.usersList = list;
	}

	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		
		session.invalidate();
	}
}
