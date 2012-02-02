package com.siemens.mp.game;

import com.siemens.mp.misc.NativeMem;

public class GraphicObject extends NativeMem {
	boolean visible;
	
	protected GraphicObject() {
		
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisible() {
		return visible;
	}
}