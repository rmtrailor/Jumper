package com.rtrailor.jumper.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LevelSelect {
	
	private GameState gameState;
	private int screenWidth, screenHeight;
	private int buttonY, buttonX;
	private Rectangle level1, level2, level3, level4, level5, level6, level7, level8, level9, level10;
	private Font levelFont = new Font("Century", Font.BOLD, 32);
	private boolean loadingLevel = false;
	
	public LevelSelect(GameState gameState, int screenWidth, int screenHeight) {
		this.gameState = gameState;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		buttonX = 64;
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
		buttonY = 100;
	}
	
	public void render(Graphics g, GameState gameState) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(levelFont);
		g.setColor(Color.white);
		
		if (gameState == GameState.LEVELSELECT && !loadingLevel) {
			g.drawString("1", buttonX + (buttonX / 2) + 8, buttonY + (buttonY / 2) + 8);
			g2d.draw(level1);
			
			g.drawString("2", buttonX + (buttonX/ 2) + 135, buttonY + (buttonY /2 ) + 8);
			g2d.draw(level2);
			
			g2d.draw(level3);
			
			g2d.draw(level4);
			
			g2d.draw(level5);
			
			g2d.draw(level6);
			
			g2d.draw(level7);
			
			g2d.draw(level8);
			
			g2d.draw(level9);
			
			g.drawString("10", buttonX + (buttonX/ 2) + 510, buttonY + (buttonY /2 ) + 165);
			g2d.draw(level10);
		} else if (gameState == GameState.LEVELSELECT && loadingLevel) {
			g.drawString("Loading", (screenWidth / 2) - 64, screenHeight / 2);
		}
	}
	
	public void isLoadingLevel(){
		loadingLevel = true;
	}

	public void doneLoadingLevel() {
		loadingLevel = false;
	}
}
