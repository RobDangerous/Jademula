package com.siemens.mp.game;

import javax.microedition.lcdui.Image;

import com.siemens.mp.misc.NativeMem;

public class ExtendedImage extends NativeMem {
	public ExtendedImage(Image image) throws IllegalArgumentException {
		System.err.println("com.siemens.mp.game.ExtendedImage not implemented.");
	}

	public void setPixel(int x, int y, byte color) {
		
	}

	public void setPixels(byte[] pixels, int x, int y, int width, int height) {
		
	}

	public int getPixel(int x, int y) {
		return 0;
	}

	public void getPixelBytes(byte[] pixels, int x, int y, int width, int height) {
		
	}

	public void clear(byte color) {
		
	}

	public void blitToScreen(int x, int y) {
		
	}

	public Image getImage() {
		return null;
	}
}