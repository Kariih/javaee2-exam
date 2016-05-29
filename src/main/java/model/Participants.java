package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Participants {
	
	@Id
	private int id;
//	@ManyToOne(targetEntity = Event.class)
	private int eventId;
	private String participantUsername;
	
	public Participants() {
		super();
	}

	public Participants(int id, int eventId, String userNick) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.participantUsername = userNick;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getUserNick() {
		return participantUsername;
	}

	public void setUserNick(String userNick) {
		this.participantUsername = userNick;
	}
	
	
}
