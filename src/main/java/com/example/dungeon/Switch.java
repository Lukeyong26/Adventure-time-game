package com.example.dungeon;

import java.util.ArrayList;

/**
 * A switch in the dungeon that can be activated to by pushing a boulder on it. Used to complete the activate switches goal.
 * @author luke Yong
 *
 */
public class Switch extends Entity implements Subject{
	
	private ArrayList<Observer> observers;
	private boolean activated;
	
	/**
	 * Constructor for switch
	 * @param x : x coordinate of the switch 
	 * @param y : y coordinate of the switch
	 */
	public Switch(int x, int y) {
		super(x,y);
		this.observers = new ArrayList<Observer>();
		this.activated = false;
	}
	
	/**
	 * Activate the switch and updates observers of the change
	 */
	public void activateSwitch() {
		if(this.activated == true) return;
		this.activated = true;
		updateObservers();
	}
	
	/**
	 * Deactivate the switch and updates observers of the change
	 */
	public void deactivateSwitch() {
		if(this.activated == false) return;
		this.activated = false;
		updateObservers();
	}
	
	/**
	 * 
	 * @return Returns the switches activated status.
	 */
	public boolean getActivated() {
		return this.activated;
	}
	
	
	//------ Observer methods ------
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void updateObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	//------ Entity methods ------
	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	/**
	 * Activates the switch if an incoming object is a boulder. Deactivates if incoming object is a player.
	 */
	@Override
	public void interact(Entity obj) {
		if ((obj instanceof Boulder)) {
			activateSwitch();
			//System.out.println("switch active");
		}
		
		if ((obj instanceof Player)) {
			deactivateSwitch();
			//System.out.println("switch not active");
		}
	}
	
}
