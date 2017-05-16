import java.util.Scanner;

import agencymanagement.services.AgencyServiceImpl;
import agencymanagement.services.AgencyServiceImplService;

public class Main {
	
	public static void main(String [] args){
		
		AgencyServiceImpl webservice = new AgencyServiceImplService().getAgencyServiceImplPort();
		
		webservice.hashCode();
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Insert name: ");
		String clientName = scanner.nextLine();
		String propertyName = "";
		


		while(true) {
			System.out.println("You have the following options: getManagedProperties");
			System.out.println("Press 1 - to make a bid in an auction");//makeABid
			System.out.println("Press 2 - to get the auction date of a specific Property");//getAuctionDate
			System.out.println("Press 3 - to verify whether an auction is going on"); //isAuctionActive
			System.out.println("Press 4 - to monitor an active auction");//monitorAuction
			System.out.println("Press 5 - to display all managed Properties");//getManagedProperties
			int choice = scanner.nextInt();
			
			scanner.nextLine();
		            
			System.out.println("-------------------------------------------\n");
			
			switch (choice) {
	            case 1: 
	            	System.out.println("Please insert the name of the property");
	            	propertyName = scanner.nextLine();
	            	System.out.println("Please insert the amount you wish to bid");
	            	System.out.println(webservice.makeABid(propertyName, scanner.nextInt(), clientName));
	            	break;
	            case 2:  
	            	System.out.println("Please insert the name of the property");
	            	propertyName = scanner.nextLine();
	            	System.out.println(webservice.getAuctionDate(propertyName));
            		break;
	            case 3:  
	            	System.out.println("Please insert the name of the property");
	            	propertyName = scanner.nextLine();
	            	if(webservice.isAuctionActive(propertyName))
	            		System.out.println("Auction is going on");
	            	else
	            		System.out.println("Auction is not going on");
            		break;
	            case 4:  
	            	System.out.println("Please insert the name of the property");
	            	propertyName = scanner.nextLine();
	            	webservice.monitorAuction(propertyName);
            		break;
	            default: 
	            	System.out.println(webservice.getManagedProperties());
            		break;
	            	
	            	
            	
			}
			
			System.out.println("-------------------------------------------\n");
        }
		
		//scanner.close();
	}
}
