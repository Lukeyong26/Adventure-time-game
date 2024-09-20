package com.example.dungeon;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A screen displaying the game rules.
 * @author Amy
 *
 */
public class ManualScreen {
	private Stage stage;
	private String title;
	private EndController controller;
	private Scene scene;
	
	public ManualScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Manual Screen";
		this.controller = new EndController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("manual.fxml"));
		loader.setController(this.controller);
		
		Parent root = loader.load();
		root.requestFocus();
		this.scene = new Scene(root, 600, 400);
		
	}
	
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public EndController getEndController() {
		return controller;
	}
}
