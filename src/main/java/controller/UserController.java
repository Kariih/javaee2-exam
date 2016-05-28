package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javaBeans.CountryEjb;
import javaBeans.UserRepo;
import model.User;
import utils.AuthStatus;
import utils.AuthStatus.Status;

import org.apache.log4j.Logger;

@ManagedBean
public class UserController {

	@Inject
	private CountryEjb countries;
	@Inject
	UserRepo uRepo;
	public User user;
	private String confirmPw;
	private String username;
	private String password;

	public List<String> countryList;
	static final Logger logger = Logger.getLogger(UserController.class);

	@PostConstruct
	private void init() {
		countryList = countries.getCountries();
	}

	public UserController() {
		setUser(new User());
	}

	public void addUser() {
		if (this.user.getPassword().equals(confirmPw) && !(this.user.getPassword().equals(""))) {
			if (uRepo.findOneUser(this.user.getUsername()) == null) {
					uRepo.add(this.user);
					AuthStatus.setStatus(Status.AUTHENTICATED);
					AuthStatus.setUser(this.user);
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
	}

	public int checkStatus() {
		return AuthStatus.getStatus().equals(Status.AUTHENTICATED) ? 1 : 0;
	}

	public void testlog() {
		logger.error("testlogg");
	}

	public void logout() {
		AuthStatus.setStatus(Status.GUEST);
		AuthStatus.setUser(new User());
		redirectToHome("loggedout.xhtml");
	}

	public void confirmPassword() {
		User userFromDb = uRepo.findOneUser(username);
		if (userFromDb != null) {
			if (this.getPassword().equals(userFromDb.getPassword())) {
				AuthStatus.setStatus(Status.AUTHENTICATED);
				AuthStatus.setUser(userFromDb);
				redirectToHome("index.html");
			}
		}
	}

	public String getWelcomeText() {
		return "Hi, " + AuthStatus.getUser().getUsername();
	}
	
	public String getCountryText() {
		return "Only " + AuthStatus.getUser().getCountry();
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

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

	}

}
