package com.example.dungeon;

import java.util.ArrayList;

/**
 * A class for composite goal that implements the Goal interface.
 * A composite goal can be of type AND or OR.
 * An AND composite goal is completed when all the subgoals are complete.
 * A OR composite goal is completed when any of the subgoals is complete.
 * @author Siyin Zhou
 *
 */
public class CompositeGoal implements Goal {

	private ArrayList<Goal> goals = new ArrayList<Goal>();
	private String type;
	
	/**
	 * 
	 * @param type takes in either "AND" or "OR" to decide on isComplete evaluation
	 */
	public CompositeGoal(String type) {
		this.type = type.toUpperCase();
	}

	@Override
	public boolean isComplete() {
		if(type.equals("OR")) {
			return this.isOrComplete();
		}
		return this.isAndComplete();
	}
	
	private boolean isAndComplete() {
		if(goals.isEmpty()) return true;
		
		for(Goal g: goals) {
			if(!g.isComplete()) return false;
		}
		return true;
	}
	private boolean isOrComplete() {
		if(goals.isEmpty()) return true;
		
		for(Goal g: goals) {
			if(g.isComplete()) return true;
		}
		return false;
	}
	
	public void addGoal(Goal g) {
		goals.add(g);
	}
	
	public void removeGoal(Goal g) {
		goals.remove(g);
	}
	
	public ArrayList<Goal> getLeafGoals() {
		ArrayList<Goal> leafs = new ArrayList<Goal>();
		for (Goal g: this.goals) {
			if (g instanceof CompositeGoal) {
				CompositeGoal compgoal = (CompositeGoal) g;
				leafs.addAll(compgoal.getLeafGoals());
			} else {
				leafs.add(g);
			}
		}
		return leafs;
	}
}
