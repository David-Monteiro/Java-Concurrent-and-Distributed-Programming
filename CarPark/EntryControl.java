/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/


public class EntryControl extends CarParkControl implements Runnable{
	

	private boolean running;
	
	EntryControl(Requests r0, CarPark c0, WatcherControl w0) {
		
		super(r0, c0, w0);
		
	}
	
	EntryControl () {
		
		super();
		
	}
	
	public void terminate() {
		running = false;
	}
	
	@Override
	public void run(){
		running = true;
		
		String request_info;
		boolean response;
		
		while(running){
			
			request_info = "";
			
			while(requests.isDemandingEntry()){
				requests.goWait();
			}
			request_info = requests.getEntryAppeal(); //store this somewhere
			
			watcher.startRead();
			response = carPark.hasSpace();
			watcher.endRead();
			
			if(response){
				
				watcher.startWrite();
				requests.addEntryResponse(makeResponse(request_info, response));
				carPark.addVehicle(request_info);
				watcher.endWrite();
				
			}
			else {
			
				requests.addEntryResponse(makeResponse(request_info, response));
				
			}
			
			
		}
		
	}

}
