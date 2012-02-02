package jademula.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

interface HandyInputListener {
	public void aButtonPressed();
	public void bButtonPressed();
	public void cButtonPressed();
	public void dButtonPressed();
	public void zeroButtonPressed();
	public void oneButtonPressed();
	public void twoButtonPressed();
	public void threeButtonPressed();
	public void fourButtonPressed();
	public void fiveButtonPressed();
	public void sixButtonPressed();
	public void sevenButtonPressed();
	public void eightButtonPressed();
	public void nineButtonPressed();
	public void upButtonPressed();
	public void downButtonPressed();
	public void rightButtonPressed();
	public void leftButtonPressed();
	public void poundButtonPressed();
	public void starButtonPressed();
}

public class HandyInput extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<HandyInputListener> listeners = new ArrayList<HandyInputListener>();

	public HandyInput() {
		setLayout(new GridLayout(9, 3));
		
		addAButton();
		add(new JLabel(""));
		addBButton();
		
		addCButton();
		add(new JLabel(""));
		addDButton();
		
		add(new JLabel(""));
		addUpButton();
		add(new JLabel(""));
		
		addLeftButton();
		add(new JLabel(""));
		addRightButton();
		
		add(new JLabel(""));
		addDownButton();
		add(new JLabel(""));
				
		addOneButton();
		addTwoButton();
		addThreeButton();
		
		addFourButton();
		addFiveButton();
		addSixButton();
			
		addSevenButton();
		addEightButton();
		addNineButton();
		
		addStarButton();		
		addZeroButton();
		addPoundButton();
	}
	
	private void addAButton() {
		JButton button = new JButton("a");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addBButton() {
		JButton button = new JButton("b");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addCButton() {
		JButton button = new JButton("c");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addDButton() {
		JButton button = new JButton("d");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addUpButton() {
		JButton button = new JButton("↑");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addRightButton() {
		JButton button = new JButton("→");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rightButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addDownButton() {
		JButton button = new JButton("↓");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					downButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addLeftButton() {
		JButton button = new JButton("←");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					leftButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addOneButton() {
		JButton button = new JButton("1");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					oneButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addTwoButton() {
		JButton button = new JButton("2");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					twoButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addThreeButton() {
		JButton button = new JButton("3");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					threeButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addFourButton() {
		JButton button = new JButton("4");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fourButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addFiveButton() {
		JButton button = new JButton("5");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fiveButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addSixButton() {
		JButton button = new JButton("6");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sixButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addSevenButton() {
		JButton button = new JButton("7");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sevenButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addEightButton() {
		JButton button = new JButton("8");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eightButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addNineButton() {
		JButton button = new JButton("9");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nineButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addStarButton() {
		JButton button = new JButton("*");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					starButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addZeroButton() {
		JButton button = new JButton("0");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					zeroButtonPressed();
				}
			}
		);
		add(button);
	}
	
	private void addPoundButton() {
		JButton button = new JButton("#");
		button.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					poundButtonPressed();
				}
			}
		);
		add(button);
	}
	
	public void aButtonPressed() {
		for (HandyInputListener listener : listeners) listener.aButtonPressed();
	}
	
	public void bButtonPressed() {
		for (HandyInputListener listener : listeners) listener.bButtonPressed();
	}
	
	public void cButtonPressed() {
		for (HandyInputListener listener : listeners) listener.cButtonPressed();
	}
	
	public void dButtonPressed() {
		for (HandyInputListener listener : listeners) listener.dButtonPressed();
	}
	
	public void zeroButtonPressed() {
		for (HandyInputListener listener : listeners) listener.zeroButtonPressed();
	}
	
	public void oneButtonPressed() {
		for (HandyInputListener listener : listeners) listener.oneButtonPressed();
	}
	
	public void twoButtonPressed() {
		for (HandyInputListener listener : listeners) listener.twoButtonPressed();
	}
	
	public void threeButtonPressed() {
		for (HandyInputListener listener : listeners) listener.threeButtonPressed();
	}
	
	public void fourButtonPressed() {
		for (HandyInputListener listener : listeners) listener.fourButtonPressed();
	}
	
	public void fiveButtonPressed() {
		for (HandyInputListener listener : listeners) listener.fiveButtonPressed();
	}
	
	public void sixButtonPressed() {
		for (HandyInputListener listener : listeners) listener.sixButtonPressed();
	}
	
	public void sevenButtonPressed() {
		for (HandyInputListener listener : listeners) listener.sevenButtonPressed();
	}
	
	public void eightButtonPressed() {
		for (HandyInputListener listener : listeners) listener.eightButtonPressed();
	}
	
	public void nineButtonPressed() {
		for (HandyInputListener listener : listeners) listener.nineButtonPressed();
	}
	
	public void upButtonPressed() {
		for (HandyInputListener listener : listeners) listener.upButtonPressed();
	}
	
	public void downButtonPressed() {
		for (HandyInputListener listener : listeners) listener.downButtonPressed();
	}
	
	public void rightButtonPressed() {
		for (HandyInputListener listener : listeners) listener.rightButtonPressed();
	}
	
	public void leftButtonPressed() {
		for (HandyInputListener listener : listeners) listener.leftButtonPressed();
	}
	
	public void poundButtonPressed() {
		for (HandyInputListener listener : listeners) listener.poundButtonPressed();
	}
	
	public void starButtonPressed() {
		for (HandyInputListener listener : listeners) listener.starButtonPressed();
	}
	
	public void addListener(HandyInputListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(HandyInputListener listener) {
		listeners.remove(listener);
	}
}