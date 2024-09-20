package com.example.dungeon;

/**
 * A bomb can have several states that can have will have different behaviors. The states are Spawned, Unlit and Lit.
 * When the bomb is in the Spawned state, the bomb can only be picked up by a player.
 * When the bomb is in the Unlit state, the bomb can only be activated by a player.
 * When the bomb is in the lit state, it will automatically detonate and kill certain entities after 2 seconds.
 * @author zenmint
 *
 */
public interface BombState {

		/**
		 * Player only picks up the bomb if player does not already have a bomb and bomb is in Spawned state.
		 * Player visual status will be holding a bomb if in Spawned state.
		 * @param bomb Bomb to be picked up by the player
		 * @param player Player picking up the bomb
		 * @return Returns Unlit if in Spawned state.
		 */
		public BombState pickUp(Bomb bomb, Player player);
		
		/**
		 * Bomb will get the coordinates of the player's current position and become visible if in Unlit state.
		 * Bomb will be removed from player and gain a Lit state if in Unlit state.
		 * @param bomb Bomb to be activated
		 * @param player Player activating bomb
		 * @return Returns Lit if in Unlit state.
		 */
		public BombState activateBomb(Bomb bomb, Player player);
		
		/**
		 * Kills player, enemies and boulders in coordinates directly adjacent to and on the bomb.
		 * Removes the bomb from the list of entities.
		 * Sets the visual status of the bomb to invisible.
		 * @param bomb Bomb to be exploded
		 * @param player Player to explode bomb
		 */
		public void explode(Bomb bomb, Player player);
}
