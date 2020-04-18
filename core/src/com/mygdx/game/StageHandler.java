package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enemy.Enemy;
import com.mygdx.player.Player;

public class StageHandler extends Stage {
	
	Player player;
	Enemy enemy;
	
	public StageHandler() {
		player = new Player(300, 300, 2);
		enemy = new Enemy(300, 300, 2);
		
		addActor(player);
		addActor(enemy);
	}
	
	@Override
	public void act(float delta) {
		player.act(delta);
		enemy.act(delta);
	}

}
