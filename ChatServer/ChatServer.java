import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;




// Main class
public class ChatServer{

    public static void main(String[] args) throws IOException {

	    boolean running = true;
	    
	    
	    new ServerThread().start();
	    
	    while(running){
	    	running = System.console().readLine().equals("q");
	    }

       
    }

}


// Server Thread
class ServerThread extends Thread {

    // The server socket and connection setup
    private final int portNumber = 7777;
    private Socket socket = null;
    private ServerSocket serverSocket = null;

   // private ExecutorService threadExecutor;
    private List<ClientThread> clients;

    private ClientThread client = null;
    
    private Buffer buffer;
    private Watcher watcher;

    private boolean running;

    
    public ServerThread() throws IOException {
    	
    	try {

    	    // Listen on defined port
    		serverSocket = new ServerSocket( portNumber );

            } catch (IOException e) {

                System.err.println("Could not listen on port: " + portNumber);
                System.exit(-1);

            }

    	
    	//threadExecuter = newCachedThreadPool();
    	clients = new ArrayList<>();
    	
    	watcher = new Watcher();

    	running = false;

    }

	
	// Handle the connection
	public void run() {

		running = true;

		while(running){

			try {

				socket = serverSocket.accept();
				
				client = new ClientThread(socket, buffer, watcher);
				
				client.start();
				clients.add(client);
				client.setName();
				
				 /*
			     * Several things going on with this lines of code:
			     * 1. Accept a connection (stored as Socket in variable socket)
			     * 2. Create a new class of type ClientThread
			     * 3. Start two new threads with method start, one for reader and the other for writer
			     * 4. Add new client to a list to keep track of connected clients
			     * 5. Set the Nickname of the new client
			     */


			} catch (IOException e) { e.printStackTrace(); }

		}

	}

}


//Not really a thread but initiates the two threads that represent the client
class ClientThread{

	public Socket clientSocket;

	public String nickname;
	private Buffer buffer;

	private Watcher watcher;
	private Writer writer;
	private Reader reader;
	

	ClientThread(Socket s, Buffer b, Watcher w) throws IOException {
		
		clientSocket = s;
		buffer = b;


		watcher = w;
		writer = new Writer(clientSocket, watcher, buffer);
		reader = new Reader(clientSocket, watcher, buffer);

	}

	public void setName(){
		
		nickname = writer.getNickName();
		
	}

	public void start() {

		writer.start();
		reader.start();

	}

	public void close() {

		writer.terminate();
		reader.terminate();

		try {
			writer.join();
			reader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class Buffer{

	private List<String> content;

	Buffer(){
		content	 = new ArrayList<>();
	}

	public boolean isEmpty(){

		return content.size() == 0;

	}


	// Adds a message to the buffer.
	public synchronized void write(String message) {
		content.add(message);

		notify();
    }

	// Reads a message from the buffer.
	public synchronized String read() {
		String message = "";

		if(content.size() > 0 )
			message = content.get(0);
		notify();

		return message;
    }

    // Removes head message of the buffer.
    public synchronized boolean remove(){
    	if(content.size() == 0 )
    		return false;

    	content.remove(0);
    	return true;
    }

    public void justWait() {
    	try {
			wait(); 
		}
		catch (InterruptedException e) { 
			 System.out.println("put");
		}
    }

}

class Watcher {

	private int readers;			//number of readers
	private int waitingWriters;		//number of waiting writers
	private boolean readersTurn;	//is the readers turn
	private boolean writing; 		//is it writing

	Watcher(){

		readers = 0;
		waitingWriters = 0;
		readersTurn = false;
		writing = false;

	}

	public void startRead(Buffer b) {

		while ((!readersTurn && waitingWriters > 0) 
			|| writing || b == null){

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		readers++;
	}

	public boolean endRead() {

		readers--;

		if(readers == 0)
			readersTurn = false;

		notifyAll();
		if (readersTurn) return false;
		return true;
	}

	public void startWrite() {

		waitingWriters++;		
		while (readers > 0 || writing){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitingWriters--;

		writing = true;
	} 

	public void endWrite() {

		writing = false;

		if(waitingWriters == 0)
			readersTurn = true;

		notifyAll();
	} 

}

class Writer extends Thread {

  	private Buffer buffer;
  	private Watcher watcher;
  	
  	private String nickname;
  	
  	private boolean running;


	private BufferedReader in = null;

	public Writer(Socket s, Watcher w, Buffer b) throws IOException {
		buffer = b;
		watcher = w;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
	}
    
	public synchronized void run() {

		running = true;
		String message = "";

		try {

			setNickName();

			greetings();

			while(running){

				watcher.startWrite();

				if((message = in.readLine()) != null){
					buffer.write(nickname + ": " + message);
				}
				
				watcher.endWrite();

			}
		} catch (IOException e) {
			 System.out.println("writer");
		}
	}

	public String getNickName(){
		
		return nickname;

	}
	
	private void setNickName() throws IOException {
		
		while((nickname = in.readLine()) != null);
		
	}

	private void greetings() throws IOException{

		watcher.startWrite();

		buffer.write(nickname + " has joined the chatroom..." );
				
		watcher.endWrite();	

	}

	public void terminate() {
		running = false;
	}

}

class Reader extends Thread {

	private Buffer buffer;
	private Watcher watcher;
	private boolean running;

  	private PrintWriter out = null;
  	
	public Reader (Socket s, Watcher w, Buffer b) throws IOException{
		buffer = b;
		watcher = w;

		out = new PrintWriter(s.getOutputStream(), true);
	}

	public synchronized void run() {
		
		running = true;

		while(running){

			watcher.startRead(buffer);

			if(!buffer.isEmpty()) out.println(buffer.read());

			if( watcher.endRead() )
				buffer.remove();

		}	
	}



	public void terminate() {
		running = false;
	}

}

