package springapp.service;

import java.sql.Time;
import java.time.LocalDate;
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

	
	public void deleteAppointment(String id) {
		appointmentDao.delete(Integer.parseInt(id));
	}

	
	public Appointment saveAppointment(AppointmentCommand toSave) {
		Appointment appointment = new Appointment(toSave.getId(), toSave.getTitle(), toSave.getDate(), toSave.getTime(), toSave.getNotes(),toSave.getPetId(), toSave.getClientId());

		return appointmentDao.save(appointment);
	}	
	
}
