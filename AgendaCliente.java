import java.io.*;
import java.net.*;

class AgendaCliente {
	 public static void main(String[] args) throws IOException {
	        
	        if (args.length != 2) {
	            System.err.println("Uso: java AgendaCliente <nombre de servidor> <numero de puerto>");
	            System.exit(1);
	        }

	        String hostName = args[0];
	        int portNumber = Integer.parseInt(args[1]);

	        try (
	            Socket echoSocket = new Socket(hostName, portNumber);
	            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
	            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
	        ) {
		    System.out.println("For adding a contact enter name, birthday, email, phone number in that order. For see all contacts just enter a single caracter");
	            String userInput;
	            while ((userInput = stdIn.readLine()) != null) {
	            	if(userInput.length() > 1) {
	            		out.println(userInput);
	    				System.out.println(in.readLine());
	            	} else {
	            		out.println(userInput);
	            		System.out.println(in.readLine());
	            	}
	            	
	                //out.println(userInput);
	                //System.out.println("echo: " + in.readLine());
	            }
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + hostName);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
	            System.exit(1);
	        } 
	    }

}
