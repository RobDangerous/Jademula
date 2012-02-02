package com.siemens.mp.ui;

import java.io.IOException;

import com.siemens.mp.misc.NativeMem;

public class Image extends NativeMem {
	public static final int COLOR_BMP_8BIT = 5;
	
	protected Image() {
		System.err.println("com.siemens.mp.ui.Image not implemented.");
	}

	public Image(int imageWidth, int imageHeight) {
		this();
	}

	public Image(Image image) {
		this();
	}

	public Image(byte[] bytes, int imageWidth, int imageHeight) {
		this();
	}

	public Image(byte[] bytes, int imageWidth, int imageHeight, boolean transparent) {
		this();
	}

	public Image(String name, boolean doScale) throws IOException {
		this();
	}

	public Image(byte[] imageData) {
		this();
	}

	public Image(byte[] bytes, int imageWidth, int imageHeight, int BitmapType) throws IOException {
		this();
	}

	public int getHeight() {
		return 0;
	}

	public int getWidth() {
		return 0;
	}

	public static javax.microedition.lcdui.Image createImageWithScaling(String name) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createImageWithoutScaling(String name) throws IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createImageFromBitmap(byte[] imageData, int imageWidth, int imageHeight) {
		return null;
	}

	public static javax.microedition.lcdui.Image createRGBImage(byte[] imageData, int imageWidth, int imageHeight, int BitmapType) throws ArrayIndexOutOfBoundsException, IOException {
		return null;
	}

	public static javax.microedition.lcdui.Image createTransparentImageFromBitmap(byte[] bytes, int width, int height) {
		return null;
	}

	public static void mirrorImageHorizontally(Image image) {
		
	}

	public static void mirrorImageVertically(Image image) {
		
	}

	protected static void setNativeImage(javax.microedition.lcdui.Image img, Image simg) {
		
	}
	
	public static Image getNativeImage(javax.microedition.lcdui.Image img) {
		return null;
	}
}