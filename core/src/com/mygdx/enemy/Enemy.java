package com.mygdx.enemy;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.SharedMethods;

public class Enemy extends Actor {

	final TextureAtlas IDLE_ATLAS, WALK_ATLAS, SHOOT_ATLAS;
	final Animation<TextureRegion> IDLE, WALK, SHOOT;
	
	Animation<TextureRegion> animation;
	
	float mx, my, pX, pY;
	float width, height;
	float speed = 150f;
	float elapsedTime = 0;
	float sequenceStartTime = 0;
	float attackStartTime = 0;
	float xDir = 0.5f, yDir = 0.5f;
	
	boolean facingRight = true;
	boolean shooting = false;
	boolean pauseSequence = true;

	public Enemy(float x, float y, int scale) {
		setX(x);
		setY(y);
		
		width = 32 * scale;
		height = 32 * scale;
		
		IDLE_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_IDLE-packed/pack.atlas"));
		IDLE = new Animation<TextureRegion>(1/5f, IDLE_ATLAS.getRegions());
		
		WALK_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_Walk-packed/pack.atlas"));
		WALK = new Animation<TextureRegion>(1/6f, WALK_ATLAS.getRegions());
		
		SHOOT_ATLAS = new TextureAtlas(Gdx.files.internal("Hunter_Shoot-packed/pack.atlas"));
		SHOOT = new Animation<TextureRegion>(1/2f, SHOOT_ATLAS.getRegions());
		
		animation = IDLE;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(getCurrentFrame(), getX(), getY(), width, height);
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
		
		updateState(delta);
		
		if (!shooting) {
			setX(getX() + mx);
			setY(getY() + my);
		}
	}
	
	public void updatePlayerPosition(float pX, float pY) {
		this.pX = pX;
		this.pY = pY;
	}
	
	private void updateState(float delta) {
		final float SEQUENCE_TIME = 2;
		
		Random random = new Random();
		
		if (elapsedTime - sequenceStartTime >= SEQUENCE_TIME) {
			pauseSequence = !pauseSequence;
			sequenceStartTime = elapsedTime;
			
			xDir = (random.nextFloat() * 2 - 1) * 0.5f;
			yDir = (random.nextFloat() * 2 - 1) * 0.5f;
		}
		else {
			if (SharedMethods.distance(getX(), getY(), pX, pY) <= 300) {
				shooting = true;
			}
			else {
				shooting = false;
			}
			
			if (!pauseSequence && !shooting) {
				mx = xDir * (speed * delta);
				my = yDir * (speed * delta);
			}
		}
		
		if (shooting) {
			animation = SHOOT;
		}
		else if (mx != 0 && my != 0) {
			animation = WALK;
		}
		else {
			animation = IDLE;
		}
	}

}
