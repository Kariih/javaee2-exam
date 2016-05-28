package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javaBeans.CountryEjb;
import javaBeans.EventRepo;
import model.Event;

@ManagedBean
public class EventController {
	
	@Inject
	private CountryEjb countries;
	@Inject
	EventRepo eRepo;
	
	public Event event;	
	public List<String> countryList;
	public List<Event> events;

	@PostConstruct
	private void init() {
		countryList = countries.getCountries();
		events = eRepo.findAll();	
	}
	
	public EventController(){
		setEvent(new Event());
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	public void addEvent(){
		eRepo.add(this.event);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public int isEventsEmpty() {
		return events.isEmpty() ? 0 : 1;
	}
}
