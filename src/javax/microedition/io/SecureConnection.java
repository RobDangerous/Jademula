package javax.microedition.io;

import java.io.IOException;

public interface SecureConnection extends SocketConnection {
	public SecurityInfo getSecurityInfo() throws IOException;
}