package com.example.dungeon;

/**
 * The enemy's chases behaviour depends on the player's invincibility state.
 * @author Luke
 *
 */
public interface ChaseBehavior {
	public void start(Enemy enemy);
	public void stop();
}
