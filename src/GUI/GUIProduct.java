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

/**
 *
 * @author ghoneim
 */
public class GUIProduct extends JFrame {

	private Product obj;
	boolean savebool;
	JLabel Expdate;
	JLabel Prodate;
	JLabel Quantity;
	JLabel Price;
	JLabel Brand;
	JLabel category;
	JLabel Name;
	JLabel ProductguiID;

	public GUIProduct(Product obj1, boolean Edit) {
		this.savebool = false;
		this.obj = obj1;
		if (obj.getID() == 0) {
			this.obj.generateID();
		}
		Font f = new Font("Calibri", Font.PLAIN, 16);
		setTitle("Product");
		setSize(550, 400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
//===========================================================initializing the variabels===========================================================
		Expdate = new JLabel("Exp date :");
		Prodate = new JLabel("Pro date :");
		Quantity = new JLabel("Quantity :");
		Price = new JLabel("Price / unity :");
		Brand = new JLabel("Brand :");
		category = new JLabel("Category :");
		Name = new JLabel("Name :");
		ProductguiID = new JLabel("ID :   " + Long.toString(obj.getID()));
//===========================================================setBounds===========================================================
		int space = 8;
		Name.setBounds(0, 70, 120, 25);
		ProductguiID.setBounds(98, Name.getBounds().y - Name.getBounds().height - space, Name.getWidth(), Name.getHeight());
		category.setBounds(0, Name.getBounds().y + Name.getBounds().height + space, Name.getWidth(), Name.getHeight());
		Brand.setBounds(0, category.getBounds().y + category.getBounds().height + space, Name.getWidth(), Name.getHeight());
		Price.setBounds(0, Brand.getBounds().y + Brand.getBounds().height + space, Name.getWidth(), Name.getHeight());
		Quantity.setBounds(0, Price.getBounds().y + Price.getBounds().height + space, Name.getWidth(), Name.getHeight());
		Prodate.setBounds(0, Quantity.getBounds().y + Quantity.getBounds().height + space, Name.getWidth(), Name.getHeight());
		Expdate.setBounds(0, Prodate.getBounds().y + Prodate.getBounds().height + space, Name.getWidth(), Name.getHeight());
//===========================================================setFont===========================================================
		Name.setFont(f);
		category.setFont(f);
		Brand.setFont(f);
		Price.setFont(f);
		Quantity.setFont(f);
		Prodate.setFont(f);
		Expdate.setFont(f);
		ProductguiID.setFont(f);
		this.setFont(f);
//===========================================================setAlignment===========================================================
		Name.setHorizontalAlignment(JLabel.RIGHT);
		category.setHorizontalAlignment(JLabel.RIGHT);
		Brand.setHorizontalAlignment(JLabel.RIGHT);
		Price.setHorizontalAlignment(JLabel.RIGHT);
		Quantity.setHorizontalAlignment(JLabel.RIGHT);
		Prodate.setHorizontalAlignment(JLabel.RIGHT);
		Expdate.setHorizontalAlignment(JLabel.RIGHT);
//===========================================================add===========================================================
		add(ProductguiID);
		add(Name);
		add(category);
		add(Brand);
		add(Price);
		add(Quantity);
		add(Prodate);
		add(Expdate);

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
//===========================================================initializing the variabels===========================================================
			JButton AddSupplierbut = new JButton("Add Supplier");
			JButton Savebut = new JButton("Save");
			JTextField Expdate1 = new JTextField(obj.getEndDate().toString());
			JTextField Prodate1 = new JTextField(obj.getStartDate().toString());
			JTextField Quantitytxt = new JTextField(obj.getQuantity().toString());
			JTextField PriceUnittxt = new JTextField(Float.toString(obj.getPricePerUnit()));
			JTextField Brandtxt = new JTextField(obj.getBrand());
			JTextField Categorytxt = new JTextField(obj.getCategory());
			JTextField Nametxt = new JTextField(obj.getName());
//===========================================================FocusListener===========================================================
			Categorytxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setCategory(Categorytxt.getText())) {
						Categorytxt.setForeground(Color.red);
					} else {
						Categorytxt.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getCategory().equals(new Product().getCategory())) {
						Categorytxt.setText("");
					}
				}
			});
			Nametxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setName(Nametxt.getText())) {
						Nametxt.setForeground(Color.red);
					} else {
						Nametxt.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getName().equals(new Product().getName())) {
						Nametxt.setText("");
					}
				}
			});
			Brandtxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setBrand(Brandtxt.getText())) {
						Brandtxt.setForeground(Color.red);
					} else {
						Brandtxt.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getBrand().equals(new Product().getBrand())) {
						Brandtxt.setText("");
					}
				}
			});
			Quantitytxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setQuantity(Quantitytxt.getText())) {
						Quantitytxt.setForeground(Color.red);
					} else {
						Quantitytxt.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getQuantity().equals(new Product().getQuantity())) {
						Quantitytxt.setText("");
					}
				}
			});
			PriceUnittxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setPricePerUnit(PriceUnittxt.getText())) {
						PriceUnittxt.setForeground(Color.red);
					} else {
						PriceUnittxt.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getPricePerUnit() == new Product().getPricePerUnit()) {
						PriceUnittxt.setText("");
					}
				}
			});
			Expdate1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setEndDate(Expdate1.getText())) {
						Expdate1.setForeground(Color.red);
					} else {
						Expdate1.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getEndDate().CompareToCurrent()) {
						Expdate1.setText("");
					}
				}
			});
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
//===========================================================action listener===========================================================
			Savebut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (savebool == false) {
						if (obj.Save()) {
							JOptionPane.showMessageDialog(null, "Failed to Save", "ERROR", ERROR_MESSAGE);
						} else {
							savebool = true;
						}
					}
				}
			});
			AddSupplierbut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String temp = JOptionPane.showInputDialog(null, "enter Supliers  ID");
					Supplier tempord = Supplier.searchSupplier(temp);
					if (tempord == null) {
						JOptionPane.showMessageDialog(null, "The ID you entered don't match any Supplier", "Failed to find Supplier", ERROR_MESSAGE);
					} else {
						obj.addToSupplierList(tempord);
						JOptionPane.showMessageDialog(null, "Supplier  " + tempord.getName() + "  Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
//===========================================================setFont===========================================================
			AddSupplierbut.setFont(f);
			Savebut.setFont(f);
			Expdate1.setFont(f);
			Prodate1.setFont(f);
			Quantitytxt.setFont(f);
			PriceUnittxt.setFont(f);
			Brandtxt.setFont(f);
			Categorytxt.setFont(f);
			Nametxt.setFont(f);
//===========================================================setBounds===========================================================
			Nametxt.setBounds(Name.getX() + Name.getWidth() + space, 70, 300, Name.getHeight());
			Categorytxt.setBounds(Nametxt.getX(), Nametxt.getBounds().y + Nametxt.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			Brandtxt.setBounds(Nametxt.getX(), Categorytxt.getBounds().y + Categorytxt.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			PriceUnittxt.setBounds(Nametxt.getX(), Brandtxt.getBounds().y + Brandtxt.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			Quantitytxt.setBounds(Nametxt.getX(), PriceUnittxt.getBounds().y + PriceUnittxt.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			Prodate1.setBounds(Nametxt.getX(), Quantitytxt.getBounds().y + Quantitytxt.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			Expdate1.setBounds(Nametxt.getX(), Prodate1.getBounds().y + Prodate1.getBounds().height + space, Nametxt.getWidth(), Name.getHeight());
			Savebut.setBounds(this.getWidth() / 2 - space * 2 - Name.getWidth(), Expdate1.getY() + space * 5, Name.getWidth(), Name.getHeight() * 3 / 2);
			AddSupplierbut.setBounds(this.getWidth() - space * 4 - Name.getWidth() * 2, Savebut.getY(), Savebut.getWidth(), Savebut.getHeight());
//===========================================================Add===========================================================
			add(Nametxt);
			add(Categorytxt);
			add(Brandtxt);
			add(PriceUnittxt);
			add(Quantitytxt);
			add(Prodate1);
			add(Expdate1);
			add(AddSupplierbut);
			add(Savebut);

		} else {
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//===========================================================initializing the variabels===========================================================
			JButton edit = new JButton("Edit");
			JButton delete = new JButton("Delete");
			JLabel ExpData1 = new JLabel(obj.getEndDate().toString());
			JLabel ProData1 = new JLabel(obj.getStartDate().toString());
			JLabel Quantity1 = new JLabel(obj.getQuantity().toString());
			JLabel Price1 = new JLabel(Float.toString(obj.getPricePerUnit()));
			JLabel Brand1 = new JLabel(obj.getBrand());
			JLabel Category1 = new JLabel(obj.getCategory());
			JLabel Name1 = new JLabel(obj.getName());
//===========================================================set bounds===========================================================
			Name1.setBounds(Name.getX() + Name.getWidth() + space, 70, 300, Name.getHeight());
			Category1.setBounds(Name1.getX(), Name1.getBounds().y + Name1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			Brand1.setBounds(Name1.getX(), Category1.getBounds().y + Category1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			Price1.setBounds(Name1.getX(), Brand1.getBounds().y + Brand1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			Quantity1.setBounds(Name1.getX(), Price1.getBounds().y + Price1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			ProData1.setBounds(Name1.getX(), Quantity1.getBounds().y + Quantity1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			ExpData1.setBounds(Name1.getX(), ProData1.getBounds().y + ProData1.getBounds().height + space, Name1.getWidth(), Name.getHeight());
			delete.setBounds(this.getWidth() / 2 - space * 2 - Name.getWidth(), ExpData1.getY() + space * 5, Name.getWidth(), Name.getHeight() * 3 / 2);
			edit.setBounds(this.getWidth() - space * 4 - Name.getWidth() * 2, delete.getY(), delete.getWidth(), delete.getHeight());
//===========================================================setFont===========================================================
			edit.setFont(f);
			delete.setFont(f);
			Category1.setFont(f);
			Name1.setFont(f);
			ExpData1.setFont(f);
			ProData1.setFont(f);
			Quantity1.setFont(f);
			Brand1.setFont(f);
			Price1.setFont(f);
//===========================================================ActionListener===========================================================
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new GUIProduct(obj, true).setVisible(true);
					dispose();
				}
			});
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
			add(Name1);
			add(Category1);
			add(Brand1);
			add(Price1);
			add(Quantity1);
			add(ProData1);
			add(ExpData1);
			add(delete);
			add(edit);
		}

	}

}
