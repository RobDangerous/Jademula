package javax.microedition.io;

import java.io.IOException;

public interface StreamConnectionNotifier {
	public StreamConnection acceptAndOpen() throws IOException;
}