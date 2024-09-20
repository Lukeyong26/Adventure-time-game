package com.example.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y, status;

    /**
     * Create an entity positioned in square (x,y)
     * @param x X coordinate of entity
     * @param y Y coordinate of entity
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.status = new SimpleIntegerProperty(0);
    }

    /**
     * Get the x coordinate of the entity
     * @return X coordinate of entity as an integer property
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * Get the y coordinate of the entity
     * @return Y coordinate of entity as an integer property
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * Get the status of the entity, indicating whether the entity is deleted from dungeon
     * @return status of the entity
     */
    public IntegerProperty status() {
        return this.status;
    }
    
    /**
     * Set the status of the entity to deleted from dungeon
     * The dungeon will remove the entity after this function is called
     */
    public void delete() {
        status().set(1);
    }
    
    /**
     *  Get y coordinate of entity as integer
     * @return Y coordinate of entity as an int
     */
    public int getY() {
        return y().get();
    }

    /**
     *  Get x coordinate of entity as integer
     * @return X coordinate of entity as an int
     */
    public int getX() {
        return x().get();
    }

    /**
     * Check if the given entity can move on to the location of this entity
     * @param obj Entity moving on to this entity.
     * @return true if possible to move to same location, else false
     */
	public abstract boolean movable(Entity obj);
	
	/**
	 * Given an entity, trigger the interaction between the two entities
	 * @param obj Entity interacting with this entity
	 */
	public abstract void interact(Entity obj);
}
