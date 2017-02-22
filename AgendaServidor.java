import java.net.*;
import java.util.ArrayList;
import java.io.*;

class AgendaServidor {
public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        String newContact[] = new String[4];
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        StringBuilder result = new StringBuilder();
        
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.length() > 1) {
                	newContact = inputLine.split(",");
                	if(newContact.length != 4) {
                		out.println("Error. Not enough information is provided or bad syntax encountered, please add the full data as the program indicates");
                	} else {
	                	contacts.add(new Contact(newContact[0], newContact[1], newContact[2], newContact[3]));
	                	out.println("Contact added");
                	}
                } else {
                	result = new StringBuilder();
                	for(Contact c : contacts) {
                		result.append(c.name + ", " + c.birthDate + ", " + c.email + ", " + c.phoneNumber + ";    ");
                	}
                	out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

class Contact {
	String name;
	String birthDate;
	String email;
	String phoneNumber;
	
	public Contact(String name, String birthDate, String email, String phoneNumber) {
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
}