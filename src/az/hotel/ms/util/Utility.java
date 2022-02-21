package az.hotel.ms.util;
import java.util.List;
import az.hotel.ms.model.Client;
public class Utility {
	
	public static void getClientList(List<Client> clientList) {
		
		for(Client client: clientList) {
			
			System.out.println(client.getID() + " -- " + client.getName() + " -- "+
			client.getSurname() + " -- " + client.getPhone() + " -- " + client.getGender() + " -- "
			+ client.getNationality());
		}
	}

}
