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
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author marcw
 */
public class GUIuserSupervisor extends JFrame {

	private Supervisor obj;
	JButton AddProducts;
	JButton searchProducts;
	JButton AddOrders;
	JButton searchOrders;
	JButton AddSupliers;
	JButton searchSupliers;
	JButton ListDeplitedProducts;
	JButton ListExpiredProducts;
	JButton ListProducts;
	JButton ListOrders;
	JButton ListSuppliers;
	JButton StartShift;
	JButton EndShift;
	JButton Logout;

	public GUIuserSupervisor(Supervisor obj) {
		this.obj = obj;
		setTitle("supervisor");
		this.setSize(600, 750);
		Font f = new Font("Calibri", Font.PLAIN, 16);
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

		AddProducts = new JButton("Add Products");
		searchProducts = new JButton("search Products");
		AddOrders = new JButton("Add Orders");
		searchOrders = new JButton("search Orders");
		AddSupliers = new JButton("Add Supliers");
		searchSupliers = new JButton("search Supliers");
		ListDeplitedProducts = new JButton("List Deplited Products");
		ListExpiredProducts = new JButton("List Expired Products");

		ListProducts = new JButton("List Products");
		ListOrders = new JButton("List Orders");
		ListSuppliers = new JButton("List Suppliers");
		StartShift = new JButton("Start Shift");
		EndShift = new JButton("End Shift");
		Logout = new JButton("Log out");

		Container supervisor = getContentPane();
		supervisor.setLayout(new GridLayout(5, 3));

		supervisor.add(AddProducts);
		supervisor.add(searchProducts);
		supervisor.add(AddOrders);
		supervisor.add(searchOrders);
		supervisor.add(AddSupliers);
		supervisor.add(searchSupliers);
		supervisor.add(ListDeplitedProducts);
		supervisor.add(ListExpiredProducts);
		supervisor.add(ListProducts);
		supervisor.add(ListOrders);
		supervisor.add(ListSuppliers);
		supervisor.add(StartShift);
		supervisor.add(EndShift);
		supervisor.add(Logout);

		this.setFont(f);
		StartShift.setFont(f);
		AddProducts.setFont(f);
		searchProducts.setFont(f);
		AddOrders.setFont(f);
		searchOrders.setFont(f);
		AddSupliers.setFont(f);
		searchSupliers.setFont(f);
		ListDeplitedProducts.setFont(f);
		ListExpiredProducts.setFont(f);
		ListProducts.setFont(f);
		ListOrders.setFont(f);
		ListSuppliers.setFont(f);
		EndShift.setFont(f);
		Logout.setFont(f);

		StartShift.addActionListener(new Clicked());
		AddProducts.addActionListener(new Clicked());
		searchProducts.addActionListener(new Clicked());
		AddOrders.addActionListener(new Clicked());
		searchOrders.addActionListener(new Clicked());
		AddSupliers.addActionListener(new Clicked());
		searchSupliers.addActionListener(new Clicked());
		ListDeplitedProducts.addActionListener(new Clicked());
		ListExpiredProducts.addActionListener(new Clicked());
		ListProducts.addActionListener(new Clicked());
		ListOrders.addActionListener(new Clicked());
		ListSuppliers.addActionListener(new Clicked());
		EndShift.addActionListener(new Clicked());
		Logout.addActionListener(new Clicked());

	}

	private class Clicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Object btnclick = event.getSource();
			if (btnclick.equals(StartShift)) {
				StartShift();
			} else if (btnclick.equals(AddProducts)) {
				new GUIProduct(new Product(), true).setVisible(true);
			} else if (btnclick.equals(searchProducts)) {
				searchProducts();
			} else if (btnclick.equals(AddOrders)) {
				new GUIOrder(new Order(), true).setVisible(true);
			} else if (btnclick.equals(searchOrders)) {
				searchOrders();
			} else if (btnclick.equals(AddSupliers)) {
				new GUISupplier(new Supplier(), true).setVisible(true);
			} else if (btnclick.equals(searchSupliers)) {
				searchSuppliers();
			} else if (btnclick.equals(ListDeplitedProducts)) {
				ListDeplitedProducts();
			} else if (btnclick.equals(ListExpiredProducts)) {
				ListExpiredProducts();
			} else if (btnclick.equals(ListProducts)) {
				new GUItabelList(obj.ListProduct(), new Product().getListHeader(), new Product().getTitle()).setVisible(true);
			} else if (btnclick.equals(ListOrders)) {
				new GUItabelList(obj.ListOrder(), new Order().getListHeader(), new Order().getTitle()).setVisible(true);
			} else if (btnclick.equals(ListSuppliers)) {
				new GUItabelList(obj.ListSupplier(), new Supplier().getListHeader(), new Supplier().getTitle()).setVisible(true);
			} else if (btnclick.equals(EndShift)) {
				EndShift();
			} else if (btnclick.equals(Logout)) {
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

		private void searchProducts() {
			String temp = JOptionPane.showInputDialog(null, "enter Product ID");
			Product temppro = Product.searchProduct(temp);
			if (temppro == null) {
				JOptionPane.showMessageDialog(null, "The ID you entered don't match any Product", "Failed to find Product", ERROR_MESSAGE);
			} else {
				new GUIProduct(temppro, false).setVisible(true);
			}
		}

		private void ListExpiredProducts() {
			String[][] temppro = obj.listSoonToExpProduct();
			if (temppro == null) {
				JOptionPane.showMessageDialog(null, "Nothing is Expired", "", INFORMATION_MESSAGE);
			} else {
				new GUItabelList(temppro, new Product().getListHeader(), new Product().getTitle()).setVisible(true);
			}
		}
	}

	private void ListDeplitedProducts() {
		String temp = JOptionPane.showInputDialog(null, "enter the lowest Quantity");
		String[][] temppro = obj.lowQuantityProduct(temp);
		if (temppro == null) {
			JOptionPane.showMessageDialog(null, "the inventory is fully stocked", "Error", INFORMATION_MESSAGE);
		} else {
			new GUItabelList(temppro, new Product().getListHeader(), new Product().getTitle()).setVisible(true);
		}
	}

	private void searchOrders() {
		String temp = JOptionPane.showInputDialog(null, "enter Order ID");
		Order tempord = Order.searchOrder(temp);
		if (tempord == null) {
			JOptionPane.showMessageDialog(null, "The ID you entered don't match any Order", "Failed to find Order", ERROR_MESSAGE);
		} else {
			new GUIOrder(tempord, false).setVisible(true);
		}
	}

	private void searchSuppliers() {
		String temp = JOptionPane.showInputDialog(null, "enter Supliers  ID");
		Supplier tempord = Supplier.searchSupplier(temp);
		if (tempord == null) {
			JOptionPane.showMessageDialog(null, "The ID you entered don't match any Supplier", "Failed to find Supplier", ERROR_MESSAGE);
		} else {
			new GUISupplier(tempord, false).setVisible(true);
		}

	}
}
