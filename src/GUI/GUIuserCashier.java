/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.*;
import Lib.*;
import User.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author marcw
 */
public class GUIuserCashier extends JFrame {

	private Cashier obj;
	JButton StartShift;
	JButton issueinvoice;
	JButton removeinvoice;
	JButton searchinvoice;
	JButton Listinvoice;
	JButton EndShift;
	JButton logout;

	public GUIuserCashier(Cashier obj) {

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
		setTitle("Cashier");
		setSize(600, 600);
		Font f = new Font("Calibri", Font.PLAIN, 16);

		issueinvoice = new JButton("Issue Invoice");
		searchinvoice = new JButton("search for an Invoice");
		Listinvoice = new JButton("List Invoices");
		StartShift = new JButton("Start Shift");
		EndShift = new JButton("End Shift");
		logout = new JButton("logout");

		Container Cashier = getContentPane();
		Cashier.setLayout(new GridLayout(3, 2));

		Cashier.add(issueinvoice);
		Cashier.add(searchinvoice);
		Cashier.add(StartShift);
		Cashier.add(Listinvoice);
		Cashier.add(EndShift);
		Cashier.add(logout);

		this.setFont(f);
		issueinvoice.setFont(f);
		searchinvoice.setFont(f);
		Listinvoice.setFont(f);
		StartShift.setFont(f);
		EndShift.setFont(f);
		logout.setFont(f);

		issueinvoice.addActionListener(new Clicked());
		searchinvoice.addActionListener(new Clicked());
		Listinvoice.addActionListener(new Clicked());
		StartShift.addActionListener(new Clicked());
		EndShift.addActionListener(new Clicked());
		logout.addActionListener(new Clicked());
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
			} else if (e.getSource().equals(searchinvoice)) {
				searchinvoice();
			} else if (e.getSource().equals(issueinvoice)) {
				issueinvoice();
			} else if (e.getSource().equals(Listinvoice)) {
				new GUItabelList(obj.ListEInvoice(), new Invoice().getListHeader(), new Invoice().getTitle()).setVisible(true);
			}
		}

		private void issueinvoice() {
			new GUIInvoice(new Invoice(), true).setVisible(true);
		}

		private void searchinvoice() {
			String temp = JOptionPane.showInputDialog(null, "Enter the invoice ID");
			Invoice tempinvo = Invoice.searchInvoice(temp);
			if (tempinvo == null) {
				JOptionPane.showMessageDialog(null, "The ID you entered don't match any invoice", "Failed to find invoice", ERROR_MESSAGE);
			} else {
				new GUIInvoice(tempinvo, false).setVisible(true);
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
