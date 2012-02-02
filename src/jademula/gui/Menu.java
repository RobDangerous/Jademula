package jademula.gui;

import jademula.Handy;
import jademula.Jademula;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private JMenu filemenu, optionsmenu, helpmenu, handymenu;
	private JMenuItem optionsitem, aboutitem;
	private JFileChooser chooser;

	public void updateHandies() {
		handymenu.removeAll();
		ButtonGroup handies = new ButtonGroup();
		for (Handy handy : Handy.getHandies()) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(handy.getName());
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Handy.setCurrent(((JRadioButtonMenuItem)e.getSource()).getText());
				}
			});
			handies.add(item);
			handymenu.add(item);
			if (handy == Handy.getCurrent()) item.setSelected(true);
		}
		handymenu.addSeparator();
		handymenu.add(optionsitem);
	}
	
	public Menu() {
		chooser = new JFileChooser(new File("").getAbsolutePath());
		chooser.setFileFilter(new FileNameExtensionFilter("Jar files", "jar"));
		add(filemenu = new JMenu("File"));
		filemenu.getPopupMenu().setLightWeightPopupEnabled(false);
		JMenuItem openitem = new JMenuItem("Open...");
		openitem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int returnVal = chooser.showOpenDialog(MainFrame.getInstance().getFrame());
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							System.out.println("You chose to open this file: " +
								chooser.getSelectedFile().getName());
							Jademula.load(chooser.getSelectedFile().getPath());
						}
					}
				}
		);
		filemenu.add(openitem);
		filemenu.addSeparator();
		JMenuItem restartitem = new JMenuItem("Restart");
		restartitem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Jademula.reload();
				}
			}
		);
		filemenu.add(restartitem);
		filemenu.addSeparator();
		JMenuItem exititem = new JMenuItem("Exit");
		exititem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jademula.exit();
					}
				}
		);
		filemenu.add(exititem);
		add(optionsmenu = new JMenu("Options"));
		optionsmenu.getPopupMenu().setLightWeightPopupEnabled(false);
		//optionsmenu.add(new JMenuItem("View"));
		//optionsmenu.getItem(0).setEnabled(false);
		JMenu viewsubmenu = new JMenu("View");
		viewsubmenu.getPopupMenu().setLightWeightPopupEnabled(false);
		ButtonGroup sizes = new ButtonGroup();
		JRadioButtonMenuItem radiobutton = new JRadioButtonMenuItem("1x"); 
		radiobutton.setSelected(true);
		radiobutton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Jademula.setZoom(1);
				}				
			}
		);
		viewsubmenu.add(radiobutton);
		sizes.add(radiobutton);
		radiobutton = new JRadioButtonMenuItem("2x");
		radiobutton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jademula.setZoom(2);
					}				
				}
			);
		viewsubmenu.add(radiobutton);
		sizes.add(radiobutton);
		radiobutton = new JRadioButtonMenuItem("3x");
		radiobutton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jademula.setZoom(3);
					}				
				}
			);
		viewsubmenu.add(radiobutton);
		sizes.add(radiobutton);
		radiobutton = new JRadioButtonMenuItem("4x");
		radiobutton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jademula.setZoom(4);
					}				
				}
			);
		viewsubmenu.add(radiobutton);
		sizes.add(radiobutton);
		optionsmenu.add(viewsubmenu);
		optionsmenu.addSeparator();
		handymenu = new JMenu("Handy");
		handymenu.getPopupMenu().setLightWeightPopupEnabled(false);
		/*handymenu.add(new JRadioButtonMenuItem("Nokia"));
		handymenu.add(new JRadioButtonMenuItem("Siemens"));
		handymenu.addSeparator();*/
		optionsitem = new JMenuItem("Config...");
		optionsitem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Options.getInstance().setVisible(true);
				}
			}
		);
		updateHandies();
		optionsmenu.add(handymenu);
		optionsmenu.addSeparator();
		JMenuItem inputitem = new JMenuItem("Input...");
		inputitem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InputDialog.getInstance().setVisible(true);
					}
				}
		);
		optionsmenu.add(inputitem);
		add(helpmenu = new JMenu("Help"));
		helpmenu.getPopupMenu().setLightWeightPopupEnabled(false);
		helpmenu.add(aboutitem = new JMenuItem("About"));
		aboutitem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						About.getInstance().setVisible(true);						
					}
				}
		);
	}
}