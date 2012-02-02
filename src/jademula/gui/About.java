package jademula.gui;

import jademula.Jademula;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JTextArea;


public class About extends JDialog {
	private static final long serialVersionUID = 1L;
	private static About instance;
	
	static {
		instance = new About();
	}
	
	private About() {
		super(MainFrame.getInstance().getFrame(), "About");
		JTextArea text = new JTextArea("Jademula " + Jademula.getVersion());
		text.setEditable(false);
		add(text);
		pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
	}
	
	public static About getInstance() {
		return instance;
	}
}