package com.example.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Tracks the game state of the dungeon game
 * The game is completed when the player wins or loses the game
 * The game state tracks all the goals of the game
 * @author momo
 *
 */
public class GameState {

	private Goal goal;
	private boolean gameInProgress;
	
	/**
	 * Create a GameState to track goal and game progress
	 */
	public GameState() {
		this.goal = null;
		this.gameInProgress = true;
	}
	
	/**
	 * Get the goal of the dungeon game
	 * @return goal
	 */
	public Goal getGoal() {
		return this.goal;
	}
	
	/**
	 * Check if game is still in progress
	 * @return true or false
	 */
	public boolean isGameInProgress() {
		return this.gameInProgress;
	}
	
	/**
	 * Set the game in progress as false to end the game
	 */
	public void gameEnded() {
		this.gameInProgress = false;
	}
	
	/**
	 * Check if game goals are completed
	 * @return true or false to indicate goal completion
	 */
	public boolean checkGameComplete() {
		if(this.goal == null) return true;
		return this.goal.isComplete();
	}
	
	/**
	 * Create a treasure goal given a dungeon
	 * @param dungeon
	 * @return TreasureGoal
	 * @precondition dungeon must have a player
	 */
	private TreasureGoal createTreasureGoal(Dungeon dungeon) {
		int count = dungeon.getTreasureCount();
		TreasureGoal tg = new TreasureGoal(count);
		dungeon.getPlayer().registerObserver(tg);
		return tg;
	}
	
	/**
	 * Create an exit goal given a dungeon
	 * @param dungeon
	 * @return ExitGoal
	 * @precondition dungeon must have an exit and player
	 */
	private ExitGoal createExitGoal(Dungeon dungeon) {
		Exit exit = dungeon.getExit();
		Player player = dungeon.getPlayer();
		ExitGoal eg = new ExitGoal(player, exit);
		return eg;
	}
	
	/**
	 * Create a switch goal given a dungeon
	 * @param dungeon
	 * @return SwitchGoal
	 */
	private SwitchGoal createSwitchGoal(Dungeon dungeon) {
		int count = dungeon.getSwitchCount();
		SwitchGoal sg = new SwitchGoal(count);
		dungeon.setUpObserverSwitchGoal(sg);
		return sg;
	}
	
	/**
	 * Create a enemy goal given a dungeon
	 * @param dungeon
	 * @return
	 */
	private EnemyGoal createEnemyGoal(Dungeon dungeon) {
		int count = dungeon.getEnemyCount();
		EnemyGoal eg = new EnemyGoal(count);
		dungeon.setUpObserverEnemyGoal(eg);
		return eg;
	}
	
	/**
	 * Create a simple goal given a string to indicate type and given a dungeon
	 * 
	 * @param type of goal
	 * @param dungeon
	 * @return
	 */
	private Goal createSimpleGoal(String type, Dungeon dungeon) {
		Goal g = null;
		
		if(type.equals("treasure")) {
			g = this.createTreasureGoal(dungeon);
		} else if (type.equals("enemies")) {
			g = this.createEnemyGoal(dungeon);
		} else if (type.equals("exit")) {
			g = this.createExitGoal(dungeon);
		} else if (type.equals("boulders")) {
			g = this.createSwitchGoal(dungeon);
		}
		
		return g;	
	}
	
	/**
	 * Create a composite goal 
	 * @param type
	 * @param subgoals
	 * @param dungeon
	 * @return
	 * @precondition type given must be either "AND" or "OR"
	 */
	private CompositeGoal createCompositeGoal(String type, JSONArray subgoals, Dungeon dungeon) {
		CompositeGoal cg = new CompositeGoal(type);
		cg = addSubGoals(cg, subgoals, dungeon);
		return cg;
	}
	
	/**
	 * Given a composite goal, add the subgoals to the composite goal
	 * @param cg
	 * @param subgoals
	 * @param dungeon
	 * @return CompositeGoal
	 * @precondition subgoals is not empty
	 */
	private CompositeGoal addSubGoals(CompositeGoal cg, JSONArray subgoals, Dungeon dungeon) {
		for (int i = 0; i < subgoals.length(); i++) {
            JSONObject goal = subgoals.getJSONObject(i);
            String sg = goal.getString("goal");
            Goal g;
            if(sg.equals("OR") || sg.equals("AND")) {
            	JSONArray tmpSub = goal.getJSONArray("subgoals");
            	g = createCompositeGoal(sg, tmpSub, dungeon);
            } else {
            	g = createSimpleGoal(sg, dungeon);
            }
             
            cg.addGoal(g);
        }
		return cg;
	}
	
	/**
	 * Add a composite goal to game state
	 * @param type Type of goal to be added
	 * @param subgoals JSONArray of subgoals
	 * @param dungeon Dungeon containing the goals
	 */
	public void addCompositeGoal(String type, JSONArray subgoals, Dungeon dungeon) {
		CompositeGoal cg = createCompositeGoal(type, subgoals, dungeon);
		this.goal = cg;
	}
	
	/**
	 * Add a simple goal to game state
	 * @param type Type of goal to be added
	 * @param dungeon Dungeon containing the goals
	 */
	public void addSimpleGoal(String type, Dungeon dungeon) {
		this.goal = createSimpleGoal(type, dungeon);
	}
	
}
