package GUI;

import Lib.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GUIOrder extends JFrame {

	final int space = 8;
	private Order obj;
	boolean savebool;
	JLabel deliverydate1;
	JLabel quantity1;
	JLabel priceperunit1;
	JLabel supplier1;
	JLabel SupplieridguiID;
	JLabel product1;
	Product temp;

	public GUIOrder(Order obj1, boolean Edit) {
		this.savebool = false;
		this.obj = obj1;
		if (obj.getID() == 0) {
			this.obj.generateID();
		}
		Font f = new Font("Calibri", Font.PLAIN, 16);
		setTitle("Order");
		setSize(525, 370);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
//===========================================================initializing the variabels===========================================================
		SupplieridguiID = new JLabel("ID :   " + Long.toString(obj.getID()));
		JLabel product1 = new JLabel("Product :");
		JLabel supplier1 = new JLabel("Supplier :");
		JLabel priceperunit1 = new JLabel("Price/unite :");
		JLabel quantity1 = new JLabel("Quantity :");
		JLabel deliverydate1 = new JLabel("Delivery Date :");
//===========================================================setBounds===========================================================
		product1.setBounds(0, 65, 120, 25);
		SupplieridguiID.setBounds(98, product1.getBounds().y - product1.getBounds().height - space, product1.getWidth(), product1.getHeight());
		supplier1.setBounds(0, product1.getBounds().y + product1.getBounds().height + space, product1.getWidth(), product1.getHeight());
		priceperunit1.setBounds(0, supplier1.getBounds().y + supplier1.getBounds().height + space, product1.getWidth(), product1.getHeight());
		quantity1.setBounds(0, priceperunit1.getBounds().y + priceperunit1.getBounds().height + space, product1.getWidth(), product1.getHeight());
		deliverydate1.setBounds(0, quantity1.getBounds().y + quantity1.getBounds().height + space, product1.getWidth(), product1.getHeight());
//===========================================================setFont===========================================================
		this.setFont(f);
		deliverydate1.setFont(f);
		quantity1.setFont(f);
		priceperunit1.setFont(f);
		supplier1.setFont(f);
		SupplieridguiID.setFont(f);
		product1.setFont(f);
//===========================================================setAlignment===========================================================
		deliverydate1.setHorizontalAlignment(JLabel.RIGHT);
		quantity1.setHorizontalAlignment(JLabel.RIGHT);
		priceperunit1.setHorizontalAlignment(JLabel.RIGHT);
		supplier1.setHorizontalAlignment(JLabel.RIGHT);
		product1.setHorizontalAlignment(JLabel.RIGHT);
//===========================================================add===========================================================
		add(SupplieridguiID);
		add(product1);
		add(supplier1);
		add(priceperunit1);
		add(quantity1);
		add(deliverydate1);
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Edit^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		if (Edit == true) {
			//===========================================================Default Close Opperation===========================================================
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (savebool == false && 0 == JOptionPane.showConfirmDialog(null, "Do you want to save befor closing ?", "Exit", YES_NO_OPTION, QUESTION_MESSAGE)) {
						obj.Save();
					}
					dispose();
				}
			});
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//===========================================================initializing the variabels===========================================================

			JComboBox product = new JComboBox(Product.ProductsIDs());
			temp = new Product();

			product.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					obj.setProductID(Product.searchProduct(product.getSelectedItem().toString()));
				}
			});
			JComboBox supplier = new JComboBox(Supplier.SuppliersIDs());
			supplier.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					obj.setSupplierID(Supplier.searchSupplier(supplier.getSelectedItem().toString()));
				}
			});
			JTextField priceperunit = new JTextField(Float.toString(obj.getPricePerUnit()));
			JTextField quantity = new JTextField(Long.toString(obj.getQuantity()));
			JTextField Prodate1 = new JTextField(obj.getStartDate().toString());
			JButton Save = new JButton("Save");
//===========================================================setBounds===========================================================
			product.setBounds(product1.getWidth() + space, product1.getY(), 350, product1.getHeight());
			supplier.setBounds(product1.getWidth() + space, product.getY() + product.getHeight() + space, product.getWidth(), product.getHeight());
			priceperunit.setBounds(product1.getWidth() + space, supplier.getY() + supplier.getHeight() + space, product.getWidth(), product.getHeight());
			quantity.setBounds(product1.getWidth() + space, priceperunit.getY() + priceperunit.getHeight() + space, product.getWidth(), product.getHeight());
			Prodate1.setBounds(product1.getWidth() + space, quantity.getY() + quantity.getHeight() + space, product.getWidth(), product.getHeight());

			Save.setBounds(this.getWidth() / 2 + space, Prodate1.getY() + Prodate1.getHeight() + space * 3, 120, product1.getHeight() * 3 / 2);
//===========================================================setFont===========================================================
			product.setFont(f);
			supplier.setFont(f);
			priceperunit.setFont(f);
			quantity.setFont(f);
			Prodate1.setFont(f);
			Save.setFont(f);
			SupplieridguiID.setFont(f);
//===========================================================Add===========================================================
			add(product);
			add(supplier);
			add(priceperunit);
			add(quantity);
			add(Prodate1);
			add(Save);
//===========================================================ActionListener===========================================================
			Prodate1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setStartDate(Prodate1.getText())) {
						Prodate1.setForeground(Color.red);
					} else {
						Prodate1.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getStartDate().CompareToCurrent()) {
						Prodate1.setText("");
					}
				}
			});
			Save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (savebool == false) {
						if (obj.Save()) {
							JOptionPane.showMessageDialog(null, "Failed Save", "ERROR", ERROR_MESSAGE);
						} else {
							savebool = true;
						}
					}
				}
			});
		} //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^Display^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		else if (Edit == false) {
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//===========================================================initializing the variabels===========================================================
			JButton edit = new JButton("Edit");
			JButton delete = new JButton("Delete");
			JLabel deliverydate = new JLabel("fuyluyf");
			JLabel quantity = new JLabel(Long.toString(obj.getQuantity()));
			JLabel priceperunit = new JLabel(Float.toString(obj.getPricePerUnit()));
			JLabel supplier = new JLabel("kugvkjv");
			JLabel product = new JLabel("obj.getName()");
//===========================================================set bounds===========================================================
			product.setBounds(product1.getWidth() + space, product1.getY(), 350, product1.getHeight());
			supplier.setBounds(product1.getWidth() + space, product.getY() + product.getHeight() + space, product.getWidth(), product.getHeight());
			priceperunit.setBounds(product1.getWidth() + space, supplier.getY() + supplier.getHeight() + space, product.getWidth(), product.getHeight());
			quantity.setBounds(product1.getWidth() + space, priceperunit.getY() + priceperunit.getHeight() + space, product.getWidth(), product.getHeight());
			deliverydate.setBounds(product1.getWidth() + space, quantity.getY() + quantity.getHeight() + space, product.getWidth(), product.getHeight());
			delete.setBounds(this.getWidth() / 2 + space, deliverydate.getY() + deliverydate.getHeight() + space * 3, 120, product1.getHeight() * 3 / 2);
			edit.setBounds(delete.getX() / 2, deliverydate.getY() + deliverydate.getHeight() + space * 3, 120, product1.getHeight() * 3 / 2);
//===========================================================setFont===========================================================
			product.setFont(f);
			supplier.setFont(f);
			priceperunit.setFont(f);
			quantity.setFont(f);
			deliverydate.setFont(f);
			edit.setFont(f);
			delete.setFont(f);
//===========================================================ActionListener===========================================================
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new GUIOrder(obj, true).setVisible(true);
					dispose();
				}
			}
			);
			delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (obj.Delete()) {
						JOptionPane.showMessageDialog(null, "Failed to Delete", "ERROR", ERROR_MESSAGE);
					} else {
						dispose();
					}
				}
			});
//===========================================================Add===========================================================
			add(product);
			add(supplier);
			add(priceperunit);
			add(quantity);
			add(deliverydate);
			add(delete);
			add(edit);
		}
	}
}
