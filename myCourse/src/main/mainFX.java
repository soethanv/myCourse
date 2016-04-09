package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class mainFX extends Application {
	
	Stage stage;
	Scene scene, scene2, scene3;
	
	Label listLabel, graphLabel;
	Button listButton, graphButton;
	Button listProceedButton, listBackButton;
	
	ComboBox<String> comboBox;
	ListView<String> listView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("App Name");
		
		listButton = new Button("List View");
		listButton.setOnAction(e -> switchScene(2));
		
		graphButton = new Button("Graph View");
		graphButton.setOnAction(e -> switchScene(3));
		
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll("Freshman", "Sophmore", "Junior", "Senior");
		comboBox.setPromptText("Select Classification");
		
		listView = new ListView<>();
		listView.getItems().addAll("cs1", "cs2", "cs3");
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		listProceedButton = new Button("Proceed");
		listBackButton = new Button("Back");
		listBackButton.setOnAction(e -> switchScene(1));
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(listButton, graphButton, comboBox);
		layout.setAlignment(Pos.CENTER);
		
		VBox layout2 = new VBox(4);
		layout2.setPadding(new Insets(20, 20, 20, 20));
		layout2.getChildren().addAll(listView, listProceedButton, listBackButton);
		
		scene = new Scene(layout, 600, 600);
		
		scene2 = new Scene(layout2, 600, 600);
		
		stage.setScene(scene);
		
		
		
		stage.show();	
	}
	
	private void switchScene(int n) {
		if (n == 1) {
			stage.setScene(scene);
		}
		else if (n == 2) {
			stage.setScene(scene2);
		}
		else if (n == 3) {
			stage.setScene(scene3);
		}
	} 

}
