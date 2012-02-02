package javax.microedition.lcdui.game;

import jademula.Jademula;

import java.awt.geom.AffineTransform;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Sprite extends Layer {
	public static final int TRANS_NONE = 0;
	public static final int TRANS_ROT90 = 5;
	public static final int TRANS_ROT180 = 3;
	public static final int TRANS_ROT270 = 6;
	public static final int TRANS_MIRROR = 2;
	public static final int TRANS_MIRROR_ROT90 = 7;
	public static final int TRANS_MIRROR_ROT180 = 1;
	public static final int TRANS_MIRROR_ROT270 = 4;
	
	private Image image;
	private int width, height;
	private int refx, refy;
	
	private int[] frames;
	private int frame;
	private int frameindex;
	private int transform;
	
	public Sprite(Image image) {
		this(image, image.getWidth(), image.getHeight());
	}

	public Sprite(Image image, int frameWidth, int frameHeight) {
		this.image = image;
		this.width = frameWidth;
		this.height = frameHeight;
		//System.out.println("w1: " + image.getImage().getWidth() + " w2: " + width);
		//System.out.println("h1: " + image.getImage().getHeight() + " h2: " + height);
		frames = new int[image.getWidth() / width];
		for (int i = 0; i < frames.length; ++i) frames[i] = i;
	}

	public Sprite(Sprite s) {
		
	}

	public void defineReferencePixel(int x, int y) {
		refx = x;
		refy = y;
	}

	public void setRefPixelPosition(int x, int y) {
		switch (transform) {
		case TRANS_NONE:
			setPosition(x - refx, y - refy);
			break;
		case TRANS_MIRROR:
			setPosition(x - (width - refx), y - refy);
			break;
		}
	}

	public int getRefPixelX() {
		return refx;
	}

	public int getRefPixelY() {
		return refy;
	}

	public void setFrame(int sequenceIndex) {
		this.frame = sequenceIndex;
	}

	public final int getFrame() {
		return frame;
	}

	public int getRawFrameCount() {
		System.err.println("Sprite.getRawFrameCount() not implemented.");
		return 0;
	}

	public int getFrameSequenceLength() {
		return frames.length;
	}

	public void nextFrame() {
		if (frameindex >= frames.length - 1) frameindex = -1;
		frame = frames[++frameindex];
	}

	public void prevFrame() {
		if (frameindex <= 1) frameindex = frames.length;
		frame = frames[--frameindex];
	}

	public final void paint(Graphics g) {
		AffineTransform trans = new AffineTransform();
		switch (transform) {
		case TRANS_NONE:
			trans.translate(getX() * Jademula.getZoom(), getY() * Jademula.getZoom());
			break;
		case TRANS_ROT90:
			System.out.println("Transformation not tested");
			trans.rotate(Math.PI / 2);
			trans.translate(getX() * Jademula.getZoom(), getY() * Jademula.getZoom());
			break;
		case TRANS_ROT180:
			System.out.println("Transformation not tested");
			trans.rotate(Math.PI);
			trans.translate(getX() * Jademula.getZoom(), getY() * Jademula.getZoom());
			break;
		case TRANS_ROT270:
			System.out.println("Transformation not tested");
			trans.rotate(Math.PI * 1.5);
			trans.translate(getX() * Jademula.getZoom(), getY() * Jademula.getZoom());
			break;
		case TRANS_MIRROR:
			trans.translate(getX() * Jademula.getZoom(), getY() * Jademula.getZoom());
			trans.scale(-1, 1);
			trans.translate(-width  * Jademula.getZoom() -  Jademula.getZoom(), 0);
			break;
		case TRANS_MIRROR_ROT90:
		case TRANS_MIRROR_ROT180:
		case TRANS_MIRROR_ROT270:
			System.out.println("Transformation not supported");
			break;
		}
		g._getGraphics().drawImage(
				image._getImage().getSubimage(
						(width * Jademula.getZoom() * frame),// % (image.getWidth()),
						0,//(height * T_Mobile.getZoom()) * (width * T_Mobile.getZoom() * frame) / (image.getWidth()),
						width * Jademula.getZoom(),
						height * Jademula.getZoom()
				),
			trans, null
		);
	}

	public void setFrameSequence(int[] sequence) {
		System.arraycopy(frames, 0, this.frames, 0, Math.min(frames.length, this.frames.length));
		frameindex = 0;
		frame = frames[0];
	}

	public void setImage(Image img, int frameWidth, int frameHeight) {
		System.err.println("Sprite.setImage() not implemented");
	}

	public void defineCollisionRectangle(int x, int y, int width, int height) {
		System.err.println("Sprite.defineCollisionRectangle() not implemented");
	}

	public void setTransform(int transform) {
		this.transform = transform;
	}

	public final boolean collidesWith(Sprite s, boolean pixelLevel) {
		System.err.println("Sprite.collidesWith() not implemented");
		return true;
	}

	public final boolean collidesWith(TiledLayer t, boolean pixelLevel) {
		System.err.println("Sprite.collidesWith() not implemented");
		return true;
	}

	public final boolean collidesWith(Image image, int x, int y, boolean pixelLevel) {
		System.err.println("Sprite.collidesWith() not implemented");
		return true;
	}
}