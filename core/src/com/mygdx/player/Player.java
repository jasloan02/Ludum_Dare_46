package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	
	final TextureAtlas IDLE_ATLAS, WALK_ATLAS, ATTACK_ATLAS;
	final Animation<TextureRegion> IDLE, WALK, ATTACK;
	
	final Slash SLASH;
	
	Animation<TextureRegion> animation;
	
	float mx, my;
	float width, height;
	float speed = 200f;
	float elapsedTime = 0;
	float attackStartTime = 0;
	
	boolean facingRight = true;
	boolean attacking = false;

	public Player(float x, float y, int scale) {
		setX(x);
		setY(y);
		
		width = 32 * scale;
		height = 32 * scale;
		
		IDLE_ATLAS = new TextureAtlas(Gdx.files.internal("Buffalo_IDLE-packed/pack.atlas"));
		IDLE = new Animation<TextureRegion>(1/5f, IDLE_ATLAS.getRegions());
		
		WALK_ATLAS = new TextureAtlas(Gdx.files.internal("Buffalo_Walk-packed/pack.atlas"));
		WALK = new Animation<TextureRegion>(1/6f, WALK_ATLAS.getRegions());
		
		ATTACK_ATLAS = new TextureAtlas(Gdx.files.internal("Buffalo_Attack-packed/pack.atlas"));
		ATTACK = new Animation<TextureRegion>(1/7f, ATTACK_ATLAS.getRegions());
		
		SLASH = new Slash();
		
		animation = IDLE;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(getCurrentFrame(), getX(), getY(), width, height);
		
		if (attacking) {
			if (facingRight) {
				SLASH.draw(batch, getX() + width - 10, getY(), width, height, false);
			}
			else {
				SLASH.draw(batch, getX() - width + 10, getY(), width, height, true);
			}
		}
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
		
		updateInput(delta);
		updateState();
		
		SLASH.update(elapsedTime - attackStartTime);
		
		if (!attacking) {
			setX(getX() + mx);
			setY(getY() + my);
		}
	}
	
	private void updateInput(float delta) {
		Input input = Gdx.input;
		
		if (input.isKeyPressed(Input.Keys.UP)) {
			my += speed * delta;
		}
		if (input.isKeyPressed(Input.Keys.LEFT)) {
			mx -= speed * delta;	
		}
		if (input.isKeyPressed(Input.Keys.DOWN)) {
			my -= speed * delta;
		}
		if (input.isKeyPressed(Input.Keys.RIGHT)) {
			mx += speed * delta;
		}
		if (input.isKeyJustPressed(Input.Keys.A) && !attacking) {
			attacking = true;
			attackStartTime = elapsedTime;
		}
	}
	
	private void updateState() {
		if (mx != 0 || my != 0) {
			animation = WALK;
			
			if (mx < 0) facingRight = false;
			else if (mx > 0) facingRight = true;
		}
		else {
			animation = IDLE;
		}
		
		if (attacking) {
			animation = ATTACK;
		}
		if (attacking && animation.isAnimationFinished(elapsedTime - attackStartTime)) {
			attacking = false;
		}
	}

}
