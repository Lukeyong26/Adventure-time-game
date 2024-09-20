/** ==
 *
 */
package com.example.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GameState gameState;
    private IntegerProperty progress;

    /**
     * 
     * @param width of the desired dungeon map
     * @param height of the desired dungeon map
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.gameState = new GameState();
        this.player = null;
        this.progress = new SimpleIntegerProperty(0);
    }

    /**
     * 
     * @return width of dungeon
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return height of dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @return player of the game
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setup the player in the dungeon map
     * @param player Player in the dungeon
     */
    public void setPlayer(Player player) {
        this.player = player;
        trackGameState(player);
        trackEntityStatus(player);
    }

    /**
     * Add entity to the dungeon's list of entities and allow dungeon to track entity
     * @param entity Entity to be added to the dungeon
     */
    public void addEntity(Entity entity) {
    	trackEntityStatus(entity);
        entities.add(entity);
    }
    /**
     * Track the player movement to check for game completion
     * @param player Player to be tracked
     */
    public void trackGameState(Player player) {
    	player.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (gameState.checkGameComplete()) {
                	gameWon();
                }
            }
        });
        player.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(gameState.checkGameComplete()) {
                	gameWon();
                }
            }
        });
    }
    
    /**
     * Setup listener for entity status to remove the entity from dungeon when it is deleted
     * @param obj Entity to be tracked
     */
    public void trackEntityStatus(Entity obj) {
    	obj.status().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (obj instanceof Player) {
                	gameState.gameEnded();
                	Timer timer = new Timer();
            		TimerTask task = new TimerTask() {
            	        public void run() {
            	        	gameLost();
            	        }
            		};
                	timer.schedule(task, 2500);
                	return;
                }
                removeEntity(obj);
            }
        });
    }
    
    /**
     * Announce game won and end game when all goals completed
     */
    private void gameWon() {
    	if (progress().intValue() == 0) {
    		gameState.gameEnded();
    		System.out.println("You completed all goals: You have WON!");
    		progress().set(1);
    	}
	}
    
    /**
     * Announce game lost and end game when player dies
     */
    private void gameLost() {
    	if (progress().intValue() == 0) {
    		progress().set(2);
    	}
	}

    /**
     * Given the x, y coordinate, get a list of entities at that coordinate in the dungeon
     * @param x X coordinate in the dungeon
     * @param y Y coordinate in the dungeon
     * @return ArrayList of entities in the specified coordinate in the dungeon.
     */
	public ArrayList<Entity> getEntity(int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	for (Entity e: entities) {
    		if (e == null) continue;
    		if(e.getX() == x && e.getY() == y) {
    			list.add(e);
    		}
    	}
    	return list;
    }
    
	/**
	 * Given an entity obj and x, y coordinate, inflict interaction between obj and all entities at that coordinate in the dungeon
	 * @param obj Entity interacting with the other entities in the specified coordinate
	 * @param x X coordinate in the dungeon
	 * @param y Y coordinate in the dungeon
	 */
    public void interactItems(Entity obj, int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list = this.getEntity(x, y);
    	for (Entity e : list) {
    		e.interact(obj);
    	}
    }

    /**
     * 
     * @return total treasure count within the dungeon
     */
	public int getTreasureCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Treasure) {
				count++;
			}
		}
		System.out.println("treasure = " + count);
		return count;
	}
	
	/**
	 * Get the exit in the dungeon, if no exit exist, returns null
	 * @return exit
	 */
	public Exit getExit() {
		for(Entity e : this.entities) {
			if (e instanceof Exit) {
				return (Exit) e;
			}
		}
		return null;
	}

	/**
	 * Get total count of enemies in dungeon
	 * @return count
	 */
	public int getEnemyCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Enemy) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Get total count of switches in dungeon
	 * @return count
	 */
	public int getSwitchCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Switch) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Remove a given entity from the dungeon list of entities
	 * @param obj Entity to be removed from the dungeon
	 */
	public void removeEntity(Entity obj) {
		if(entities.contains(obj))
			entities.remove(obj);
	}

	/**
	 * Get the GameState of the dungeon game
	 * @return GameState of the dungeon game
	 */
	public GameState getGameState() {
		return this.gameState;
	}

	/**
	 * Set up all subject observer relationship for a given enemy goal
	 * @param goal EnemyGoal for enemies to be registered to
	 */
	public void setUpObserverEnemyGoal(EnemyGoal goal) {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				enemy.registerObserver(goal);
			}
		}
		
	}
	
	/**
	 * Set up all subject observer relationship for a given switch goal
	 * @param goal SwitchGoal for switches to be registered to
	 */
	public void setUpObserverSwitchGoal(SwitchGoal goal) {
		for (Entity e : entities) {
			if (e instanceof Switch) {
				Switch sw = (Switch) e;
				sw.registerObserver(goal);
			}
		}
	}
	
	/**
	 * Set up subject observer relationship for a given treasure goal
	 * @param goal TreasureGoal for the player to register
	 */
	public void setUpObserverTreasureGoal(TreasureGoal goal) {
		player.registerObserver(goal);
	}
	
	/**
	 * Set up subject observer relationship between enemies and player to turn on enemy pursue feature
	 */
	public void setUpEnemy() {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				player.registerObserver(enemy);
				enemy.start();
			}
		}
	}

	/**
	 * Get boolean value indicating if game is still in progress
	 * @return true or false
	 */
	public boolean getGameInProgress() {
		return this.gameState.isGameInProgress();
	}

	public IntegerProperty progress() {
		return this.progress;
	}
}
