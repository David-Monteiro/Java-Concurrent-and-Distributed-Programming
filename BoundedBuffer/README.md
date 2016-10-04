# Bounded Buffer 
DCU ca216 assign #1

Developers:
	  David Furtado Monteiro

	Description:
	  Program making use of a created bounded buffer shared across multiple threads.
	  Three class extending threads(Producer, Consumer and Watcher).
	  One as a form of data structure(Buffer).
	  One to manage the threads and run the program(main).
	  Producer class sleeps for a maximum time of 3 seconds after adding data to the buffer.
	  Consumer class sleeps for a maximum time of 3 seconds after taking data from the buffer.
	  Watcher class wakes up every second to print the buffer details.
	  Buffer class has some synchronizedd functions which will sleep before entering the 
	    critical section. They will sleep until some condition has been met. After running
	    the code in the critical section they will all threads waking them up so they 
	    can re-verify if their waiting condition has been met.

	Run:
	  1.To Compile
		javac BoundedBuffer.java
	  3.To run
		java BoundedBuffer
			Expected:
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
			  Delta = 0 Occupied = 0
