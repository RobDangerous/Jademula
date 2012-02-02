package jademula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener, Serializable {
	private static final long serialVersionUID = 1L;
	private int button;
	private List<Key> keys = new ArrayList<Key>(); //wird in Control-Config konfiguriert
	//private List<Integer> emitters = new ArrayList<Integer>(); //wird in Handy-Config konfiguriert
	
	public Input(int button) {
		this.button = button;
	}
	
	public void keyPressed() {
		Handy.getCurrent().keyPressed(button);
	}
	
	public void keyReleased() {
		Handy.getCurrent().keyReleased(button);
	}
	
	public void add(Key key) {
		if (keys.contains(key)) return;
		key.addButtonListener(this);
		keys.add(key);
	}
	
	/*public void addEmitter(int emitter) {
		emitters.add(emitter);
	}
	
	public List<Integer> getEmitters() {
		return emitters;
	}*/
	
	public void clearKeys() {
		for (Key key : keys) key.removeButtonListener(this);
		keys.clear();
	}
	
	/*public void clearEmitters() {
		emitters.clear();
	}*/
	
	public String toString() {
		if (keys.size() == 0) return " ";
		String ret = "";
		boolean first = true;
		for (Key key : keys) {
			if (first) {
				ret += key.toString();
				first = false;
			}
			else ret += ", " + key.toString();
		}
		return ret;
	}
	
	public void checkKey(Key key) { //können Keys übrigbleiben
		for (Key k : keys) {
			if (k.equals(key)) {
				keys.remove(k);
				keys.add(key);
				key.addButtonListener(this);
				return;
			}
		}
	}
}