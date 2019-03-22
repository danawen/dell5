package springapp.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Appointment {	
	private final Integer id;
	private final String title;
	private final LocalDate date;
	private final LocalTime time;
	private final String notes;
	private final Integer petId;
	private final Integer clientId;
	
	public Appointment(Integer id, String title, LocalDate date, LocalTime time, String notes, Integer petId, Integer clientId) {
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

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
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
