package springapp.command;

import java.sql.Date;
import java.sql.Time;

import springapp.domain.Appointment;

/**
 * This command class is used to pass information back and force between the appointment and the server
 * 
 */
public class AppointmentCommand {
	
	private Integer id;
	private String notes;
	private String title;
	private Time time;
	private Date date;
	private int petId;
	private int clientId;

	/**
	 * Creates a command object that has the initial values the same as the Appointment passed in
	 * @param Appointment the Appointment to initialize the command object with
	 */
	public AppointmentCommand(Appointment appointment) {
		if(appointment != null) {
			id = appointment.getId();
			this.title= appointment.getTitle();
			this.notes = appointment.getNotes();
			this.time = appointment.getTime();
			this.date = appointment.getDate();
			this.petId= appointment.getPetId();
			this.clientId=appointment.getClientId();
		}
	}

	/**
	 * Set the id of the appointment
	 * @param id the appointment id, null if this appointment is not persisted to the database (ie a new appointment)
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Set the name of the appointment
	 * @param name the name of the appointment
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set the name of the appointment
	 * @param name the name of the appointment
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Set the name of the appointment
	 * @param name the name of the appointment
	 */
	public void setpetId(int petId) {
		this.petId = petId;
	}
	

	/**
	 * Set the name of the appointment
	 * @param name the name of the appointment
	 */
	public void setclientId(int clientId) {
		this.clientId = clientId;
	}


	/**
	 * Set the time of the appointment
	 * @param time the appointment address
	 */
	public void setTime(Time time) {
		this.time = time;
	}

	/**
	 * Set the date of the appointment
	 * @param date of appointment
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the appointment id, returns null if this appointment is new and not persisted to the database yet
	 */
	
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
