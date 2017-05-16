package agencymanagement.domain;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class PropertyUtility {
	
	private Map<Property, AuctionDate> property_map;
	private boolean isAuctionActive;
	/*
	 * key is Property object
	 * value is the auction time of property
	 *  date format = ("yyyy:MM:dd:HH:mm");
	 */
	
	public PropertyUtility(Property property, AuctionDate auctionDate){
		
		property_map = new HashMap<>();
		property_map.put(property, auctionDate);
		
		isAuctionActive = false;
		
	}
	
	public void setAuctionOn(){
		
		isAuctionActive = true;
	}
	
	public void setAuctionOff(){
		
		isAuctionActive = false;
		
	}
	
	public String listDetails(){
		String value = "";
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
				
				value = value + entry.getKey().toString();
	
		}
		
		return value;
	}
	
	public Property getProperty(){
		
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
			
			return entry.getKey();

		}
		
		return null;
	}
	
	public String getAuctionDate() {
				
		String value = "";
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
				
				return entry.getValue().getFrom();
	
		}
		
		return value;
	}
	
	public AuctionDate getAuctionDateAdv(){
		
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
			
			return entry.getValue();

		}
		
		return null;
		
	}
	
	
	
	public String getDetails(String propertyName){
		
		if(property_map.containsKey(propertyName)){
			return property_map.get(propertyName).toString();
			
		}
		return "";
		
	}
		

	public String getName(){
		
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
				
				return  entry.getKey().getName();
	
		}
		return "";
	}
	
	public boolean equals(String propertyName){
		
		return getName().equals(propertyName);
		
	}
	
	public boolean isAuctionOn(){
		
		for (Map.Entry<Property, AuctionDate> entry : property_map.entrySet()) {
			
			try {
				return  entry.getValue().auctionOn();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	

	

}
