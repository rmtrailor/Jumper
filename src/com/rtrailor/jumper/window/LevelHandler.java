package com.rtrailor.jumper.window;

import com.rtrailor.jumper.levels.Level;
import com.rtrailor.jumper.levels.Level1;
import com.rtrailor.jumper.levels.Level10;
import com.rtrailor.jumper.levels.Level2;
import com.rtrailor.jumper.objects.Player;

public class LevelHandler {
	
	private GameObjectHandler handler, environHandler;
	private Player player;
	private int screenWidth, screenHeight;
	private Level currentLevel; // TODO: Create an abstract level later
	private String[] levels = {"res/levels/level_1.json", "res/levels/level_2.json", "res/levels/level_10.json"};
	
	public LevelHandler(GameObjectHandler handler, GameObjectHandler environHandler, Player player, int screenWidth, int screenHeight) {
		this.handler = handler;
		this.environHandler = environHandler;
		this.player = player;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
//	public void chooseLevel(TestLevel level) {
	public void chooseLevel(int levelNum) {
//		currentLevel = level;
		if (levelNum == 1) {
			currentLevel = new Level1(handler, environHandler, levels[levelNum - 1], player, screenWidth, screenHeight);
		} else if (levelNum == 2) {
			currentLevel = new Level2(handler, environHandler, levels[levelNum - 1], player, screenWidth, screenHeight);
		} else if (levelNum == 10) {
			currentLevel = new Level10(handler, environHandler, levels[2], player, screenWidth, screenHeight);
		}
	}
	
	public boolean loadLevel() {
		handler.removeAll();
		environHandler.removeAll();
		System.out.println(environHandler.getNumObjects());
		if (currentLevel != null) {
			currentLevel.generateLevel();
			return true;
		}
		return false;
	}
	
	public GameObjectHandler getEnviron() {
		environHandler = currentLevel.getEnviron();
		return environHandler;
	}
	
	public GameObjectHandler getObjects() {
		handler = currentLevel.getObjectHandler();
		return handler;
	}
	
	public void clearLevel() {
		if (!handler.isEmpty()) {
			handler.removeAll();
		}
	}

}
