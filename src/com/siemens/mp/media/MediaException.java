package com.siemens.mp.media;

public class MediaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MediaException() {
		
	}

	public MediaException(String reason) {
		super(reason);
	}
}