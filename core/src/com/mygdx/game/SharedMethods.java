package com.mygdx.game;

public class SharedMethods {

	public static float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

}
