package com.rtrailor.jumper.objects.environment;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.LinkedList;

import com.rtrailor.jumper.objects.GameObject;
import com.rtrailor.jumper.objects.GameObjectID;

/* *
 *  Class:	DeathBlock
 *  ------------------
 *  Block that kills player's character upon contact.
 *  
 *  NOTE: This block is invisible to the player.
 * 
 */

public class DeathBlock extends GameObject {
	
	int width = 32, height = 32;

	public DeathBlock(float x, float y, GameObjectID id) {
		super(x, y, id);
	}

	@Override
	public void update(LinkedList<GameObject> collisionObjects) {
		
	}

	@Override
	public void render(Graphics g) {
		
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
