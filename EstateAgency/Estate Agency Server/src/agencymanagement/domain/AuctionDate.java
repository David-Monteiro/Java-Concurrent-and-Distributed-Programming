package agencymanagement.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuctionDate{
	
	private String from;
	private String to;
	// dateFormat = ("yyyy:MM:dd:HH:mm");


	public void initiliser(int year, int month, int day, int hour, int minute, int duration){

		from =  "" +year + ":" + month + ":" + day + ":" + hour + ":" + minute;
		
		setTo(year, month, day, hour, minute, duration);
				
	}
	
	public AuctionDate(int startIn, int duration){
		
		long ONE_MINUTE_IN_MILLIS=60000;
		
		//Here I will get the current date in milliseconds
		//then I will add to that and set a new date
		Date currentDate = new Date();
		long t = currentDate.getTime();
		Date afterAddingSomeMins = new Date((t + (startIn * ONE_MINUTE_IN_MILLIS)));
				
		//Here I will change a date to an array of strings
		//using that array I will fully initialise the Auctiondate class
	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
	    String [] output = myFormat.format(afterAddingSomeMins).split(":");
	    
	    initiliser(Integer.parseInt(output[0]), Integer.parseInt(output[1]), Integer.parseInt(output[2])
	    		, Integer.parseInt(output[3]), Integer.parseInt(output[4]), duration);
	    
	}

	public boolean isEqual(AuctionDate slot){ 
		return getFrom().equals(slot.getFrom())
			&& getTo().equals(slot.getTo());

	}
	
	public boolean auctionOn() throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
				
		Date startDate = dateFormat.parse(from);
        Date endDate = dateFormat.parse(to);
		Date currentDate = new Date();
		
		
		if( afterStart(currentDate, startDate) && beforeEnd(currentDate, endDate))
			return true;
				
		return false;
	}
	
	public boolean afterStart(Date d1, Date d2){
		//Ideally d1 will be current date and d2 will be start date
		
		return d1.compareTo(d2) > 0;
		
		/*
		if (d1.compareTo(d2) < 0)
            return true;
            
		return false;
		*/
	}
	
	public boolean beforeEnd(Date d1, Date d2){
		////Same as afterStart, d1 will be current date and d2 will be end date
		
		return d1.compareTo(d2) < 0;
		/*
		if (d1.compareTo(d2) < 0)
            return true;
            
		return false;
		*/
	}

	public String getFrom(){
		return from;
	}

	public String getTo(){
		return to;
	}
	
	public void setTo(int year, int month, int day, int hour, int minute, int duration){
		
		//The method will set variable "to" based on duration being provided as parameter
		
		if(minute + duration > 60){
			
			if(hour + 1 > 24){
				
				
				if (month==9 || month==4 || month==6 || month==11){
					if(day + 1 > 30){
						if(month + 1 > 12){
							year = year + 1;
						}
						month = (month + 1) % 12;
					}
					day = (day + 1) % 30;
				}
				else if (month==2) { 
					// catch leap year      
					if (year%4==0 && year!=1900){
						if(day + 1 > 29){
							if(month + 1 > 12){
								year = year + 1;
							}
							month = (month + 1) % 12;
						}
						day = (day + 1) % 29;
					}
					else{
						if(day + 1 > 28){
							if(month + 1 > 12){
								year = year + 1;
							}
							month = (month + 1) % 12;
						}
						day = (day + 1) % 28;     
					}
				}     
				else{
					if(day + 1 > 31){
						if(month + 1 > 12){
							year = year + 1;
						}
						month = (month + 1) % 12;
					}
					day = (day + 1) % 31;
				}
		
				
			}
			
			hour = (hour + 1) % 24;
		}
		
		minute = (minute + duration) % 60;
		
		to =  "" +year + ":" + month + ":" + day + ":" + hour + ":" + minute;

		
	}
	
	// TODO change date format to a sinple string
	// this way everything will be made simplier

}