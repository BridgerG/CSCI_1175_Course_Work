package chapter33_01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
    
    new Thread (() -> {
    	
    	try {
    		
    		Platform.runLater(() -> ta.appendText("Server started at " + new Date() + "\n"));
    		int port = 10000;
    		ServerSocket server = new ServerSocket(port);
    		Socket socket = server.accept();
    		DataInputStream input = new DataInputStream(socket.getInputStream());
    		DataOutputStream output = new DataOutputStream(socket.getOutputStream());

		
    		Platform.runLater(() -> ta.appendText("Client connected at " + new Date() + "\n"));
    		
    		while (true) {
			
    			double annualInterestRate = input.readDouble();
    			int numberOfYears = input.readInt();
    			double loanAmount = input.readDouble();
    			
    			Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

    			double monthlyPayment = loan.getMonthlyPayment();
    			double totalPayment = loan.getTotalPayment();
	          
    			output.writeDouble(monthlyPayment);
    			output.writeDouble(loan.getTotalPayment());
    			output.flush();

    			Platform.runLater(() -> {
    				ta.appendText("Annual Interest Rate: " + annualInterestRate + "\n"
    						+ "Number of Years: " + numberOfYears + "\n"
    						+ "Loan Amount: $" + loanAmount + "\n"
    						+ "Monthly Payment: $" + monthlyPayment + "\n"
    						+ "Total Payment: $" + totalPayment + "\n");
    			});

    		}
    		
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
    }).start();
    	
   
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
