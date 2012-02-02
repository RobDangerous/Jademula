package javax.microedition.lcdui;

public class TextField extends Item {
	public static final int ANY = 0;
	public static final int EMAILADDR = 1;
	public static final int NUMERIC = 2;
	public static final int PHONENUMBER = 3;
	public static final int URL = 4;
	public static final int DECIMAL = 5;
	public static final int PASSWORD = 0x10000;
	public static final int UNEDITABLE = 0x20000;
	public static final int SENSITIVE = 0x40000;
	public static final int NON_PREDICTIVE = 0x80000;
	public static final int INITIAL_CAPS_WORD = 0x100000;
	public static final int INITIAL_CAPS_SENTENCE = 0x200000;
	public static final int CONSTRAINT_MASK = 0xFFFF;
	
	public TextField(String label, String text, int maxSize, int constraints) {
		System.err.println("TextField not implemented.");
	}

	public String getString() {
		return "";
	}

	public void setString(String text) {
		
	}

	public int getChars(char[] data) {
		return 0;
	}

	public void setChars(char[] data, int offset, int length) {
		
	}

	public void insert(String src, int position) {
		
	}

	public void insert(char[] data, int offset, int length, int position) {
		
	}

	public void delete(int offset, int length) {
		
	}

	public int getMaxSize() {
		return 0;
	}

	public int setMaxSize(int maxSize) {
		return 0;
	}

	public int size() {
		return 0;
	}

	public int getCaretPosition() {
		return 0;
	}

	public void setConstraints(int constraints) {
		
	}

	public int getConstraints() {
		return 0;
	}

	public void setInitialInputMode(String characterSubset) {
		
	}
}