	Developers:
	  David Furtado Monteiro

	Description:
		Client-server application with Java Web Services JAX-WS(SOAP).
		Server manages a list of properties and provides the server with numeroes functionalities.
		Functionalities are:
		 - Provide a list of the properties it manages and their details in price ranges.
		 - Put up a property listing for sale within a certain time frame.
		 - Respond to a client's bid and
		 - Check the viewing times of a property


	Run:
		**Note - Go from step #1 to #5 
			* (all steps from directory 10364119)
			* Run step #1 and #2 in first terminal(or cmd) 
			* In a new terminal run step #3 while server is running in the first terminal
			* Input step #4 and #5 in the terminal while server is running in the first terminal
		

		#1 - To compile the server:
			javac "Estate Agency Server/src/agencymanagement/domain/"*.java -d "Estate Agency Server\bin" -classpath "Estate Agency Server\bin"
			javac "Estate Agency Server/src/agencymanagement/auction/"*.java -d "Estate Agency Server\bin" -classpath "Estate Agency Server\bin"
			javac "Estate Agency Server/src/agencymanagement/services/"*.java -d "Estate Agency Server\bin" -classpath "Estate Agency Server\bin"


		#2 - To run the server
			java -classpath "Estate Agency Server\bin" agencymanagement.services.AgencyPublisher


		#3 - To import from server
			wsimport -keep -verbose -s "Estate Agency Client\src" -d "Estate Agency Client\bin" http://127.0.0.1:8080/EstateAgency?wsdl


		#4 - To compile client
			javac "Estate Agency Client/src/agencymanagement/services/"*.java -d "Estate Agency Client\bin" -classpath "Estate Agency Client\bin"
			javac "Estate Agency Client/src/"Main.java -d "Estate Agency Client\bin" -classpath "Estate Agency Client\bin"


		#5 - To run client
			java -classpath "Estate Agency Client\bin" Main


