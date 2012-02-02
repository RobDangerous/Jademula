package com.siemens.mp.game;

import javax.microedition.lcdui.Image;

import com.siemens.mp.misc.NativeMem;

public class GraphicObjectManager extends NativeMem {
	public GraphicObjectManager() {
		System.err.println("com.siemens.mp.game.GraphicObjectManager not implemented.");
	}
	
	public void addObject(GraphicObject gobject) {
		
	}

	public void insertObject(GraphicObject gobject, int position) {
		
	}

	public int getObjectPosition(GraphicObject gobject) {
		return 0;
	}

	public GraphicObject getObjectAt(int index) {
		return new GraphicObject();
	}

	public void deleteObject(GraphicObject gobject) {
		
	}

	public void deleteObject(int position) {
		
	}

	public void paint(Image image, int x, int y) throws IllegalArgumentException {
		
	}

	public void paint(ExtendedImage eimage, int x, int y) throws IllegalArgumentException {
		
	}

	public static byte[] createTextureBits(int width, int height, byte[] texture) {
		return null;
	}
}