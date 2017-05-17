/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/


public class ExitControl extends CarParkControl implements Runnable {
	
	private boolean running;
	
	ExitControl(Requests r0, CarPark c0, WatcherControl w0) {
		
		super(r0, c0, w0);
		
	}
	ExitControl() {
		
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
			
			System.out.println("Control with id " + Thread.currentThread().getId() + ": is waiting for people trying to exit");
			while(requests.isDemandingExit()){
				requests.goWait();
			}
			request_info = requests.getExitAppeal(); 
			

			System.out.println("Control with id " + Thread.currentThread().getId() + ": received a request and will verify if there is space in the carPark");
			
			watcher.startWrite();
			response = carPark.canLeave(request_info);
			watcher.endWrite();
			
			if(response){
				

				System.out.println("Control with id " + Thread.currentThread().getId() + ": request to exit is granted");
				watcher.startWrite();
				requests.addExitResponse(makeResponse(request_info, response));
				carPark.takeVehicle(request_info);
				watcher.endWrite();
				
			}
			else {

				System.out.println("Control with id " + Thread.currentThread().getId() + ": request to exit is denied");
				requests.addExitResponse(makeResponse(request_info, response));
				
			}
			
			
			
		}
		
	}

	
	

}
