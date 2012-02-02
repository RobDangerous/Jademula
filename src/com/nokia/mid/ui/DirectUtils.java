package com.nokia.mid.ui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DirectUtils {
	public static Image createImage(int width, int height, int ARGBcolor) {
		Image img = Image.createImage(width, height);
		Graphics g = img.getGraphics();
		g.setColor(ARGBcolor);
		g.fillRect(0, 0, width, height);
		return img;
	}
	
	public static DirectGraphics getDirectGraphics(Graphics g) {
		return new _DirectGraphics(g);
	}
}