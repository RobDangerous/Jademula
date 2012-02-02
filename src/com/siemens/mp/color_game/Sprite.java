package com.siemens.mp.color_game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprite extends Layer {
	public Sprite(Image image) throws NullPointerException {
		super(0, 0);
		System.err.println("com.siemens.mp.color_game.Sprite not implemented.");
	}

	public Sprite(Image image, int frameWidth, int frameHeight) throws NullPointerException, IllegalArgumentException {
		super(0, 0);
		System.err.println("com.siemens.mp.color_game.Sprite not implemented.");
	}

	public Sprite(Sprite s) throws NullPointerException {
		super(0, 0);
		System.err.println("com.siemens.mp.color_game.Sprite not implemented.");
	}

	public boolean collidesWith(Sprite s, boolean pixelLevel) {
		return false;
	}

	public boolean collidesWith(Image image, int x, int y, boolean pixelLevel) {
		return false;
	}

	public final int getFrame() {
		return 0;
	}

	public int getFrameSequenceLength() {
		return 0;
	}

	public int getRawFrameCount() {
		return 0;
	}

	public void nextFrame() {
		
	}

	public final void paint(Graphics g) throws NullPointerException {
		
	}

	public void prevFrame() {
		
	}

	public void setCollisionRectangle(int x, int y, int width, int height) throws IllegalArgumentException {
		
	}

	public void setFrame(int sequenceIndex) throws IndexOutOfBoundsException {
		
	}

	public void setFrameSequence(int[] sequence) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		
	}

	public void setImage(Image image, int frameWidth, int frameHeight) throws NullPointerException, IllegalArgumentException {
		
	}
}