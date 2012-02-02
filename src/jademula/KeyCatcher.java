package jademula;

public class KeyCatcher implements Runnable {
	private static KeyCatcher instance = new KeyCatcher();
	private DirectInput di;
	private Input input;
	private boolean activated;
	private Thread currentThread;
	private KeyCatchListener listener;
	
	public void setListener(KeyCatchListener listener) {
		this.listener = listener;
	}
	
	public void setInput(DirectInput di) {
		this.di = di;
	}
	
	public void start() {
		System.err.println("Starting KeyCatcher");
		stop();
		activated = true;
		currentThread = new Thread(this);
		currentThread.start();
	}
	
	public void stop() {
		activated = false;
		if (currentThread != null) {
			currentThread.interrupt();
			while (currentThread.isAlive()) {
				try { Thread.sleep(10); }
				catch (InterruptedException e) { }
			}
		}
	}
	
	public void run() {
		while (activated) {
			Key key = di.getPressedKey();
			if (key != null) {
				System.err.println("Key catched");
				input.add(key);
				if (listener != null) listener.catched();
				return;
			}
			try { Thread.sleep(100); }
			catch (InterruptedException e) { }
		}
	}
	
	public synchronized void setInput(Input input) {
		this.input = input;
	}
	
	public static KeyCatcher getInstance() {
		return instance;
	}
}