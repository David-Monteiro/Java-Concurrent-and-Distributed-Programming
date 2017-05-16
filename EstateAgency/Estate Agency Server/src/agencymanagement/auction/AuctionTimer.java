package agencymanagement.auction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import agencymanagement.domain.AuctionDate;
import agencymanagement.domain.Properties;

public class AuctionTimer extends Thread{
	
	Properties properties;
	Auction auction;
	int duration;
	
	AuctionTimer(Properties properties,Auction auction, AuctionDate auctionDate){
		
		this.properties = properties;
		this.auction = auction;
		
		try {
			setDuration(auctionDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			Thread.sleep(Math.abs(duration * 60 * 1000));
			
			
			//terminate auction so it doesn't accept anymore bids
			auction.terminate();
			
			//change in  property details auction to off, to not cause confusion
			properties.getPropertyUtility(auction.getAuctionName()).setAuctionOff();
			
			//change the name of the owner of such property
			properties.getProperty(auction.getAuctionName()).setNewOwner(auction.gethighestBidderName());
	         
	    } catch (InterruptedException ex) {
	     
	    }
	}
	
	public void setDuration(AuctionDate auctionDates) throws ParseException{
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
		
		Date startDate = dateFormat.parse(auctionDates.getFrom());
        Date endDate = dateFormat.parse(auctionDates.getTo());
        
        int diff = (int) (startDate.getTime() - endDate.getTime());
		
        
		duration = diff / (60 * 1000) % 60;
		
	}

}
