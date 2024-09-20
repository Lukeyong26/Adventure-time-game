package com.example.dungeon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The enemy class goes into RunAway state behaviour when the player is invincible.
 * @author Luke
 *
 */
public class RunAway implements ChaseBehavior{
	private Timer timer;
	
	public RunAway() {
		this.timer = new Timer();
	}

	@Override
	public void start(Enemy enemy) {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {	
				if (!enemy.getGameInProgress()) stop();
				if (enemy.isAlive()) {
					ArrayList<Node> pathList = runAway(enemy);
					if (!enemy.getPlyIsInvincible()) {
						enemy.setChaseBehavior(new ChasePlayer());
						stop();
						enemy.start();
					}
					enemy.followPath(pathList);
				}
			}
		}, 0, 100);		
	}

	@Override
	public void stop() {
		timer.cancel();
	}
	
	public ArrayList<Node> runAway(Enemy enemy) {
		// Using random generator to determine path
		Dungeon dungeon = enemy.getDungeon();
		int plyX = enemy.getPlayerX();
		int plyY = enemy.getPlayerY();
		
		Random rand = new Random();
		
		int midx = dungeon.getWidth()/2;
		int midy = dungeon.getHeight()/2;
		
		int newX = enemy.getX()+1;
		int newY = enemy.getY()+1;
		
		
		if (plyX >= midx && plyY <= midy) {
			// player in 1st quadrant 
			newX = rand.nextInt(midx);
			newY = rand.nextInt(midy) + midy;
		} else if (plyX <= midx && plyY <= midy) {
			// player in 2nd quadrant
			newX = rand.nextInt(midx) + midx;
			newY = rand.nextInt(midy) + midy;
		} else if (plyX <= midx && plyY >= midy) {
			// player in 3rd quadrant
			newX = rand.nextInt(midx) + midx;
			newY = rand.nextInt(midy);
		} else {
			newX = rand.nextInt(midx);
			newY = rand.nextInt(midy);
		}
		
		

		return enemy.findPathTo(newX, newY);
	}
}
