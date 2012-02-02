package jademula.gui;

import jademula.Input;
import jademula.InputManager;
import jademula.KeyCatchListener;
import jademula.KeyCatcher;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;


public class InputDialog extends JDialog implements HandyInputListener, KeyCatchListener {
	private static final long serialVersionUID = 1L;
	private static InputDialog instance = new InputDialog();
	private JLabel label1, label2;
	private Input current;
	
	private InputDialog() {
		super(MainFrame.getInstance().getFrame(), "Input");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		label1 = new JLabel(" ");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label1);
		label2 = new JLabel(" ");
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label2);
		JButton button = new JButton("Reset");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (current != null) {
							current.clearKeys();
							label1.setText(" ");
						}
					}
				});
		add(button);
		add(new JSeparator());
		HandyInput hi = new HandyInput();
		hi.addListener(this);
		add(hi);
		pack();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
		KeyCatcher.getInstance().setListener(this);
	}
	
	public static InputDialog getInstance() {
		return instance;
	}
	
	public void catched() {
		if (current != null) label1.setText(current.toString());
		label2.setText(" ");
	}
	
	private void pressed(Input input) {
		current = input;
		label1.setText(input.toString());
		label2.setText("Waiting for input...");
		KeyCatcher.getInstance().setInput(input);
		KeyCatcher.getInstance().start();
	}

	public void aButtonPressed() {
		pressed(InputManager.getInstance().gamea);
	}

	public void bButtonPressed() {
		pressed(InputManager.getInstance().gameb);
	}

	public void cButtonPressed() {
		pressed(InputManager.getInstance().gamec);
	}

	public void dButtonPressed() {
		pressed(InputManager.getInstance().gamed);
	}

	public void downButtonPressed() {
		pressed(InputManager.getInstance().down);
	}

	public void eightButtonPressed() {
		pressed(InputManager.getInstance().num8);
	}

	public void fiveButtonPressed() {
		pressed(InputManager.getInstance().num5);
	}

	public void fourButtonPressed() {
		pressed(InputManager.getInstance().num4);
	}

	public void leftButtonPressed() {
		pressed(InputManager.getInstance().left);
	}

	public void nineButtonPressed() {
		pressed(InputManager.getInstance().num9);
	}

	public void oneButtonPressed() {
		pressed(InputManager.getInstance().num1);
	}

	public void poundButtonPressed() {
		pressed(InputManager.getInstance().pound);
	}

	public void rightButtonPressed() {
		pressed(InputManager.getInstance().right);
	}

	public void sevenButtonPressed() {
		pressed(InputManager.getInstance().num7);
	}

	public void sixButtonPressed() {
		pressed(InputManager.getInstance().num6);
	}

	public void starButtonPressed() {
		pressed(InputManager.getInstance().star);
	}

	public void threeButtonPressed() {
		pressed(InputManager.getInstance().num3);
	}

	public void twoButtonPressed() {
		pressed(InputManager.getInstance().num2);
	}

	public void upButtonPressed() {
		pressed(InputManager.getInstance().up);
	}

	public void zeroButtonPressed() {
		pressed(InputManager.getInstance().num0);
	}
}