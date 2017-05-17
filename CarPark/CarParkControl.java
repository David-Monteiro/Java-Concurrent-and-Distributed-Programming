/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/


public class CarParkControl implements Runnable{
	
	protected Requests requests;
	protected CarPark carPark;
	protected WatcherControl watcher;
	
	public CarParkControl(Requests r0, CarPark c0, WatcherControl w0) {
		
		requests = r0;
		carPark = c0;
		watcher = w0;
		
		
	}
	public CarParkControl() {
		
		requests = new Requests();
		carPark = new CarPark();
		watcher = new WatcherControl();
		
	}
	
	public void run() {
		
	
	}
	
	public String makeResponse(String request, boolean response){
		
		if(response)
			return request + "," + "YES";
		return request + "," + "NO";
		
	}
	
	

}
