package javax.microedition.lcdui;

import java.util.ArrayList;

public class ChoiceGroup extends Item implements Choice {
	//private String label;
	//private int choiceType;
	private java.util.List<String> strings = new ArrayList<String>();
	private java.util.List<Image> images = new ArrayList<Image>();
	
	public ChoiceGroup(String label, int choiceType) {
		//this.label = label;
		//this.choiceType = choiceType;
	}

	public ChoiceGroup(String label, int choiceType, String[] stringElements, Image[] imageElements) {
		this(label, choiceType);
		for (String s : stringElements) strings.add(s);
		if (imageElements != null) for (Image i : imageElements) images.add(i);
	}

	public int size() {
		return strings.size();
	}

	public String getString(int elementNum) {
		return strings.get(elementNum);
	}

	public Image getImage(int elementNum) {
		return images.get(elementNum);
	}

	public int append(String stringPart, Image imagePart) {
		strings.add(stringPart);
		images.add(imagePart);
		return strings.size() - 1;
	}

	public void insert(int elementNum, String stringPart, Image imagePart) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.insert() not implemented.");
	}

	public void delete(int elementNum) {
		strings.remove(elementNum);
		images.remove(elementNum);
	}

	public void deleteAll() {
		strings.clear();
		images.clear();
	}

	public void set(int elementNum, String stringPart, Image imagePart) {
		strings.set(elementNum, stringPart);
		images.set(elementNum, imagePart);
	}

	public boolean isSelected(int elementNum) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.isSelected() not implemented.");
		return true;
	}

	public int getSelectedIndex() {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.getSelectedIndex() not implemented.");
		return 0;
	}

	public int getSelectedFlags(boolean[] selectedArray_return) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.getSelectedFlags() not implemented.");
		return 0;
	}

	public void setSelectedIndex(int elementNum, boolean selected) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.setSelectedIndex() not implemented.");
	}

	public void setSelectedFlags(boolean[] selectedArray) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.setSelectedFlags() not implemented.");
	}

	public void setFitPolicy(int fitPolicy) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.setFitPolicy() not implemented.");
	}

	public int getFitPolicy() {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.getFitPolicy() not implemented.");
		return 0;
	}

	public void setFont(int elementNum, Font font) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.setFont() not implemented.");
	}

	public Font getFont(int elementNum) {
		System.err.println("javax.microedition.lcdui.ChoiceGroup.getFont() not implemented.");
		return null;
	}
}