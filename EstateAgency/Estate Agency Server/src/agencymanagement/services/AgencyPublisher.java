package agencymanagement.services;

import javax.xml.ws.Endpoint;



public class AgencyPublisher {

	public static void main(String [] args){
		int port = 8080;
		String url = "http://localhost:" + port + "/EstateAgency";
		
		
		Endpoint.publish(url, new AgencyServiceImpl());
		//Endpoint.publish("http://127.0.0.1:8080/", new AgencyServiceImpl());
		
	}
}
