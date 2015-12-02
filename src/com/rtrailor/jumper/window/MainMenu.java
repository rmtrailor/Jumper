package com.rtrailor.jumper.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/* * 
 *  Class:  MainMenu
 *  ----------------
 *  Creates the screen used as the main menu of the game.
 * 
 */

public class MainMenu {
	
	private GameState gameState;
	private int screenWidth, screenHeight;
	private Rectangle play, controls, quit;
	private final String TITLE = "JUMPER", PLAY = "PLAY", CONTROLS = "CONTROLS", QUIT = "QUIT";
	private Font menuFont = new Font("Century", Font.BOLD, 72);
	private Font buttonFont = new Font("Century", Font.BOLD, 32);

	public MainMenu(GameState gameState, int screenWidth, int screenHeight) {
		this.gameState = gameState;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		play = new Rectangle((screenWidth / 2 - 150), 200, 275, 50);
		controls = new Rectangle((screenWidth / 2) - 150, 300, 275, 50);
		quit = new Rectangle((screenWidth / 2) - 150, 400, 275, 50);
	}
	
	public void render(Graphics g, GameState gameState) {
		if (gameState == GameState.MAINMENU) {
			Graphics2D g2d = (Graphics2D) g;
			g.setFont(menuFont);
			g.setColor(Color.white);
			g.drawString(TITLE, (screenWidth / 2) - 175, 100);
			g.setFont(buttonFont);
			g2d.draw(play);
			g.drawString(PLAY, (play.x + (play.width / 3)), play.y + play.height - 10);
			g2d.draw(controls);
			g.drawString(CONTROLS, controls.x + (play.width / 5), controls.y + controls.height - 10);
			g2d.draw(quit);
			g.drawString(QUIT, quit.x + (quit.width / 3), quit.y + quit.height - 10);
		}
	}
	
}
