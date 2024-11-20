/**
 * 
 */
package acsse.csc2b.gui;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import acsse.csc2b.network.SMTPClient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Keamogetswe
 * @version P01
 * @see SMTPClient
 */
public class ClientPane extends GridPane{
	
	
	
	public ClientPane() {
		
		GridPane gp = new GridPane();

	    // Create Labels and TextFields
	    Label hostLabel = new Label("SMTP Host:");
	    TextField hostField = new TextField();
	    gp.add(hostLabel, 0, 0);
	    gp.add(hostField, 1, 0);

	    Label portLabel = new Label("Port:");
	    TextField portField = new TextField();
	    gp.add(portLabel, 0, 1);
	    gp.add(portField, 1, 1);

	    Label senderLabel = new Label("Sender:");
	    TextField senderField = new TextField("sender@csc2b.uj.ac.za.");
	    gp.add(senderLabel, 0, 2);
	    gp.add(senderField, 1, 2);
	    
	    
	    Label recipientLabel = new Label("Recipient:");
	    TextField recipientField = new TextField("recipient@csc2b.uj.ac.za.");
	    gp.add(recipientLabel, 0, 3);
	    gp.add(recipientField, 1, 3);

	    Label messageLabel = new Label("Message:");
	    TextArea messageArea = new TextArea();
	    gp.add(messageLabel, 0, 4);
	    gp.add(messageArea, 1, 4);

	    Button sendButton = new Button("Send");
	    
	    gp.add(sendButton, 1, 5);
	    
	 // Set gap between rows and columns
	    gp.setAlignment(Pos.CENTER);
	    gp.setPadding(new Insets(10));
	    gp.setHgap(10);
	    gp.setVgap(10);

	   
	    

	    // Event Handling for send button
	    sendButton.setOnAction(e -> {
	        
	      
	        try {
	        	String host = hostField.getText();
	        	int port = Integer.parseInt(portField.getText());
	        	String sender = senderField.getText();
		        String recipient = recipientField.getText();
		        String message = messageArea.getText();
		        
		        SMTPClient.sendEmail(host, sender, recipient, message, port);  
		        displayAlert("Successfuly sent","Email Sent");
	        }catch(NumberFormatException nfe) {
	        	
	        	displayAlert("Make sure your port is actually a number(integer)", "Number format incorrect");
				System.err.print("Something went wrong");
	        	
	        }
	        
	        
	    });
		
		
	    this.getChildren().add(gp);
		
	}

	
	

/**
 * @author Keamogetswe
 * @param msg
 * @param title
 * This method is for any display logic
 */
public static void displayAlert(String msg, String title) {
	
	
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    
    alert.setContentText(msg);
    alert.setHeaderText(null);
    alert.showAndWait();
}
	
	

	
		
		
	
		
		
		
		
		
		
		
		
	
	
	
	
	
}
