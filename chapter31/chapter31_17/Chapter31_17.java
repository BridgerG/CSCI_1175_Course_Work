package chapter31_17;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Chapter31_17 extends Application{

	private TextField tfInvestment = new TextField();
	private TextField tfYear = new TextField();
	private TextField tfAIR = new TextField();
	private TextField tfFinalValue = new TextField();
	  
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	    MenuBar menuBar = new MenuBar();    
	    
	    Menu menuOperation = new Menu("Operation");
	    menuBar.getMenus().addAll(menuOperation);
	    
	    MenuItem calc = new MenuItem("Calculate");
	    MenuItem close = new MenuItem("Close");
	    menuOperation.getItems().addAll(calc, close);
	    
	    HBox hBox1 = new HBox(5);
	    tfInvestment.setPrefColumnCount(14);
	    hBox1.getChildren().addAll(new Label("  Investment Amount: "), tfInvestment);
	    hBox1.setAlignment(Pos.CENTER_LEFT);
	            
	    HBox hBox2 = new HBox(5);
	    tfYear.setPrefColumnCount(14);
	    hBox2.getChildren().addAll(new Label("  Number of Years:     "), tfYear);
	    hBox2.setAlignment(Pos.CENTER_LEFT);
	    
	    HBox hBox3 = new HBox(5);
	    tfAIR.setPrefColumnCount(14);
	    hBox3.getChildren().addAll(new Label("  Annual Intrest Rate: "), tfAIR);
	    hBox3.setAlignment(Pos.CENTER_LEFT);
	    
	    HBox hBox4 = new HBox(5);
	    tfFinalValue.setPrefColumnCount(14);
	    hBox4.getChildren().addAll(new Label("  Future Value:           "), tfFinalValue);
	    hBox4.setAlignment(Pos.CENTER_LEFT);
	    
	    HBox hBox5 = new HBox(5);
	    Button btn = new Button("Calculate");
	    hBox5.getChildren().add(btn);
	    hBox5.setAlignment(Pos.CENTER_RIGHT);
	    btn.setOnAction(e -> {
	    	Calculate();
	    });
	    
	    VBox vBox = new VBox(10);
	    vBox.getChildren().addAll(menuBar, hBox1, hBox2, hBox3, hBox4, hBox5);
	    Scene scene = new Scene(vBox, 300, 250);  
		primaryStage.setTitle("MenuDemo"); // Set the window title
	    primaryStage.setScene(scene); // Place the scene in the window
	    primaryStage.show(); // Display the window
	    
	    
	    calc.setOnAction(e -> Calculate());
	    close.setOnAction(e -> System.exit(0));
	    
	  
	}
	
	public void Calculate() {
		
		//futureValue = investmentAmount × (1 + monthlyInterestRate)years×12
		double initail = Double.parseDouble(tfInvestment.getText());
	    double year = Double.parseDouble(tfYear.getText());
	    double aIR = Double.parseDouble(tfAIR.getText());
	    
	    String str = "$" + String.format("%.2f", (initail * Math.pow((1 + (aIR / 12) / 100), (year * 12))));
		tfFinalValue.setText(str);
	}

}
