package controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import javaBeans.UserRepo;
import model.User;
import utils.AuthStatus;

@ManagedBean
public class LoginController {
	static final Logger logger = Logger.getLogger(LoginController.class);

	@Inject
	private UserRepo repo;

	private String username;
	private String password;

	public int checkStatus() {
		return AuthStatus.State.status.equals(AuthStatus.AUTHENTICATED) ? 1 : 0;
	}

	public void testlog() {
		logger.error("testlogg");
	}

	public void logout() {
		AuthStatus.State.status = AuthStatus.GUEST;
		AuthStatus.State.user = new User();
		redirectToHome("loggedout.xhtml");
	}

	public void confirmPassword() {
		User userFromDb = repo.findOneUser(username);
		if (userFromDb != null) {
			if (this.getPassword().equals(userFromDb.getPassword())) {
				AuthStatus.State.status = AuthStatus.AUTHENTICATED;
				AuthStatus.State.user = userFromDb;
				redirectToHome("home.xhtml");
			}
		}
	}

	public String getWelcomeText() {
		return "Hi, " + AuthStatus.State.user.getUsername();
	}

	private void redirectToHome(String path) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
