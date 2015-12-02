package com.rtrailor.jumper.levels;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import com.rtrailor.jumper.objects.GameObjectID;
import com.rtrailor.jumper.objects.Player;
import com.rtrailor.jumper.objects.environment.Block;
import com.rtrailor.jumper.objects.environment.DeathBlock;
import com.rtrailor.jumper.objects.environment.SpikeBlock;
import com.rtrailor.jumper.objects.environment.WinBlock;
import com.rtrailor.jumper.window.GameObjectHandler;
import com.rtrailor.jumper.window.LevelParser;

public abstract class Level {
	
	private BufferedImage levelMap, tileSet;
	private GameObjectHandler objectHandler, environHandler;
	private String tileSetPath = "res/spritesheets/TestLevelBlocks.png", levelPath;
	private Player player;
	private int screenWidth, screenHeight;
	private int x = 0, y = 0;

	public Level(GameObjectHandler objectHandler, GameObjectHandler environHandler, String levelPath, Player player, int screenWidth, 
			int screenHeight) {
		this.objectHandler = objectHandler;
		this.environHandler = environHandler;
		this.levelPath = levelPath;
		this.player = player;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void generateLevel() {
		LevelParser parser = new LevelParser(levelPath, objectHandler, environHandler);
		parser.init();
		
		objectHandler.removeAll();
		environHandler.removeAll();
		
		int levelWidth = parser.getLevelWidth();
		int levelHeight = parser.getLevelHeight();
		int[] environmentArray = parser.getEnvironmentArray();
//		int[] backgroundArray = parser.getBackgroundArray(); /* Backgrounds to possibly be implemented later */
		
		int x = 0;
		int y = 0;
		
		int count = 0;
		
		for (int i = 0; i < environmentArray.length; i++, x++) {
			int objectNumber = environmentArray[i];
			if (x % levelWidth == 0) {
				x = 0;
				y++;
			}
			if (objectNumber == 3) {
				player.setX(x * 32);
				player.setY(y * 32);
			} 
			else if (objectNumber != 0) {
				int objectX = x * 32;
				int objectY = y * 32;
				
				int imageX;
				Image objectImage;
				if (objectNumber == 4) {
					imageX = (objectNumber * 32) - 32;
					objectImage = getObjectImage(tileSetPath, objectNumber, imageX, 0, 32, 32);
					WinBlock wb = new WinBlock(objectX, objectY, GameObjectID.WINBLOCK);
					environHandler.addGameObject(wb);
				} else if (objectNumber == 5) {
					imageX = (objectNumber * 32) - 32;
					objectImage = getObjectImage(tileSetPath, objectNumber, imageX, 0, 32, 32);
					DeathBlock db = new DeathBlock(objectX, objectY, GameObjectID.DEATHBLOCK);
					environHandler.addGameObject(db);
				} else if (objectNumber == 6 || objectNumber == 7 || objectNumber == 8) {
					imageX = (objectNumber *32) - 32;
					objectImage = getObjectImage(tileSetPath, objectNumber, imageX, 0, 32, 32);
					SpikeBlock sb = new SpikeBlock(objectX, objectY, GameObjectID.SPIKEBLOCK, objectImage);
					environHandler.addGameObject(sb);
				} else if (objectNumber == 9) {
					imageX = 0;
					int imageY = 32;
					objectImage = getObjectImage(tileSetPath, objectNumber, imageX, imageY, 32, 32);
					SpikeBlock sb = new SpikeBlock(objectX, objectY, GameObjectID.SPIKEBLOCK, objectImage);
					environHandler.addGameObject(sb);
				} else {
					imageX = (objectNumber * 32) - 32;
					objectImage = getObjectImage(tileSetPath, objectNumber, imageX, 0, 32, 32);
					
					Block block = new Block(objectX, objectY, GameObjectID.BLOCK, objectImage);
					
	//				objectHandler.addGameObject(block);
					environHandler.addGameObject(block);
				}
			}
		}
		
		
	}
	
	public GameObjectHandler getEnviron() {
		return environHandler;
	}
	
	public GameObjectHandler getObjectHandler() {
		return objectHandler;
	}
	
//	public abstract void placeEnvironObject(int i);
	
	private Image getObjectImage(String path, int objectNumber, int x, int y, int width, int height) {
		try {
			tileSet = ImageIO.read(new FileImageInputStream(new File(path)));
		} catch (IOException e) {
			System.err.println("Error opening tile set file");
		}
		Image image = tileSet.getSubimage(x, y, width, height);
		return image;
	}
	
	private BufferedImage loadLevelMap(String path) {
		try {
			levelMap = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			System.err.println("Error loading level image.");
		}
		return levelMap;
	}
}
