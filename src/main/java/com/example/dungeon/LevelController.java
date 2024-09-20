package com.example.dungeon;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelController {

	private DungeonScreen dungeonScreen;
	private WinScreen winScreen;
	private LostScreen lostScreen;
	private MenuScreen menuScreen;
	private ArrayList<String> levelFiles;
	
	@FXML
	private Button StartButton;
	
	public LevelController() {
		this.levelFiles = new ArrayList<String>();
		this.levelFiles.add("maze.json");
		this.levelFiles.add("boulders.json");
		this.levelFiles.add("advanced.json");
		this.levelFiles.add("testGoals.json");
		this.levelFiles.add("testEnemiess.json");
		this.levelFiles.add("PacMan.json");
	}
	
	@FXML
	private void handleStartButton(ActionEvent event) throws IOException {
		Button level = (Button) event.getSource();
		Integer index = Integer.parseInt(level.getText());
		dungeonScreen.start(this.levelFiles.get(index.intValue()-1));
	    dungeonScreen.getDungeonController().setMenuScreen(this.menuScreen);
		dungeonScreen.getDungeonController().setWinScreen(winScreen);
		dungeonScreen.getDungeonController().setLostScreen(lostScreen);

	}
	
	@FXML
    public void handleMenuButton(ActionEvent event) {
    	menuScreen.start();
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


	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}	
	
}
