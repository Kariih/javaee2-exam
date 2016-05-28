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

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

@ManagedBean
public class UserController {

	@Inject
	private CountryEjb countries;
	@Inject
	UserRepo uRepo;
	public User user;
	private String confirmPw;

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
			if (uRepo.getUser(this.user.getUsername()) == null) {
					uRepo.add(this.user);
					AuthStatus.State.status = AuthStatus.AUTHENTICATED;
					AuthStatus.State.user = this.user;
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
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
