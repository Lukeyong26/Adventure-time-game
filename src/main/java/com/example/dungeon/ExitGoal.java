package com.example.dungeon;

/**
 * An exit goal for the dungeon game
 * @author momo
 *
 */
public class ExitGoal implements Goal {

	private Player player;
	private Exit exit;
	
	/**
	 * Create an exit goal for the dungeon game
	 * @param player Player of the game. The dungeon has exactly one player
	 * @param exit Exit of the game. The dungeon has exactly one exit
	 */
	public ExitGoal(Player player, Exit exit) {
		this.player = player;
		this.exit = exit;
	}
	
	/**
	 * Return if game is complete
	 */
	@Override
	public boolean isComplete() {
		if(player.getX() == exit.getX() && player.getY() == exit.getY()) {
			return true;
		}
		return false;
	}

}
