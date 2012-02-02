package javax.microedition.lcdui;

import jademula.Jademula;

import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Font {
	public static final int STYLE_PLAIN = 0;
	public static final int STYLE_BOLD = 1;
	public static final int STYLE_ITALIC = 2;
	public static final int STYLE_UNDERLINED = 4;
	public static final int SIZE_SMALL = 8;
	public static final int SIZE_MEDIUM = 0;
	public static final int SIZE_LARGE = 16;
	public static final int FACE_SYSTEM = 0;
	public static final int FACE_MONOSPACE = 32;
	public static final int FACE_PROPORTIONAL = 64;
	public static final int FONT_STATIC_TEXT = 0;
	public static final int FONT_INPUT_TEXT = 1;
	
	private java.awt.Font font;
	private int face, style, size;
	
	private static BufferedImage testImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	private static java.awt.Graphics2D testGraphics;
	private static Font defaultFont;
	private static Map<Integer, String> faces = new HashMap<Integer, String>();
	private static Map<Integer, Integer> sizes = new HashMap<Integer, Integer>();
	
	static {
		fonts = new ArrayList<WeakReference<Font>>();
		faces.put(FACE_SYSTEM, "Default");
		faces.put(FACE_MONOSPACE, "Monospaced");
		faces.put(FACE_PROPORTIONAL, "Dialog");
		sizes.put(SIZE_SMALL, 9);
		sizes.put(SIZE_MEDIUM, 11);
		sizes.put(SIZE_LARGE, 13);
		defaultFont = new Font(FACE_SYSTEM, 0, SIZE_MEDIUM);
		testGraphics = testImage.createGraphics();
	}
	
	public static Font getFont(int fontSpecifier) {
		//FONT_INPUT_TEXT oder FONT_STATIC_TEXT
		return defaultFont;
	}

	public static Font getDefaultFont() {
		return defaultFont;
	}

	public static Font getFont(int face, int style, int size) {
		return new Font(face, style, size);
	}

	public int getStyle() {
		return style;
	}

	public int getSize() {
		return size;
	}

	public int getFace() {
		return face;
	}

	public boolean isPlain() {
		return (style & STYLE_PLAIN) != 0;
	}

	public boolean isBold() {
		return (style & STYLE_BOLD) != 0;
	}

	public boolean isItalic() {
		return (style & STYLE_ITALIC) != 0;
	}

	public boolean isUnderlined() {
		return (style & STYLE_UNDERLINED) != 0;
	}

	public int getHeight() {
		return testGraphics.getFontMetrics(font).getHeight() / Jademula.getZoom();
	}

	public int getBaselinePosition() {
		return testGraphics.getFontMetrics(font).getHeight() / Jademula.getZoom() - testGraphics.getFontMetrics(font).getLeading() / Jademula.getZoom();
	}

	public int charWidth(char ch) {
		return testGraphics.getFontMetrics(font).charWidth(ch) / Jademula.getZoom();
	}

	public int charsWidth(char[] ch, int offset, int length) {
		return stringWidth(new String(ch, offset, length)) / Jademula.getZoom();
	}

	public int stringWidth(String str) {
		if (str == null) throw new NullPointerException();
		return testGraphics.getFontMetrics(font).stringWidth(str) / Jademula.getZoom();
	}

	public int substringWidth(String str, int offset, int len) {
		if (str == null) throw new NullPointerException();
		if (offset < 0 || offset > str.length() || len < 0 || (offset + len) > str.length()) throw new StringIndexOutOfBoundsException();
		return stringWidth(str.substring(offset, offset + len)) / Jademula.getZoom(); // -1
	}
	
	private static java.util.List<WeakReference<Font>> fonts;
	
	private Font(int face, int style, int size) {
		this.face = face;
		this.style = style;
		this.size = size;
		font = new java.awt.Font(faces.get(face), style, sizes.get(size) * Jademula.getZoom());
		fonts.add(new WeakReference<Font>(this));
	}
	
	public java.awt.Font _getFont() {
		return font;
	}
	
	public static void _updateSize() {
		for (int i = 0; i < fonts.size(); ++i) {
			if (fonts.get(i).get() == null) {
				fonts.remove(i);
				--i;
				continue;
			}
			Font font = fonts.get(i).get();
			font.font = new java.awt.Font(faces.get(font.face), font.style, sizes.get(font.size) * Jademula.getZoom());
		}
	}
}