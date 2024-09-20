package com.example.dungeon;

import java.util.ArrayList;

/**
 * A boulder in the dungeon that can be moved by the player.
 * @author luke Yong
 *
 */
public class Boulder extends Entity{
	
	private Dungeon dungeon;
	
	/**
	 * Constructor for boulder.
	 * @param dungeon : The instance of the dungeon
	 * @param x : the boulders x coordinate
	 * @param y : the boulders y coordinate
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	
	/**
	 * Check if a given coordinate can be moved to.
	 * @param x : X coordinate for checking
	 * @param y : Y coordinate for checking
	 * @return Returns true if the given coordinate can be moved to.
	 */
	public boolean checkMoveable(int x, int y) {
		
    	if(!((y < dungeon.getHeight() - 1) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth() - 1) && (x >= 0)))		return false;
    	
    	ArrayList<Entity> list = dungeon.getEntity(x, y);
        if(!list.isEmpty()) {
        	for (Entity e: list) {
        		if(! e.movable(this)) return false;
        		if(e instanceof Switch) {
        			e.interact(this);
        		}
            }
        }
        return true;
    }
	
	public void moveUp() {
    		y().set(getY() - 1);
    }

    public void moveDown() {
    		y().set(getY() + 1);
    }

    public void moveLeft() {
    		x().set(getX() - 1);
    }

    public void moveRight() {
    		x().set(getX() + 1);
    }
    
    /**
     * Returns a boolean value if the boulder can be moved.
     */
	@Override
	public boolean movable(Entity obj) {
		if (!(obj instanceof Player)) return false;
		Player player = (Player) obj;
		
		int futureX = this.getX();
		int futureY = this.getY();
		
		
		int playerX = player.getX();
		int playerY = player.getY();
		
		if (playerX == this.getX()) {
			// player moving either up or down
			if (playerY > this.getY()) {
				// player moving up so check what's up of boulder
				futureY = this.getY() - 1;
			} else {
				// player moving down ''
				futureY = this.getY() + 1;
			}
		} else if (playerY == this.getY()) {
			// player moving either left or right
			if (playerX > this.getX()) {
				// player moving left ''
				futureX = this.getX() - 1;
			} else {
				// player moving right ''
				futureX = this.getX() + 1;
			}
		}
		
		boolean result = checkMoveable(futureX, futureY);
		return result;
	}
	
	/**
	 * Moves the boulder based on the players position with respects to the boulder.
	 */
	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		
		int playerX = player.getX();
		int playerY = player.getY();
		
		if (playerX == this.getX()) {
			if (playerY > this.getY()) this.moveUp();
			else {
				this.moveDown();
			}
		} else if (playerY == this.getY()) {
			if (playerX > this.getX()) this.moveLeft();
			else {
				this.moveRight();
			}
		}		
	}

}
