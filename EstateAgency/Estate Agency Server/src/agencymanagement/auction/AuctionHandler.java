package agencymanagement.auction;

import java.util.List;

import agencymanagement.domain.Properties;
import agencymanagement.domain.PropertyUtility;

public class AuctionHandler extends Thread {
	
	Properties properties;
	Auctions auctions;
	
	boolean running;
	
	
	public AuctionHandler(Properties properties, Auctions auctions){
		
		this.properties = properties;
		this.auctions = auctions;
		
		running = true;
		
		
	}
	
	public void run(){
		
		while(running){
			System.out.println("Handler ");
			
			List<PropertyUtility> propertieswithAuctionsOn;
			propertieswithAuctionsOn = properties.getPropertiesWithAuctionOn();
			
			auctionInitialiser(propertieswithAuctionsOn);
			
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void auctionInitialiser(List<PropertyUtility> prop){
		
		for(PropertyUtility property: prop){
			
			if(!auctions.contains(property.getName())){
				auctions.startNewAuction(property.getProperty());
				//auctions.a
				AuctionTimer timer = new AuctionTimer(properties, auctions.getAuction(property.getName()), property.getAuctionDateAdv());
				timer.start();
			}
		}
		
	}
	
	public void terminate() {
		
		running = false;
		
	}


}
