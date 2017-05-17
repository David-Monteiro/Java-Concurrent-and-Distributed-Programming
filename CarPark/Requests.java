/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/

import java.util.LinkedHashSet;
import java.util.Set;

public class Requests {

	private Set<String> enterRequests;
	private Set<String> exitRequests;
	
	private Set<String> enterResponse;
	private Set<String> exitResponse;
	
	private static final int MAX_ENTRY_DOORS = 3;
	private static final int MAX_EXIT_DOORS = 3;
	
	private int available_entry_doors;
	private int available_exit_doors;
		
	
	Requests () {
		//class constructor
		enterRequests = new LinkedHashSet <>();
		exitRequests = new LinkedHashSet<>();
		
		enterResponse = new LinkedHashSet<>();
		exitResponse = new LinkedHashSet<>();
		
		available_entry_doors = MAX_ENTRY_DOORS;
		available_exit_doors = MAX_EXIT_DOORS;
		
	}
	
////////////////////////////////////////////////////////////
	public boolean isDemandingEntry() {
		
		return enterRequests.size() > 0;
		
	}
	public boolean isDemandingExit() {
		
		return exitRequests.size() > 0;
		
	}
	
	
	////////////////////////////////////////////////////////////
	public boolean isEntryResponse() {
		
		return !enterResponse.isEmpty();
		
	}
	public boolean isExitResponse() {
		
		return !exitResponse.isEmpty();
		
	}
	
	
	////////////////////////////////////////////////////////////
	public synchronized boolean addEntryAppeal(String r0) {
		
		return enterRequests.add(r0);
	}
	
	public synchronized String getEntryAppeal() {
		
		String entryAppeal = "" + enterRequests.toArray()[0];
		enterRequests.remove(entryAppeal);		
		return "" + entryAppeal;
		                       
	}
	

	////////////////////////////////////////////////////////////
	public synchronized boolean addExitAppeal(String r0) {
		
		return enterRequests.add(r0);
	}
	
	public synchronized String getExitAppeal() {
		
		String exitAppeal = "" + exitRequests.toArray()[0];
		exitRequests.remove(exitAppeal);		
		return "" + exitAppeal;
				
	}
	

	////////////////////////////////////////////////////////////
	public synchronized boolean addEntryResponse(String r0) {
		
		return enterResponse.add(r0);
	}
	
	public synchronized String getEntryResponse(String info) {
		
		String [] split_response_info; 
		String [] person_info = info.split(",");
		
		
		for (String response_info : enterResponse){
			
			split_response_info = response_info.split(",");
			if(split_response_info[0].equals(person_info[0]) 
					&& split_response_info[1].equals(person_info[1])){	
				enterResponse.remove(response_info);
				return response_info;
			}
			
		}
		
		return "NO";
	}
	

	////////////////////////////////////////////////////////////
	public synchronized boolean addExitResponse(String r0) {
		
		return enterResponse.add(r0);
	}
	
	public synchronized String getExitResponse(String info) {
		
		
		String [] split_response_info; 
		String [] person_info = info.split(",");
		
		
		for (String response_info : exitResponse){
			
			split_response_info = response_info.split(",");
			if(split_response_info[0].equals(person_info[0]) 
					&& split_response_info[1].equals(person_info[1])){	
				exitResponse.remove(response_info);
				return response_info;
			}
			
		}
		
		return "NO";
		
		
	}
	
	

	////////////////////////////////////////////////////////////
	
	public boolean isEntryDoorFree(){
		
		return available_entry_doors >= 0;
	}
	
	public boolean isExitDoorFree(){
		
		return available_exit_doors >= 0;
	}
	
	public synchronized void occupy_entry(){
		available_entry_doors -= 1;		
	}
	public synchronized void release_entry(){
		available_entry_doors += 1;		
	}
	
	public synchronized void occupy_exit(){
		available_exit_doors -= 1;
	}
	public synchronized void release_exit(){
		available_entry_doors += 1;		
	}
	
	
	
	////////////////////////////////////////////////////////////
	public void goWait()  {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void goNotify() {
		notifyAll();
	}
	
}
