/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	
	private static List<Integer> ids_list;
	private static Requests requests;
	private static CarPark carPark;
	private static WatcherControl watcher;
	
	
	public static void main (String [] args) {
		
		requests = new Requests();
		carPark = new CarPark();
		watcher = new WatcherControl();
			
		ids_list = new LinkedList<>();
		listGenerator();
		

		//////////////////////////////////////////////////////////////////////////////
		
		EntryControl [] entryControl = new EntryControl[3];
		ExitControl [] exitControl = new ExitControl[3];
		

		ExecutorService threadControlExecutor = Executors.newFixedThreadPool(6);
		
		for(int i = 0; i < 3; i++){
			
			entryControl[i] = new EntryControl(requests, carPark, watcher);
			threadControlExecutor.execute(entryControl[i]);
			
		}
		for(int i = 0; i < 3; i++){

			exitControl[i] = new ExitControl(requests, carPark, watcher);
			threadControlExecutor.execute(exitControl[i]);
			
		}
		
		//////////////////////////////////////////////////////////////////////////////
		
		//To generate threads that represent a person
		personGenerator();
		
		
		//Once it is over the following command will terminate the thread executer
		//and all the threads running through it
		threadControlExecutor.shutdown();
		
	}
	

	private static void listGenerator(){

		for(int i = 1; i <= 200; i++){

			ids_list.add(new Integer(i ));

		}

	}
	
	private static int idGenerator(){
		
		return (int)(Math.random() * ids_list.size());

	}
	
	private static String nameGenerator(){
		
		String value = "";
		try{
			Scanner in = new Scanner(new File("names.txt"));
			
			int rand = (int)(Math.random() * 200);
			
			int i = 0;
			while(in.hasNext()){

				value = "" + in.next();
				i++;

				if(i == rand){
					break;
				}
			}

			in.close();
			

		} catch(IOException e){
			System.out.println("File unreadable");
			e.printStackTrace();
		}
		return value;
		
	}
	
	private static char typeGenerator(){
		
		int value = (int)(Math.random() * 1000);
		
		if(value > 300) //L is for learner and is for students
			return 'L';
		
		if(value > 50 && value <= 300) //S is for Staff and is used mostly by lecturers 
			return 'S';
		
		return 'V'; //G is for Visitors 

	}
	
	private static void personGenerator() {

		int new_id;
		Person person;

		ExecutorService threadPersonExecutor = Executors.newFixedThreadPool(6);

		while(ids_list.size() > 0){

			new_id = (int)ids_list.remove(idGenerator());

			person = new Person(requests, nameGenerator(), typeGenerator(), new_id);
			threadPersonExecutor.execute(person);

		}
		
		threadPersonExecutor.shutdown();

	}

}
