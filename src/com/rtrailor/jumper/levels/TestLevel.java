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
import com.rtrailor.jumper.objects.environment.NormalBlock;
import com.rtrailor.jumper.window.GameObjectHandler;
import com.rtrailor.jumper.window.LevelParser;

public class TestLevel {
	
	private BufferedImage levelMap, tileSet;
	private String levelPath = "/TestLevel.png";
	private GameObjectHandler objectHandler;
	private Player player;
	private int screenWidth, screenHeight;
	
	public TestLevel(GameObjectHandler objectHandler, Player player, int screenWidth, int screenHeight) {
		this.objectHandler = objectHandler;
		this.player = player;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void generateLevel() {
		LevelParser parser = new LevelParser("res/levels/TestLevel.json", objectHandler, objectHandler);
		parser.init();
		
		int levelWidth = parser.getLevelWidth();
		int levelHeight = parser.getLevelHeight();
		int[] environmentArray = parser.getEnvironmentArray();
		int[] backgroundArray = parser.getBackgroundArray();
//		String tileSetPath = parser.getTileSetPath().substring(3);
		String tileSetPath = "res/spritesheets/TestLevelBlocks.png";
		
		int x = 0;
		int y = 0;
		// test
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
			} else if (objectNumber != 0) {
				int objectX = x * 32;
				int objectY = y * 32;
//				System.out.println("x: " + objectX + " y: " + objectY);
				int imageX = (objectNumber * 32) - 32;
				Image objectImage = getObjectImage(tileSetPath, objectNumber, imageX, 0, 32, 32);
				
				Block block = new Block(objectX, objectY, GameObjectID.BLOCK, objectImage);
				
				objectHandler.addGameObject(block);
			}
		}
		
		
		
		
//		BufferedImage levelMap = loadLevelMap(levelPath);
//		int levelWidth = levelMap.getWidth();
//		int levelHeight = levelMap.getHeight();
//		
//		for (int x = 0; x < levelWidth; x++) {
//			for (int y = 0; y < levelHeight; y++) {
//				int pixel = levelMap.getRGB(x, y);
//				int r = (pixel >> 16) & 0xff;
//				int g = (pixel >> 8) & 0xff;
//				int b = pixel & 0xff;
//				
//				if (r == 255 && g == 255 && b == 255) {
//					NormalBlock block = new NormalBlock(x * 32, y * 32, GameObjectID.normalBlock);
//					objectHandler.addGameObject(block);
//				}
//				
//				if (r == 0 && g == 0 && b == 255) {
//					player.setX(x * 32);
//					player.setY(y * 32);
//				}
//			}
//		}
		
		
//		int numBlocks = 24;
//		float x = 0, y = screenHeight - (screenHeight / 10);
//		
//		placeNormalBlockRow(numBlocks, x, y);
//		
//		numBlocks = 10;
//		x = screenWidth / 5; 
//		y = screenHeight / 2;
//		
//		placeNormalBlockRow(numBlocks, x, y);
//		
//		
//		numBlocks = 3;
//		x = screenHeight - (screenHeight / 30);
//		y = screenHeight - (screenHeight / 3);
//		
//		placeNormalBlockRow(numBlocks, x, y);
//		
//		numBlocks = 15;
//		x = 0;
//		y = 0;
//		
//		placeNormalBlockColumn(numBlocks, x, y);
	}
	
	private Image getObjectImage(String path, int objectNumber, int x, int y, int width, int height) {
		try {
//			tileSet = ImageIO.read(getClass().getResource(path));
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
	
	
	
	/**
	 * Places a row of Normal Blocks
	 * @param numBlocks
	 * @param startX
	 */
	public void placeNormalBlockRow(int numBlocks, float startX, float startY) {
		for (int i = numBlocks; i >= 0; i--) {
			NormalBlock block = new NormalBlock(startX, startY, GameObjectID.normalBlock);
			objectHandler.addGameObject(block);
			startX += block.getWidth();
		}
	}

	public void placeNormalBlockColumn(int numBlocks, float startX, float startY) {
		for (int i = numBlocks; i >= 0; i--) {
			NormalBlock block = new NormalBlock(startX, startY, GameObjectID.normalBlock);
			objectHandler.addGameObject(block);
			startY += block.getHeight();
		}
	}
	
}
