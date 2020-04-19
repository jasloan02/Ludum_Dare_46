package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enemy.Enemy;
import com.mygdx.player.Player;

public class StageHandler extends Stage {
	
	Player player;
	Enemy enemy;
	
	OrthographicCamera camera;
	TiledMap tilemap;
	OrthogonalTiledMapRenderer tilemapRender;
	
	public StageHandler() {
		player = new Player(100, 100, 2);
		enemy = new Enemy(500, 500, 2);
		
		addActor(player);
		addActor(enemy);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		
		tilemap = new TmxMapLoader().load("Field.tmx");
		tilemapRender = new OrthogonalTiledMapRenderer(tilemap);
	}
	
	public void renderTiles() {
		camera.update();
		tilemapRender.setView(camera);
		tilemapRender.render();
	}
	
	@Override
	public void act(float delta) {
		player.act(delta);
		enemy.updatePlayerPosition(player.getX(), player.getY());
		enemy.act(delta);
	}

}
