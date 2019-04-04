package springapp.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.command.ClientCommand;
import springapp.dao.ClientDao;
import springapp.dao.PetDao;
import springapp.domain.Client;
import springapp.domain.Pet;


@Service
public class ClientService {

	@Autowired 
	ClientDao clientDao;

	@Autowired 
	PetDao petDao;

	public List<Client> getClients(){
		return clientDao.list();
	}
	
	public List<Client> searchClients(String name){
		return clientDao.get(name);
	}
	

	public Client getClient(String id) {
		return clientDao.get(Integer.parseInt(id));
	}
	
	public Client getClient(Integer id) {
		return clientDao.get(id);
	}

	
	public void deleteClient(String id) {
		clientDao.delete(Integer.parseInt(id));
	}

	
	public Client saveClient(ClientCommand toSave) {
		Client client = new Client(toSave.getId(), toSave.getName(), toSave.getPhoneNumber(), toSave.getAddress(), toSave.getEmail());	

		return clientDao.save(client);
	}
	
	public List<Pet> getPets(int clientId) {
	
		return petDao.listForClient(clientId);
	}
}
