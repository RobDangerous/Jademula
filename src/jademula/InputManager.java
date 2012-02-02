package jademula;

import java.io.Serializable;

import javax.microedition.lcdui.Canvas;

public class InputManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private static InputManager instance = new InputManager();
	public Input up, down, left, right, fire, gamea, gameb, gamec, gamed;
	public Input num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, star, pound;
	
	private InputManager() {
		up = new Input(Canvas.UP);
		down = new Input(Canvas.DOWN);
		left = new Input(Canvas.LEFT);
		right = new Input(Canvas.RIGHT);
		fire = new Input(Canvas.FIRE);
		gamea = new Input(Canvas.GAME_A);
		gameb = new Input(Canvas.GAME_B);
		gamec = new Input(Canvas.GAME_C);
		gamed = new Input(Canvas.GAME_D);
		
		num0 = new Input(Canvas.KEY_NUM0);
		num1 = new Input(Canvas.KEY_NUM1);
		num2 = new Input(Canvas.KEY_NUM2);
		num3 = new Input(Canvas.KEY_NUM3);
		num4 = new Input(Canvas.KEY_NUM4);
		num5 = new Input(Canvas.KEY_NUM5);
		num6 = new Input(Canvas.KEY_NUM6);
		num7 = new Input(Canvas.KEY_NUM7);
		num8 = new Input(Canvas.KEY_NUM8);
		num9 = new Input(Canvas.KEY_NUM9);
		star = new Input(Canvas.KEY_STAR);
		pound = new Input(Canvas.KEY_POUND);
	}
	
	public static InputManager getInstance() {
		return instance;
	}
	
	public static void load(InputManager im) {
		instance = im;
	}
	
	public void checkKey(Key key) {
		up.checkKey(key);
		down.checkKey(key);
		left.checkKey(key);
		right.checkKey(key);
		fire.checkKey(key);
		gamea.checkKey(key);
		gameb.checkKey(key);
		gamec.checkKey(key);
		gamed.checkKey(key);
		num0.checkKey(key);
		num1.checkKey(key);
		num2.checkKey(key);
		num3.checkKey(key);
		num4.checkKey(key);
		num5.checkKey(key);
		num6.checkKey(key);
		num7.checkKey(key);
		num8.checkKey(key);
		num9.checkKey(key);
		star.checkKey(key);
		pound.checkKey(key);
	}
}