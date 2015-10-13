package com.rtrailor.jumper.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected GameObjectID id;
	protected float x, y;
	protected float xVelocity = 0, yVelocity = 0;
	protected boolean falling = true, jumping = false;
	
	public GameObject(float x, float y, GameObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void update(LinkedList<GameObject> collisionObjects);
	public abstract void render(Graphics g);
	public abstract Rectangle2D.Float getBottomBounds();
	public abstract Rectangle2D.Float getTopBounds();
	public abstract Rectangle2D.Float getLeftBounds();
	public abstract Rectangle2D.Float getRightBounds();
	
	public GameObjectID getGameObjectID() {
		return this.id;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public float getXVelocity() {
		return xVelocity;
	}

	public float getYVelocity() {
		return yVelocity;
	}

	public void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public void setYVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	public boolean isFalling() {
		return falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
}
