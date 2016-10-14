
class Buffer{

	private int [] content;
	private int nextIn, nextOut;
	private int ins, outs;
	private int size, occupied;

	Buffer(){}

	Buffer(int s){

		size = s;
		content = new int [size];
		ins = 0;
		outs = 0;
		nextIn = 0;
		nextOut = 0;
		occupied = 0;
	}


	public boolean dataAvailable(){
		return occupied > 0;
	}

	public boolean roomAvailable(){
		return occupied < size;
	}


	public  void getStatus() {
		System.out.println("Delta = " + (ins - outs - occupied)
			+ " Occupied = " + occupied);
	}

	public synchronized void put (int data) {

		while(!roomAvailable()) { 	
			try {
				wait(); 
			}
			catch (InterruptedException e) { 
				 System.out.println("put");
			}
		}
		content[nextIn] = data;
		
		ins++;
		occupied++;
		if(nextIn == size - 1){
			nextIn = 0;
		}
		else{
			nextIn++;
		}
		notifyAll();

	} 
	
	
	public synchronized int take () {

		while(!dataAvailable()){	//wait till something appears in the buffer
			try{ 	
				wait(); 
			}
			catch (InterruptedException e) { 
				 System.out.println("take");
			}
		}

		int removed = content[nextOut];
		occupied--;
		outs++;

		if(nextOut == size - 1){
			nextOut = 0;
		}
		else{
			nextOut++;
		}
		notifyAll();

		return removed;
	}


}

class Producer extends Thread {

  	private Buffer buffer;
  	private boolean running;

  	public Producer(Buffer b) {
		buffer = b;
		running = true;
	}
    
	public synchronized void run() {
		try {
			while(running){

				buffer.put((int)(Math.random()*100));

				//sleep up to 3 seconds
				Thread.sleep((int)(Math.random() * 3) * 1000);

			}
		} catch (InterruptedException e) {
			 System.out.println("producer error");
		}
	}

	public void terminate() {
		running = false;
	}
}

class Consumer extends Thread {

	private Buffer buffer;
	private boolean running;
  	
	public Consumer (Buffer b){
		buffer = b;
		running = true;	
	}

	public synchronized void run() {
		//int item;

		try {
			while(running){

				buffer.take();

				//sleep up to 2 seconds
				Thread.sleep((int)(Math.random() * 2) * 1000);

			}

		}  catch (InterruptedException e) {
			 System.out.println("consumer error");
		}	
	}

	public void terminate() {
		running = false;
	}

}

class Watcher extends Thread {

	private Buffer buffer;
	private boolean running;

	Watcher(Buffer buffer0){
		buffer = buffer0;
		running = true;
	}

	public synchronized void run(){
		try{
			while(running){

				buffer.getStatus();

				//sleep for 1 second
				Thread.sleep( 1 * 1000);
			}
		}
		catch(InterruptedException e){
			System.out.println("watcher error");
		}

	}

	public void terminate() {
		running = false;
	}

}

class BoundedBuffer{

	private static void initializeThread(Thread t) {
		t.start();
	}

	private static void terminateThread(Thread t) {
		try{
			t.interrupt();
			t.join();
		}  catch (InterruptedException e) {}
	}

	public static void main (String [] args){

		//int size = Integer.parseInt(args[0]);
		int size = 10;
		Buffer buffer = new Buffer(size);

		Producer prod = new Producer(buffer);
		Consumer cons = new Consumer(buffer);
		Watcher wat = new Watcher(buffer);

		try {
			
			prod.start(); cons.start(); wat.start();

			Thread.sleep(60 * 1000);
			
			prod.terminate(); cons.terminate(); wat.terminate();

			terminateThread(prod);
			terminateThread(cons);
			terminateThread(wat);

		    System.out.println("end");
			

		}  catch (InterruptedException e) {
			 System.out.println("main error");
		}
		
	}

}

