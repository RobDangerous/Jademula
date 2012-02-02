package com.siemens.mp.gsm;

import com.siemens.mp.NotAllowedException;

public class Call {
	public Call() {
		
	}

	public static void start(String number) throws NotAllowedException {
		throw new NotAllowedException("Arrr, this is not a phone!");
	}
}