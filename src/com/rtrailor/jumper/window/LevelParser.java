package com.rtrailor.jumper.window;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rtrailor.jumper.objects.GameObject;

/* * 
 *  Class:  LevelParser
 *  -------------------
 *  Validates that a given JSON level file has the necessary information. 
 *  If the file has the needed information, then class parses the JSON file and creates
 *  the level.
 * 
 */

public class LevelParser {
	
	private String levelFile;
	private GameObjectHandler objectHandler;
	private JSONObject levelData;
	private int[] environmentArray, backgroundArray;
	private int levelWidth, levelHeight;
	private String tileSetPath;

	public LevelParser(String levelFile, GameObjectHandler objectHandler, GameObjectHandler environHandler) {
		this.levelFile = levelFile;
		this.objectHandler = objectHandler;
	}
	
	public void init() {
		JSONParser parser = new JSONParser();
		
		Path levelPath = FileSystems.getDefault().getPath(levelFile);
		
		try (BufferedReader in = Files.newBufferedReader(levelPath, Charset.forName("UTF-8"))) {
			levelData = (JSONObject) parser.parse(in);
		} catch (IOException e) {
			System.err.println("Unable to open file");
		} catch (ParseException e) {
			System.err.println("Unable to parse file");
		} 
		
		boolean isValidJSONLevel = validate();
		
		if (!isValidJSONLevel) {
			System.err.println("JSON level file did not contain the necessary information");
			System.exit(1);
		}
	}
	
	private boolean validate() {
		if (levelData.containsKey("layers")) {
			boolean hasEnvironment = false;
//			boolean hasBackground = false;
			JSONArray layerArray = (JSONArray) levelData.get("layers");
			
			for (int i = 0; i < layerArray.size(); i++) {
				JSONObject currentObject = (JSONObject) layerArray.get(i);
				
				if (currentObject.get("name").equals("Environment")) {
					hasEnvironment = true;
					JSONArray jsonArray = (JSONArray) currentObject.get("data");
					environmentArray = new int[jsonArray.size()];
					for (int j = 0; j < jsonArray.size(); j++) {
						long tempLong = (long) jsonArray.get(j);
						environmentArray[j] = (int) tempLong;
					}
//					environmentArray = (int[]) currentObject.get("data");
				}
				
//				if (currentObject.get("name").equals("Background")) {
//					hasBackground = true;
//					JSONArray jsonArray = (JSONArray) currentObject.get("data");
//					backgroundArray = new int[jsonArray.size()];
//					for (int j = 0; j < jsonArray.size(); j++) {
//						long tempLong = (long) jsonArray.get(j);
//						backgroundArray[j] = (int) tempLong;
//					}
////					backgroundArray = (int[]) currentObject.get("data");
//				}
				
			}
//			if (!hasEnvironment || !hasBackground) {
			if (!hasEnvironment) {
				System.err.println("JSON level file does not contain the necessary layer data");
				return false;
			}
		} else {
			System.err.println("JSON level file does not contain correct layer data");
			return false;
		}
		if (levelData.containsKey("height") && levelData.containsKey("width")) {
			long tempLong = (long) levelData.get("width");
			levelWidth = (int) tempLong;
			tempLong = (long) levelData.get("height");
			levelHeight = (int) tempLong;
//			levelWidth = (int) levelData.get("width");
//			levelHeight = (int) levelData.get("height");
		} else {
			System.err.println("JSON level file does not contain height and/or width information");
			return false;
		}
		if (levelData.containsKey("renderorder")) {
			String renderOrder = (String) levelData.get("renderorder");
			if (renderOrder.equals("right-down")) {
			} else {
				System.err.println("JSON level file does not contain the correct render order");
				return false;
			}
		} else {
			System.err.println("JSON level file does not contain render order information");
			return false;
		}
		if (levelData.containsKey("tilesets")) {
			JSONArray tileSets = (JSONArray) levelData.get("tilesets");
			JSONObject obj = (JSONObject) tileSets.get(0);
			tileSetPath = (String) obj.get("image");
		} else {
			System.err.println("JSON level file does not contain tile set information");
			return false;
		}
		return true;
	}
	
	public int[] getEnvironmentArray() {
		return environmentArray;
	}
	
	public int[] getBackgroundArray() {
		return backgroundArray;
	}
	
	public int getLevelWidth() {
		return levelWidth;
	}
	
	public int getLevelHeight() {
		return levelHeight;
	}
	
	public String getTileSetPath() {
		return tileSetPath;
	}
	
	
}
