/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.*;
import User.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author marcw
 */
public class GUIuserManger extends JFrame {

	private final Manger obj;

	JButton StartShift;
	JButton addEmployee;
	JButton searchEmployee;
	JButton listemployee;
	JButton BackUpfiles;
	JButton EndShift;
	JButton logout;

	public GUIuserManger(Manger obj) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (0 == JOptionPane.showConfirmDialog(null, "are you sure you want to Logout ?", "Logout", YES_NO_OPTION, QUESTION_MESSAGE)) {
					new GUILogIn().setVisible(true);
					dispose();
				}
			}
		});

		this.obj = obj;
		setTitle("Manager");
		setSize(600, 600);
		Container Manager = getContentPane();
		Manager.setLayout(new GridLayout(4, 2));
		Font f = new Font("Calibri", Font.PLAIN, 16);

		addEmployee = new JButton("Add Employee");
		searchEmployee = new JButton("Search Employee");
		listemployee = new JButton("List Employee");
		BackUpfiles = new JButton("Backup Files");
		StartShift = new JButton("Start Shift");
		EndShift = new JButton("End Shift");
		logout = new JButton("logout");

		Manager.add(addEmployee);
		Manager.add(searchEmployee);
		Manager.add(listemployee);
		Manager.add(BackUpfiles);
		Manager.add(StartShift);
		Manager.add(EndShift);
		Manager.add(logout);

		this.setFont(f);
		addEmployee.setFont(f);
		searchEmployee.setFont(f);
		listemployee.setFont(f);
		BackUpfiles.setFont(f);
		StartShift.setFont(f);
		EndShift.setFont(f);
		logout.setFont(f);

		addEmployee.addActionListener(new myHandler());
		searchEmployee.addActionListener(new myHandler());
		listemployee.addActionListener(new myHandler());
		BackUpfiles.addActionListener(new myHandler());
		StartShift.addActionListener(new myHandler());
		EndShift.addActionListener(new myHandler());
		logout.addActionListener(new myHandler());

		addEmployee.setBorderPainted(false);
	}

	private class myHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Object btnclick = event.getSource();
			if (btnclick.equals(StartShift)) {
				StartShift();
			} else if (btnclick.equals(addEmployee)) {
				addEmployee();
			} else if (btnclick.equals(searchEmployee)) {
				searchEmployee();
			} else if (btnclick.equals(listemployee)) {
				new GUItabelList(obj.ListEmployee(), new Employee().getListHeader(), new Employee().getTitle()).setVisible(true);
			} else if (btnclick.equals(BackUpfiles)) {
				BackUpFiles();
			} else if (btnclick.equals(EndShift)) {
				EndShift();
			} else if (btnclick.equals(logout)) {
				logout();
			}

		}

		private void addEmployee() {
			new GUIEmployee_ED(new Employee(), true).setVisible(true);
		}

		private void searchEmployee() {
			String temp = JOptionPane.showInputDialog(null, "Enter the Employee ID");
			Employee tempinvo = obj.searchEmployee(temp);
			if (tempinvo == null) {
				JOptionPane.showMessageDialog(null, "The ID you entered don't match any Employee", "Failed to find Employee", ERROR_MESSAGE);
			} else {
				new GUIEmployee_ED(tempinvo, false).setVisible(true);
			}
		}

		private void StartShift() {
			if (obj.LogShiftIn()) {
				JOptionPane.showMessageDialog(null, "Failed to Start the Shift", "Error : FileCorupt", ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Started Shift at  " + new Date().toString(), "Shift Started", INFORMATION_MESSAGE);
			}
		}

		private void EndShift() {
			if (obj.LogShiftOut()) {
				JOptionPane.showMessageDialog(null, "You must start shift befor you can end it", "Error : Failed to Start the Shift", ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Ended Shift at  " + new Date().toString(), "Shift Ended", INFORMATION_MESSAGE);
			}
		}

		private void logout() {
			new GUILogIn().setVisible(true);
			dispose();
		}

		private void BackUpFiles() {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Creat  BackUp");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (obj.BackUpfiles(chooser.getSelectedFile().toString())) {
					JOptionPane.showMessageDialog(null, "Failed to backup files", "Error : FileCorupt", ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Back up Succsesfull", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
