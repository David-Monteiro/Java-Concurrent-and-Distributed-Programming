 /* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/

public class Person implements Runnable{
	
	private final int id;
	private final String name;
	private final char type; //can be learner(student), staff or guest
	
	private boolean inCarPark;
	
	private final Requests requests;
	
	Person (Requests r0, String n0, char t0, int id0) {
		
		requests = r0;
		
		name = n0;
		type = t0;
		id = id0;
		
		inCarPark = false;
		
	}
	
	
	public String myRequest() {
		
		//return type + "," + id + "," + name;
		return name + "," + id;
	}
	
	public void run() {

		while(!inCarPark){
			
			while(!requests.isEntryDoorFree()){
				requests.goWait();
			}
			
			requests.occupy_entry();
			requests.addEntryAppeal(myRequest());
			requests.goNotify();
			System.out.println(myRequest() + ": Requesting to enter CarPark");
			
			while(!requests.isEntryResponse()){
				requests.goWait();
			}
			
			readEntryResponse();
			requests.release_entry();
			requests.goNotify();
			if(inCarPark)
				System.out.println(myRequest() + ": request to enter accepted");
			else
				System.out.println(myRequest() + ": request to enter denied");
					
		}
		
		inCarPark = true;
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(inCarPark){
			
			while(!requests.isExitDoorFree()){
				requests.goWait();
			}
			
			requests.occupy_exit();
			requests.addExitAppeal(myRequest());
			requests.goNotify();
			System.out.println(myRequest() + ": Requesting to go leave carpark");
			
			while(!requests.isExitResponse()){
				requests.goWait();
			}
			
			readExitResponse();
			if(!inCarPark)
				System.out.println(myRequest() + ": request to leave accepted");
			else
				System.out.println(myRequest() + ": request to leave denied");
			
			requests.release_exit();
			requests.notifyAll();
		}
		
	}
	
	protected void readEntryResponse(){
		
		String [] response = requests.getEntryResponse(myRequest()).split(",");
		if(response[response.length-1].equals("YES"))
			inCarPark = true;
			
	}
	
	protected void readExitResponse(){
		
		String [] response = requests.getExitResponse(myRequest()).split(",");
		if(response[response.length-1].equals("YES"))
			inCarPark = false;
		
	}

	public int getId() {
		return id;
	}

	public char getType() {
		return type;
	}
}
