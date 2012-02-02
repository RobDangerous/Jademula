package com.siemens.mp.media;

import java.io.IOException;
import java.io.InputStream;

public final class Manager {
	public static final String MIDI_DEVICE_LOCATOR = "device://midi";
	public static final String TONE_DEVICE_LOCATOR = "device://tone";
	
	public static String[] getSupportedContentTypes(String protocol) {
		return new String[]{};
	}
	
	public static String[] getSupportedProtocols(String content_type) {
		return new String[]{};
	}

	public static Player createPlayer(String locator) throws IOException, MediaException {
		return new _Player(locator);	
	}

	public static Player createPlayer(InputStream stream, String type) throws IOException, MediaException {
		return null;
	}

	public static Player createPlayer(com.siemens.mp.media.protocol.DataSource source) throws IOException, MediaException {
		return null;
	}

	public static void playTone(int note, int duration, int volume) throws MediaException {
		
	}
}