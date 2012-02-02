package jademula;

import jademula.gui.MainFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;


public class Jademula {
	//public static int gameindex = 1;
	//private static final String gamedir = "games/" +
		//"collection/";
		//"johnny_rumble/";
		//"robin/";
	//private static Handy handy = new Handy(176, 208);
	//private static String[] games;
	private static MidletLoader loader;
	
	private static void setProperties() {
		System.setProperty("microedition.platform", "Nokia3650");
		System.setProperty("microedition.locale", "de");
		//System.setProperty("microedition.platformName", null);
		System.setProperty("microedition.encodingDefault", "ISO8859_1");
		System.setProperty("microedition.configuration", "CLDC-1.0");
		//System.setProperty("microedition.profilesNames", null);
	}
	
	public static void main(String[] args) {
		setProperties();
		load();
		//if (args.length > 0) gameindex = Integer.parseInt(args[0]);
		new Thread(new DirectInput(MainFrame.getInstance().getFrame())).start(); //ungut?
		/*File dir = new File(gamedir);
		games = dir.list();
		System.out.println("Starting " + gameindex + ": " + games[gameindex - 1]);
		loader = new MidletLoader(gamedir + games[gameindex - 1]);
		loader.run();*/
		/*javax.swing.SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run()
					{
						handy = new Handy(200,200);
					}
				}
			);
		*/
	}
	
	public static void load(String filename) {
		if (loader != null) loader.stop();
		loader = new MidletLoader(filename);
		loader.run();
	}
	
	public static void reload() {
		if (loader != null) loader.restart();
	}
	
	//public static Handy getHandy() {
		//return handy;
	//}
	
	//public static String getGame() {
	//	return games[gameindex - 1];
	//}
	
	public static String getName() {
		return loader.getAttribute("MIDlet-Name");
	}

	public static String getVendor() {
		return loader.getAttribute("MIDlet-Vendor").trim();
	}
	
	public static String getVersion() {
		return "0.23";
	}
	
	private static int zoom = 1;
	
	public static void setZoom(int zoomlevel) {
		zoom = zoomlevel;
		MainFrame.getInstance().setDisplaySize(Handy.getCurrent().getWidth() * zoom, Handy.getCurrent().getHeight() * zoom);
		Font._updateSize();
		Image._updateSize();
	}
	
	public static int getZoom() {
		return zoom;
	}
	
	public static void exit() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("options.dat");
			out = new ObjectOutputStream(fos);
			Handy.save(out);
			out.writeObject(InputManager.getInstance());
			out.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}
	
	private static void load() {
		File file = new File("options.dat");
		if (!file.exists()) return;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("options.dat");
			in = new ObjectInputStream(fis);
			Handy.load(in);
			InputManager.load((InputManager)in.readObject());
			in.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}