package com.example.dungeon;

/**
 * A weapon strategy stating the player has no weapon.
 * @author Amy
 *
 */
public class NoWeapon implements WeaponStrategy {
	
	@Override
	public boolean attack(Entity obj) {
		return false;
	}

	@Override
	public boolean hasDurability() {
		return true;
	}

}
