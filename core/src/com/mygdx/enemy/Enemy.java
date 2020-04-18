package com.mygdx.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor {

	final TextureAtlas IDLE_ATLAS, WALK_ATLAS, SHOOT_ATLAS;
	final Animation<TextureRegion> IDLE, WALK, SHOOT;
	
	Animation<TextureRegion> animation;
	
	float x, y, mx, my;
	float width, height;
	float speed = 150f;
	float elapsedTime = 0;
	float attackStartTime = 0;
	
	boolean facingRight = true;
	boolean shooting = false;

	public Enemy(float x, float y, int scale) {
		this.x = x;
		this.y = y;
		
		width = 32 * scale;
		height = 32 * scale;
		
		IDLE_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_IDLE-packed/pack.atlas"));
		IDLE = new Animation<TextureRegion>(1/5f, IDLE_ATLAS.getRegions());
		
		WALK_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_Walk-packed/pack.atlas"));
		WALK = new Animation<TextureRegion>(1/6f, WALK_ATLAS.getRegions());
		
		SHOOT_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_Shoot-packed/pack.atlas"));
		SHOOT = new Animation<TextureRegion>(1/3f, SHOOT_ATLAS.getRegions());
		
		animation = IDLE;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(getCurrentFrame(), x, y, width, height);
	}
	
	private TextureRegion getCurrentFrame() {
		TextureRegion frame = animation.getKeyFrame(elapsedTime, true);
		
		if (facingRight) {
			if (frame.isFlipX()) frame.flip(true, false);
			return frame;
		}
		else {
			if (!frame.isFlipX()) frame.flip(true, false);
			return frame;
		}
	}
	
	@Override
	public void act(float delta) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		mx = 0;
		my = 0;
		
		if (!shooting) {
			x += mx;
			y += my;
		}
	}

}
