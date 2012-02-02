package javax.microedition.io;

import java.io.IOException;

public interface ServerSocketConnection extends StreamConnectionNotifier {
	public String getLocalAddress() throws IOException;
	public int getLocalPort() throws IOException;
}