package agencymanagement.auction;

import java.util.LinkedList;
import java.util.List;

import agencymanagement.domain.Property;

public class Auction {
	
	private String auctionName;
	private int initialPrice;
	private int highestBid;
	private String highestBidName;
	private Property sellingItem;
	private List<String> biddingLog;
	private boolean isActive;
	
	Auction(Property property) {
		
		sellingItem = property;
		
		auctionName = property.getName();
		initialPrice = highestBid = property.getPrice();
		highestBidName = property.getOwnerName();
		
		biddingLog = new LinkedList<>();
		
		isActive = true;
		
	}
	
	public boolean isActive(){
		
		return isActive;
		
	}
	
	public boolean terminate(){
		
		isActive = false;
		return true;
		
	}
	
	public String getAuctionName(){
		
		return auctionName;
	}
	
	
	 public void makeBid(int bidValue , String bidderName){
		 
		setHighestBid(bidValue);
		setHighestBidName(bidderName);
		
		biddingLog.add(bidderName + ":" + bidValue);
		 
	 }
	 
	 /************************************************/
	 
	 public int getHighestBid(){
		 
		 return highestBid;
		 
	 }
	 
	 public void setHighestBid(int bidValue){
		 
		 highestBid = bidValue;
		 
	 }
	 
	 /************************************************/
	 
	 public String gethighestBidderName(){
		 
		 return highestBidName; 
	 }
	 
	 public void setHighestBidName(String bidderName){
		 
		 highestBidName = bidderName;
		 
	 }
	 
	 
	 /************************************************/
	 
	 public List<String> getBidLog(){
		 
		 return biddingLog;
		 
	 }
	 
	 public String getItemDetails(){
		 
		 return sellingItem.toString();
		 
	 }
	 
	 public String getLastBid(){
		 
		 return gethighestBidderName() + ":" + getHighestBid();
	 }
	 
	 
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}


