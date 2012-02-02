package com.siemens.mp.gsm;

import com.siemens.mp.NotAllowedException;

public class PhoneBook {
	public PhoneBook() {
		
	}

	public static String[] getMDN() throws NotAllowedException {
		throw new NotAllowedException("Arrr, this is not a phone!");
	}
}