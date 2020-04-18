package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enemy.Enemy;
import com.mygdx.player.Player;

public class StageHandler extends Stage {
	
	Player player;
	Enemy enemy;
	
	public StageHandler() {
		player = new Player(100, 100, 2);
		enemy = new Enemy(500, 500, 2);
		
		addActor(player);
		addActor(enemy);
	}
	
	@Override
	public void act(float delta) {
		player.act(delta);
		enemy.updatePlayerPosition(player.getX(), player.getY());
		enemy.act(delta);
	}

}
