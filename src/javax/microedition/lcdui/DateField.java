package javax.microedition.lcdui;

import java.util.Date;
import java.util.TimeZone;

public class DateField extends Item {
	public static final int DATE = 1;
	public static final int TIME = 2;
	public static final int DATE_TIME = 3;
	
	//private String label;
	private int mode;
	//private TimeZone timeZone;
	private Date date;
	
	public DateField(String label, int mode) {
		//this.label = label;
		this.mode = mode;
	}

	public DateField(String label, int mode, TimeZone timeZone) {
		this(label, mode);
		//this.timeZone = timeZone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getInputMode() {
		return mode;
	}

	public void setInputMode(int mode) {
		this.mode = mode;
    }
}