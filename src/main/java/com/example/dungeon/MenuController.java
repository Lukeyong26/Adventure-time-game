package com.example.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * A controller to control the interaction with the menu screen.
 * @author Amy
 *
 */
public class MenuController {
	
	private DungeonScreen dungeonScreen;
	private MenuScreen menuScreen;
	private WinScreen winScreen;
	private LostScreen lostScreen;
	private LevelScreen levelScreen;
	private ManualScreen manualScreen;
	
	@FXML
	private Button StartButton;
	
	@FXML
	private Button ExitButton;
	
	@FXML
	private Button LevelButton;
	
	@FXML
	private Button ManualButton;

	
	public void setMenuScreen(MenuScreen ms) {
		this.menuScreen = ms;
	}
	
	@FXML
	private void handleStartButton(ActionEvent event) throws IOException {
		dungeonScreen.start();
	    dungeonScreen.getDungeonController().setMenuScreen(this.menuScreen);
		dungeonScreen.getDungeonController().setWinScreen(winScreen);
		dungeonScreen.getDungeonController().setLostScreen(lostScreen);

	}
	
	@FXML
	private void handleExitButton(ActionEvent event) {
		System.exit(0);
	}
	
	
	@FXML
	private void openManual(ActionEvent event) {
		manualScreen.start();
	}
	
	
	@FXML
	private void handleLevelButton(ActionEvent event) {
		levelScreen.getController().setDungeonScreen(dungeonScreen);
		levelScreen.getController().setLostScreen(lostScreen);
		levelScreen.getController().setWinScreen(winScreen);
		levelScreen.getController().setMenuScreen(menuScreen);
		levelScreen.start();		
	}


	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setWinScreen(WinScreen winScreen) {
		this.winScreen = winScreen;
	}
	

	public void setLostScreen(LostScreen lostScreen) {
		this.lostScreen = lostScreen;
	}
	
	public void setLevelScreen(LevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}
	
	public void setManualScreen(ManualScreen manualScreen) {
		this.manualScreen = manualScreen;
	}
	

}
