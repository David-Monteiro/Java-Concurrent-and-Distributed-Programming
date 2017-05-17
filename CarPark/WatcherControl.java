/* 	
	Name:			David Monteiro
	Student no:		10364119	
	CA4006 Concurrent & Distributed Programming
	Assignment 1: University Car Park Problem
*/


public class WatcherControl {


	private int readers;			//number of readers
	private int waitingWriters;		//number of waiting writers
	private boolean readersTurn;	//is the readers turn
	private boolean writing; 		//is it writing

	WatcherControl(){
		

		readers = 0;
		waitingWriters = 0;
		readersTurn = true;
		writing = false;

	}

	public void startRead() {

		while ((!readersTurn && waitingWriters > 0) 
			|| writing){
			goWait();
		}
		readers++;

	}

	public boolean endRead() {

		readers--;

		if(readers == 0)
			readersTurn = false;

		goNotify();
		if (readersTurn) return false;
		return true;
	}

	public void startWrite() {

		waitingWriters++;	

		while (readers > 0 || writing){
			goWait();
		}

		waitingWriters--;

		writing = true;
	} 

	public void endWrite() {

		writing = false;

		if(waitingWriters == 0)
			readersTurn = true;

		goNotify();

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
