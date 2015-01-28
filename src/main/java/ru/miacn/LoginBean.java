package ru.miacn;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ru.miacn.persistence.model.User;
import ru.miacn.utils.Md5;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 271781091507093732L;

	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	private List<User> usersList;
	private String login;
	private String password;
	private User authedUser;
	private String originalURL;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		originalURL = (String) externalContext.getRequestMap().get(
				RequestDispatcher.FORWARD_REQUEST_URI);

		if (originalURL == null) {
			originalURL = externalContext.getRequestContextPath();
		} else {
			String originalQuery = (String) externalContext.getRequestMap()
					.get(RequestDispatcher.FORWARD_QUERY_STRING);

			if (originalQuery != null) {
				originalURL += "?" + originalQuery;
			}
		}
	}

	public void login() throws IOException, NoSuchAlgorithmException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {	
			request.login(login, password);
			searchUser();
			externalContext.redirect(originalURL);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Ошибка входа, введены неверные данные",
					"Введены неверные данные"));
			logout();
		}
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath()
				+ "/login.xhtml");
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void searchUser() throws NoSuchAlgorithmException {
		setUsersList(em.createQuery(
				"SELECT u FROM " + User.class.getName() + " u WHERE u.login= '"
						+ getLogin() + "'", User.class).getResultList());
		if (getUsersList().size() > 0
				&& getUsersList().get(0).getPassword().equals(Md5.md5Custom(password))) {
			authedUser = getUsersList().get(0);
		}
		usersList.clear();
	}

	private List<User> getUsersList() {
		return usersList;
	}

	private void setUsersList(List<User> list) {
		this.usersList = list;
	}

	public User getAuthedUser() {
		return authedUser;
	}
}
