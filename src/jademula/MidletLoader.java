package jademula;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.microedition.midlet.MIDlet;

public class MidletLoader {
	private Attributes attr;
	private URLClassLoader loader;
	private String midletName;
	private MIDlet m;

	public MidletLoader(String midletName) {
		this.midletName = midletName;
		start();
	}

	public void run() {
		try {
			Class<?> c = loader.loadClass(getMidletClass().replace('/', '.'));
			MIDlet._setAttr(attr);
			m = (MIDlet) c.newInstance();
			m._startApp();
		}
		catch (Throwable t) {
			t.printStackTrace();
			throw new RuntimeException("Could not start the MIDlet. " + t);
		}
	}
	
	public void restart() {
		m._destroyApp();
		run();
	}
	
	private void start() {
		try {
			loader = new URLClassLoader(new URL[]{new File(midletName).toURI().toURL()}, ClassLoader.getSystemClassLoader());
			JarFile midlet = new JarFile(midletName);
			Manifest manifest = midlet.getManifest();
			attr = manifest.getMainAttributes();
			ClassPathHacker.addFile(midletName);
		}
		catch (Throwable t) {
			throw new RuntimeException("Could not start the MIDlet. " + t);
		}
	}

	public void stop() {
		ClassPathHacker.removeFile(midletName);
	}
	
	public String getAttribute(String name) {
		return attr.getValue(name);
	}
	
	private String getMidletClass() {
		return attr.getValue("MIDlet-1").split(",")[2].trim();
	}
}