package com.example.dungeon;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.*;
import javafx.stage.*;

/**
 * A screen displaying the menu options
 * @author Amy
 *
 */
public class MenuScreen {

	private Stage stage;
	private String title;
	private MenuController controller;
	private Scene scene;
	
	public MenuScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Start Screen";
		this.controller = new MenuController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
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
	
	public MenuController getController() {
		return controller;
	}
}
