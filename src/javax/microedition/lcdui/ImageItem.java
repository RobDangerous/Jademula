package javax.microedition.lcdui;

public class ImageItem extends Item {
	public static final int LAYOUT_DEFAULT = 0;
	public static final int LAYOUT_LEFT = 1;
	public static final int LAYOUT_RIGHT = 2;
	public static final int LAYOUT_CENTER = 3;
	public static final int LAYOUT_NEWLINE_BEFORE = 0x100;
	public static final int LAYOUT_NEWLINE_AFTER = 0x200;

	private int layout, appearanceMode;
	private Image img;
	private String altText;
	
	public ImageItem(String label, Image img, int layout, String altText) {
		this(label, img, layout, altText, Item.PLAIN);
	}

	public ImageItem(String label, Image image, int layout, String altText, int appearanceMode) {
		this.layout = layout;
		this.img = image;
		this.altText = altText;
		this.appearanceMode = appearanceMode;
	}

	public Image getImage() {
		return img;
	}

	public void setImage(Image img) {
		this.img = img;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String text) {
		this.altText = text;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	public int getAppearanceMode() {
		return appearanceMode;
	}
}