package com.siemens.mp.color_game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TiledLayer extends Layer {
	public TiledLayer(int columns, int rows, Image image, int tileWidth, int tileHeight) throws NullPointerException, IllegalArgumentException, OutOfMemoryError {
		super(0, 0);
		System.err.println("com.siemens.mp.color_game.TiledLayer not implemented.");
	}

	public int createAnimatedTile(int staticTileIndex) throws IndexOutOfBoundsException, IllegalArgumentException {
		return 0;
	}

	public void fillCells(int col, int row, int width, int height, int tileIndex) throws IndexOutOfBoundsException {
		
	}

	public int getAnimatedTile(int animatedTileIndex) throws IndexOutOfBoundsException {
		return 0;
	}

	public int getCell(int col, int row) throws IndexOutOfBoundsException {
		return 0;
	}

	public final int getCellHeight() {
		return 0;
	}

	public final int getCellWidth() {
		return 0;
	}

	public final int getColumns() {
		return 0;
	}

	public final int getRows() {
		return 0;
	}

	public final void paint(Graphics g) throws NullPointerException {
		
	}

	public void setAnimatedTile(int animatedTileIndex, int staticTileIndex) {
		
	}

	public void setCell(int col, int row, int tileIndex) throws IndexOutOfBoundsException {
		
	}

	public void setStaticTileSet(Image image, int tileWidth, int tileHeight) throws NullPointerException, IllegalArgumentException {
		
	}
}