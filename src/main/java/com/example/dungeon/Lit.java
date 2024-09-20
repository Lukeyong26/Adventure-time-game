package com.example.dungeon;

public class Lit implements BombState {

	/**
	 * Lit bomb should not be able to be picked up.
	 * @param bomb Bomb to be picked up
	 * @param player Player picking up bomb
	 * @return Returns the Lit state
	 */
	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Lit bomb should not be able to be activated.
	 * @param bomb Bomb to be activated
	 * @param player Player to be activated
	 * @return Returns the Lit state.
	 */
	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Kills player, enemies and boulders in coordinates directly adjacent to and on the bomb.
	 * Removes the bomb from the list of entities.
	 * Sets the visual status of the bomb to invisible.
	 * @param bomb Bomb to be exploded
	 * @param player Player to explode bomb
	 */
	@Override
	public void explode(Bomb bomb, Player player) {

		Dungeon dungeon = player.getDungeon();
		
		int[] diffx = {-1, +1, 0, 0, 0};
		int[] diffy = {0, 0, +1, -1, 0};
		
		for (int i = 0; i < 5; i++) {
			int relativePosX = bomb.getX() + diffx[i];
			int relativePosY = bomb.getY() + diffy[i];
			
			for (Entity k : dungeon.getEntity(relativePosX, relativePosY)) {
				if ((k instanceof Player)
				|| (k instanceof Boulder)) {
					k.delete();
				}
				if (k instanceof Enemy) {
					Enemy enemy = (Enemy) k;
					enemy.isDead();
				}
			}
		}
		
		return;
	}
}
