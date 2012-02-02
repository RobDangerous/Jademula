package jademula.gui;

import jademula.Handy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.microedition.lcdui.Canvas;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class Options extends JDialog implements HandyInputListener {
	private static final long serialVersionUID = 1L;
	private static Options instance;
	private JTextField inputfield;
	private JButton inputbutton, resetbutton;
	private Color inputback;
	private int currenthandy = 0;
	private int currentkey = -1;
	JLabel inputlabel;
	JComboBox<String> handies;
	JTextField namefield, widthfield, heightfield;
	ItemListener itemlistener;
	
	static {
		instance = new Options();
	}

	private synchronized void changeHandy(int handy) {
		currenthandy = handy;
		namefield.setText(Handy.getHandies().get(currenthandy).getName());
		widthfield.setText("" + Handy.getHandies().get(currenthandy).getWidth());
		heightfield.setText("" + Handy.getHandies().get(currenthandy).getHeight());
		inputlabel.setText(" ");
		inputfield.setText("");
		inputfield.setEnabled(false);
		inputfield.setBackground(Color.red);
		inputbutton.setEnabled(false);
		resetbutton.setEnabled(false);
	}
	
	private synchronized void updateList() {
		handies.removeItemListener(itemlistener);
		handies.removeAllItems();
		for (Handy handy : Handy.getHandies())
			handies.addItem(handy.getName());
		handies.setSelectedIndex(currenthandy);
		handies.addItemListener(itemlistener);
		MainFrame.getInstance().updateHandies();
	}
	
	private Options() {
		super(MainFrame.getInstance().getFrame(), "Options");
		//JPanel
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel start = new JPanel(new FlowLayout());
		handies = new JComboBox<String>();
		updateList();
		handies.setPreferredSize(new Dimension(100, handies.getPreferredSize().height));
		itemlistener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (handies.getSelectedIndex() != -1) changeHandy(handies.getSelectedIndex());
			}
		};
		handies.addItemListener(itemlistener);
		//handies.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				//if (handies.getSelectedIndex() != -1) changeHandy(handies.getSelectedIndex());
			//}
		//});
		start.add(handies);
		JButton addbutton = new JButton("Add");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Handy();
				updateList();
			}
		});
		start.add(addbutton);
		add(start, BorderLayout.PAGE_START);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(new JSeparator());
		center.add(new JLabel(""));
		JPanel text = new JPanel();
		text.setLayout(new GridLayout(3, 2));
		text.add(new JLabel(" Name:"));
		text.add(namefield = new JTextField());
		namefield.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) { }

			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < Handy.getHandies().size(); ++i) {
					if (i != currenthandy) {
						if (Handy.getHandies().get(i).getName().equals(namefield.getText())) {
							namefield.setBackground(Color.red);
							return;
						}
					}
				}
				Handy.getHandies().get(currenthandy).setName(namefield.getText());
				namefield.setBackground(inputback);
				updateList();
			}

			public void keyTyped(KeyEvent e) { }
		});
		text.add(new JLabel(" Width:"));
		text.add(widthfield = new JTextField());
		widthfield.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) { }

			public void keyReleased(KeyEvent e) {
				try {
					Handy.getHandies().get(currenthandy).setWidth(Integer.parseInt(widthfield.getText()));
					widthfield.setBackground(inputback);
				}
				catch (NumberFormatException ex) {
					widthfield.setBackground(Color.red);
				}
			}

			public void keyTyped(KeyEvent e) { }
		});
		text.add(new JLabel(" Height:"));
		text.add(heightfield = new JTextField());
		heightfield.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) { }

			public void keyReleased(KeyEvent e) {
				try {
					Handy.getHandies().get(currenthandy).setHeight(Integer.parseInt(heightfield.getText()));
					heightfield.setBackground(inputback);
				}
				catch (NumberFormatException ex) {
					heightfield.setBackground(Color.red);
				}
			}

			public void keyTyped(KeyEvent e) { }
		});
		center.add(text);
		
		center.add(new JSeparator());

		inputlabel = new JLabel(" ");
		inputlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		center.add(inputlabel);
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(1, 2));
		inputfield = new JTextField();
		inputfield.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) { }

			public void keyReleased(KeyEvent e) {
				System.err.println(inputfield.getText());
				try {
					Integer.parseInt(inputfield.getText());
					inputfield.setBackground(inputback);
					inputbutton.setEnabled(true);
				}
				catch (NumberFormatException ex) {
					inputfield.setBackground(Color.red);
					inputbutton.setEnabled(false);
				}
			}

			public void keyTyped(KeyEvent e) { }
		});
		input.add(inputfield);
		inputbutton = new JButton("Add");
		inputbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (currentkey != -1) {
						//current.addEmitter(Integer.parseInt(inputfield.getText()));
						Handy.getHandies().get(currenthandy).addKey(currentkey, Integer.parseInt(inputfield.getText()));
						updateInputLabel();
					}
				}
				catch (NumberFormatException ex) { }
			}
		});
		input.add(inputbutton);
		center.add(input);
		inputback = inputfield.getBackground();
		inputfield.setBackground(Color.red);
		inputfield.setEnabled(false);
		inputbutton.setEnabled(false);
		resetbutton = new JButton("Reset");
		resetbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resetbutton.setEnabled(false);
		resetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentkey != -1) {
					Handy.getHandies().get(currenthandy).clearKey(currentkey);
					inputlabel.setText(" ");
				}
			}
		});
		center.add(resetbutton);
		center.add(new JSeparator());
		HandyInput handyinput = new HandyInput();
		handyinput.addListener(this);
		center.add(handyinput);
		add(center, BorderLayout.CENTER);
		
		/*JPanel end = new JPanel(new FlowLayout());
		end.add(new JButton("OK"));
		end.add(new JButton("Cancel"));
		add(end, BorderLayout.PAGE_END);*/
		
		changeHandy(0);
		
		setResizable(false);
		pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
	}
	
	public static Options getInstance() {
		return instance;
	}
	
	private void updateInputLabel() {
		if (currentkey == -1) return;
		String text = "";
		List<Integer> emitters = Handy.getHandies().get(currenthandy).getEmitters(currentkey);
		if (emitters.size() == 0) text = " ";
		boolean first = true;
		for (Integer emitter : emitters) {
			if (first) {
				text += emitter;
				first = false;
			}
			else text += ", " + emitter;
		}
		inputlabel.setText(text);
	}
	
	private void updateInput(int key) {
		currentkey = key;
		updateInputLabel();
		resetbutton.setEnabled(true);
		inputfield.setEnabled(true);
	}

	public void aButtonPressed() {
		updateInput(Canvas.GAME_A);
	}

	public void bButtonPressed() {
		updateInput(Canvas.GAME_B);
	}

	public void cButtonPressed() {
		updateInput(Canvas.GAME_C);
	}

	public void dButtonPressed() {
		updateInput(Canvas.GAME_D);
	}

	public void downButtonPressed() {
		updateInput(Canvas.DOWN);
	}

	public void eightButtonPressed() {
		updateInput(Canvas.KEY_NUM8);
	}

	public void fiveButtonPressed() {
		updateInput(Canvas.KEY_NUM5);
	}

	public void fourButtonPressed() {
		updateInput(Canvas.KEY_NUM4);
	}

	public void leftButtonPressed() {
		updateInput(Canvas.LEFT);
	}

	public void nineButtonPressed() {
		updateInput(Canvas.KEY_NUM9);
	}

	public void oneButtonPressed() {
		updateInput(Canvas.KEY_NUM1);
	}

	public void poundButtonPressed() {
		updateInput(Canvas.KEY_POUND);
	}

	public void rightButtonPressed() {
		updateInput(Canvas.RIGHT);
	}

	public void sevenButtonPressed() {
		updateInput(Canvas.KEY_NUM7);
	}

	public void sixButtonPressed() {
		updateInput(Canvas.KEY_NUM6);
	}

	public void starButtonPressed() {
		updateInput(Canvas.KEY_STAR);
	}

	public void threeButtonPressed() {
		updateInput(Canvas.KEY_NUM3);
	}

	public void twoButtonPressed() {
		updateInput(Canvas.KEY_NUM2);
	}

	public void upButtonPressed() {
		updateInput(Canvas.UP);
	}

	public void zeroButtonPressed() {
		updateInput(Canvas.KEY_NUM0);
	}
}