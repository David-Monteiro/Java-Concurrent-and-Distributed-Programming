import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// One thread per connection, this is it
class ServerThread extends Thread {

    // The socket passed from the creator
    private Socket socket = null;
    
    public ServerThread(Socket socket) {

    	this.socket = socket;




    }

	

    // Handle the connection
    public void run() {

		String socketOutput = null;		//this is what is going to be flushed to the socket

    	try {
	    //create a buffer to read from the client
	   

	    // Attach a printer to the socket's output stream
	    socketOut = new PrintWriter(socket.getOutputStream(), true);


	    //read from the client
	    socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	   
	    socketOutput = socketIn.readLine();

		while(socketOutput != null){

	    // Send a message to the client
	    socketOut.println(socketOutput);
	    socketOutput = socketIn.readLine();

		}
	    // Close things
	    socketOut.close();
	    socket.close();

		} catch (IOException e) {

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

// The server
public class ChatServer{

    public static void main(String[] args) throws IOException {

	// The server socket, connections arrive here
        ServerSocket serverSocket = null;

        try {

	    // Listen on on port 7777
            serverSocket = new ServerSocket(7777);

        } catch (IOException e) {

            System.err.println("Could not listen on port: 7777");
            System.exit(-1);

        }

	// Loop forever
        while (true) {

	    /*
	     * Several things going on with this line of code:
	     * 1. Accept a connection (returns a new socket)
	     * 2. Create a new thread of type ServerThread
	     * 3. Call start on the new thread
	     */
	    new ServerThread(serverSocket.accept()).start();

        serverSocket.close();
        }
    }
}

class ClientThread{

	public Socket clientSocket;

	private String nickname;
	private Buffer buffer;

	private Watcher watcher;
	private Writer writer;
	private Reader reader;
	

	ClientThread(Socket s, String n, Buffer b) throws IOException {

		nickname = n;
		
		clientSocket = s;
		buffer = b;


		watcher = new Watcher();
		writer = new Writer(clientSocket, watcher, buffer);
		reader = new Reader(clientSocket, watcher, buffer);

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
			|| writing || b.isEmpty()){

			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		readers++;
	}

	public void endRead() {

		readers--;

		if(readers == 0)
			readersTurn = false;

		notifyAll();
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
			while(running){

				watcher.startWrite();

				if((message = in.readLine()) != null){
					buffer.write(message);
				}
				
				watcher.endWrite();

			}
		} catch (IOException e) {
			 System.out.println("writer");
		}
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

			out.println(buffer.read());

			watcher.endRead();

		}	
	}



	public void terminate() {
		running = false;
	}

}

