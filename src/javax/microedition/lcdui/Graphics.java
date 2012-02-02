package javax.microedition.lcdui;

import jademula.Jademula;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.microedition.lcdui.game.Sprite;


public class Graphics {
	public static final int HCENTER = 1;
	public static final int VCENTER = 2;
	public static final int LEFT = 4;
	public static final int RIGHT = 8;
	public static final int TOP = 16;
	public static final int BOTTOM = 32;
	public static final int BASELINE = 64;
	public static final int SOLID = 0;
	public static final int DOTTED = 1;
	
	private Graphics2D g2d;
	private Font font = Font.getFont(0, 0, 0);
	private int cx, cy, cw, ch;
	private int tx, ty;
	private int strokeStyle;
	
	public void translate(int x, int y) {
		tx = x;
		ty = y;
		g2d.translate(x * Jademula.getZoom(), y * Jademula.getZoom());
	}
	
	public int getTranslateX() {
		return tx;
	}

	public int getTranslateY() {
		return ty;
	}

	public int getColor() {
		return g2d.getColor().getRGB();
	}

	public int getRedComponent() {
		return g2d.getColor().getRed();
	}

	public int getGreenComponent() {
		return g2d.getColor().getGreen();
	}

	public int getBlueComponent() {
		return g2d.getColor().getBlue();
	}

	public int getGrayScale() {
		return (getRedComponent() + getGreenComponent() + getBlueComponent()) / 3;
	}

	public void setColor(int red, int green, int blue) {
		g2d.setColor(new Color(red, green, blue));
	}

	public void setColor(int RGB) {
		g2d.setColor(new Color(RGB));
	}

	public void setGrayScale(int value) {
		setColor(value, value, value);
	}

	public Font getFont() {
		return font;
	}

	public void setStrokeStyle(int style) {
		System.err.println("Graphics.strokeStyle not used.");
		strokeStyle = style;
	}

	public int getStrokeStyle() {
		return strokeStyle;
	}

	public void setFont(Font font) {
		this.font = font;
		g2d.setFont(font._getFont());
	}

	public int getClipX() {
		return cx;
	}

	public int getClipY() {
		return cy;
	}

	public int getClipWidth() {
		return cw;
	}

	public int getClipHeight() {
		return ch;
	}

	public void clipRect(int x, int y, int width, int height) { //Hä?
		g2d.clipRect(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom());
	}

	public void setClip(int x, int y, int width, int height) {
		cx = x; cy = y; cw = width; ch = height;
		g2d.setClip(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom());
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		g2d.drawLine(x1 * Jademula.getZoom(), y1 * Jademula.getZoom(), x2 * Jademula.getZoom(), y2 * Jademula.getZoom());
	}

	public void fillRect(int x, int y, int width, int height) {
		g2d.fillRect(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom());
	}

	public void drawRect(int x, int y, int width, int height) {
		g2d.drawRect(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom());
	}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g2d.drawRoundRect(x * Jademula.getZoom(), y * Jademula.getZoom(),
				width * Jademula.getZoom(), height * Jademula.getZoom(),
				arcWidth * Jademula.getZoom(), arcHeight * Jademula.getZoom());
	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g2d.fillRoundRect(x * Jademula.getZoom(), y * Jademula.getZoom(),
				width * Jademula.getZoom(), height * Jademula.getZoom(),
				arcWidth * Jademula.getZoom(), arcHeight * Jademula.getZoom());
	}

	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g2d.fillArc(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom(), startAngle, arcAngle);
	}

	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g2d.drawArc(x * Jademula.getZoom(), y * Jademula.getZoom(), width * Jademula.getZoom(), height * Jademula.getZoom(), startAngle, arcAngle);
	}

	public void drawString(String str, int x, int y, int anchor) {
		g2d.drawString(
				str,
				x * Jademula.getZoom() - xanchor(anchor, g2d.getFontMetrics().stringWidth(str)),
				y * Jademula.getZoom() + g2d.getFontMetrics().getAscent() - yanchor(anchor, g2d.getFontMetrics().getHeight())
		);
	}

	public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
		drawString(str.substring(offset, offset + len - 1), x, y, anchor);
	}

	public void drawChar(char character, int x, int y, int anchor) {
		drawString("" + character, x, y, anchor);
	}

	public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
		drawString(new String(data, offset, length), x, y, anchor);
	}

	public void drawImage(Image img, int x, int y, int anchor) {
		if (img != null) {
			g2d.drawImage(
				img._getImage(),
				x * Jademula.getZoom() - xanchor(anchor, img.getWidth() * Jademula.getZoom()),
				y * Jademula.getZoom() - yanchor(anchor, img.getHeight() * Jademula.getZoom()),
				null
			);
		}
	}

	public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
		System.err.println("drawRegion");if (src != null) {
			Sprite sprite = new Sprite(Image._createImage(src._getImage().getSubimage(x_src, y_src, width, height)));
			if (transform != 0) sprite.setTransform(transform);
			sprite.setPosition(
				x_dest - xanchor(anchor, width),
				y_dest - yanchor(anchor, height));
			sprite.paint(this);
		}
	}

	public void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor) {
		System.err.println("Graphics.copyArea not implemented.");
	}

	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		g2d.fillPolygon(
				new int[]{x1 * Jademula.getZoom(), x2 * Jademula.getZoom(), x3 * Jademula.getZoom()},
				new int[]{y1 * Jademula.getZoom(), y2 * Jademula.getZoom(), y3 * Jademula.getZoom()}, 3);
	}

	public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int ix = 0; ix < width; ++ix) {
			for (int iy = 0; iy < height; ++iy) {
				img.setRGB(ix, iy, rgbData[offset + ix + iy * scanlength]);
			}
		}
		g2d.drawImage(img, x, y, null);
	}

	public int getDisplayColor(int color) {
		return color;
	}
	
	private Graphics(Graphics2D g2d) {
		this.g2d = g2d;
		g2d.setStroke(new BasicStroke(Jademula.getZoom()));
	}
	
	public static Graphics _create(Graphics2D g2d) {
		return new Graphics(g2d);
	}
	
	private int yanchor(int anchor, int height) {
		if ((anchor & VCENTER) != 0) return height / 2;
		if ((anchor & BASELINE) != 0) System.err.println("Baseline");
		if ((anchor & BOTTOM) != 0) return height;
		else return 0;
	}
	
	private int xanchor(int anchor, int width) {
		if ((anchor & HCENTER) != 0) return width / 2;
		if ((anchor & RIGHT) != 0) return width;
		return 0;
	}
	
	public Graphics2D _getGraphics() {
		return g2d;
	}
}