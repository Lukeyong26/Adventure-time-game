package com.example.dungeon;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the dungeon game screen.
 * The dungeon screen is controlled using the dungeon controller.
 * @author Amy
 *
 */
public class DungeonScreen {
	private Stage stage;
	private String title;
	private Scene scene;
	private DungeonController controller;
	public DungeonScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Dungeon";
		
		
	}
	
	public void start() throws IOException {

		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("marking.json");
		controller = dungeonLoader.loadController();
	    
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("trial.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		root.requestFocus();
		this.scene = new Scene(root);
		
		stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void start(String levelFile) throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelFile);
		controller = dungeonLoader.loadController();
	    
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("trial.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		root.requestFocus();
		this.scene = new Scene(root);
		
		stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}

	public DungeonController getDungeonController() {
		return this.controller;
	}
}
