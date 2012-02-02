package javax.microedition.lcdui;

import jademula.Jademula;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Image {
	private BufferedImage original, scaled;
	private int width, height;
	private boolean hasToUpdate = false;

	public static Image createImage(int width, int height) {
		return new Image(width, height);
	}

	public static Image createImage(Image source) {
		return new Image(source);
	}

	public static Image createImage(String name) throws IOException {
		if (!name.startsWith("/")) name = "/" + name;
		InputStream stream = "".getClass().getResourceAsStream(name);
		try {
			return new Image(ImageIO.read(stream));
		}
		catch (IOException ex) {
			System.err.println("Unexpected IOException in Image.createImage()");
			throw ex;
		}
	}

	public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
		try {
			return new Image(ImageIO.read(new ByteArrayInputStream(imageData, imageOffset, imageLength)));
		}
		catch (IOException ex) {
			System.err.println("Unexpected IOException in Image.createImage()");
			throw new IllegalArgumentException();
		}
	}

	public static Image createImage(Image image, int x, int y, int width, int height, int transform) {
		return new Image(image);
	}

	public static Image createImage(java.io.InputStream stream) throws IOException {
		try {
			return new Image(ImageIO.read(stream));
		}
		catch (IOException ex) {
			System.err.println("Unexpected IOException in Image.createImage()");
			throw ex;
		}
	}

	public Graphics getGraphics() {
		hasToUpdate = true;
		return Graphics._create(original.createGraphics());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isMutable() {
		return true;
	}

	public static Image createRGBImage(int[] rgb, int width, int height, boolean processAlpha) {
		if (!processAlpha) {
			int[] rgbnew = new int[rgb.length];
			for (int i = 0; i < rgb.length; ++i) rgbnew[i] = rgb[i] | 0xff000000;
			rgb = rgbnew;
		}
		return new Image(rgb, width, height);
	}

	public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
		System.err.println("Image.getRGB() not implemented.");
	}
	
	private Image(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		this.original = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.original.getGraphics().drawImage(image, 0, 0, null);
		_createScaled();
		images.add(new WeakReference<Image>(this));
	}
	
	private Image(Image source) {
		this.original = source.original;
		this.width = source.width;
		this.height = source.height;
		_createScaled();
		images.add(new WeakReference<Image>(this));
	}

	private Image(int width, int height) {
		original = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		_createScaled();
		images.add(new WeakReference<Image>(this));
	}
	
	private Image(int[] rgb, int width, int height) {
		this(width, height);
		original.setRGB(0, 0, width, height, rgb, 0, width);
		_createScaled();
		images.add(new WeakReference<Image>(this));
	}
	
	private void _createScaled() {
		scaled = new BufferedImage(width * Jademula.getZoom(), height * Jademula.getZoom(), BufferedImage.TYPE_INT_ARGB);
		scaled.getGraphics().drawImage(original.getScaledInstance(original.getWidth() * Jademula.getZoom(), original.getHeight() * Jademula.getZoom(), 0), 0, 0, null);
	}
	
	public BufferedImage _getImage() {
		if (hasToUpdate) {
			_createScaled();
			hasToUpdate = false;
		}
		return scaled;
	}
	
	public static Image _createImage(BufferedImage image) {
		return new Image(image);
	}
	
	public static void _updateSize() {
		for (int i = 0; i < images.size(); ++i) {
			if (images.get(i).get() == null) {
				images.remove(i);
				--i;
				continue;
			}
			images.get(i).get()._createScaled();
		}
	}
	
	private static java.util.List<WeakReference<Image>> images = new ArrayList<WeakReference<Image>>();
}