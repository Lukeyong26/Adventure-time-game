package com.example.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The enemy chase the player when the player is not invincible.
 * @author Luke
 *
 */
public class ChasePlayer implements ChaseBehavior {
	
	private Timer timer;
	
	public ChasePlayer() {
		this.timer = new Timer();
	}

	@Override
	public void start(Enemy enemy) {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {	
				if (!enemy.getGameInProgress()) stop();
				if (enemy.isAlive()) {
					ArrayList<Node> pathList = playerPath(enemy);
					if (pathList.size() == 0) {
						enemy.setChaseBehavior(new Wander());
						stop();
						enemy.start();
					} else if (enemy.getPlyIsInvincible()) {
						enemy.setChaseBehavior(new RunAway());
						stop();
						enemy.start();
					}
					enemy.followPath(pathList);
				}
			}
		}, 0, 500);		
	}

	@Override
	public void stop() {
		timer.cancel();		
	}
	
	private ArrayList<Node> playerPath(Enemy enemy) {
		return enemy.findPathTo(enemy.getPlayerX(), enemy.getPlayerY());
	}

}
