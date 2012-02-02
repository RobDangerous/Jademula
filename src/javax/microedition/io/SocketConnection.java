package javax.microedition.io;

import java.io.IOException;

public interface SocketConnection extends StreamConnection {
	public static final byte DELAY = 0;
	public static final byte LINGER = 1;
	public static final byte KEEPALIVE = 2;
	public static final byte RCVBUF = 3;
	public static final byte SNDBUF = 4;

	public void setSocketOption(byte option, int value) throws IllegalArgumentException, IOException;
	public int getSocketOption(byte option) throws IllegalArgumentException, IOException;
	public String getLocalAddress() throws IOException;
	public int getLocalPort() throws IOException;
	public String getAddress() throws IOException;
	public int getPort() throws IOException;
}