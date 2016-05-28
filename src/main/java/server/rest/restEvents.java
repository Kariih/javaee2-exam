package server.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import javaBeans.CountryEjb;
import javaBeans.EventRepo;
import model.Event;

@Path("events")
public class restEvents {
	
	@Inject
	private EventRepo eRepo;
	@Inject
	CountryEjb contries;
	
	List<Event> events;
	GenericEntity<List<Event>> list;
	
	@GET
	@Path("all")
	@Produces(MediaType.TEXT_XML)
	public Response allEvents(){
		events = eRepo.findAll();
		list = new GenericEntity<List<Event>>(events) {};
		return Response.ok(list).build();		
	}	
	@GET
	@Path("all")
	@Produces(MediaType.TEXT_XML)
	public Response getEventsByCountry(@QueryParam("country") String country){
		//if(loadCountries().contains(country)){
			events = eRepo.findByCountry(country);	
			list = new GenericEntity<List<Event>>(events) {};
			return Response.ok(list).build();
	//	}
	//	return Response.status(404).entity("Invalid country name").build();
	}
	
	private List<String> loadCountries(){
		return contries.getCountries();
	}

}
