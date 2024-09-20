package com.example.dungeon;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    private MenuScreen menuScreen;
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    private WinScreen winScreen;
	private LostScreen lostScreen;
	private AudioClip wonSound;
	private AudioClip lostSound;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.trackGameState();
        // this.wonSound = new AudioClip(new File("sounds/won.mp3").toURI().toString());
        //this.lostSound = new AudioClip(getClass().getResource("lost.mp3").toString());
    }

    @FXML
    public void initialize() {
        // Image ground = new Image(getClass().getResourceAsStream("/images/bluefloor.png"));
        Image ground = new Image(getClass().getResourceAsStream("images/bluefloor.png"));
        
        // Add the ground first so it is below all other entities
        
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        for (int y = 1; y < dungeon.getHeight(); y++) {
            squares.getRowConstraints().add(new RowConstraints(ground.getHeight())); // column 1 is 200 wide

        }
        for (int y = 1; y < dungeon.getWidth(); y++) {
        	squares.getColumnConstraints().add(new ColumnConstraints(ground.getWidth()));

        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case W:
            player.moveUp();
            break;
        case S:
            player.moveDown();
            break;
        case A:
            player.moveLeft();
            break;
        case D:
            player.moveRight();
            break;
        case B:
        	player.dropBomb();
        	break;
        case X:
        	player.attack();
        	break;
        default:
            break;
        }
    }

    public void setMenuScreen(MenuScreen menuScreen) {
    	this.menuScreen = menuScreen;
    }
    
    @FXML
    public void handleMenuButton(ActionEvent event) {
    	dungeon.getGameState().gameEnded();
    	menuScreen.start();
    }
    
    public void setWinScreen(WinScreen winScreen) {
    	this.winScreen = winScreen;
    }
    
	public void setLostScreen(LostScreen lostScreen) {
		this.lostScreen = lostScreen;
		
	}
    
    public void trackGameState() {
    	dungeon.progress().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if(newValue.equals(1)) {
            		//wonSound.play();
            		System.out.println("Game won");
            		winScreen.start();
            	} else if (newValue.equals(2)) {
            		Platform.runLater(() -> {
            			//lostSound.play();
            			lostScreen.start();
                		System.out.println("Game lost");
            		});
            		

            	}
            }
        });
    }


}

