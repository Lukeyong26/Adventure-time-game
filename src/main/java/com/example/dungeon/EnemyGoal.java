package com.example.dungeon;

/**
 * A simple Enemy Goal that tracks whether the player has killed all the enemies in the dungeon
 * @author Siyin Zhou
 *
 */
public class EnemyGoal implements Goal, Observer {

	private int defeated;
	private int totalEnemies;
	
	/**
	 * Create a EnemyGoal with total enemies to kill to complete the goal
	 * @param count of total enemies
	 */
	public EnemyGoal(int count) {
		this.defeated = 0;
		this.totalEnemies = count;
	}
	
	/**
	 * Update the enemy goal status when an enemy is defeated
	 */
	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			this.defeated++;
		}
	}
	
	/**
	 * Get count of enemies currently defeated
	 * @return count
	 */
	public int getDefeated() {
		return defeated;
	}
	
	/**
	 * Get total enemies to defeat
	 * @return total
	 */
	public int getTotalEnemies() {
		return totalEnemies;
	}

	/**
	 * Check if the goal is complete
	 * @return ture or false
	 */
	@Override
	public boolean isComplete() {
		if (this.defeated == this.totalEnemies)
			return true;
		return false;
	}

}
