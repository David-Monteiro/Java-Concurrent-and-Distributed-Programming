package agencymanagement.services;

import javax.jws.WebService;

import agencymanagement.auction.AuctionHandler;
import agencymanagement.auction.Auctions;
import agencymanagement.domain.Properties;

@WebService
//(endpointInterface = "agencymanagement.services.AgencyPublisher")
public class AgencyServiceImpl implements AgencyService {

	private Properties properties;
	private Auctions auctions;
	private AuctionHandler handler;

	
	public AgencyServiceImpl(){
		
		properties = new Properties();
		//TO DO generate some properties
		auctions = new Auctions();
		
		handlerInitialiser();		
		
	}
	
	public AgencyServiceImpl(Properties properties, Auctions auctions){
		
		this.properties = properties;
		this.auctions = auctions;
		
		handlerInitialiser();
		
	}
	
	
	public void handlerInitialiser(){
		
		handler = new AuctionHandler(properties, auctions);
		
		handler.start();
		//new Thread(handler).start ();
		
	}
	
	public void handlerFinaliser(){
		try {
			handler.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public String getManagedProperties() {
		
		return properties.listDetails();
		
	}


	public String getAuctionDate(String propertyName) {

		return properties.getAuctionDate(propertyName);
		
	}


	public boolean isAuctionActive(String propertyName) {
		
		return auctions.isAuctionActive(propertyName);
		
	}


	public String monitorAuction(String auctionName) {
		if(!auctions.contains(auctionName)){
			return "Property not Found!";
		}
		String lastSeenBid = auctions.getAuction(auctionName).getLastBid();
		while(auctions.isAuctionActive(auctionName)
				|| lastSeenBid.equals(auctions.getAuction(auctionName).getLastBid())){
			
			//Do nothing
			//waiting for auction to terminate
			//or a new bid
		
		}
		if(lastSeenBid.equals(auctions.getAuction(auctionName).getLastBid()))
			return "Auction is over! No changes in the last bidder";
		
		return "New Bid! " + auctions.getAuction(auctionName).getLastBid();
		
	}


	public String makeABid(String propertyName, int bidValue, String bidderName) {
		if(!auctions.contains(propertyName))
			return "Property not found";
		
		if(auctions.makeBid(propertyName, bidValue, bidderName)){
			return "Your bid was a success.";
		}
		return "Failed to make a bid. Either Bid is over or value was not high enough";
	}

}
