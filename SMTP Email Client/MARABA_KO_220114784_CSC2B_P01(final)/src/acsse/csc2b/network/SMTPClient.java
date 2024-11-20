/**
 * 
 */
package acsse.csc2b.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import acsse.csc2b.gui.ClientPane;

/**
 * @author Keamogetswe
 * @version PO1
 * This class is responsible for initiating the SMTP client
 */
public class SMTPClient {
	
	
	/**
	 * @author Keamogetswe
	 * @param host
	 * @param recep
	 * @param sender
	 * @param content
	 * @param port
	 * this method is responsible for handling the SMTP session logic
	 */
public static void sendEmail(String host, String recep, String sender, String content, int port){
		

		Socket scSMTP = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		String response = null;
		String message = null;
		
	
		
		
		try {
			 scSMTP = new Socket(host,port);
			System.out.println("Connected successfully!");
			
			writer = new PrintWriter(scSMTP.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(scSMTP.getInputStream()));
			
			//Initiate SMTP session
		
			//Checking the response from server
			response = reader.readLine();
			System.out.println(response);
			
			//Sending HELO command
			message = new String("HELO "+host);
			writer.println(message);
			writer.flush();
			System.out.println(reader.readLine());
			
			//Sending MAIL FROM command
			message = new String("MAIL FROM:<"+sender+">");
			writer.println(message);
			writer.flush();
			System.out.println(reader.readLine());
			
			
			//Sending RCPT TO command
			message = new String("RCPT TO:<"+recep+">");
			writer.println(message);
			writer.flush();
			System.out.println(reader.readLine());
			
			//Sending DATA command
			message = new String("DATA");
			writer.println(message);
			writer.flush();
			System.out.println(reader.readLine());
	        
			//Sending email content
        writer.println(content);
        writer.println(".");  // End of message body
        System.out.println(reader.readLine());

        // QUITING the session
        message = new String("QUIT");
        writer.println(message);
        System.out.println(reader.readLine());
	
			writer.flush();
			
			reader.close();
			writer.close();
			
			
		} catch (ConnectException ce) {
			ClientPane.displayAlert("Could not connect to port", "Connect Exception");
			System.err.print("Connection to port/socket failed");
			
		} catch(UnknownHostException uhe) {
			
			ClientPane.displayAlert("Retype the host", "UnknownHost Exception");
			System.err.print("Could not find host");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ClientPane.displayAlert("Fatal Error", "Could not connect");
			System.err.print("Something went wrong");
		}catch(Exception e) {
			
			ClientPane.displayAlert("Fatal Error", "Could not connect");
			System.err.print("Something went wrong");
			
		}finally {
			
			
			try {
				if(scSMTP != null) {
					scSMTP.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
		

	}
	

}
