package com.rtrailor.jumper.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.rtrailor.jumper.levels.TestLevel;
import com.rtrailor.jumper.objects.GameObjectID;
import com.rtrailor.jumper.objects.Player;

/* *
 *  Class:	Game
 *  ------------
 *  Main class for the game. Initiliazes the necessary components used in the game and
 *  starts/runs the game loop.
 *
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3398252241937273322L;
	private static int width = 800, height = 600;
	private static final String TITLE = "Jumper";
	private GameObjectHandler objectHandler;
	private GameObjectHandler environHandler;
	private LevelHandler levelHandler;
	private Camera camera;
	private Player player;
	
	private MainMenu mainMenu;
	private LevelSelect levelSelect;
	private PauseMenu pauseMenu;
	private WinScreen winScreen;
	private DeathScreen deathScreen;
	
	private GameState gameState;	
	private LevelParser levelParser;
	private int levelNum;
	private final int NUM_LEVEL = 10;
	
	private boolean isRunning = false;
	private Thread thread;
	
	/**
	 * Starts the thread for the game.
	 */
	public synchronized void start() {
		if (!isRunning) {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	/**
	 * Stops the thread for the game.
	 */
	public synchronized void stop() {
		if (isRunning) {
			return;
		}
		System.exit(1);
	}
	
	/**
	 * Shutsdown the game by allowing the thread to be stopped.
	 */
	public void shutdown() {
		isRunning = false;
	}
	
	/**
	 * Initializes all the necessary components of the game. Adds the listeners for mousey and
	 * key input. Sets the game state to the main menu.
	 */
	private void init() {
		/* Initializing the different screens used in the game */
		mainMenu = new MainMenu(gameState, width, height);
		levelSelect = new LevelSelect(gameState, width, height);
		pauseMenu = new PauseMenu(gameState, width, height);
		winScreen = new WinScreen(gameState, width, height);
		deathScreen = new DeathScreen(gameState, width, height);
		
		/* Initializing object handlers and their necessary objects */
		objectHandler = new GameObjectHandler();
		environHandler = new GameObjectHandler();
		player = new Player(0, 0, GameObjectID.PLAYER, objectHandler, environHandler);
		objectHandler.addGameObject(player);
		levelHandler = new LevelHandler(objectHandler, environHandler, player, width, height);
		camera = new Camera(player.getX(), player.getY(), width, height);
		
		this.requestFocus();
		this.addKeyListener(new KeyManager(this, player));
		this.addMouseListener(new MouseManager(this, width, height));
		
		/* Start the game at the main menu */
		gameState = GameState.MAINMENU;
	}

	/**
	 * The game loop. The two main components of the loop are update() and render().
	 */
	@Override
	public void run() { 
		init();
		long lastTime = System.nanoTime();
		double updateCap = 120d;
		double nanoSecond = 1000000000 / updateCap;
		double timePassed = 0;
		long currentTime = 0;
		
		while(isRunning) {
			currentTime = System.nanoTime();
			timePassed += (currentTime - lastTime) / nanoSecond;
			lastTime = currentTime;
			
			while(timePassed >= 1) {
				update();
				timePassed--;
			}
			
			render();
		}
		stop();
	}
	
	/**
	 * Updates all the objects on the handler and updates the camera to the player's
	 * position.
	 */
	private void update() {
		if (gameState == GameState.INGAME) {
			objectHandler.updateAll();
			camera.update(player);
			
			/* Check player status */
			if (player.atWin()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setGameState(GameState.WINSCREEN);
			} else if (player.isDead()) {
				render();
				try {
					thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setGameState(GameState.DEATHSCREEN);
			}
		}
	}
	
	/**
	 * Initially creates a buffer strategy. Renders different objects based on what
	 * state the game is in.
	 */
	private void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		if (gameState == GameState.INGAME) {
			g2d.translate(camera.getX(), camera.getY());
			objectHandler.renderAll(g);
			environHandler.renderAll(g);
			g2d.translate(camera.getX() * -1, camera.getY() * -1);
		} else if (gameState == GameState.MAINMENU) {
			mainMenu.render(g, gameState);
		} else if (gameState == GameState.LEVELSELECT) {
			levelSelect.render(g, gameState);
		} else if (gameState == GameState.DEATHSCREEN) {
			deathScreen.render(g2d, gameState);
		} else if (gameState == GameState.WINSCREEN) {
			winScreen.render(g2d, gameState);
		} else if (gameState == GameState.PAUSED) {
			pauseMenu.render(g2d, gameState);
		}
		
		g.dispose();
		buffer.show();
	}
	
	/**
	 * Sets the level to the level specified.
	 * @param levelNum	The number for the corresponding level
	 */
	public void setLevel(int levelNum) {
		this.levelNum = levelNum;
	}
	
	public int getLevel() {
		return this.levelNum;
	}
	
	public void nextLevel() {
		if (this.levelNum < NUM_LEVEL) {
			setLevel(getLevel() + 1);
		}
	}
	
	/**
	 * Sets up the level for the level handler.
	 * @return	True if the level loaded. False otherwise.
	 */
	public boolean prepareLevel() {
		player.reset();
		levelSelect.isLoadingLevel();
		flushLevel();
		levelHandler.chooseLevel(levelNum);
		boolean levelLoaded = levelHandler.loadLevel();
		if (levelLoaded) {
			objectHandler = levelHandler.getObjects();
			environHandler = levelHandler.getEnviron();
			levelSelect.doneLoadingLevel();
			return true;
		}
		return false;
	}
	
	public void flushLevel() {
		objectHandler.removeAll();
		environHandler.removeAll();
	}
	
	/**
	 * Gets the current game state.
	 * @return	Current game state.
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * Sets the game state.
	 * @param gameState	The desired game state.
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	/**
	 * Main
	 * @param args	None used
	 */
	public static void main(String[] args) {
		WindowManager window = new WindowManager(width, height, TITLE, new Game());
	}

}
