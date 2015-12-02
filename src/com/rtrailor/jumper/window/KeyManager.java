package com.rtrailor.jumper.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rtrailor.jumper.objects.Player;

public class KeyManager extends KeyAdapter {
	
	private Game game;
	private Player player;
	
	public KeyManager(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (game.getGameState() == GameState.INGAME) {
			if (key == KeyEvent.VK_A) {
				player.setMovingLeft(true);
			}
			
			if (key == KeyEvent.VK_D) {
				player.setMovingRight(true);
			}
			
			if (key == KeyEvent.VK_SPACE && !player.isJumping()) {
				player.setJumping(true);
				player.setYVelocity(player.getJumpSpeed());
			}
			
			if (key == KeyEvent.VK_ENTER) {
				game.setGameState(GameState.PAUSED);
			}
		}
		else if (game.getGameState() == GameState.PAUSED) {
			if (key == KeyEvent.VK_ENTER) {
				game.setGameState(GameState.INGAME);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (game.getGameState() == GameState.INGAME) {
			if (key == KeyEvent.VK_A) {
				player.setMovingLeft(false);
				player.setXVelocity(0);
			}
			
			if (key == KeyEvent.VK_D) {
				player.setMovingRight(false);
				player.setXVelocity(0);
			}
		}
	}
}
