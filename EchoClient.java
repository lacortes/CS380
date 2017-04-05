/**
*	Modified
*/


import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
    	String info; // Store info from server
    	Scanner kb = new Scanner(System.in);
    	System.out.println("Connecting ... ");

        try (Socket socket = new Socket("localhost", 2222)) {
        	InputStream is = socket.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
        	System.out.println(br.readLine());
           	
           	// To print out to output stream. send to server
           	PrintStream outStream = new PrintStream(socket.getOutputStream(), true, "UTF-8"); 
            
        	while(!socket.isClosed()) {
            	System.out.print("Client> ");
            	info = kb.nextLine();

            	outStream.println(info); // Send to server

            	info = br.readLine(); // read from server
            	System.out.println("Server> "+info);
            	
            	if (info.equals("exit")) { 
            		socket.close();
            		continue;
            	}
        	}
        }
    }
}















