package javax.microedition.io;

import java.io.IOException;

public interface UDPDatagramConnection extends DatagramConnection {
	public String getLocalAddress() throws IOException;
	public int getLocalPort() throws IOException;
}