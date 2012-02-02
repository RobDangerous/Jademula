package jademula;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public class ButtonListener implements ActionListener {
	private Displayable displayable;
	private Command command;
	private CommandListener listener;
	
	public ButtonListener(Displayable displayable, Command command, CommandListener listener) {
		this.displayable = displayable;
		this.command = command;
		this.listener = listener;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		listener.commandAction(command, displayable);
	}
}