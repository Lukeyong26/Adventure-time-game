package com.example.dungeon;
/**
 * A node class used for graph path searching for the enemy.
 * @author Luke
 *
 */
public class Node {
	private int index;
	private int parent_index;
	private int x;
	private int y;
	
	public Node(int index, int pIndex, int x, int y) {
		super();
		this.index = index;
		this.parent_index = pIndex;
		this.x = x;
		this.y = y;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getParentIndex() {
		return this.parent_index;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
