package com.siemens.mp;

import javax.microedition.io.ConnectionNotFoundException;

public class MIDlet {
	public MIDlet() { System.err.println("com.siemens.mp.MIDlet not implemented."); }

	public static void notifyDestroyed() { }

	public static void notifyPaused() { }

	public static String getAppProperty(String key) { return ""; }

	public static final boolean platformRequest(String locator) throws ConnectionNotFoundException, NotAllowedException { return false; }

	public static String[] getSupportedProtocols() { return new String[] { }; }
}