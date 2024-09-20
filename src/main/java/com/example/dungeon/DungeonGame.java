package com.example.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * This class launches the game app starting from the menu screen.
 * @author Amy
 *
 */
public class DungeonGame extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuScreen menuScreen = new MenuScreen(primaryStage);
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		WinScreen winScreen = new WinScreen(primaryStage);
		LostScreen lostScreen = new LostScreen(primaryStage);
		LevelScreen levelScreen = new LevelScreen(primaryStage);
		ManualScreen manualScreen = new ManualScreen(primaryStage);

		menuScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setDungeonScreen(dungeonScreen);
		menuScreen.getController().setWinScreen(winScreen);
		menuScreen.getController().setLostScreen(lostScreen);
		menuScreen.getController().setLevelScreen(levelScreen);
		menuScreen.getController().setManualScreen(manualScreen);
		
		winScreen.getEndController().setMenuScreen(menuScreen);
		lostScreen.getEndController().setMenuScreen(menuScreen);
		manualScreen.getEndController().setMenuScreen(menuScreen);
		levelScreen.getController().setMenuScreen(menuScreen);
		menuScreen.start();
		
	}

	public static void main (String[] args) {
		launch(args);
	}
	
}
