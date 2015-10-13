package com.rtrailor.jumper.window;

import com.rtrailor.jumper.objects.Player;

public class Camera {
	
	private float x, y;
	private int screenWidth, screenHeight;

	public Camera(float initialX, float initialY, int screenWidth, int screenHeight) {
		x = initialX;
		y = initialY;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void update(Player player) {
		x = -player.getX() + (screenWidth / 2);
		y = -player.getY() + (screenHeight / 2);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
