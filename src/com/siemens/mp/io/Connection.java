package com.siemens.mp.io;

import com.siemens.mp.NotAllowedException;
import com.siemens.mp.misc.NativeMem;

public class Connection extends NativeMem {
	public Connection(String connectTo) {
		System.err.println("com.siemens.mp.io.Connection not implemented.");
	}

	public void send(byte[] data) throws NotAllowedException {
		
	}

	public void setListener(ConnectionListener listener) {
		
	}

	public ConnectionListener getListener() {
		return null;
	}
}