package com.siemens.mp.media.control;

import com.siemens.mp.media.Control;

public interface VolumeControl extends Control {
	public void setMute(boolean mute);
	public boolean isMuted();
	public int setLevel(int level);
	public int getLevel();
}