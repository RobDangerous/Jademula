package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Alert extends Screen {
	public static final int FOREVER = -2;
	public static final Command DISMISS_COMMAND = new Command("Dismiss", Command.HELP, 0);
	
	private int timeout;
	private AlertType alertType;
	//private String title;
	private String text;
	private Image alertImage;
	private Gauge indicator;

	public Alert(String title) {
		System.err.println("Alert title: " + title);
		//this.title = title;
	}

	public Alert(String title, String alertText, Image alertImage, AlertType alertType) {
		this(title);
		text = alertText;
		this.alertImage = alertImage;
		this.alertType = alertType;
	}

	public int getDefaultTimeout() {
		return 2000;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int time) {
		timeout = time;
	}

	public AlertType getType() {
		return alertType;
	}

	public void setType(AlertType type) {
		this.alertType = type;
	}

	public String getString() {
		return text;
	}

	public void setString(String str) {
		System.err.println("Alert str: " + str);
		text = str;
	}

	public Image getImage() {
		return alertImage;
	}

	public void setImage(Image img) {
		this.alertImage = img;
	}

	public void setIndicator(Gauge indicator) {
		this.indicator = indicator;
	}

	public Gauge getIndicator() {
		return indicator;
	}

	public void addCommand(Command cmd) {
		System.err.println("javax.microedition.lcdui.Alert.addCommand() not implemented.");
	}

	public void removeCommand(Command cmd) {
		System.err.println("javax.microedition.lcdui.Alert.addCommand() not implemented.");
	}

	public void setCommandListener(CommandListener l) {
		System.err.println("javax.microedition.lcdui.Alert.addCommand() not implemented.");
	}
	
	public void _activate(JPanel panel) {
		System.err.println("Alert activated");
		panel.setLayout(new FlowLayout());
		panel.setSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		panel.add(new JLabel(text));
		panel.repaint();
	}
}