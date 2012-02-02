package com.siemens.mp.lcdui;

import java.io.IOException;

public class Image extends com.siemens.mp.ui.Image {
	public static void writeBmpToFile(Image image, String filename) throws IOException {
		
	}

	public static Image createImageFromFile(String filename, boolean ScaleToFullScreen) throws IOException {
		return null;
	}

	public static Image createImageFromFile(String filename, int ScaleToWidth, int ScaleToHeight) throws IOException {
		return null;
	}

	public static int getPixelColor(Image image, int x, int y) throws IllegalArgumentException {
		return 0;
	}

	public static void setPixelColor(Image image, int x, int y, int color) throws IllegalArgumentException {
		
	}
}