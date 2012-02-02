package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TiledLayer {
	public TiledLayer(int columns, int rows, Image image, int tileWidth, int tileHeight) {
		System.err.println("TiledLayer not implemented.");
	}

	public int createAnimatedTile(int staticTileIndex) {
		return 0;
	}

	public void setAnimatedTile(int animatedTileIndex, int staticTileIndex) {
		
	}

	public int getAnimatedTile(int animatedTileIndex) {
		return 0;
	}

	public void setCell(int col, int row, int tileIndex) {
		
	}

	public int getCell(int col, int row) {
		return 0;
	}

	public void fillCells(int col, int row, int numCols, int numRows, int tileIndex) {
		
	}

	public final int getCellWidth() {
		return 0;
	}

	public final int getCellHeight() {
		return 0;
	}

	public final int getColumns() {
		return 0;
	}

	public final int getRows() {
		return 0;
	}

	public void setStaticTileSet(Image image, int tileWidth, int tileHeight) {
		
	}

	public final void paint(Graphics g) {
		
	}
}