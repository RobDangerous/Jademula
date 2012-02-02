package javax.microedition.midlet;

import java.util.jar.Attributes;

import javax.microedition.io.ConnectionNotFoundException;

public abstract class MIDlet {
	private static Attributes attributes;
	private Attributes attr;

	public static void _setAttr(Attributes attr) {
		attributes = attr;
	}
	
	protected MIDlet() {
		attr = attributes;
	}

	protected abstract void startApp() throws MIDletStateChangeException;
	protected abstract void pauseApp();
	protected abstract void destroyApp(boolean unconditional) throws MIDletStateChangeException;

	public final void notifyDestroyed() {
		//System.exit(0);
	}

	public final void notifyPaused() {
		
	}

	public final String getAppProperty(String key) {
		String prop = attr.getValue(key);
		if (prop != null) prop = prop.trim();
		return prop;
	}

	public final void resumeRequest() {
		
	}

	public final boolean platformRequest(String URL) throws ConnectionNotFoundException {
		return true;
	}

	public final int checkPermission(String permission) {
		return 0;
	}
	
	public void _startApp() {
		try {
			startApp();
		}
		catch (MIDletStateChangeException ex) {
			ex.printStackTrace();
		}
	}
	
	public void _destroyApp() {
		try {
			destroyApp(true);
		}
		catch (Throwable t) {
			System.out.println("Error destroying app");
		}
	}
}