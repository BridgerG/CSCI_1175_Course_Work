package chapter33_09;

import javafx.application.Application;
import static javafx.application.Application.launch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  
  DataInputStream input;
  DataOutputStream output;
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    taClient.setOnKeyPressed(e -> {
    	
        if (e.getCode() == KeyCode.ENTER) {
        	
          try {
        	  
            String message = taClient.getText().trim();
            
            if (!message.isEmpty()) {
              output.writeUTF(message);
              taServer.appendText("C: " + message + "\n");
              taClient.clear();
            }
            
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          
          e.consume(); // without this a new line is created in taClient
          
        }
        
      });
    
    new Thread(() -> {
        try {
          Thread.sleep(2000);
          Socket socket = new Socket("localhost", 10001);

          input = new DataInputStream(socket.getInputStream());
          output = new DataOutputStream(socket.getOutputStream());

          while (true) {
        	  
            taServer.appendText("S: " + input.readUTF() + "\n");
            
          }
          
        } catch (Exception ex) { //fix
          ex.printStackTrace();
        }
        
      }).start();


    // To complete later
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
