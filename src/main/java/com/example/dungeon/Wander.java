package com.example.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Wander implements ChaseBehavior{
	private Timer timer;
	
	public Wander() {
		this.timer = new Timer();
	}

	@Override
	public void start(Enemy enemy) {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {	
				if (!enemy.getGameInProgress()) stop();
				if (enemy.isAlive()) {
					ArrayList<Node> pathList = wander(enemy);
					if (enemy.findPathTo(enemy.getPlayerX(), enemy.getPlayerY()).size() != 0) {
						enemy.setChaseBehavior(new ChasePlayer());
						stop();
						enemy.start();
					}
					enemy.followPath(pathList);
				}
			}
		}, 0, 1200);		
	}

	@Override
	public void stop() {
		timer.cancel();		
	}
	
	private ArrayList<Node> wander(Enemy enemy) {
		ArrayList<Node> path = new ArrayList<Node>();
		
		int x = enemy.getX();
		int y = enemy.getY();
		
		int[] dx = {-1, +1, 0, 0};
		int[] dy = {0, 0, +1, -1};
		
		for (int i=0; i < 4; i++) {
			int futureX = x + dx[i];
			int futureY = y + dy[i];
	
			if (enemy.checkMoveable(futureX, futureY)) {
				Node node = new Node(0,0, futureX, futureY);
				path.add(node);
			}
		}
		
		Collections.shuffle(path);
		
		return path;
	}
}
