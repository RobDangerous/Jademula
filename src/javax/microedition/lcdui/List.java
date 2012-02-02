package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


class ListElement {
	public String string;
	public Image image;
	
	public ListElement(String s) {
		string = s;
	}
	
	public ListElement(String s, Image i) {
		this(s);
		image = i;
	}
}

public class List extends Screen implements Choice {
	public static final Command SELECT_COMMAND = new Command("",0,0);
	
	private java.util.List<ListElement> elements = new ArrayList<ListElement>();
	private int selected;
	private JPanel panel;

	public List(String title, int listType) {
		System.err.println("List not implemented.");
	}

	public List(String title, int listType, String[] stringElements, Image[] imageElements) {
		this(title, listType);
		for (int i = 0; i < stringElements.length; ++i)
			elements.add(new ListElement(stringElements[i], imageElements != null ? imageElements[i] : null));
	}

	public int size() {
		return elements.size();
	}

	public String getString(int elementNum) {
		return elements.get(elementNum).string;
	}

	public Image getImage(int elementNum) {
		return elements.get(elementNum).image;
	}

	public int append(String stringPart, Image imagePart) {
		elements.add(new ListElement(stringPart, imagePart));
		return elements.size() - 1;
	}

	public void insert(int elementNum, String stringPart, Image imagePart) {
		java.util.List<ListElement> temp = new ArrayList<ListElement>();
		temp.addAll(elements.subList(elementNum, elements.size() - 1));
		for (int i = 0; i < temp.size(); ++i) elements.remove(elements.size() - 1);
		elements.add(new ListElement(stringPart, imagePart));
		elements.addAll(temp);
	}

	public void delete(int elementNum) {
		elements.remove(elementNum);
	}

	public void deleteAll() {
		elements.clear();
	}

	public void set(int elementNum, String stringPart, Image imagePart) {
		elements.get(elementNum).string = stringPart;
		elements.get(elementNum).image = imagePart;
	}

	public boolean isSelected(int elementNum) {
		System.err.println("List.isSelected");
		return selected == elementNum;
	}

	public int getSelectedIndex() {
		System.err.println("List.getSelectedIndex");
		return selected;
	}

	public int getSelectedFlags(boolean[] selectedArray_return) {
		System.err.println("List.getSelectedFlags");
		return 0;
	}

	public void setSelectedIndex(int elementNum, boolean selected) {
		
	}

	public void setSelectedFlags(boolean[] selectedArray) {
		
	}

	public void removeCommand(Command cmd) {
		
	}

	public void setSelectCommand(Command command) {
		System.err.println("List.setSelectCommand");
		super.addCommand(command);
	}

	public void setFitPolicy(int fitPolicy) {
		
	}

	public int getFitPolicy() {
		return 0;
	}

	public void setFont(int elementNum, Font font) {
		
	}

	public Font getFont(int elementNum) {
		return null;
	}
	
	public void _activate(JPanel panel) {
		System.err.println("List activated");
		this.panel = panel;
		_repaint();
	}
	
	public void _onKeyPressed(int k) {
		if (k == 53) {
			super._getListener().commandAction(SELECT_COMMAND, this);
			return;
		}
		if (k == 50) --selected;
		else if (k == 56) ++selected;
		if (selected < 0) selected = elements.size() - 1;
		else if (selected > elements.size() - 1) selected = 0;
		_repaint();
	}
	
	public void _repaint() {
		panel.removeAll();
		panel.setLayout(new FlowLayout());
		panel.setSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		int height = 10;
		for (int i = 0; i < elements.size(); ++i) {
			JLabel text;
			if (i == selected) text = new JLabel("> " + elements.get(i).string + " <");
			else text = new JLabel(elements.get(i).string);
			text.setBounds(10, height, MainFrame.getInstance().getWidth(), 15);
			panel.add(text);
			height += 20;
		}
		panel.repaint();
	}
}