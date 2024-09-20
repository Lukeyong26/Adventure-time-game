package com.example.dungeon;

/**
 * An exit in the dungeon
 * @author Siyin Zhou
 *
 */
public class Exit extends Entity {
	
	public Exit(int x, int y) {
		super(x,y);
	}

	@Override
	public boolean movable(Entity obj) {
		if (!(obj instanceof Player)) return false;
		return true;
	}

	@Override
	public void interact(Entity obj) {
		return;
	}
	
}
