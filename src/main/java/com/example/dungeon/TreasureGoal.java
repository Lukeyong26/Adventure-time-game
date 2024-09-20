package com.example.dungeon;

/**
 * A treasure goal that tracks the number of treasure collected by the player
 * @author Siyin Zhou
 *
 */
public class TreasureGoal implements Goal, Observer {

	

	private int totalTreasure;
	private int treasureCount;
	
	/**
	 * Create a treasure goal given a total treasure count goal
	 * @param goal Total number of treasure
	 */
	public TreasureGoal(int goal) {
		this.totalTreasure = goal;
		this.treasureCount = 0;
	}
	
	/**
	 * Get the current treasure count collected
	 * @return The number of treasure collected
	 */
	public int getTreasureCount() {
		return treasureCount;
	}

	/**
	 * Get total treasure goal count
	 * @return total
	 */
	public int getTotalTreasure() {
		return totalTreasure;
	}
	
	@Override
	public boolean isComplete() {
		if(this.treasureCount >= this.totalTreasure) {
			System.out.println("Treasure goal is complete");
			return true;
		}
		return false;
	}

	
	@Override
	public void update(Subject obj) {
		Player pl = (Player) obj;
		this.treasureCount = pl.getTreasureCollected();	
	}

}
