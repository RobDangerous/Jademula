package com.nokia.mid.ui;

import jademula.Jademula;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;


public class _DirectGraphics implements DirectGraphics {
	public static final int FLIP_HORIZONTAL = 0x2000;
	public static final int FLIP_VERTICAL = 0x4000;
	public static final int ROTATE_90 = 90;
	public static final int ROTATE_180 = 180;
	public static final int ROTATE_270 = 270; 
	
	private Graphics g;
	
	public _DirectGraphics(Graphics g) {
		this.g = g;
	}

	private int yanchor(int anchor, int height) {
		if ((anchor & Graphics.VCENTER) != 0) return height / 2;
		if ((anchor & Graphics.BASELINE) != 0) System.err.println("Baseline");
		if ((anchor & Graphics.BOTTOM) != 0) return height;
		else return 0;
	}
	
	private int xanchor(int anchor, int width) {
		if ((anchor & Graphics.HCENTER) != 0) return width / 2;
		else if ((anchor & Graphics.RIGHT) != 0) return width;
		else return 0;
	}
	
	//public void drawImage(Image img, int x, int y, int anchor) {
	//	g2d.drawImage(
	//			img.getImage(),
	//			x * T_Mobile.getZoom() - xanchor(anchor, img.getWidth() * T_Mobile.getZoom()),
	//			y * T_Mobile.getZoom() - yanchor(anchor, img.getHeight() * T_Mobile.getZoom()),
	//			null);
	//}
	
	public void drawImage(Image img, int x, int y, int anchor, int manipulation) {
		Sprite sprite = new Sprite(img);
		switch (manipulation) {
		case FLIP_HORIZONTAL:
			sprite.setTransform(Sprite.TRANS_MIRROR);
			break;
		case FLIP_VERTICAL:
			//sprite.setTransform(Sprite.TRANS_MIRROR);
			break;
		case ROTATE_90:
			sprite.setTransform(Sprite.TRANS_ROT90);
			break;
		case ROTATE_180:
			sprite.setTransform(Sprite.TRANS_MIRROR_ROT180);
			break;
		case ROTATE_270:
			sprite.setTransform(Sprite.TRANS_MIRROR_ROT270);
			break;
		}
		sprite.setPosition(
				x - xanchor(anchor, img.getWidth()),
				y - yanchor(anchor, img.getHeight())
		);
		sprite.paint(g);
	}

	public void fillPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
		int[] xp = new int[xPoints.length - xOffset];
		int[] yp = new int[yPoints.length - yOffset];
		for (int i = 0; i < xp.length; ++i) {
			xp[i] = xPoints[i + xOffset] * Jademula.getZoom();
		}
		for (int i = 0; i < yp.length; ++i) {
			yp[i] = yPoints[i + yOffset] * Jademula.getZoom();
		}
		Color c = g._getGraphics().getColor();
		g.setColor(argbColor);
		g._getGraphics().fillPolygon(xp, yp, nPoints);
		g._getGraphics().setColor(c);
	}

	public void setARGBColor(int argbColor) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.setARGBColor() not implemented.");
	}

	public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.drawTriangle() not implemented.");
	}

	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.fillTriangle() not implemented.");
	}

	public void drawPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
		int[] xp = new int[xPoints.length - xOffset];
		int[] yp = new int[yPoints.length - yOffset];
		for (int i = 0; i < xp.length; ++i) {
			xp[i] = xPoints[i + xOffset] * Jademula.getZoom();
		}
		for (int i = 0; i < yp.length; ++i) {
			yp[i] = yPoints[i + yOffset] * Jademula.getZoom();
		}
		Color c = g._getGraphics().getColor();
		g.setColor(argbColor);
		g._getGraphics().drawPolygon(xp, yp, nPoints);
		g._getGraphics().setColor(c);
	}

	public void drawPixels(int[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.drawPixels() not implemented.");
	}

	public void drawPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.drawPixels() not implemented.");
	}

	public void drawPixels(short[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
		//System.err.println("com.nokia.mid.ui._DirectGraphics.drawPixels() not implemented.");
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//P(x1, y1) = pixels[offset + (x1 - x) + (y1 - y) * scanlength],
		 //for each P(x1, y1), where (x <= x1 < x + width) and (y <= y1 < y +
		 //height).
		for (int ix = 0; ix < width; ++ix) {
			for (int iy = 0; iy < height; ++iy) {
				img.setRGB(ix, iy, _getPixel(pixels[offset + ix + iy * scanlength], format));
			}
		}
		g._getGraphics().drawImage(img, x, y, null);
	}
	
	private int _getPixel(short pixel, int format) {
		switch (format) {
		case TYPE_USHORT_4444_ARGB:
			return ((pixel & 0xf000) << 16) |
			((pixel & 0xf00) << 12) |
			((pixel & 0xf0) << 8) |
			((pixel & 0xf) << 4);
		case TYPE_USHORT_444_RGB:
			return (0xf000 << 16) |
			((pixel & 0xf00) << 12) |
			((pixel & 0xf0) << 8) |
			((pixel & 0xf) << 4);
		case TYPE_USHORT_555_RGB:
		case TYPE_USHORT_1555_ARGB:
		case TYPE_USHORT_565_RGB:
			System.err.println("Pixelformat: " + format);
		}
		return pixel;
	}

	public void getPixels(int[] pixels, int offset, int scanlength, int x, int y, int width, int height, int format) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.getPixels() not implemented.");
	}

	public void getPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int width, int height, int format) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.getPixels() not implemented.");
	}

	public void getPixels(short[] pixels, int offset, int scanlength, int x, int y, int width, int height, int format) {
		System.err.println("com.nokia.mid.ui._DirectGraphics.getPixels() not implemented.");
	}

	public int getNativePixelFormat() {
		System.err.println("com.nokia.mid.ui._DirectGraphics.getNativePixelFormat() not implemented.");
		return 0;
	}

	public int getAlphaComponent() {
		System.err.println("com.nokia.mid.ui._DirectGraphics.getAlphaComponent() not implemented.");
		return 0;
	}
}