package com.example.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A screen indicating the player has lost the dungeon game level
 * @author Amy
 *
 */
public class LostScreen {
	private Stage stage;
	private String title;
	private EndController controller;
	private Scene scene;
	
	public LostScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Lost Screen";
		this.controller = new EndController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("lost.fxml"));
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
