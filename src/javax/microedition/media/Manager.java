package javax.microedition.media;

import java.io.IOException;
import java.io.InputStream;

public class Manager {
	public static final String TONE_DEVICE_LOCATOR = "device://tone";

	public static String[] getSupportedContentTypes(String protocol) {
		return new String[]{};
	}

	public static String[] getSupportedProtocols(String content_type) {
		return new String[]{};
	}

	public static Player createPlayer(String locator) throws IOException, MediaException {
		System.err.println("Manager.createPlayer not implemented.");
		return null;
	}

	public static Player createPlayer(InputStream stream, String type) throws IOException, MediaException {
		return new _Player(stream, type);
	}

	public static void playTone(int note, int duration, int volume) throws MediaException {
        	
 	}
}