package com.rtrailor.jumper.objects.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.LinkedList;

import com.rtrailor.jumper.objects.GameObject;
import com.rtrailor.jumper.objects.GameObjectID;

public class NormalBlock extends GameObject {
	
	private float width = 32, height = 32;

	public NormalBlock(float x, float y, GameObjectID id) {
		super(x, y, id);
	}

	@Override
	public void update(LinkedList<GameObject> collisionObjects) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int) x, (int) y, (int) width, (int) height);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}

	@Override
	public Float getBottomBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	@Override
	public Float getTopBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	@Override
	public Float getLeftBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	@Override
	public Float getRightBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

}
