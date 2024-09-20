package com.example.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A screen indicating the player has won the dungeon game level
 * @author Amy
 *
 */
public class WinScreen {
	
	private Stage stage;
	private String title;
	private EndController controller;
	private Scene scene;
	
	public WinScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Win Screen";
		this.controller = new EndController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("win.fxml"));
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
