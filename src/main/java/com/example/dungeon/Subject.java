package com.example.dungeon;

/**
 * Subject interface for the observer pattern
 * @author Siyin Zhou
 *
 */
public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void updateObservers();
	
}
