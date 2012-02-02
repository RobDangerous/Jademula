package com.siemens.mp.game;

import javax.microedition.lcdui.Image;

public class Sprite extends GraphicObject {
	public Sprite(Image pixels, Image mask, int numFrames) throws IllegalArgumentException {
		System.err.println("com.siemens.mp.game.Sprite not implemented.");
	}

	public Sprite(ExtendedImage pixels, ExtendedImage mask, int numFrames) throws IllegalArgumentException {
		
	}

	public Sprite(byte[] pixels, int pixel_offset, int width, int height, byte[] mask, int mask_offset, int numFrames) throws IllegalArgumentException {
		
	}

	public void setPosition(int x, int y) {
		
	}

	public int getXPosition() {
		return 0;
	}

	public int getYPosition() {
		return 0;
	}

	public void setCollisionRectangle(int x, int y, int width, int height) {
		
	}

	public boolean isCollidingWith(Sprite other) {
		return false;
	}

	public boolean isCollidingWithPos(int xpos, int ypos) {
		return false;
	}
	
	public void setFrame(int framenumber) {
		
	}

	public int getFrame() {
		return 0;
	}
}