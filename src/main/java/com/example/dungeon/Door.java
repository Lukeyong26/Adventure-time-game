package com.example.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	
	private int ID;
	private BooleanProperty locked;

	public Door(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		this.locked = new SimpleBooleanProperty(true);
	}

	@Override
	public boolean movable(Entity obj) {

		if (locked.get() == true) {
			return false;
		} 

		return true;
	}
	
	@Override
	public void interact(Entity obj) {
		if(!(obj instanceof Player)) {
			return;
		} else {
			Player player = (Player) obj;
			if (player.getKeyID() == this.ID) {
				this.locked.set(false);
				player.setKeyID(-1);
			}
		}
	}

	public BooleanProperty locked() {
		return this.locked;
	}
}
