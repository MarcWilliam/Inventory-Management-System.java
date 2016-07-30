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
public class GUIuserAccountant extends JFrame {

	private Accountant obj;
	JButton StartShift;
	JButton EndShift;
	JButton logout;
	JButton Search_Employee;
	JButton reportemployeesalary;
	JButton ProfitGraph;
	JButton InvoiceTaxesReport;

	public GUIuserAccountant(Accountant obj) {
		this.obj = obj;
		setTitle("Accountant");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Font f = new Font("Calibri", Font.PLAIN, 16);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (0 == JOptionPane.showConfirmDialog(null, "are you sure you want to Logout ?", "Logout", YES_NO_OPTION, QUESTION_MESSAGE)) {
					new GUILogIn().setVisible(true);
					dispose();
				}
			}
		});

		StartShift = new JButton("Start Shift");
		EndShift = new JButton("End Shift");
		logout = new JButton("logout");
		Search_Employee = new JButton("Search Employee");
		reportemployeesalary = new JButton("Report Employee Salary");
		ProfitGraph = new JButton("Monthly profit report");
		InvoiceTaxesReport = new JButton("Report Taxes");

		Container employee = getContentPane();
		employee.setLayout(new GridLayout(4, 2));

		employee.add(StartShift);
		employee.add(Search_Employee);
		employee.add(reportemployeesalary);
		employee.add(InvoiceTaxesReport);
		employee.add(ProfitGraph);
		employee.add(EndShift);
		employee.add(StartShift);
		employee.add(logout);

		this.setFont(f);
		StartShift.setFont(f);
		EndShift.setFont(f);
		logout.setFont(f);
		Search_Employee.setFont(f);
		reportemployeesalary.setFont(f);
		ProfitGraph.setFont(f);
		InvoiceTaxesReport.setFont(f);

		StartShift.addActionListener(new Clicked());
		EndShift.addActionListener(new Clicked());
		logout.addActionListener(new Clicked());
		Search_Employee.addActionListener(new Clicked());
		reportemployeesalary.addActionListener(new Clicked());
		ProfitGraph.addActionListener(new Clicked());
		InvoiceTaxesReport.addActionListener(new Clicked());
	}

	public Accountant getObj() {
		return obj;
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
			} else if (e.getSource().equals(Search_Employee)) {
				searchEmployee();
			} else if (e.getSource().equals(reportemployeesalary)) {
				new GUItabelList(obj.EmployeesSaleryReport(), obj.EmployeesSaleryReportHeader(), "Monthly salary report").setVisible(true);
			} else if (e.getSource().equals(ProfitGraph)) {
				new GUIGraph("Monthly Profit", obj.MonthProfitGraph(), "Profit in $", "Day").setVisible(true);
			} else if (e.getSource().equals(InvoiceTaxesReport)) {
				InvoiceTaxesReport();
			}
		}

		private void InvoiceTaxesReport() {
			Date start = getDate("Enter the starting date"), end = getDate("Enter the ending date");
			if (start == null || end == null) {
				return;
			}
			if (start.GetDate() > end.GetDate()) {
				JOptionPane.showMessageDialog(null, "staring date can't be after the ending date", "Error", ERROR_MESSAGE);
				return;
			}
			new GUItabelList(obj.InvoiceTaxesReport(start, end), obj.InvoiceTaxesReportHeader(), "Invoice taxes report      " + start.toString() + " ~ " + end.toString()).setVisible(true);
		}

		private Date getDate(String Header) {
			Date start = new Date();
			String temp = JOptionPane.showInputDialog(null, Header);
			if (temp != null) {
				if (start.SetDate(temp)) {
					JOptionPane.showMessageDialog(null, "invalid Date format", "Error", ERROR_MESSAGE);
					return getDate(Header);
				} else {
					return start;
				}
			}
			return null;
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

		private void searchEmployee() {
			String temp = JOptionPane.showInputDialog(null, "Enter the Employee ID");
			Employee tempinvo = obj.searchEmployee(temp);
			if (tempinvo == null) {
				JOptionPane.showMessageDialog(null, "The ID you entered don't match any Employee", "Failed to find Employee", ERROR_MESSAGE);
			} else {
				new GUIEmployee_ED(tempinvo, false).setVisible(true);
			}
		}
	}
}
