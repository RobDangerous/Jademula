package javax.microedition.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface InputConnection {
	public InputStream openInputStream() throws IOException;
	public DataInputStream openDataInputStream() throws IOException;
}