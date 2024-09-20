package com.example.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * A controller for the game end screen.
 * @author Amy
 *
 */
public class EndController {
	
	private MenuScreen menuScreen;
	
	@FXML
	private Button StartButton;
	
	@FXML
	private Button ExitButton;
	
	public void setMenuScreen(MenuScreen menuScreen) {
    	this.menuScreen = menuScreen;
    }
    
    @FXML
    public void handleMenuButton(ActionEvent event) {
    	menuScreen.start();
    }
	
	@FXML
	private void handleExitButton(ActionEvent event) {
		System.exit(0);
	}
	
}
