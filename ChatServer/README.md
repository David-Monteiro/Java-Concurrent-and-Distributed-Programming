# ChatServer 
DCU CA216 assign #2

	Developers:
	  David Furtado Monteiro

	Description:
	  Program making use of a created unbounded buffer shared across multiple threads. The program use a reader writer problem as solution that gives priority to writers.
	  Three class extending threads(Writer, Reader and ServerThread).
	  One as a form of data structure(Buffer).
	  One to manage the threads and run the program(main).
	  Writer class allows chat users to share/sen their messages. This class waits for clients on the server to send messages. Once it receives the message, the class stores the message on our unbounded buffer.
	  Reader class allows the clients to receive messages from other clients. The class is always waiting for a message to be added to the buffer. If a message is found, it sends the message to all connected clients.
	  Watcher class ensures the writer preference patter is done correctly. Easy to manage and eases debugging
	  Buffer class has some synchronizedd functions to ensure mutual exclusion. This class stores content being sent from client to client.



	Run:
		1.To Compile
			javac ChatServer.java
			javac applet/ChatApplet.java
		3.To run
			java ChatServer
			applet/appletviewer -J-Djava.security.policy=all.policy chat1.html
			applet/appletviewer -J-Djava.security.policy=all.policy chat2.html
			applet/appletviewer -J-Djava.security.policy=all.policy chat3.html

		Expected:
			Perfect synchronised conversation between numerous chat users

		Result:
			Synchronised conversation between X chat users with delay of X messages


