/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/

import java.util.HashSet;
import java.util.Set;

public class CarPark {

	private static final int MAX = 1000;
	private Set<String> ParkSpace;
	
	CarPark() {
		
		ParkSpace = new HashSet<>();
		
	}
	
	public synchronized boolean addVehicle(String info){
		
		return ParkSpace.add(info);
		
	}
	
	public synchronized boolean takeVehicle(String info){
		
		return ParkSpace.remove(info);
		
	}
	
	public boolean hasSpace() {
		
		return MAX > ParkSpace.size();
		
	}
	
	public boolean contains(String info){
		
		return ParkSpace.contains(info);
		
	}
	
	public boolean canLeave(String Info){
		return true;
	}

	
	public void goWait() throws InterruptedException {
		wait();
	}
	
	public void goNotify() {
		notifyAll();
	}
	
}
