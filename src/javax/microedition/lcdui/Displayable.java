package javax.microedition.lcdui;

import jademula.Jademula;
import jademula.gui.MainFrame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public abstract class Displayable {
	private List<Command> commands = new ArrayList<Command>();
	private CommandListener listener;
	private String title;
	private Ticker ticker;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String s) {
		this.title = s;
	}

	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}
	
	public Ticker getTicker() {
		return ticker;
	}

	public boolean isShown() {
		return MainFrame.getInstance().getDisplay().getCurrent() == this;
	}

	public void addCommand(Command cmd) {
		commands.add(cmd);
		if (isShown()) MainFrame.getInstance().setDisplayable(this); //Bug? !isShown()?
	}

	public void removeCommand(Command cmd) {
		commands.remove(cmd);
		if (isShown()) MainFrame.getInstance().setDisplayable(this);
	}

	public void setCommandListener(CommandListener l) {
		listener = l;
	}

	public int getWidth() {
		return MainFrame.getInstance().getWidth() / Jademula.getZoom();
	}

	public int getHeight() {
		return MainFrame.getInstance().getHeight() / Jademula.getZoom();
	}

	protected void sizeChanged(int w, int h) {
    	
    }
	
	public Command[] _getCommands() {
		return commands.toArray(new Command[]{});
	}
	
	public CommandListener _getListener() {
		return listener;
	}
	
	public void _repaint() { }
	
	public void _activate(JPanel panel) { }
	
	public void _deactivate() { }
	
	public void _onKeyPressed(int k) { }
	
	public void _onKeyReleased(int k) {	}
}