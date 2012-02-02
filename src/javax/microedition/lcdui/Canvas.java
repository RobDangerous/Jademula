package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Queue;

import javax.swing.JPanel;


public abstract class Canvas extends Displayable {
	public static final int UP = 1;
	public static final int DOWN = 6;
	public static final int LEFT = 2;
	public static final int RIGHT = 5;
	public static final int FIRE = 8;
	public static final int GAME_A = 9;
	public static final int GAME_B = 10;
	public static final int GAME_C = 11;
	public static final int GAME_D = 12;
	public static final int KEY_NUM0 = 48;
	public static final int KEY_NUM1 = 49;
	public static final int KEY_NUM2 = 50;
	public static final int KEY_NUM3 = 51;
	public static final int KEY_NUM4 = 52;
	public static final int KEY_NUM5 = 53;
	public static final int KEY_NUM6 = 54;
	public static final int KEY_NUM7 = 55;
	public static final int KEY_NUM8 = 56;
	public static final int KEY_NUM9 = 57;
	public static final int KEY_STAR = 42;
	public static final int KEY_POUND = 35;
	
	private java.awt.Canvas canvas;
	private Graphics g;
	private boolean activated;
	private BufferStrategy bs;
	private long lasttime;
	
	protected Canvas() {
		canvas = new java.awt.Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		//Jademula.getHandy().getDisplay().setCurrent(this); //?
		lasttime = System.nanoTime();
	}

	public int getWidth() {
		return super.getWidth();
	}

	public int getHeight() {
		return super.getHeight();
	}

	public boolean isDoubleBuffered() {
		return true;
	}

	public boolean hasPointerEvents() {
		return false; //todo
	}
	
	public boolean hasPointerMotionEvents() {
		return false; //todo
	}

	public boolean hasRepeatEvents() {
		return false; //todo
	}

	//da fehlt wohl was...
	public int getKeyCode(int gameAction) {
		switch (gameAction) {
		case UP:
			return 50;
		case DOWN:
			return 56;
		case LEFT:
			return 52;
		case RIGHT:
			return 54;
		case FIRE:
			return 53;
		case GAME_A:
			return 21;
		case GAME_B:
			return 22;
		case GAME_C:
			return 23;
		case GAME_D:
			return 24;
		default:
			return 0;
		}
	}

	public String getKeyName(int keyCode) {
		switch (keyCode) {
		case 50:
			return "UP";
		case 56:
			return "DOWN";
		case 52:
			return "LEFT";
		case 54:
			return "RIGHT";
		case 53:
			return "FIRE";
		case 21:
			return "GAME_A";
		case 22:
			return "GAME_B";
		case 23:
			return "GAME_C";
		case 24:
			return "GAME_D";
		default:
			return "Unknown Key";
		}
	}

	public int getGameAction(int keyCode) {
		switch (keyCode) {
		case 50:
			return UP;
		case 56:
			return DOWN;
		case 52:
			return LEFT;
		case 54:
			return RIGHT;
		case 53:
			return FIRE;
		case 21:
			return GAME_A;
		case 22:
			return GAME_B;
		case 23:
			return GAME_C;
		case 24:
			return GAME_D;
		default:
			return 0;
		}
	}

	public void setFullScreenMode(boolean mode) { }

	protected void keyPressed(int keyCode) { }

	protected void keyRepeated(int keyCode) { } //todo

	protected void keyReleased(int keyCode) { }

	protected void pointerPressed(int x, int y) { }

	protected void pointerReleased(int x, int y) { }

	protected void pointerDragged(int x, int y) { }

	public final void repaint(int x, int y, int width, int height) {
		repaint();
	}

	public final void repaint() {
		//int runnablecount = MainFrame.getInstance().getDisplay()._getRunnables().size();
		if (activated) {
			try {
				paint(g);
				_flipBuffers();
			}
			catch (NullPointerException ex) { }
		}
		Queue<Runnable> queue = MainFrame.getInstance().getDisplay()._getRunnables();
		//for (int i = 0; i < runnablecount; ++i) {
			Runnable r = queue.poll();
			if (r != null) r.run();
		//}
	}

	public final void serviceRepaints() { }

	protected void showNotify() { }

	protected void hideNotify() { }

	protected abstract void paint(Graphics g);

	protected void sizeChanged(int w, int h) { }
	
	public void _repaint() {
		repaint();
	}

	public synchronized void _activate(JPanel panel) {
		panel.setSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		panel.add(canvas);
		canvas.setSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		canvas.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = Graphics._create((Graphics2D) bs.getDrawGraphics());
		activated = true;
		showNotify();
		repaint();
		System.err.println("Canvas-Width: " + canvas.getWidth());
	}
	
	public synchronized void _deactivate() {
		activated = false;
		hideNotify();
	}
	
	public void _onKeyPressed(int k) {
		keyPressed(k);
	}
	
	public void _onKeyReleased(int k) {
		keyReleased(k);
	}
	
	public Graphics _getGraphics() {
		return g;
	}
	
	public synchronized void _flipBuffers() {
		//System.err.println("Canvas-Width: " + canvas.getWidth());
		bs.show();
		try {
			long dif = 33 * 1000000 - (System.nanoTime() - lasttime);
			lasttime = System.nanoTime();
			if (dif > 0) {
				lasttime += 33 * 1000000;
				long millidif = dif / 1000000;
				Thread.sleep(millidif, (int)(dif - millidif * 1000000));
			}
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}
}