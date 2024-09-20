package com.example.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelScreen {
	private Stage stage;
	private String title;
	private LevelController controller;
	private Scene scene;
	

	
	public LevelScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Level Screen";
		this.controller = new LevelController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("levelselect.fxml"));
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
	
	public LevelController getController() {
		return controller;
	}



}
