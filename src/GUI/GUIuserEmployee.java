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
public class GUIuserEmployee extends JFrame {

	private Employee obj;
	JButton EndShift;
	JButton StartShift;
	JButton logout;

	public GUIuserEmployee(Employee obj) {
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
		setTitle("Employee");
		setSize(300, 600);
		StartShift = new JButton("Start Shift");
		EndShift = new JButton("End Shift");
		logout = new JButton("logout");
		Font f = new Font("Calibri", Font.PLAIN, 16);

		StartShift.addActionListener(new Clicked());
		EndShift.addActionListener(new Clicked());
		logout.addActionListener(new Clicked());

		Container employee = getContentPane();
		employee.setLayout(new GridLayout(3, 1));

		this.setFont(f);
		StartShift.setFont(f);
		EndShift.setFont(f);
		logout.setFont(f);

		employee.add(StartShift);
		employee.add(EndShift);
		employee.add(logout);
	}

	private final class Clicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(StartShift)) {
				StartShift();
			} else if (e.getSource().equals(EndShift)) {
				EndShift();
			} else if (e.getSource().equals(logout)) {
				logout();
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
	}
}
