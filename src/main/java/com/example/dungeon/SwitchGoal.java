package com.example.dungeon;

/**
 * A switch goal that tracks the number of switches activated
 * The goal is completed when number of activated switches equal total switches
 * @author Siyin Zhou
 *
 */
public class SwitchGoal implements Goal, Observer {

	private int totalSwitches;
	private int switchActivated;
	
	/**
	 * Create a switch goal given the total number of switches
	 * @param count The total number of switches. Must be positive
	 */
	public SwitchGoal(int count) {
		this.totalSwitches = count;
		this.switchActivated = 0;
	}
	
	/**
	 * Get total number of switches of goal
	 * @return total
	 */
	public int getTotalSwitches() {
		return totalSwitches;
	}

	/**
	 * Get number of switches currently activated
	 * @return The number of switches currently activated
	 */
	public int getSwitchActivated() {
		return switchActivated;
	}


	@Override
	public boolean isComplete() {
		if(this.totalSwitches == this.switchActivated) {
			return true;
		}
		return false;
	}

	@Override
	public void update(Subject obj) {
		if(!(obj instanceof Switch)) return;
		Switch sw = (Switch) obj;
		if(sw.getActivated()) {
			this.switchActivated += 1;
		} else {
			this.switchActivated -= 1;
		}
	}

}
