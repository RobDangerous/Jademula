package com.siemens.mp.media;

public interface Controllable {
	public Control[] getControls();
	public Control getControl(String controlType);
}