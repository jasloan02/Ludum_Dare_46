package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.player.Player;

public class MainGame extends ApplicationAdapter {
	StageHandler stageHandler;
	
	@Override
	public void create () {
		stageHandler = new StageHandler();
		Gdx.input.setInputProcessor(stageHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.8f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stageHandler.act(Gdx.graphics.getDeltaTime());
		stageHandler.draw();
	}
	
	@Override
	public void dispose () {
		stageHandler.dispose();
	}
}
