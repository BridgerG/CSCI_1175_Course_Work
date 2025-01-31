package chapter31_21;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneDemo extends Application {
	
	RadioButton top = new RadioButton("Top");
	RadioButton bottom = new RadioButton("Bottom");
	RadioButton right = new RadioButton("Right");
	RadioButton left = new RadioButton("Left");
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) { 
		
		TabPane tabPane = new TabPane();
		Tab tab1 = new Tab("Line");
		StackPane pane1 = new StackPane();
		pane1.getChildren().add(new Line(10, 10, 80, 80));
		tab1.setContent(pane1);
		Tab tab2 = new Tab("Rectangle");
		tab2.setContent(new Rectangle(10, 10, 200, 200));
		Tab tab3 = new Tab("Circle");
		tab3.setContent(new Circle(50, 50, 20));    
		Tab tab4 = new Tab("Ellipse");
		tab4.setContent(new Ellipse(10, 10, 100, 80));
		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
		
		ToggleGroup tg = new ToggleGroup();
		top.setToggleGroup(tg);
		bottom.setToggleGroup(tg);
		right.setToggleGroup(tg);
		left.setToggleGroup(tg);
		HBox h1 = new HBox(15);
		h1.getChildren().addAll(top, bottom, right, left);
		
		VBox v1 = new VBox(20);
		v1.getChildren().addAll(tabPane, h1);

		Scene scene = new Scene(v1);  
		primaryStage.setTitle("DisplayFigure"); // Set the window title
		primaryStage.setScene(scene); // Place the scene in the window
		primaryStage.show(); // Display the window
		
		top.setOnAction(e -> tabPane.setRotate(perform('T')));
		bottom.setOnAction(e -> tabPane.setRotate(perform('B')));
		right.setOnAction(e -> tabPane.setRotate(perform('R')));
		left.setOnAction(e -> tabPane.setRotate(perform('L')));

	}
	
	private int perform(char operator) {
		    
		switch (operator) {
		case 'T': return 0;
		case 'B': return 180;
		case 'R': return 90;
		case 'L': return 270;
	    }
		
		return 0;
		    
	}

	/**
   	* The main method is only needed for the IDE with limited
   	* JavaFX support. Not needed for running from the command line.
   	* line.
   	*/
	public static void main(String[] args) {
		launch(args);
	}
}