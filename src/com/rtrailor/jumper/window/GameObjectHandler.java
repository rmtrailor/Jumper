package com.rtrailor.jumper.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.rtrailor.jumper.objects.GameObject;
import com.rtrailor.jumper.objects.GameObjectID;

public class GameObjectHandler {
	
	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private GameObject tempObject;
	
	public void updateAll() {
		for (int i = 0; i < objects.size(); i++) {
			tempObject = objects.get(i);
			if (tempObject.getGameObjectID() == GameObjectID.player) {
				tempObject.update(objects);
			}
		}
	}
	
	public void renderAll(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			tempObject = objects.get(i);
			tempObject.render(g);
		}
	}
	
	public void addGameObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		objects.remove(object);
	}
	
	public void removeAll() {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getGameObjectID() != GameObjectID.player) {
				objects.remove(i);
			}
		}
	}
	
	public GameObject getObject(int i) {
		return objects.get(i);
	}
	
	public int getNumObjects() {
		return objects.size();
	}
	
	public boolean isEmpty() {
		if (objects.isEmpty()) {
			return true;
		}
		return false;
	}
}
