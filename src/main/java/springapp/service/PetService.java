package springapp.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.command.PetCommand;
import springapp.controller.AppointmentController;
import springapp.controller.PetController;
import springapp.dao.AppointmentDao;
import springapp.dao.PetDao;
import springapp.domain.Appointment;
import springapp.domain.Client;
import springapp.domain.Pet;


@Service
public class PetService {

	private Logger logger = LoggerFactory.getLogger(PetService.class);
	@Autowired 
	PetDao petDao;
	
	@Autowired 
	AppointmentDao aptDao;
	
	
	public List<Pet> getPets(){
		return petDao.list();
		
	}
	
	public List<Pet> getPetsforClient(int clientId){
		return petDao.listForClient(clientId);
		
	}

	public void deletePet(String id) {
		petDao.delete(Integer.parseInt(id));
	}

	public Pet getPet(String id) {
		return petDao.get(Integer.parseInt(id));
	}
	
	public Pet savePet(PetCommand command) {
		Pet newPet = new Pet(command.getId(), command.getName(), command.getGender(), command.isAltered(), command.getClientId());
		return petDao.save(newPet);
	}
	
	public List<Appointment> getAppointments(Integer petId) {
		
		logger.info("pet Id from petservice is "+ petId);
		return aptDao.listappointmetsforPet(petId);
	}
}
