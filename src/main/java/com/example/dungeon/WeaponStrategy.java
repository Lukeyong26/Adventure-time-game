package com.example.dungeon;

/**
 * A weapon strategy that determines whether the player can attack an enemy
 * @author Siyin Zhou
 *
 */
public interface WeaponStrategy {
	
	public boolean attack(Entity obj);
	public boolean hasDurability();
	
}
