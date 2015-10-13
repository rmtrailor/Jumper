package com.rtrailor.jumper.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DeathScreen {

	private GameState gameState;
	private int screenWidth, screenHeight;
	private int buttonY, buttonX;
	private Rectangle retry, levelSelect, mainMenu;
	private Font font = new Font("Century", Font.BOLD, 32), promptFont = new Font("Century", Font.BOLD, 48);
	
	private final String PROMPT = "You Died!", RETRY = "Retry", LS = "Level Select", MM = "Main Menu";
	
	public DeathScreen(GameState gameState, int screenWidth, int screenHeight) {
		this.gameState = gameState;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		buttonX = 64;
		buttonY = 100;
		
		retry = new Rectangle((screenWidth / 2 - 150), 200, 275, 50);
		levelSelect = new Rectangle((screenWidth / 2) - 150, 300, 275, 50);
		mainMenu = new Rectangle((screenWidth / 2) - 150, 400, 275, 50);
	}
	
	public void render(Graphics g, GameState gameState) {
		if (gameState == GameState.DEATHSCREEN) {
			Graphics2D g2d = (Graphics2D) g;
			g.setFont(promptFont);
			g.setColor(Color.white);
			g.drawString(PROMPT, (screenWidth / 2) - 120, 100);
			g.setFont(font);
			
			g2d.draw(retry);
			g.drawString(RETRY, (retry.x + (retry.width / 3)), retry.y + retry.height - 10);
			
			g2d.draw(levelSelect);
			g.drawString(LS, levelSelect.x + (levelSelect.width / 5), levelSelect.y + levelSelect.height - 10);
			
			g2d.draw(mainMenu);
			g.drawString(MM, mainMenu.x + (mainMenu.width / 5), mainMenu.y + mainMenu.height - 10);
		}
	}
}
