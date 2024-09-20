package com.example.dungeon;

public class Spawned implements BombState {

	/**
	 * Player only picks up the bomb if player does not already have a bomb and bomb is in Spawned state.
	 * Player visual status will be holding a bomb.
	 * @param bomb Bomb to be picked up by the player
	 * @param player Player picking up the bomb
	 * @return Returns Unlit state.
	 */
	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		if (player.getBomb() == null) {
			player.setBomb(bomb);
			player.setPrevVisualStatus();
			player.setVisualStatus(3);
			bomb.delete();
			bomb.setVisualStatus(1);
			return new Unlit();
		} else {
			return this;
		}
	}

	/**
	 * Bomb should not be activated in Spawned state.
	 * @param bomb Bomb to be activated
	 * @param player Player activating the bomb
	 * @return Returns the Spawned state.
	 */
	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Bomb should not explode in Spawned state.
	 * @param bomb Bomb to be exploded
	 * @param player Player activating the bomb
	 */
	@Override
	public void explode(Bomb bomb, Player player) {
		return;
	}
}
