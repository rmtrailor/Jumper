package com.rtrailor.jumper.objects.environment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.LinkedList;

import com.rtrailor.jumper.objects.GameObject;
import com.rtrailor.jumper.objects.GameObjectID;

/* *
 *  Class:	SpikeBlock
 *  ------------------
 *  Block that resembles spikes. This block is an obstacle that the player must
 *  avoid. The spikes kills the player's character upon contact. 
 * 
 */

public class SpikeBlock extends GameObject {

	private int width = 32, height = 32;
	private Image image;

	public SpikeBlock(float x, float y, GameObjectID id, Image image) {
		super(x, y, id);
		this.image = image;
	}

	@Override
	public void update(LinkedList<GameObject> collisionObjects) {

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(image, (int) x, (int) y, width, height, null);
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
