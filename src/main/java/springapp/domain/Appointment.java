package springapp.domain;

import java.sql.Date;
import java.sql.Time;


public class Appointment {	
	private final Integer id;
	private final String title;
	private final Date date;
	private final Time time;
	private final String notes;
	private final Integer petId;
	private final Integer clientId;
	
	public Appointment(Integer id, String title, Date date, Time time, String notes, Integer petId, Integer clientId) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.time = time;
		this.notes = notes;
		this.petId = petId;
		this.clientId = clientId;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public String getNotes() {
		return notes;
	}

	public Integer getPetId() {
		return petId;
	}

	public Integer getClientId() {
		return clientId;
	}
	
}
