package server.rest;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.handler.MessageContext;

import javaBeans.CountryEjb;
import javaBeans.EventRepo;
import model.Event;

@Path("/events")
public class restEvents {

	@Inject
	private EventRepo eRepo;
	@Inject
	CountryEjb contries;

	List<Event> events;
	GenericEntity<List<Event>> list;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response allEvents(@DefaultValue("all") @QueryParam("country") String country) {

		if (country.equals("all")) {
			events = eRepo.findAll();
		} else {
			if (loadCountries().contains(country)) {
				events = eRepo.findByCountry(country);
			} else {
				return Response.serverError().entity("404 - Invalid country name").build();
			}
		}
		list = new GenericEntity<List<Event>>(events) {
		};
		return Response.ok(list).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response getEventsById(@DefaultValue("noId") @QueryParam("id") String id) {
		if (id.equals("noId")) {
			return Response.serverError().entity("400 - Add an id").build();
		} else {
			Event e = eRepo.findOneEvent(Integer.parseInt(id));
			if (e.getTitle().equals(null)) {
				return Response.serverError().entity("404 - No element with id: " + id).build();
			} else {
				return Response.ok(e).build();
			}
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response allEventsJson(@DefaultValue("all") @QueryParam("country") String country) {

		if (country.equals("all")) {
			events = eRepo.findAll();
		} else {
			if (loadCountries().contains(country)) {
				events = eRepo.findByCountry(country);
			} else {
				return Response.serverError().entity("404 - Invalid country name").build();
			}
		}
		list = new GenericEntity<List<Event>>(events) {
		};
		return Response.ok(list).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEventsByIdJson(@DefaultValue("noId") @QueryParam("id") String id) {
		if (id.equals("noId")) {
			return Response.serverError().entity("400 - Add an id").build();
		} else {
			Event e = eRepo.findOneEvent(Integer.parseInt(id));
			if (e.getTitle().equals(null)) {
				return Response.serverError().entity("404 - No element with id: " + id).build();
			} else {
				return Response.ok(e).build();
			}
		}
	}

	private List<String> loadCountries() {
		return contries.getCountries();
	}

}
