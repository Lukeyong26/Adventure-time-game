package com.example.dungeon;

/**
 * A treasure that can be picked up by the player in the dungeon
 * @author Siyin Zhou
 *
 */
public class Treasure extends Entity{

	private boolean isCollected;
	
	/**
	 * Create a treasure given the coordinates
	 * @param x : x coordinate of treasure
	 * @param y : y coordinate of treasure
	 */
	public Treasure(int x, int y) {
		super(x, y);
		this.isCollected = false;
	}
	
	/**
	 * Check if the treasure has already been collected
	 * @return true if collected, else false
	 */
	public boolean isCollected() {
		return isCollected;
	}
	/**
	 * Collect the treasure by setting isCollected to true
	 */
	public void collect() {
		this.isCollected = true;
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	/**
	 * Only the player can interact with the treasure
	 * and only if the treasure is not yet collected
	 * Upon interaction, player pickup the treasure
	 * The treasure is then deleted from the dungeon map
	 */
	@Override
	public void interact(Entity obj) {
		// Increment treasure count on player
		if (obj instanceof Player && isCollected == false) {
			Player player = (Player) obj;
			player.pickUpTreasure(this);
			this.delete();
		}
	}
	
}
