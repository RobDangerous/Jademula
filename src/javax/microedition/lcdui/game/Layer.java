package javax.microedition.lcdui.game;

import jademula.gui.MainFrame;

import javax.microedition.lcdui.Graphics;


public abstract class Layer {
	private int x, y;
	private boolean visible;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int dx, int dy) {
		
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	public final int getWidth() {
		return MainFrame.getInstance().getWidth(); //falsch?
	}

	public final int getHeight() {
		return MainFrame.getInstance().getHeight(); //falsch?
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public final boolean isVisible() {
		return visible;
	}

	public abstract void paint(Graphics g);
}