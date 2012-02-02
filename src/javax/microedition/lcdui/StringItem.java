package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class StringItem extends Item {
	private String text;
	private Font font;
	private int appearanceMode;
	
	public StringItem(String label, String text) {
		this.text = text;
		setLabel(label);
	}

	public StringItem(String label, String text, int appearanceMode) {
		this(label, text);
		this.appearanceMode = appearanceMode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAppearanceMode() {
		return appearanceMode;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}
	
	public void _paint(JPanel panel) {
		JLabel text = new JLabel(getText());
		text.setSize(MainFrame.getInstance().getWidth(), 15);
		panel.add(text);
	}
}