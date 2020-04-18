package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Slash {
	
	final TextureAtlas ATLAS;
	final Animation<TextureRegion> SLASH;
	
	float elapsedTime;
	
	public Slash() {
		ATLAS = new TextureAtlas(Gdx.files.internal("Slash_Attack-packed/pack.atlas"));
		SLASH = new Animation<TextureRegion>(1/8f, ATLAS.getRegions());
	}
	
	public void draw(Batch batch, float x, float y, float width, float height, boolean flipped) {
		if (!SLASH.isAnimationFinished(elapsedTime)) {
			TextureRegion frame = SLASH.getKeyFrame(elapsedTime);
			
			if (!flipped && frame.isFlipX()) {
				frame.flip(true, false);
			}
			else if (flipped && !frame.isFlipX()) {
				frame.flip(true, false);
			}
			
			batch.draw(frame, x, y, width, height);
		}
	}
	
	public void update(float elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

}
