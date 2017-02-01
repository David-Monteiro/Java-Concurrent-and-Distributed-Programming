/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: The Elevator Problem
*/
import java.io.*;
import java.lang.Math;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Random;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


	
class Node<T> {
	public T request;	// data          
	public Node<T> next = null;	// successor node
	public Node<T> pred = null;	// predecessor node
	
	Node(T req0, Node<T> pred0, Node<T> next0){
		request = req0;
		pred = pred0;
		next = next0;
	}
}

class Requests{
	//This class acts as a queue using a bounded nodes

	private static final int MAX_REQUESTS = 10;
	private int num_request; 		// total number of requests

	//requests going down
	private Node<String> downHead; 
	private Node<String> downTail;
	private int upRequests;  

	//requests going up
	private Node<String> upHead; 
	private Node<String> upTail;
	private int downRequests;

	//class Constructor
	Requests(){

		num_request = 0;

		downHead = null; 
		downTail = null;
		upRequests = 0;  

		upHead = null; 
		upTail = null;
		downRequests = 0;

	}
 
	boolean isEmpty() { 	return num_request==0;	}
	boolean isFull(){		return num_request == MAX_REQUESTS;	}

	static int date_compare(String a0, String b0){
		//Here I will compare 2 dates
		//The oldest date will be have priority
		//if equal dates, the elevator will go up
		String [] a = a0.split(":");
		String [] b = b0.split(":");
		for(int i = 0; i < 6; i++){
			if(Integer.parseInt(a[i]) > Integer.parseInt(a[i])) return 1;
			if(Integer.parseInt(a[i]) < Integer.parseInt(a[i])) return 2;
		}
		return 1;
	}

	public synchronized boolean put(String request){
		String [] info = request.split(" "); //HereI read the request

		//the next lines will decide whether it is a goingup or goingdown request
		if(Integer.parseInt(info[info.length-1]) > Integer.parseInt(info[info.length-2])) put(request, 1);
		else put(request, 2);
		if(num_request > MAX_REQUESTS){
			if(Integer.parseInt(info[info.length-1]) > Integer.parseInt(info[info.length-2])) takeHead(1);
			else takeHead(2);
			return false;
		}
		return true;
	}
	public synchronized void put(String request, int type) {
		//type parameter stands for the type of add
		// if 1 it will add to upRequest
		// if 2 it will add to downRequest

		if(type == 1){
			upHead = new Node<String>(request,null,upHead);

			if(upTail==null) upTail = upHead;    
			else upHead.next.pred = upHead;

			upRequests++;
		}
		else if(type == 2){
			downHead = new Node<String>(request,null,downHead);

			if(downTail==null) downTail = downHead;    
			else downHead.next.pred = downHead;

			downRequests++;
		}

		num_request = upRequests +  downRequests;
	}

	public synchronized String take(){
		if (downRequests <= 0 && upRequests > 0) return take(1);
		if(upRequests <= 0 && downRequests > 0) return take(2);

		return take(date_compare(upTail.request.split(" ")[1], downTail.request.split(" ")[1]));	
	}
	public synchronized String take(int type){
		//type parameter stands for the type of add
		// if 1 it will add to upRequest
		// if 2 it will add to downRequest
		
		String t = "";
		if(!isEmpty()){
			if(type == 1){
				t = upTail.request;
				upTail = upTail.pred;
				if (upTail!=null)	upTail.next = null;
				else	upHead = null;
				upRequests--;
			}
			else if(type == 2){
				t = downTail.request;
				downTail = downTail.pred;
				if (downTail!=null)	downTail.next = null;
				else	downHead = null;
				downRequests--;
			}
		}
		num_request = upRequests +  downRequests;
		return t;
	}
	public synchronized String take(int type, int current_floor){ 

		Node<String> req;
		int temp;
		if(type == 1){
			req = upTail;
			if(upRequests > 0){
				while(upRequests > 0 && req !=null){
					if(req.request != null){
						temp = req.request.split(" ").length;
						if(temp > 3){
							if(Integer.parseInt(req.request.split(" ")[req.request.split(" ").length-2]) != current_floor){
								req = req.pred;
							}
						}
					}
				}
			}
			if(req != null){
				if(req.request.equals(upTail.request)){
					upTail = upTail.pred;
					if (upTail!=null)	upTail.next = null;
					else	upHead = null;
				}
				else if(req.request.equals(upHead.request)){
					upHead = upHead.next;
					upHead.pred = null;
				}
				else{
					req.pred.next = req.next;
					req.next.pred = req.pred;	
				}
			}
		}
		else{
			req = downTail;
			if(upRequests > 0){
				while(upRequests > 0 && req !=null){
					if(req.request != null){
						temp = req.request.split(" ").length;
						if(temp > 3){
							if(Integer.parseInt(req.request.split(" ")[req.request.split(" ").length-2]) != current_floor){
								req = req.pred;
							}
						}
					}
				}
			}
			if(req != null){
				if(req.request.equals(downTail.request)){
					downTail = downTail.pred;
					if (downTail!=null)	downTail.next = null;
					else	downHead = null;
				}
				else if(req.request.equals(downHead.request)){
					downHead = downHead.next;
					downHead.pred = null;
				}
				else{
					req.pred.next = req.next;
					req.next.pred = req.pred;	
				}
			}
		}

		if(req == null)	return "";
		else {
			if(type == 1){
				upRequests--;
				num_request = upRequests +  downRequests;
			}
			else{
				downRequests--;
				num_request = upRequests +  downRequests;
			}
			return req.request;
		}
	}
	public synchronized String takeHead(int type){
		//this method will solely be used to removed excess requests
		String value;
		if(type == 1){
			value = upHead.request;
			upHead = upHead.next;
			upHead.pred = null; 
		}
		else{
			value = downHead.request;
			downHead = downHead.next;
			downHead.pred = null;
		}
		return value;
	}

	public synchronized String peek(){
		if (downRequests <= 0 && upRequests > 0) return peek(1);
		if(upRequests <= 0 && downRequests > 0) return peek(2);

		return take(date_compare(upTail.request.split(" ")[1], downTail.request.split(" ")[1]));	
	}
	public synchronized String peek(int type){
		//type parameter stands for the type of add
		// if 1 it will add to upRequest
		// if 2 it will add to downRequest
		
		String t = "";
		if(!isEmpty()){
			if(type == 1)	t = upTail.request;
			else if(type == 2)	t = downTail.request;
		}
		return t;
	}
	public synchronized String peek(int type, int current_floor){ 

		Node<String> req;
		int floor;
		int temp;
		if(type == 1){
			req = upTail;
			while(upRequests > 0 && req !=null){
				if(req.request != null){
					temp = req.request.split(" ").length;
					if(temp > 3){
						if(Integer.parseInt(req.request.split(" ")[req.request.split(" ").length-2]) != current_floor){
							req = req.pred;
						}
					}
				}
			}
		}
		else{
			req = downTail;
			if(upRequests > 0){
				while(upRequests > 0 && req !=null){
					if(req.request != null){
						temp = req.request.split(" ").length;
						if(temp > 3){
							if(Integer.parseInt(req.request.split(" ")[req.request.split(" ").length-2]) != current_floor){
								req = req.pred;
							}
						}
					}
				}
			}
		}
		if(req == null)	return "";
		else	return req.request;
	}

	public synchronized void callWait() {

		try {
			wait(); 
		} catch (InterruptedException e) { }
	}

	public synchronized void callNotify() {

		notifyAll();
	}

	public synchronized void callSleep(int time) {

		try{
			Thread.sleep(time);
		} catch (InterruptedException e) { }
	}


}

class Person implements Runnable {

	//Person information
	private final int id;
	private final int weight;
	private final int arrivalFloor;
	private final int destinationFloor;

	private final Requests requests;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
	private Date date;

	private Random generator = new Random();

	Person(Requests r) {

		requests = r;
		id = (int)(Math.random() * 100);
		weight = (int)(Math.random() * 5 + 5);
		arrivalFloor = (int)(Math.random() * 10);;
		int temp = (int)(Math.random() * 10);;
		destinationFloor = (temp == arrivalFloor ? (temp + 1) % 10 : temp);

	}

	public void run() { 
		if(requests.isFull()) {
			requests.callWait(); // sleeps for some time and then repeats run
		}
		else {
			arrivingGoingFromTo();

		}
	}

	public String myRequest(){
		return (id + " " + dateFormat.format(date) + " " + weight + " " + arrivalFloor + " " + destinationFloor);
	}

	public void arrivingGoingFromTo(){
		date = new Date();
		requests.put(myRequest());
		requests.callNotify();
	}

}

class Elevator implements Runnable {

	public PrintWriter out;

	private static final int MAX_WEIGHT = 20;

	private final Requests requests;
	private String [] req_in_service;
	private int in_service_size;

	private int currentWeight;
	private int currentFloor;
	private int destinationFloor;
	private int movement_flag; // this will tell if the elevator is going up or down

	Elevator(Requests r) {

		requests = r;
		req_in_service = new String[10];
		in_service_size = 0;
		currentWeight = 0;	//No one is inside, so the value is 0
		currentFloor = 1;	//Elevator starts in the 1st floor
		destinationFloor= -1;
		movement_flag = 1; //up is 1 and down is 2, since we begin on 1st floor we can only go up
	}

	public void setOutputStream(PrintWriter out0){	out = out0;	}

	public void run() {
		if(requests.isEmpty() && in_services_empty()){
			out.print("Elevator has no request, it waits in " + currentFloor + " floor.\n");
			requests.callWait();
		}
		else{
			printAirport();
			if(in_services_empty()){
				startMovement();
				out.print("Elevator gets request from " + destinationFloor + " floor. It starts running\n");
			}
			if(destinationFloor != currentFloor){
				inMovement();
				requests.callSleep(1000);
				out.println("Elevator goes to  " + currentFloor + " floor");
			}

			if(movement_flag == 1) currentFloor = currentFloor + 1;
			else if(movement_flag == 2) currentFloor = currentFloor - 1;
		}
	}

	public void startMovement(){
		req_in_service[in_service_size] = requests.peek();
		in_service_size += 1;

		setDestination();
		if(currentFloor < destinationFloor) movement_flag = 1;
		else movement_flag = 2;
		out.print("Elevator got a request from " + destinationFloor + " floor, starts moving in that direction\n");
	}

	public void inMovement(){
		goingOut();
		goingIn();
		setDestination();
		
	}

	public void goingIn() {
		String value = " ";
		if(!value.equals("") ) {
			String temp = requests.peek(movement_flag, currentFloor);
			System.out.println(temp);
			if(!temp.equals("")){
				if( (Integer.parseInt(temp.split(" ")[2]) + currentWeight) <= MAX_WEIGHT) {					
					value = requests.take(movement_flag, currentFloor);
					req_in_service[in_service_size] = value;
					currentWeight = currentWeight + (Integer.parseInt(value.split(" ")[2]));
					in_service_size += 1;
					out.print("Person with ID: " + (Integer.parseInt(value.split(" ")[0])) + " got in the elevator\n");
					requests.callNotify();
				}
				else if( (Integer.parseInt(temp.split(" ")[2]) + currentWeight) > MAX_WEIGHT){
					out.print("Person with ID: " + (Integer.parseInt(value.split(" ")[0])) + " has excess weight, so it waits\n");
				}
			}
		}
	}
	public String goingOut() {
		String value = ""; 
		if(!in_services_empty()){
			for(int i=0; i<in_service_size; i++){
				if(currentFloor == Integer.parseInt(req_in_service[i].split(" ")[4])){
					value = req_in_service[i];
					remove_req_in_service(i);
					currentWeight = currentWeight - (Integer.parseInt(value.split(" ")[2]));
					out.print("Person with ID: " + (Integer.parseInt(value.split(" ")[0])) + " left the elevator\n");
				}
			}
		} return value;
	}

	public void remove_req_in_service(int index) {
		for (int i = index; i < in_service_size-1; i++)
    		req_in_service[i] = req_in_service[i + 1];
    	in_service_size -= 1;
	}

	public boolean in_services_empty() { 	return in_service_size==0;	}

	public void setDestination(){
		if(!in_services_empty()){
			for(int i=0; i<in_service_size; i++){
				if(movement_flag == 1 && destinationFloor<Integer.parseInt(req_in_service[i].split(" ")[2]))
					destinationFloor = Integer.parseInt(req_in_service[i].split(" ")[2]);
				if(movement_flag == 2 && destinationFloor>Integer.parseInt(req_in_service[i].split(" ")[2]))
					destinationFloor = Integer.parseInt(req_in_service[i].split(" ")[2]);
			}
		}
	}

	public void printAirport(){
		for(int k=0; k<12; k++){
			System.out.println("----------------------------------------------------------");
			if(k == 0)	System.out.println("-|   Elevator  |      Hall              |       Floor    -");
			else if(k == 11)	System.out.printf("-|%30s %26s", "AIRPORT", "-\n");
			//	System.out.println("-          				AIRPORT        				     -");
			else if(k > 0 && k < 11){
				if(currentFloor == k)	System.out.printf("-|%-13s", elevatorDisplay());
				else 	System.out.printf("-|%-13s", "");

					System.out.printf("|%-24s", personDisplay(k));

					System.out.printf("|%-16s-\n", ""+k);
			}
		}
	}

	public String elevatorDisplay(){
		String value = "E"; 
		int temp;
		for(int i=0; i<in_service_size; i++){
			temp =  Integer.parseInt(req_in_service[i].split(" ")[0]);
			value = value + String.format(":%02d", temp);
		}return value;
	}

	public String personDisplay(int floor){
		String value = "P";
		int temp;
		while( !(requests.peek(movement_flag, floor).equals("")) ){
			temp = Integer.parseInt(requests.peek(movement_flag, floor).split(" ")[0]);
			if(temp < 10)
				value = value + "0" + temp;
			else
				value = value + temp;
		}
		if	(value.length()==1) return "";
		return value;
	}

	public int getCurrentFloor(){	return currentFloor;	}
	
	public int seeDirectionOfMovement(){	return movement_flag;	}
}

//Todo
//Add wait and notify to requests class. 
//Verify and Validate synchronisation 
//


class ElevatorSystem{

	/*public void S(String value){
		//System.out

	}*/

	public static void main (String [] args){
		try{
			Requests requests = new Requests();

			PrintWriter logbook = new PrintWriter("elevatorLog.dat");

			Elevator elevator = new Elevator(requests);
			Person person;

			ExecutorService threadExecutor = Executors.newFixedThreadPool(3);

			elevator.setOutputStream(logbook);
			
			System.out.println( "Starting Elevator" );
			
			threadExecutor.execute(elevator);

			//elev.start();
			for(int i=0; i<100; i++){
				person = new Person(requests);
				
				threadExecutor.execute(person);
			}
			threadExecutor.shutdown();

			while (!threadExecutor.isTerminated()) {}
			System.out.println("Finished all threads");


			logbook.close();
		}
		catch(IOException exception){
		//catch(IOException | InterruptedException exception){
			System.out.println("File not founInterruptedException exd!");
			exception.printStackTrace();
		}
	}

}