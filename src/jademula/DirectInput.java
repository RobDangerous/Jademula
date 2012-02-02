package jademula;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

interface KeyListener {
	void keyPressed();
	void keyReleased();
}

abstract class Key implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<KeyListener> listeners = new ArrayList<KeyListener>();
	private boolean pressed = false;
	
	public abstract boolean isDown();
	
	public void check() {
		if (isDown()) {
			if (!pressed) {
				keyPressed();
				pressed = true;
			}
		}
		else {
			if (pressed) {
				keyReleased();
				pressed = false;
			}
		}
	}
	
	public void addButtonListener(KeyListener listener) {
		listeners.add(listener);
	}
	
	public void removeButtonListener(KeyListener listener) {
		listeners.remove(listener);
	}
	
	protected void keyPressed() {
		for (KeyListener listener : listeners) listener.keyPressed();
	}
	
	protected void keyReleased() {
		for (KeyListener listener : listeners) listener.keyReleased();
	}
}

class KeyboardKey extends Key {
	private static final long serialVersionUID = 1L;
	private int num;
	
	public KeyboardKey(int num) {
		this.num = num;
	}

	public boolean isDown() {
		return DirectInput.getInstance().getKeys()[num];
	}
	
	public String toString() {
		return "" + num; 
	}
	
	public boolean equals(Object o) {
		try {
			return ((KeyboardKey) o).num == num;
		}
		catch (Throwable t) {
			return false;
		}
	}
}

class JoystickButton extends Key {
	private static final long serialVersionUID = 1L;
	private int num, buttonnum;
	
	public JoystickButton(int num, int buttonnum) {
		this.num = num;
		this.buttonnum = buttonnum;
	}

	public boolean isDown() {
		return DirectInput.getInstance().joyButtons(num)[buttonnum];
	}
	
	public String toString() {
		return "J" + num + "-" + buttonnum; 
	}
	
	public boolean equals(Object o) {
		try {
			JoystickButton button = (JoystickButton)o;
			return button.num == num && button.buttonnum == buttonnum;
		}
		catch (Throwable t) {
			return false;
		}
	}
}

class JoystickDirection extends Key {
	private static final long serialVersionUID = 1L;
	private int num, axisnum;
	private boolean posdir;
	private final static int margin = 250;
	
	public JoystickDirection(int num, int axisnum, boolean posdir) {
		this.num = num;
		this.axisnum = axisnum;
		this.posdir = posdir;
	}
	
	public boolean isDown() {
		if (posdir) return DirectInput.getInstance().joyAxes(num)[axisnum] > margin;
		else return DirectInput.getInstance().joyAxes(num)[axisnum] < -margin;
	}
	
	public String toString() {
		return "J" + num + "ax" + axisnum + (posdir ? "+" : "-"); 
	}
	
	public boolean equals(Object o) {
		try {
			JoystickDirection dir = (JoystickDirection)o;
			return dir.num == num && dir.axisnum == axisnum && dir.posdir == posdir;
		}
		catch (Throwable t) {
			return false;
		}
	}
}

public class DirectInput implements Runnable {
	private native boolean init(java.awt.Window canvas);
	private native void close();
	private native void poll();
	public native int[] joyAxes(int joy);
	public native boolean[] joyButtons(int joy);
	public native int getJoyNum();
	public native boolean[] getKeys();
	
	private static Map<Integer, Integer> keys = new HashMap<Integer, Integer>();
	
	private List<Key> allkeys = new ArrayList<Key>();
	private static DirectInput instance;

	static {
		keys.put(2, 21); //GAME_A
		keys.put(3, 22); //GAME_B
		keys.put(200, 50); //UP
		keys.put(203, 52); //LEFT
		keys.put(205, 54); //RIGHT
		keys.put(208, 56); //DOWN
		keys.put(29, 53); //FIRE
		/*
		keys.put(96, Canvas.KEY_NUM0);
		keys.put(97, Canvas.KEY_NUM1);
		keys.put(98, Canvas.KEY_NUM2);
		keys.put(99, Canvas.KEY_NUM3);
		keys.put(100, Canvas.KEY_NUM4);
		keys.put(101, Canvas.KEY_NUM5);
		keys.put(102, Canvas.KEY_NUM6);
		keys.put(103, Canvas.KEY_NUM7);
		keys.put(104, Canvas.KEY_NUM8);
		keys.put(105, Canvas.KEY_NUM9);
		
		keys.put(106, Canvas.KEY_STAR);
		keys.put(520, Canvas.KEY_POUND);
		
		keys.put(112, -76);*/
		keys.put(59, -6);
		keys.put(60, -7);
		System.load(new File("").getAbsolutePath() + "/lib/DirectInput.dll");
	}

	public DirectInput(JFrame frame) {
		boolean success = init(frame);
		System.out.println("Initializing DirectInput: " + (success ? "Success" : "Failure"));
		for (int i = 0; i < 256; ++i) {
			Key key = new KeyboardKey(i);
			InputManager.getInstance().checkKey(key);
			allkeys.add(key);
		}
		for (int i = 0; i < getJoyNum(); ++i) {
			for (int button = 0; button < 10; ++button) {
				Key key = new JoystickButton(i, button);
				InputManager.getInstance().checkKey(key);
				allkeys.add(key);
			}
			for (int axis = 0; axis < 2; ++axis) {
				Key key = new JoystickDirection(i, axis, false);
				InputManager.getInstance().checkKey(key);
				allkeys.add(key);
				key = new JoystickDirection(i, axis, true);
				InputManager.getInstance().checkKey(key);
				allkeys.add(key);
			}
		}
		KeyCatcher.getInstance().setInput(this);
		if (instance != null) throw new RuntimeException("Only one DirectInput please.");
		instance = this;
	}
	
	public static DirectInput getInstance() {
		return instance;
	}
	
	protected void finalize() {
		close();
	}
	
	public Key getPressedKey() {
		for (Key key : allkeys) if (key.isDown()) return key;
		return null;
	}

	public void run() {
		for (;;) {
			poll();
			for (Key key : allkeys) key.check();
			try { Thread.sleep(10); }
			catch (InterruptedException e) { }
		}
	}
}