package com.example.dungeon;

public class Unlit implements BombState {

	/**
	 * Bomb should not be able to be picked up when in Unlit state.
	 * @param bomb Bomb to be picked up by the player
	 * @param player Player picking up the bomb
	 * @return Returns the Unlit state.
	 */
	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Bomb will get the coordinates of the player's current position and become visible.
	 * Bomb will be removed from player and gain a Lit state.
	 * @param bomb Bomb to be activated
	 * @param player Player activating bomb
	 * @return Returns the Lit state.
	 */
	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		bomb.x().set(player.getX());
		bomb.y().set(player.getY());
		bomb.setVisualStatus(2);
		player.setVisualStatus(player.getPrevVisualStatus().intValue());
		player.setBomb(null);
		return new Lit();
	}

	/**
	 * Bomb should not explode in Unlit state.
	 * @param bomb Bomb to be activated
	 * @param player Player activating the bomb
	 */
	@Override
	public void explode(Bomb bomb, Player player) {
		return;
	}
}
