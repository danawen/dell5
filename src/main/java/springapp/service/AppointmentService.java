package springapp.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.command.AppointmentCommand;
import springapp.dao.AppointmentDao;
import springapp.domain.Appointment;


@Service
public class AppointmentService {

	@Autowired 
	AppointmentDao appointmentDao;


	public List<Appointment> getAppointments(){
		return appointmentDao.list();
		
	}
	
	public Map<String,Appointment> getTodayAppointments(){		
		Map<String,Appointment> todaysAppointments = new HashMap<String,Appointment>();			
		List<Appointment> list = appointmentDao.list();
		for(Appointment appointment : list ) {
			if(appointment.getDate().equals(LocalDate.now())) {
				todaysAppointments.put(Integer.toString(appointment.getTime().getHour()), appointment);
			}
		}
		return todaysAppointments;		
	}

	public Appointment getAppointment(String id) {
		return appointmentDao.get(Integer.parseInt(id));
	}
	
	public Appointment getAppointment(Integer id) {
		return appointmentDao.get(id);
	}
	
	public Map<String,Appointment> getAppointmentForGivenDate(String date) {
		Map<String,Appointment> appointments = new HashMap<String,Appointment>();			
		List<Appointment> list = appointmentDao.list();
		for(Appointment appointment : list ) {
			if(appointment.getDate().toString().equals(date)) {
				appointments.put(Integer.toString(appointment.getTime().getHour()), appointment);
			}
		}
		return appointments;		
	}

	
	public void deleteAppointment(String id) {
		appointmentDao.delete(Integer.parseInt(id));
	}

	
	public Appointment saveAppointment(AppointmentCommand toSave) {
		Appointment appointment = new Appointment(toSave.getId(), toSave.getTitle(), toSave.getDate(), toSave.getTime(), toSave.getNotes(),toSave.getPetId(), toSave.getClientId());

		return appointmentDao.save(appointment);
	}
	
	public String getTodayDate()
	{
		return LocalDate.now().toString();
	}
	
	public Map<String,String> getPastCurrentFutureDate(String date)
	{
		Map<String,String> dates = new HashMap<String,String>();
		LocalDate localDate = LocalDate.parse(date);
		dates.put("past", localDate.minusDays(1).toString());
		dates.put("current", localDate.toString());
		dates.put("future", localDate.plusDays(1).toString());
		return dates;
	}
	
	public String getFormattedDate(String date)
	{				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d yyyy");
		LocalDate localDate = LocalDate.parse(date);
		return localDate.format(formatter).toString();
	}
	
}
