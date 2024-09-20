package com.example.dungeon;

/**
 * An invincible potion in the dungeon game
 * Player gains invincibility when player drink the potion
 * @author Siyin Zhou
 *
 */
public class InvinciblePotion extends Entity {

	
	/**
	 * Create an invincible potion
	 * When picked up by the player, gives the player 10 seconds of invincibility
	 * When player is invincible, player cannot be killed by enemies or bombs
	 * Enemy runs away from the player when player is invincible
	 * Invincible mode player kills the enemy upon contact
	 * @param x X coordinate of the potion
	 * @param y Y coordinate of the potion
	 */
	
	public InvinciblePotion(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	/**
	 * When player interact with the invincible potion, the player drinks the potion
	 * Only the player can drink the potion
	 */
	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		Player pl = (Player) obj;
		pl.drinkPotion(this);
	}

}