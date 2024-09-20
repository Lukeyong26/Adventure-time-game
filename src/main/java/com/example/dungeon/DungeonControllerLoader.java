package com.example.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image mageImage;
    private Image playerSwordImage;
    private Image playerDeadImage;
    private Image wallImage;
    private Image bombImage;
    private Image explodeAnim;
    private Image attackAnim;
    private Image enemyImage;
    private Image exitImage;
    private Image switchImage;
    private Image treasureImage;
    private Image boulderImage;
    private Image swordImage;
    private Image invImage;
    private Image playerBombImage;
    private Image closedImage;
    private Image openImage;
    private Image keyImage;
    
    // Sounds
    private AudioClip bombSound;
    private AudioClip doorUnlock;
    private AudioClip swordSlash;

    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image(getClass().getResourceAsStream("images/player.png"));
        attackAnim = new Image(getClass().getResourceAsStream("images/playerattack.png"));
        wallImage = new Image(getClass().getResourceAsStream("images/blueWall.png"));
        bombImage = new Image(getClass().getResourceAsStream("images/bomb_unlit.png"));
        explodeAnim = new Image(getClass().getResourceAsStream("images/BombExploding.png"));
        playerSwordImage = new Image(getClass().getResourceAsStream("images/playerSword.png"));
        enemyImage = new Image(getClass().getResourceAsStream("images/lemongrab.png"));
        exitImage = new Image(getClass().getResourceAsStream("images/exit.png"));
        switchImage = new Image(getClass().getResourceAsStream("images/pressure_plate.png"));
        treasureImage = new Image(getClass().getResourceAsStream("images/gold_pile.png"));
        boulderImage = new Image(getClass().getResourceAsStream("images/boulder.png"));
        swordImage = new Image(getClass().getResourceAsStream("images/greatsword_1_new.png"));
        invImage = new Image(getClass().getResourceAsStream("images/potion.png"));
        mageImage = new Image(getClass().getResourceAsStream("images/playergodmode.png"));
        playerBombImage = new Image(getClass().getResourceAsStream("images/playerBomb.png"));
        closedImage = new Image(getClass().getResourceAsStream("images/closed_door.png"));
        openImage = new Image(getClass().getResourceAsStream("images/open_door.png"));
        keyImage = new Image(getClass().getResourceAsStream("images/key.png"));
        playerDeadImage = new Image(getClass().getResourceAsStream("images/playerdead.png"));
        // bombSound = new AudioClip(new File("/sounds/bomb.mp3"));
        // doorUnlock = new AudioClip(new File("/sounds/door.mp3"));
        // swordSlash = new AudioClip(new File("/sounds/sword.wav"));
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addPlayer(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
        
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Switch gameSwitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(gameSwitch, view);
    }

    @Override
    public void onLoad(InvinciblePotion inv) {
    	ImageView view = new ImageView(invImage);
    	addEntity(inv, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	addBomb(bomb, view);
	}
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(closedImage);
    	addDoor(door, view);
    }

    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }

    private void addPlayer(Entity player, ImageView view) {
    	trackPlayer(player, view);
    	trackPosition(player, view);
    	entities.add(view);
    }
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(0, view);
    }
    private void addDoor(Entity door, ImageView view) {
    	trackDoor(door, view);
    	entities.add(0, view);
    }
    private void addBomb(Entity bomb, ImageView view) {
    	trackBomb(bomb, view);
    	entities.add(0, view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.status().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (entity instanceof Player) {
            		ImageView view = (ImageView) node;
            		view.setImage(playerDeadImage);
            		view.setViewport(new Rectangle2D(0, 0, 32, 32));
            		Animation animation = new SpriteAnimation(view, Duration.millis(2000), 10, 13, 0, 0, 32, 32);
            		animation.setCycleCount(1);
            		animation.play();
            	} else {
	            	if(newValue.equals(0)) {
	            		node.setVisible(true);
	            	} else if (newValue.equals(1)) {
	            		node.setVisible(false);
	            	}
            	}
            }
        });
    }
    
    private void trackPlayer(Entity entity, Node node) {
    	Player player = (Player) entity;

        player.visualStatus().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	System.out.println("manipulate player image");
            	ImageView view = (ImageView) node;
            	if(newValue.equals(0)) {
                	System.out.println("no sword image");
                	view.setImage(playerImage);
            	} else if(newValue.equals(1)) {
            		System.out.println("sword image");
            		view.setImage(playerSwordImage);
            	} else if(newValue.equals(2)) {
            		System.out.println("mage image");
            		view.setImage(mageImage);
            	} else if(newValue.equals(3)) {
            		System.out.println("player with bomb image");
            		view.setImage(playerBombImage);
            	} else if(newValue.equals(4)) {
            		
            		//swordSlash.play();
            		Rectangle2D vp = view.getViewport();
            		view.setImage(attackAnim);
            		view.setViewport(new Rectangle2D(0, 0, 32, 32));
            		Animation animation = new SpriteAnimation(view, Duration.millis(500), 5, 13, 0, 0, 32, 32);
            		animation.setCycleCount(1);
            		animation.play();
            		Timer timer = new Timer();
            		TimerTask task = new TimerTask() {
            	        public void run() {
            	        	animation.stop();
            	        	System.out.println("set");
            	        	view.setViewport(vp);
            	        	player.setVisualStatus(1);	
            	        }
            		};
            	    
            	    timer.schedule(task, 500);	
            		
            	}
            }
        });
    }
    
    private void trackDoor(Entity entity, Node node) {
    	Door door = (Door) entity;
    	GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        door.locked().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				ImageView view = (ImageView) node;
				if (newValue.equals(true)) {
					view.setImage(closedImage);
				} else {
					//doorUnlock.play();
					view.setImage(openImage);
				}
			}
        });
    }
    
    private void trackBomb(Entity entity, Node node) {
        
    	if(entity instanceof Bomb) {
    		Bomb bomb = (Bomb) entity;
    		GridPane.setColumnIndex(node, entity.getX());
            GridPane.setRowIndex(node, entity.getY());
            bomb.x().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                    GridPane.setColumnIndex(node, newValue.intValue());
                }
            });
            bomb.y().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                    GridPane.setRowIndex(node, newValue.intValue());
                }
            });
            bomb.getVisualStatus().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                	if(newValue.equals(0)) {
                		node.setVisible(true);
                		
                	} else if (newValue.equals(1)) {
                		node.setVisible(false);
                		
                	} else if (newValue.equals(2)) {
                		//bombSound.play();
                		node.setVisible(true);
                		ImageView view = (ImageView) node;
                		view.setImage(explodeAnim);
                		view.setViewport(new Rectangle2D(0, 0, 32, 32));
                		Animation animation = new SpriteAnimation(view, Duration.millis(2000), 13, 13, 0, 0, 32, 96);
                		animation.setCycleCount(1);
                		animation.play();                		
                	}
                }
            });
    	}
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return new DungeonController
     * @throws FileNotFoundException File not found
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
