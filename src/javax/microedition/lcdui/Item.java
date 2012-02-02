package javax.microedition.lcdui;

import javax.swing.JPanel;

public abstract class Item {
	public static final int LAYOUT_DEFAULT = 0;
	public static final int LAYOUT_LEFT = 1;
	public static final int LAYOUT_RIGHT = 2;
	public static final int LAYOUT_CENTER = 3;
	public static final int LAYOUT_TOP = 0x10;
	public static final int LAYOUT_BOTTOM = 0x20;
	public static final int LAYOUT_VCENTER = 0x30;
	public static final int LAYOUT_NEWLINE_BEFORE = 0x100;
	public static final int LAYOUT_NEWLINE_AFTER = 0x200;
	public static final int LAYOUT_SHRINK = 0x400;
	public static final int LAYOUT_EXPAND = 0x800;
	public static final int LAYOUT_VSHRINK = 0x1000;
	public static final int LAYOUT_VEXPAND = 0x2000;
	public static final int LAYOUT_2 = 0x4000;
	public static final int PLAIN = 0;
	public static final int HYPERLINK = 1;
	public static final int BUTTON = 2;
	
	private String label;
	private int layout, preferredWidth = 100, preferredHeight = 100;
	
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	public void addCommand(Command cmd) {
		
	}

	public void removeCommand(Command cmd) {
		
	}

	public void setItemCommandListener(ItemCommandListener l) {
		
	}

	public int getPreferredWidth() {
		return preferredWidth;
	}

	public int getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredSize(int width, int height) {
		preferredWidth = width;
		preferredHeight = height;
	}

	public int getMinimumWidth() {
		return 0;
	}

	public int getMinimumHeight() {
		return 0;
	}

	public void setDefaultCommand(Command cmd) {
		
	}

	public void notifyStateChanged() {
		
	}
	
	public void _paint(JPanel panel) { }
}