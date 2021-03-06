package javax.microedition.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface OutputConnection {
	public OutputStream openOutputStream() throws IOException;
	public DataOutputStream openDataOutputStream() throws IOException;
}