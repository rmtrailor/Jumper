package com.rtrailor.jumper.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import com.rtrailor.jumper.window.GameObjectHandler;

public class Player extends GameObject {
	
	private BufferedImage playerImage;
	private String playerImagePath = "res/spritesheets/TestPlayer.png";
	private float width = 32, height = 64;
	private int health = 100;
	private float gravity = 0.1f;
	private final float MAX_VELOCITY = 1.0f;
	private float movementSpeed = 4.0f, jumpSpeed = -5.5f;
	private boolean isMovingLeft = false, isMovingRight = false;
	private boolean atWin = false; 
	private GameObjectHandler objectHandler, environHandler;
	
	public Player(float x, float y, GameObjectID id, GameObjectHandler objectHandler, GameObjectHandler environHandler) {
		super(x, y, id);
		this.objectHandler = objectHandler;
		this.environHandler = environHandler;
		System.out.println(playerImagePath);
		playerImage = loadImage(playerImagePath);
	}

	public void update(LinkedList<GameObject> collisionObjects) {
		checkMovements();
		x += xVelocity;
		y += yVelocity;
		
		if (falling || jumping && yVelocity <= MAX_VELOCITY) {
			yVelocity += gravity;
		}
		
		checkCollisions();
	}
	
	public void checkCollisions() {
		for (int i = 0; i < environHandler.getNumObjects(); i++) {
			GameObject tempObject = environHandler.getObject(i);	
			
			if (tempObject.getGameObjectID() != GameObjectID.PLAYER) {
				if (tempObject.getGameObjectID() == GameObjectID.WINBLOCK) {
					if (getBottomBounds().intersects(tempObject.getTopBounds()) ||
							getTopBounds().intersects(tempObject.getBottomBounds()) ||
							getLeftBounds().intersects(tempObject.getRightBounds()) ||
							getRightBounds().intersects(tempObject.getLeftBounds())) {
						atWin = true;
					}
				} 
				else if (tempObject.getGameObjectID() == GameObjectID.SPIKEBLOCK) { 
					if (getBottomBounds().intersects(tempObject.getTopBounds()) ||
							getTopBounds().intersects(tempObject.getBottomBounds()) ||
							getLeftBounds().intersects(tempObject.getRightBounds()) ||
							getRightBounds().intersects(tempObject.getLeftBounds())) {
						damagePlayer(getHealth());
					}
				} else {
					if (getBottomBounds().intersects(tempObject.getTopBounds())) {
						y -= yVelocity;
						yVelocity = 0;
						falling = false;
						jumping = false;
					} else {
	//					if (!jumping && !falling) {
	//						falling = true;
	//					}
						falling = true;
					}
					
					if (getTopBounds().intersects(tempObject.getBottomBounds())) {
						y += height / 10;
						yVelocity = 0;
						falling = true;
					}
					
					if (getLeftBounds().intersects(tempObject.getRightBounds())) {
						x += 2;
						xVelocity = 0;
						isMovingLeft = false;
					}
					
					if (getRightBounds().intersects(tempObject.getLeftBounds())) {
						x -= 2;
						xVelocity = 0;
						isMovingRight = false;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		if (!isDead()) {
			g.drawImage(playerImage, (int) x, (int) y, 32, 64, null);
		} else {
			this.setMovementSpeed(0);
		}
		
	//	showBounds(g);	/* Uncomment this to show player bounds for debugging */
		
	}
	
	public void reset() {
		health = 100;
		movementSpeed = 4.0f;
		yVelocity = 0;
		atWin = false;
	}
	
	private void checkMovements() {
		if (isMovingLeft) {
			setXVelocity(getMovementSpeed() * -1);
		}
		if (isMovingRight) {
			setXVelocity(getMovementSpeed());
		}
	}
	
	private BufferedImage loadImage(String path) {
		BufferedImage pI = null;
		try {
			pI = ImageIO.read(new FileImageInputStream(new File(path)));
		} catch (IOException e) {
			System.err.println("Error loading player image.");
		}
		return pI;
	}
	
	/**
	 * To visually see the collision boxes of the player.
	 * For testing only.
	 * @param g
	 */
	private void showBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBottomBounds());
		g.setColor(Color.green);
		g2d.draw(getTopBounds());
		g.setColor(Color.yellow);
		g2d.draw(getLeftBounds());
		g.setColor(Color.orange);
		g2d.draw(getRightBounds());
	}
	
	public int getHealth() {
		return health;
	}
	
	public void damagePlayer(int damage) {
		health -= damage;
	}
	
	public void healPlayer(int heal) {
		health += heal;
	}
	
	public boolean isDead() {
		if (health <= 0) {
			return true;
		} 
		return false;
	}
	
	public float getMovementSpeed() {
		return movementSpeed;
	}
	
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	public float getJumpSpeed() {
		return jumpSpeed;
	}
	
	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}
	
	public boolean atWin() {
		return atWin;
	}

	@Override
	public Float getBottomBounds() {
		return new Rectangle2D.Float(x + (width / 6), y + (height - (height / 20)), width / 1.5f, height / 10);
	}

	@Override
	public Float getTopBounds() {
		return new Rectangle2D.Float(x + (width / 6), y - (height / 25), width / 1.5f, height / 10);
	}

	@Override
	public Float getLeftBounds() {
		return new Rectangle2D.Float(x - (width / 20), y + (width / 6), width / 10, height / 1.25f);
	}

	@Override
	public Float getRightBounds() {
		return new Rectangle2D.Float(x + (width - (width / 20)), y + (width / 6), width / 10, height / 1.25f);
	}

	

}
