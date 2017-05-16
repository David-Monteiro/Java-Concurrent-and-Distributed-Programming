package agencymanagement.domain;

import java.util.ArrayList;
import java.util.List;

//import agencymanagement.auction.Auction;

public class Properties {

		List<PropertyUtility> properties;

		
		public Properties(){
			
			properties = new ArrayList<>();
			
			generateProperties();
			
		}
		
		/*************************************************************/
		
		public String listDetails(){
			String value = "";
			value = value + "Property" + "\t" + "District" + "\t" + "Bedrooms" + "\t" + "Price(€)" + "\n";
			
			
			for(PropertyUtility property: properties) {
				
				String [] details = property.listDetails().split(":");
				
				for(int i = 0; i < details.length; i++){
					value = i == ( details.length -1 ) ? value + details[i] + "\n" : value + details[i] + "\t \t";
				}
			}
			
			
			return value;
			
		}
		
		/*************************************************************/
		
		
		public String listDetails(String propertyName){
			

			/*for(PropertyUtility property: properties) {
				
				return property.getDetails(propertyName);
			}*/
			
			PropertyUtility tempProperty = getPropertyUtility(propertyName);
			
			if(tempProperty != null)
				return tempProperty.getDetails(propertyName);
			
			return "";
		}
		
		
		/*************************************************************/
		
		
		
		public String getAuctionDate(String propertyName){
			
			//return getProperty(propertyName).getAuctionDate();
			if(!contains(propertyName)){
				return "Property not found";
			}
			PropertyUtility tempProperty = getPropertyUtility(propertyName);
			
			if(tempProperty != null)
				return tempProperty.getAuctionDate();
			
			return "Property not found";
			
			
		}
		
		public boolean contains(String propertyName){
			
			for(PropertyUtility property: properties) {
				
				if(property.getName().equals(propertyName))
					return true;
			}
			
			return false;
			
			
		}
		/*************************************************************/
		
		
		
		public PropertyUtility getPropertyUtility(String propertyName){
			
			for(PropertyUtility property: properties) {
				
				if(property.equals(propertyName))
					return property;
			}
			
			return null;
			
		}
		
		public Property getProperty(String propertyName){
			
			return getPropertyUtility(propertyName).getProperty();
			
		}
		
		public List<PropertyUtility> getPropertiesWithAuctionOn(){
			
			List<PropertyUtility> propertieswithAuctionOn = new ArrayList<>();
			
			for(PropertyUtility property: properties) {
				
				if(property.isAuctionOn()){
					propertieswithAuctionOn.add(property);
				}
				
			}
			
			return propertieswithAuctionOn;
		}
		
		public void generateProperties(){
			AuctionDate d1 = new AuctionDate(2, 2);
			AuctionDate d2 = new AuctionDate(4, 2);
			AuctionDate d3 = new AuctionDate(6, 4);
			AuctionDate d4 = new AuctionDate(10, 2);
			AuctionDate d5 = new AuctionDate(12, 3);
			
			Property p1 = new Property("H1", "3", 2, 375000);
			Property p2 = new Property("H2", "5", 3, 360000);
			Property p3 = new Property("H3", "3", 3, 500000);
			Property p4 = new Property("A1", "5", 2, 250000);
			Property p5 = new Property("A2", "7", 1, 150000);
			
			properties.add(new PropertyUtility(p1, d1));
			properties.add(new PropertyUtility(p2, d2));
			properties.add(new PropertyUtility(p3, d3));
			properties.add(new PropertyUtility(p4, d4));
			properties.add(new PropertyUtility(p5, d5));
			
			
		}
}
