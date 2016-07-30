/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Lib.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author marcw
 */
public class GUIInvoice extends JFrame {

	private Invoice obj;

	String tempquantity;
	String tempid;
	JTable table;

	public GUIInvoice(Invoice obj, boolean edit) {
		this.obj = obj;
		if (obj.getID() == 0) {
			this.obj.generateID();
		}
		setTitle("invoice");
		setSize(500, 500);
		setLayout(null);
		setResizable(false);
		Font f = new Font("Calibri", Font.PLAIN, 16);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (0 == JOptionPane.showConfirmDialog(null, "Do you want to save befor closing ?", "Exit", YES_NO_OPTION, QUESTION_MESSAGE)) {
					obj.Save();
				}
				dispose();
			}
		});
		setLocationRelativeTo(null);

		JLabel invoicedate = new JLabel("Invoice Date :  " + obj.getSoldDate().toString());
		JLabel invoiceid = new JLabel("Invoice ID : " + Long.toString(obj.getID()));
		JLabel tax = new JLabel("Taxes :  " + obj.getCurrentTax());
		JLabel total = new JLabel("Total :  " + Float.toString(obj.calculateTotal()));
		JButton addproduct = new JButton("Add Product");
		JLabel Quantitylabel = new JLabel("Quantity :");
		JLabel idlabel = new JLabel("ID :");
		JTextField quantity = new JTextField("Quantity");
		JTextField id = new JTextField("ID");

		final int space = 8;
		final int w = 25;

		invoicedate.setBounds(25, 25, 180, w);
		invoiceid.setBounds(25, invoicedate.getY() + invoicedate.getHeight() + space, 180, w);
		tax.setBounds(invoicedate.getX() + 300, invoicedate.getBounds().y, 180, w);
		total.setBounds(invoicedate.getX() + 300, tax.getY() + tax.getHeight() + space * 3, 180, w);
		idlabel.setBounds(0, invoicedate.getY() + invoicedate.getHeight() + space * 8, 90, w);

		Quantitylabel.setBounds(0, idlabel.getHeight() + idlabel.getY() + space, idlabel.getWidth(), w);
		id.setBounds(idlabel.getX() + idlabel.getWidth() + space, idlabel.getY(), 90, w);
		quantity.setBounds(Quantitylabel.getX() + Quantitylabel.getWidth() + space, Quantitylabel.getBounds().y, id.getWidth(), w);
		addproduct.setBounds(id.getX() + id.getWidth() + space, id.getY(), 120, w * 2 + space);

		invoicedate.setFont(f);
		invoiceid.setFont(f);
		tax.setFont(f);
		total.setFont(f);
		addproduct.setFont(f);
		idlabel.setFont(f);
		Quantitylabel.setFont(f);
		id.setFont(f);
		quantity.setFont(f);

		idlabel.setHorizontalAlignment(JLabel.RIGHT);
		Quantitylabel.setHorizontalAlignment(JLabel.RIGHT);

		add(invoicedate);
		add(invoiceid);
		add(tax);
		add(total);
		add(addproduct);
		add(Quantitylabel);
		add(idlabel);
		add(quantity);
		add(id);
		MTabel();

		id.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				id.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				tempid = id.getText();
			}
		});
		quantity.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				quantity.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				tempquantity = quantity.getText();
			}
		});
		addproduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (obj.addToProductsList(Product.searchProduct(tempid), tempquantity)) {
					JOptionPane.showMessageDialog(null, "there isn't product with this id", "ERROR", ERROR_MESSAGE);
				} else {
					total.setText("Total :  " + Float.toString(obj.calculateTotal()));
					remove(table);
					MTabel();
				}
			}
		});

	}

	private void MTabel() {
		table = new JTable(obj.getTabel(), obj.getTabelHeader());
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		add(table);

		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane);
		scrollpane.setBounds(table.getBounds());

		table.setBounds(25, 250, 425, 200);
		scrollpane.setBounds(table.getBounds());

		table.getTableHeader().setReorderingAllowed(false);
	}
}
