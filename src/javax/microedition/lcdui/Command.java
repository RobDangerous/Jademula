package javax.microedition.lcdui;

public class Command {
	public static final int SCREEN = 1;
	public static final int BACK = 2;
	public static final int CANCEL = 3;
	public static final int OK = 4;
	public static final int HELP = 5;
	public static final int STOP = 6;
	public static final int EXIT = 7;
	public static final int ITEM = 8;

	private String shortLabel, longLabel;
	private int commandType;
	private int priority;
	
	public Command(String label, int commandType, int priority) {
		this(label, label, commandType, priority);
	}

	public Command(String shortLabel, String longLabel, int commandType, int priority) {
		this.shortLabel = shortLabel;
		this.longLabel = longLabel;
		this.commandType = commandType;
		this.priority = priority;
	}

	public String getLabel() {
		return shortLabel;
	}

	public String getLongLabel() {
		return longLabel;
	}

	public int getCommandType() {
		return commandType;
	}

	public int getPriority() {
		return priority;
	}
}