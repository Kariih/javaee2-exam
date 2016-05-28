package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class Event {
	
	//this has to be incremented
	@Id
	@XmlElement(name = "id")
	private int id;
	private String title;
	private String location;
	private String country;
	private String description;
	
	public Event(String title, String location, String country, String description) {
		super();
		this.title = title;
		this.location = location;
		this.country = country;
		this.description = description;
	}

	public Event() {
		super();

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descdription) {
		this.description = descdription;
	}
	
	
	
	

}
