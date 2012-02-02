package com.siemens.mp;

public class NotAllowedException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotAllowedException() { }

	public NotAllowedException(String msg) {
		super(msg);
	}
}