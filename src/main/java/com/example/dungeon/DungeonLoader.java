package com.example.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("E:\\VSCode\\repos\\demo\\src\\main\\java\\com\\example\\dungeon\\dungeons\\" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return a new dungeon
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject jsonGoals = json.optJSONObject("goal-condition");
        
        for (int i = 0; i < jsonEntities.length(); i++) {
        	JSONObject obj = jsonEntities.getJSONObject(i);
        	if (obj.getString("type").equals("boulder")) {
        		loadEntity(dungeon, obj);
        	}
        }

        for (int i = 0; i < jsonEntities.length(); i++) {
        	JSONObject obj = jsonEntities.getJSONObject(i);
        	if (obj.getString("type").equals("boulder")) {
        		continue;
        	}
        	if (obj.getString("type").equals("switch")) continue;
        	loadEntity(dungeon, obj);
        	
        }
        
        for (int i = 0; i < jsonEntities.length(); i++) {
        	JSONObject obj = jsonEntities.getJSONObject(i);
        	if (obj.getString("type").equals("switch")) {
        		loadEntity(dungeon, obj);
        	}
        }
        
        
        
        if(jsonGoals != null) {
        	loadGoal(dungeon, jsonGoals);
        }
        dungeon.setUpEnemy();

        return dungeon;
    }

    private void loadGoal(Dungeon dungeon, JSONObject jsonGoals) {
    	GameState gameState = dungeon.getGameState();
    	
        String type = jsonGoals.getString("goal");
        switch (type) {
        case "AND":
        	JSONArray subAnd = jsonGoals.getJSONArray("subgoals");
        	gameState.addCompositeGoal("AND", subAnd, dungeon);
        	break;
        case "OR":
        	JSONArray subOr = jsonGoals.getJSONArray("subgoals");
        	gameState.addCompositeGoal("OR", subOr, dungeon);
        	break;
        default:
        	gameState.addSimpleGoal(type, dungeon);
        }
        
		
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	InvinciblePotion inv = new InvinciblePotion(x, y);
        	onLoad(inv);
        	entity = inv;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon,x,y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch gameSwitch = new Switch(x, y);
        	onLoad(gameSwitch);
        	entity = gameSwitch;
        	break;
        case "door":
        	Door door = new Door(x, y, json.getInt("ID"));
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	Key key = new Key(x, y, json.getInt("ID"));
        	onLoad(key);
        	entity = key;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Bomb bomb);

    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch gameSwitch);

	public abstract void onLoad(Treasure treasure);

	public abstract void onLoad(Sword sword);
	
	public abstract void onLoad(InvinciblePotion inv);

	public abstract void onLoad(Enemy enemy);

	public abstract void onLoad(Exit exit);
	
	public abstract void onLoad(Door door);
	
	public abstract void onLoad(Key key);
	

}
