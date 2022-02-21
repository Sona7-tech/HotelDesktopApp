package az.hotel.ms.dao;

import java.util.List;

import az.hotel.ms.model.Client;


public interface ClientDao {
	
	List <Client> getClientList() throws Exception;

	void addClient(Client client) throws Exception;
	
	Client getClientById(Long clientId) throws Exception;
	
	void updateClient(Client client) throws Exception;
	
	void deleteClient(Long clientId) throws Exception;
	
	List<Client> searchClientData(String keyword) throws Exception;
}
