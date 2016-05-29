package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import javaBeans.CountryEjb;
import javaBeans.EventRepo;
import model.Event;
import utils.AuthStatus;

@ManagedBean
public class EventController {

	@Inject
	private CountryEjb countries;
	@Inject
	EventRepo eRepo;

	public Event event;
	public List<String> countryList;
	public List<Event> allEvents = new ArrayList<Event>();
	public List<Event> events;
	private boolean boxChecked = true;

	@PostConstruct
	private void init() {
		countryList = countries.getCountries();
		allEvents = eRepo.findAll();
	}

	public EventController() {
		setEvent(new Event());
	}

	public void getEventsBasedOnCheckbox() {
		if(boxChecked){
			setEvents(eRepo.findByCountry(AuthStatus.getUser().getCountry()));
		}else if(!boxChecked){
			setEvents(allEvents);
		}			
		checkBox();
		RequestContext.getCurrentInstance().update("contentPanel");
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void addEvent() {
		eRepo.add(this.event);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkBox() {
		if(boxChecked){
			boxChecked = false;
		}else{
			boxChecked = true;
		}
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<Event> getEvents() {
		return allEvents;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int isEventsEmpty() {
		return allEvents.isEmpty() ? 0 : 1;
	}

	public boolean isBoxChecked() {
		return boxChecked;
	}

	public void setBoxChecked(boolean boxChecked) {
		this.boxChecked = boxChecked;
	}
}
