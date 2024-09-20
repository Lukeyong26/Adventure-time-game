package com.example.dungeon;

/**
 * A sword in the dungeon. Can be picked up by the player to fend off enemies.
 * @author luke
 *
 */
public class Sword extends Entity {

	/**
	 * Constructor for sword 
	 * @param x : x coordinate of the sword
	 * @param y : y coordinate of the sword
	 */
	public Sword(int x, int y) {
		super(x, y);
	}
	
	
	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		Player pl = (Player) obj;
		if(pl.haveWeapon()) return;
		pl.setWeaponStrategy(new SwordAttack());
		pl.pickUpSword(this);
	}

}
