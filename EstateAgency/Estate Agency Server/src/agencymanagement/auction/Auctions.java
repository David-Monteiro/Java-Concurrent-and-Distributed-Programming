package agencymanagement.auction;

import java.util.ArrayList;
import java.util.List;

import agencymanagement.domain.Property;
import agencymanagement.domain.PropertyUtility;

public class Auctions {
	
	List<Auction> auctions;
	
	public Auctions(){
		
		auctions = new ArrayList<>();
		
	}
	
	public boolean contains(String auctionItemName){
		
		for(Auction auction: auctions) {
			
			if(auction.getAuctionName().equals(auctionItemName))
				return true;
		}
		
		return false;
		
		
	}
	
	public boolean startNewAuction(Property property){
		
		Auction auction = new Auction(property);
		
		return auctions.add(auction);
		
	}
	
	/*
	  
	 	public boolean startNewAuction(Auction auction){
			
			
			return auctions.add(auction);
			
		}
	 */
	
	public boolean closeAuction(String auctionName){

		/*for(Auction auction: auctions) {
			
			if(auction.getAuctionName().equals(auctionName))
				return auction.terminate();
			
		}
		
		return false;*/
		
		Auction auction = auctions.get(getIndexOf(auctionName));
		
		return auction.terminate();
		
		
	}
	

	public boolean makeBid(String auctionName, int bidValue, String bidderName){
		
		/*
		for(Auction auction: auctions) {
			
			if(auction.getAuctionName().equals(auctionName)){
			
				if(auction.isActive && bidValue > auction.highestBid){
					 
					auction.makeBid(bidValue, bidderName);
					 return true;
					 
				 }
			}
			
		}
		*/
		
		Auction auction = auctions.get(getIndexOf(auctionName));
		
		if(auction.getAuctionName().equals(auctionName)){
			
			//if(auction.isActive && bidValue > auction.highestBid){
			if(auction.isActive() && bidValue > auction.getHighestBid()){ 
				auction.makeBid(bidValue, bidderName);
				 return true;
				 
			 }
		}
		
		 
		 return false;
		 
	 }
	
	public String getAuctionInfo(String auctionName){
		
		Auction auction = auctions.get(getIndexOf(auctionName));
		
		return auction.getItemDetails();
		
		
	}
	
	public int getIndexOf(String auctionName){
		
		for(Auction auction: auctions) {
			
			if(auction.getAuctionName().equals(auctionName))
				return auctions.indexOf(auction);
			
		}
		
		return -1;
		
	}
	
	public boolean isAuctionActive(String auctionName){
		if (contains(auctionName))
			return auctions.get(getIndexOf(auctionName)).isActive();
		return false;
		
	}
	
	
	public Auction getAuction(String auctionName){
		
		for(Auction auction: auctions) {
			
			if(auction.getAuctionName().equals(auctionName))
				return auction;
		}
		
		return null;
		
	}
	
	
	
	

}
