package com.siemens.mp.gsm;

import java.io.IOException;

import com.siemens.mp.NotAllowedException;

public class SMS {
	public static int send(String number, String data) throws IOException, NotAllowedException {
		throw new NotAllowedException("Arrr, this is not a phone!");
	}

	public static int send(String number, byte[] data) throws IOException, NotAllowedException {
		throw new NotAllowedException("Arrr, this is not a phone!");
	}
}