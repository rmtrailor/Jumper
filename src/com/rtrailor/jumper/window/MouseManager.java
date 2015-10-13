package com.rtrailor.jumper.window;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

	private GameState gameState; 
	private Game game;
	private int screenWidth, screenHeight;
	
	public MouseManager(Game game, int screenWidth, int screenHeight) {
//		this.gameState = gameState;
		this.game = game;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		if (gameState == GameState.MAINMENU) {
		if (game.getGameState() == GameState.MAINMENU) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			/**
			 * play = new Rectangle((screenWidth / 2 - 150), 200, 275, 50);
				controls = new Rectangle((screenWidth / 2) - 150, 300, 275, 50);
				quit = new Rectangle((screenWidth / 2) - 150, 400, 275, 50);
			 */
			if (mouseX >= (screenWidth / 2) - 150 && mouseX <=  ((screenWidth / 2) - 150) + 275 
					&& mouseY >= 200 && mouseY <= 250) {
//				gameState = GameState.INGAME;
				game.setGameState(GameState.LEVELSELECT);
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 300 && mouseY <= 350) {
				/////////////////////////////
				// enter control menu here
				////////////////////////////
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 400 && mouseY <= 450) {
				game.shutdown();
			}
		}
		if (game.getGameState() == GameState.LEVELSELECT) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			int buttonX = 64;
			int buttonY = 100;
			
			/**
			 * buttonX = 64;
				buttonY = 100;
				level1 = new Rectangle(buttonX, buttonY, 96, 96);
				level2 = new Rectangle(buttonX * 3, buttonY, 96, 96);
				level3 = new Rectangle(buttonX * 5, buttonY, 96, 96);
				level4 = new Rectangle(buttonX * 7, buttonY, 96, 96);
				level5 = new Rectangle(buttonX * 9, buttonY, 96, 96);
				buttonY = 250;
				level6 = new Rectangle(buttonX, buttonY, 96, 96);
				level7 = new Rectangle(buttonX * 3, buttonY, 96, 96);
				level8 = new Rectangle(buttonX * 5, buttonY, 96, 96);
				level9 = new Rectangle(buttonX * 7, buttonY, 96, 96);
				level10 = new Rectangle(buttonX * 9, buttonY, 96, 96);
			 */
		
			
			/* LEVEL 1 */
			if (mouseX >= buttonX && mouseX <= (buttonX + 96) && mouseY >= buttonY && mouseY <= (buttonY + 96)) {
//				game.setGameState(GameState.INGAME);
				game.setLevel(1);
				boolean levelPrepared = game.prepareLevel();
				if (levelPrepared) {
					game.setGameState(GameState.INGAME);
				}
			} else if (mouseX >= buttonX *3 && mouseY <= ((buttonX * 32) + 96) && mouseY >= buttonY && mouseY <= (buttonY + 96)) {
				game.setLevel(2); /* Level 2 */
				boolean levelPrepared = game.prepareLevel();
				if (levelPrepared) {
					game.setGameState(GameState.INGAME);
				} 
			} else if (mouseX >= buttonX * 9 && mouseY <= ((buttonX * 32) + 96) && mouseY >= 250 && mouseY <= (250 + 96)) {
				game.setLevel(10);
				boolean levelPrepared = game.prepareLevel();
				if (levelPrepared) {
					game.setGameState(GameState.INGAME);
				} 
			}
		}
		if (game.getGameState() == GameState.DEATHSCREEN || game.getGameState() == GameState.PAUSED) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			int buttonX = 64;
			int buttonY = 100;
			
			if (mouseX >= (screenWidth / 2) - 150 && mouseX <=  ((screenWidth / 2) - 150) + 275 
					&& mouseY >= 200 && mouseY <= 250) {
				game.prepareLevel();
				game.setGameState(GameState.INGAME);
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 300 && mouseY <= 350) {
				game.setGameState(GameState.LEVELSELECT);
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 400 && mouseY <= 450) {
				game.setGameState(GameState.MAINMENU);
			}
		}
		if (game.getGameState() == GameState.WINSCREEN) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			if (mouseX >= (screenWidth / 2) - 150 && mouseX <=  ((screenWidth / 2) - 150) + 275 
					&& mouseY >= 200 && mouseY <= 250) {
				game.flushLevel();
				game.nextLevel();
				game.prepareLevel();
				game.setGameState(GameState.INGAME);
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 300 && mouseY <= 350) {
				game.setGameState(GameState.LEVELSELECT);
			} else if (mouseX >= (screenWidth / 2) - 150 && mouseX <= ((screenWidth / 2) - 150) + 275
					&& mouseY >= 400 && mouseY <= 450) {
				game.setGameState(GameState.MAINMENU);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
		
}
