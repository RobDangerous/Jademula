package com.siemens.mp.color_game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class GameCanvas extends Canvas {
	public static final int DOWN_PRESSED   =   64;
	public static final int FIRE_PRESSED   =  256;
	public static final int GAME_A_PRESSED =  512;
	public static final int GAME_B_PRESSED = 1024;
	public static final int GAME_C_PRESSED = 2048;
	public static final int GAME_D_PRESSED = 4096;
	public static final int LEFT_PRESSED   =    4;
	public static final int RIGHT_PRESSED  =   32;
	public static final int UP_PRESSED     =    2;
	
	protected GameCanvas(boolean suppressKeyEvents) throws OutOfMemoryError {
		System.err.println("com.siemens.mp.color_game.GameCanvas not implemented.");
	}
	
	public void flushGraphics() { }

	public void flushGraphics(int x, int y, int width, int height) {
		
	}

	protected Graphics getGraphics() {
		return null;
	}

	public int getKeyStates() {
		return 0;
	}

	protected void keyPressed(int keyCode) {
		
	}

	protected void keyRepeated(int keyCode) {
		
	}

	public void paint(Graphics g) {
		
	}
}