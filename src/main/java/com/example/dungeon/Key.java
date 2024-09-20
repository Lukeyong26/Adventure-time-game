package com.example.dungeon;

public class Key extends Entity {

	private int ID;
	
	public Key(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		
		if (obj instanceof Player) {
			Player player = (Player) obj;
			pickUp(player);
		}
	}
	
	public void pickUp(Player player) {
		if ((player.getKeyID() == -1) && (this.status().getValue() == 0)) {
			player.setKeyID(ID);
			this.delete();
			Dungeon dungeon = player.getDungeon();
			dungeon.removeEntity(this);
		}
	}

}
