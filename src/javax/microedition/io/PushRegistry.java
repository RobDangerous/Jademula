package javax.microedition.io;

public class PushRegistry {
	public static void registerConnection(String connection, String midlet, String filter) throws ClassNotFoundException, java.io.IOException {
		throw new ConnectionNotFoundException();
	}

	public static boolean unregisterConnection(String connection) {
		return false;
	}

	public static String[] listConnections(boolean available) {
		return new String[]{};
	}

	public static String getMIDlet(String connection) {
		return null;
	}

	public static String getFilter(String connection) {
		return null;
	}

	public static long registerAlarm(String midlet, long time) throws ClassNotFoundException, ConnectionNotFoundException {
		throw new ConnectionNotFoundException();
	}
}