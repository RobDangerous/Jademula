package com.siemens.mp.color_game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class Layer {
	protected com.siemens.mp.misc.NativeMem NativeMemoryTable;

	protected Layer(int width, int height) throws IllegalArgumentException {
		System.err.println("com.siemens.mp.color_game.Layer not implemented.");
	}

	protected Layer(Image image) throws NullPointerException {
		System.err.println("com.siemens.mp.color_game.Layer not implemented.");
	}

	protected Image getLayerImage() {
		return null;
	}

	protected void setLayerImage(Image image) throws NullPointerException {
		
	}

	protected void copyAllLayerVariables(Layer l) {
		
	}

	public final int getHeight() {
		return 0;
	}

	public final int getWidth() {
		return 0;
	}

	public final int getX() {
		return 0;
	}

	public final int getY() {
		return 0;
	}

	public final boolean isVisible() {
		return true;
	}

	public void move(int dx, int dy) {
		
	}

	public abstract void paint(Graphics g) throws NullPointerException;

	public void setHeight(int height) throws IllegalArgumentException {
		
	}

	public void setPosition(int x, int y) {
		
	}

	public void setVisible(boolean visible) {
		
	}

	public void setWidth(int width) throws IllegalArgumentException {
		
	}
}