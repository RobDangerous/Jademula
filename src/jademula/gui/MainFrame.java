package jademula.gui;

import jademula.ButtonListener;
import jademula.Handy;
import jademula.Jademula;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class MainFrame {
	private static MainFrame instance;
	private int width, height;
	private Display display;
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bs;
	private JPanel displayPanel, commandsPanel;
	private JButton[] commands;
	private int commandIndex;
	private Graphics2D g;
	private Menu menu;

	static {
		instance = new MainFrame(Handy.getCurrent().getWidth() * Jademula.getZoom(), Handy.getCurrent().getHeight() * Jademula.getZoom());
	}
	
	public void updateHandies() {
		menu.updateHandies();
	}

	private MainFrame(int width, int height) {
		//super("Jademula " + Jademula.getVersion());
		this.width = width;
		this.height = height;
		display = Display._create();
		setLookAndFeel();
		frame = new JFrame("Jademula " + Jademula.getVersion());
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			public void windowClosed(WindowEvent e) { }
			public void windowActivated(WindowEvent e) { }
			public void windowClosing(WindowEvent e) {
				Jademula.exit();
			}
			public void windowDeactivated(WindowEvent e) { }
			public void windowDeiconified(WindowEvent e) { }
			public void windowIconified(WindowEvent e) { }
			public void windowOpened(WindowEvent e) { }
		});
		frame.setJMenuBar(menu = new Menu());
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(width, height));
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setPreferredSize(new Dimension(width, height));
		displayPanel.add(canvas);
		frame.getContentPane().add(displayPanel);
		
		commandsPanel = new JPanel();
		commands = new JButton[2];
		commands[0] = new JButton("0");
		commands[1] = new JButton("1");
		//commands[2] = new JButton("2");
		commandsPanel.add(commands[0]);
		commandsPanel.add(commands[1]);
		//commandsPanel.add(commands[2]);
		frame.getContentPane().add(commandsPanel, BorderLayout.PAGE_END);
		
		frame.setResizable(false);
		frame.pack();
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static MainFrame getInstance() {
		return instance;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public Graphics2D getGraphics() {
		if (g == null) g = (Graphics2D)bs.getDrawGraphics();
		return g;
	}
	
	public void flipBuffers() {
		bs.show();
	}
	
	private void addCommand(Command cmd) {
		commands[commandIndex].setText(cmd.getLabel());
		commands[commandIndex].repaint();
		++commandIndex;
	}
	
	private void removeCommands() {
		commandIndex = 0;
	}
	
	Displayable current;
	
	public void setDisplayable(Displayable displayable) {
		if (current != null) current._deactivate();
		current = displayable;
		removeCommands();
		if (displayable._getCommands().length > 0) addCommand(displayable._getCommands()[0]);
		if (displayable._getCommands().length > 1) addCommand(displayable._getCommands()[1]);
		if (displayable._getListener() != null) {
			if (displayable._getCommands().length > 0) {
				commands[0].removeAll();
				commands[0].addActionListener(
					new ButtonListener(displayable, displayable._getCommands()[0], displayable._getListener())
				);
			}
			if (displayable._getCommands().length > 1) {
				commands[1].removeAll();
				commands[1].addActionListener(
					new ButtonListener(displayable, displayable._getCommands()[1], displayable._getListener())
				);
			}
		}
		displayPanel.removeAll();
		current._activate(displayPanel);
	}
	
	private static void setLookAndFeel() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setDisplaySize(int width, int height) {
		this.width = width;
		this.height = height;
		displayPanel.setPreferredSize(new Dimension(width, height));
		displayPanel.setSize(new Dimension(width, height));
		//if (current != null) current._resize(width, height);
		frame.pack();
		//displayPanel.setSize(new Dimension(width, height));
		if (current != null) current._activate(displayPanel);
		
		else System.err.println("current is null");
		System.err.println("DPX: " + displayPanel.getWidth());
		/*canvas.setPreferredSize(new Dimension(width, height));
		canvas.setSize(new Dimension(width, height));
		frame.pack();
		//displayPanel.removeAll();
		//displayPanel.add(canvas);
		//canvas.setPreferredSize(new Dimension(width, height));
		//bs.dispose();
		//canvas.addNotify();
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		*/
		/*displayPanel.removeAll();
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setPreferredSize(new Dimension(width, height));
		displayPanel.add(canvas);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();*/
	}
}