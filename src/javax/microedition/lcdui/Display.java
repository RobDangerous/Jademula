package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import java.util.LinkedList;
import java.util.Queue;

import javax.microedition.midlet.MIDlet;


public class Display {
	public static final int LIST_ELEMENT = 1;
	public static final int CHOICE_GROUP_ELEMENT = 2;
	public static final int ALERT = 3;
	public static final int COLOR_BACKGROUND = 0;
	public static final int COLOR_FOREGROUND = 1;
	public static final int COLOR_HIGHLIGHTED_BACKGROUND = 2;
	public static final int COLOR_HIGHLIGHTED_FOREGROUND = 3;
	public static final int COLOR_BORDER = 4;
	public static final int COLOR_HIGHLIGHTED_BORDER = 5;
	
	private Displayable current;
	private Queue<Runnable> runners = new LinkedList<Runnable>();
	
	public static Display getDisplay(MIDlet m) {
		return MainFrame.getInstance().getDisplay();
	}

	public int getColor(int colorSpecifier) {
		return 0;
	}

	public int getBorderStyle(boolean highlighted) {
		return 0;
	}

	public boolean isColor() {
		return true;
	}

	public int numColors() {
		return 2 << 32;
	}

	public int numAlphaLevels() {
		return 2 << 8;
	}

	public Displayable getCurrent() {
		return current;
	}

	public void setCurrent(Displayable nextDisplayable) {
		if (nextDisplayable instanceof Alert) {
			Alert alert = (Alert) nextDisplayable;
			MainFrame.getInstance().setDisplayable(alert);
			try {
				if (alert.getTimeout() != -2) Thread.sleep(alert.getTimeout());
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (alert.getTimeout() != -2) MainFrame.getInstance().setDisplayable(current);
		}
		else {
			current = nextDisplayable;
			MainFrame.getInstance().setDisplayable(current);
		}
	}

	public void setCurrent(Alert alert, Displayable nextDisplayable) {
		setCurrent(nextDisplayable);
	}

	public void setCurrentItem(Item item) {
		System.err.println("Display.setCurrentItem() not implemented.");
	}

	public void callSerially(Runnable r) {
		//System.err.println("Display.callSerially()");
		//r.run();
		runners.add(r);
	}

	public boolean flashBacklight(int duration) {
		return true;
	}

	public boolean vibrate(int duration) {
		return true;
	}

	public int getBestImageWidth(int imageType) {
		return 100;
	}

	public int getBestImageHeight(int imageType) {
		return 100;
	}
	
	public static Display _create() {
		return new Display();
	}
	
	public Queue<Runnable> _getRunnables() {
		return runners;
	}
}