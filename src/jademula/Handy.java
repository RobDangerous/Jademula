package jademula;

import jademula.gui.MainFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.lcdui.Canvas;


public class Handy implements Serializable {
	private static final long serialVersionUID = 1L;
	private static List<Handy> handies = new ArrayList<Handy>();
	private static Handy current;
	private String name;
	private int width, height;
	private Map<Integer, List<Integer>> keys = new HashMap<Integer, List<Integer>>();
	
	static {
		new Handy();
		current = handies.get(0);
	}
	
	public static List<Handy> getHandies() {
		return handies;
	}
	
	private void addKey(int key) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(key);
		keys.put(key, list);
	}
	
	public Handy() {
		name = "Handy ";
		width = 176;
		height = 208;
		
		addKey(Canvas.UP);
		addKey(Canvas.DOWN);
		addKey(Canvas.LEFT);
		addKey(Canvas.RIGHT);
		addKey(Canvas.FIRE);
		addKey(Canvas.GAME_A);
		keys.get(Canvas.GAME_A).add(-6);
		addKey(Canvas.GAME_B);
		keys.get(Canvas.GAME_B).add(-7);
		addKey(Canvas.GAME_C);
		addKey(Canvas.GAME_D);
		addKey(Canvas.KEY_NUM0);
		addKey(Canvas.KEY_NUM1);
		addKey(Canvas.KEY_NUM2);
		addKey(Canvas.KEY_NUM3);
		addKey(Canvas.KEY_NUM4);
		addKey(Canvas.KEY_NUM5);
		addKey(Canvas.KEY_NUM6);
		addKey(Canvas.KEY_NUM7);
		addKey(Canvas.KEY_NUM8);
		addKey(Canvas.KEY_NUM9);
		addKey(Canvas.KEY_STAR);
		addKey(Canvas.KEY_POUND);
				
		for (int i = handies.size(); ; ++i) {
			boolean nameexists = false;
			for (Handy handy : handies) {
				if (handy.name.equals(name + i)) {
					nameexists = true;
					break;
				}
			}
			if (!nameexists) {
				name = name + i;
				break;
			}
		}
		handies.add(this);
	}
	
	public void keyPressed(int key) {
		if (MainFrame.getInstance().getDisplay().getCurrent() == null) return;
		for (Integer k : keys.get(key))
			MainFrame.getInstance().getDisplay().getCurrent()._onKeyPressed(k);
	}
	
	public void keyReleased(int key) {
		if (MainFrame.getInstance().getDisplay().getCurrent() == null) return;
		for (Integer k : keys.get(key))
			MainFrame.getInstance().getDisplay().getCurrent()._onKeyReleased(k);
	}
	
	public void clearKey(int key) {
		keys.get(key).clear();
	}
	
	public void addKey(int key, int num) {
		keys.get(key).add(num);
	}
	
	public List<Integer> getEmitters(int key) {
		return keys.get(key);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public static Handy getCurrent() {
		return current;
	}
	
	public static void setCurrent(String handyname) {
		for (int i = 0; i < handies.size(); ++i) {
			if (handies.get(i).getName().equals(handyname)) {
				current = handies.get(i);
				return;
			}
		}
	}
	
	public static void save(ObjectOutputStream out) throws IOException {
		out.writeObject(handies);
		//out.writeObject(current);
	}
	
	@SuppressWarnings("unchecked")
	public static void load(ObjectInputStream in) throws IOException, ClassNotFoundException {
		handies = (List<Handy>)in.readObject();
		current = handies.get(0);
	}
}