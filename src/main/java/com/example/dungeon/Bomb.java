package com.example.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A bomb entity that is able to be picked up and dropped/activated. The bomb will kill players, enemies and boulders that are in adjacent coordinates.
 * @author zenmint
 *
 */
public class Bomb extends Entity {

	private BombState state;
	private IntegerProperty visualStatus;
	
	/**
	 * Initializes a new bomb object.
	 * @param x X coordinate of bomb object
	 * @param y Y coordinate of bomb object
	 */
	public Bomb(int x, int y) {
		super(x, y);
		this.state = new Spawned();
		this.visualStatus = new SimpleIntegerProperty(0);
	}
	
	/**
	 * Check if an entity can move over the bomb.
	 * Should always return true.
	 * @param obj Entity to move over bomb
	 * @return boolean Always returns true
	 */
	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	/**
	 * Entity interacts with bomb.
	 * Should call state.pickUp if entity is a player.
	 * @param obj Entity to interact with bomb
	 */
	@Override
	public void interact(Entity obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			state = state.pickUp(this, player);
		}
	}
	
	/**
	 * Gives the bomb a lit state if already picked up.
	 * Bomb tries to change visual state to show explosion after 1.5 seconds.
	 * Tries to explode the bomb 2 seconds after activation.
	 * Bomb only explodes if in the unlit state before activation.
	 * @param player Player activating the bomb
	 */
	public void activate(Player player) {
		state = state.activateBomb(this, player);
		Bomb bombRef = this;
		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			public void run() {
				state.explode(bombRef, player);
			}
		};
		
		TimerTask task2 = new TimerTask() {
			public void run() {
				
				player.getDungeon().removeEntity(bombRef);
				bombRef.setVisualStatus(1);
			}
		};
		timer.schedule(task, 1500);
		timer.schedule(task2, 2000);
	}

	/**
	 * Returns the visual status of the bomb.
	 * @return Returns the visual status of the bomb
	 */
	public IntegerProperty getVisualStatus() {
		return visualStatus;
	}

	/**
	 * Sets the visual status to the given visual status.
	 * @param visualStatus The visual status of the bomb
	 */
	public void setVisualStatus(int visualStatus) {
		this.visualStatus.set(visualStatus);
	}
}
