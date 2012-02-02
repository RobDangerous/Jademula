package javax.microedition.lcdui;

import jademula.gui.MainFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Form extends Screen {
	private List<Item> items = new ArrayList<Item>();
	//private ItemStateListener iListener;
	
	public Form(String title) {
		
	}

	public Form(String title, Item[] items) {
		for (Item i : items) this.items.add(i);
	}
	
	public int append(Item item) {
		items.add(item);
		return items.size() - 1;
	}

	public int append(String str) {
		return append(new StringItem(null, str));
	}

	public int append(Image img) {
		return append(new ImageItem(null, img, 0, ""));
	}

	public void insert(int itemNum, Item item) {
		System.err.println("Form.insert() not implemented.");
	}

	public void delete(int itemNum) {
		items.remove(itemNum);
	}

	public void deleteAll() {
		items.clear();
	}

	public void set(int itemNum, Item item) {
		items.set(itemNum, item);
	}

	public Item get(int itemNum) {
		return items.get(itemNum);
	}
	
	public void setItemStateListener(ItemStateListener iListener) {
		//this.iListener = iListener;
	}

	public int size() {
		return items.size();
	}

	public int getWidth() {
		return super.getWidth();
	}
	
	public int getHeight() {
		return super.getHeight();
	}
	
	public void _activate(JPanel panel) {
		panel.setLayout(new FlowLayout());
		panel.setSize(new Dimension(MainFrame.getInstance().getWidth(), MainFrame.getInstance().getHeight()));
		for (Item i : items) {
			i._paint(panel);
		}
		panel.repaint();
	}
}