package agencymanagement.domain;

public class Property {
	
	private String property;
	private String district;
	private int bedRooms;
	private int price;
	
	private String owner;
	
	Property (String property, String district, int bedRooms, int price){
		
		this.property = property;
		this.district = district;
		this.bedRooms = bedRooms;
		this.price = price;
		
		this.owner = "State Agency";
	}
	
	public String toString() {
		
		 return property + ":" + district + ":" + bedRooms + ":" + price; 
	}
	
	public int getPrice() {
		
		return price;
		
	}
	
	public String getName() {
		
		return property;
		
	}
	
	public void setNewOwner(String name) {
		
		owner = name;
	}
	
	public String getOwnerName() {
		
		return owner;
		
	}
	
}