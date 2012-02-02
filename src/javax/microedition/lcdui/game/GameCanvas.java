package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class GameCanvas extends Canvas {
	public static final int UP_PRESSED = 0x0002;
	public static final int DOWN_PRESSED = 0x0040;
	public static final int LEFT_PRESSED = 0x0004;
	public static final int RIGHT_PRESSED = 0x0020;
	public static final int FIRE_PRESSED = 0x0100;
	public static final int GAME_A_PRESSED = 0x0200;
	public static final int GAME_B_PRESSED = 0x0400;
	public static final int GAME_C_PRESSED = 0x0800;
	public static final int GAME_D_PRESSED = 0x1000;
	
	protected GameCanvas(boolean suppressKeyEvents) {
		
	}

	protected Graphics getGraphics() {
		return _getGraphics();
	}

	public int getKeyStates() {
		System.err.println("GameCanvas.getKeyStates() not implemented.");
		return 0;
	}

	public void paint(Graphics g) {
		
	}

	public void flushGraphics(int x, int y, int width, int height) {
		flushGraphics();
	}

	public void flushGraphics() {
		_flipBuffers();
	}
}