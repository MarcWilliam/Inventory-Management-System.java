package GUI;

import Lib.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class GUISupplier extends JFrame {

	private Supplier obj;
	boolean savebool;
	JLabel ID1;
	JLabel Name1;
	JLabel Bankacc1;
	JLabel Website1;
	JLabel Email1;
	JLabel Adress1;
	JLabel PhoneNumber1;

	public GUISupplier(Supplier obj1, boolean Edit) {
		this.savebool = false;
		this.obj = obj1;
		if (obj.getID() == 0) {
			this.obj.generateID();
		}
		Font f = new Font("Calibri", Font.PLAIN, 16);
		setTitle("Supplier");
		setSize(550, 400);
		setLayout(null);
		//	setResizable(false);
		setLocationRelativeTo(null);
		//setAlwaysOnTop(rootPaneCheckingEnabled);
//===========================================================initializing the variabels===========================================================
		ID1 = new JLabel("ID : " + Long.toString(obj.getID()));
		Name1 = new JLabel("Name :");
		Bankacc1 = new JLabel("Bank Account :");
		Website1 = new JLabel("Website :");
		Email1 = new JLabel("Email :");
		Adress1 = new JLabel("Address :");
		PhoneNumber1 = new JLabel("Phone Number :");
//===========================================================setBounds===========================================================

		Name1.setBounds(0, 70, 120, 25);
		ID1.setBounds(98, Name1.getBounds().y - 5 - Name1.getBounds().height, 200, Name1.getHeight());
		Bankacc1.setBounds(0, Name1.getBounds().y + Name1.getBounds().height + 5, Name1.getWidth(), Name1.getHeight());
		Website1.setBounds(0, Bankacc1.getBounds().y + Bankacc1.getBounds().height + 5, Name1.getWidth(), Name1.getHeight());
		Email1.setBounds(0, Website1.getBounds().y + Website1.getBounds().height + 5, Name1.getWidth(), Name1.getHeight());
		Adress1.setBounds(0, Email1.getBounds().y + Email1.getBounds().height + 5, Name1.getWidth(), Name1.getHeight());
		PhoneNumber1.setBounds(0, Adress1.getBounds().y + Adress1.getBounds().height + 5, Name1.getWidth(), Name1.getHeight());
//===========================================================setAlignment===========================================================
		Name1.setHorizontalAlignment(JLabel.RIGHT);
		Bankacc1.setHorizontalAlignment(JLabel.RIGHT);
		Website1.setHorizontalAlignment(JLabel.RIGHT);
		Email1.setHorizontalAlignment(JLabel.RIGHT);
		Adress1.setHorizontalAlignment(JLabel.RIGHT);
		PhoneNumber1.setHorizontalAlignment(JLabel.RIGHT);
//===========================================================setFont===========================================================
		this.setFont(f);
		ID1.setFont(f);
		Name1.setFont(f);
		Bankacc1.setFont(f);
		Website1.setFont(f);
		Email1.setFont(f);
		Adress1.setFont(f);
		PhoneNumber1.setFont(f);
//===========================================================add===========================================================
		add(ID1);
		add(Name1);
		add(Bankacc1);
		add(Website1);
		add(Email1);
		add(Adress1);
		add(PhoneNumber1);

		if (Edit == false) {
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//===========================================================initializing the variabels===========================================================
			JLabel Name = new JLabel(obj.getName());
			JLabel Bankacc = new JLabel(Long.toString(obj.getBankAcc()));
			JLabel Website = new JLabel(obj.getWebsite());
			JLabel Email = new JLabel(obj.getEmail());
			JLabel Adress = new JLabel(obj.getAddress());
			JLabel PhoneNumber = new JLabel(Long.toString(obj.getPhoneNumber()));
			JButton delete = new JButton("Delete");
			JButton edit = new JButton("Edit");
//===========================================================setBounds===========================================================
			Dimension dimLab = new Dimension(350, 25);
			Name.setBounds(125, 70, 350, 25);
			Bankacc.setBounds(125, Name.getBounds().y + Name.getBounds().height + 5, dimLab.width, dimLab.height);
			Website.setBounds(125, Bankacc.getBounds().y + Bankacc.getBounds().height + 5, dimLab.width, dimLab.height);
			Email.setBounds(125, Website.getBounds().y + Website.getBounds().height + 5, dimLab.width, dimLab.height);
			Adress.setBounds(125, Email.getBounds().y + Email.getBounds().height + 5, dimLab.width, dimLab.height);
			PhoneNumber.setBounds(125, Adress.getBounds().y + Adress.getBounds().height + 5, dimLab.width, dimLab.height);
			delete.setBounds(125, 280, 150, 40);
			edit.setBounds(delete.getBounds().x + delete.getBounds().width + 20 + 20, 280, 150, 40);
//===========================================================setFont===========================================================
			Name.setFont(f);
			Bankacc.setFont(f);
			Website.setFont(f);
			Email.setFont(f);
			Adress.setFont(f);
			PhoneNumber.setFont(f);
			delete.setFont(f);
			edit.setFont(f);
//===========================================================ActionListener===========================================================
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new GUISupplier(obj, true).setVisible(true);
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
			}
			);
//===========================================================Add===========================================================
			add(Name);
			add(Bankacc);
			add(Website);
			add(Email);
			add(Adress);
			add(PhoneNumber);
			add(delete);
			add(edit);
		} else if (Edit == true) {
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
			JTextField Name = new JTextField(obj.getName());
			JTextField Bankacc = new JTextField(Long.toString(obj.getBankAcc()));
			JTextField Website = new JTextField(obj.getWebsite());
			JTextField Email = new JTextField(obj.getEmail());
			JTextField Adress = new JTextField(obj.getAddress());
			JTextField PhoneNumber = new JTextField(Long.toString(obj.getPhoneNumber()));
			JButton Addproduct = new JButton("Add Product");
			JButton Save = new JButton("Save");

//===========================================================set bounds===========================================================
			Name.setBounds(125, 70, 350, 25);
			Bankacc.setBounds(125, Name.getBounds().y + Name.getBounds().height + 5, 350, 25);
			Website.setBounds(125, Bankacc.getBounds().y + Bankacc.getBounds().height + 5, 350, 25);
			Email.setBounds(125, Website.getBounds().y + Website.getBounds().height + 5, 350, 25);
			Adress.setBounds(125, Email.getBounds().y + Email.getBounds().height + 5, 350, 25);
			PhoneNumber.setBounds(125, Adress.getBounds().y + Adress.getBounds().height + 5, 350, 25);
			Addproduct.setBounds(125, 280, 120, 40);
			Save.setBounds(Addproduct.getBounds().x + Addproduct.getBounds().width + 30, 280, 70, 40);
//===========================================================setFont===========================================================
			Name.setFont(f);
			Bankacc.setFont(f);
			Website.setFont(f);
			Email.setFont(f);
			Adress.setFont(f);
			PhoneNumber.setFont(f);
			Addproduct.setFont(f);
			Save.setFont(f);
//===========================================================FocusListener===========================================================
			Name.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = Name.getText();
					if (temp != null) {
						if (obj.setName(temp)) {
							Name.setForeground(Color.red);
						} else {
							Name.setForeground(Color.black);
						}
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getName().equals(new Supplier().getName())) {
						Name.setText("");
					}
				}
			});
			Bankacc.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = Bankacc.getText();
					if (obj.setBankAcc(temp)) {
						Bankacc.setForeground(Color.red);
					} else {
						Bankacc.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getBankAcc() == new Supplier().getBankAcc()) {
						Bankacc.setText("");
					}
				}
			});
			Website.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = Website.getText();
					if (temp != null) {
						if (obj.setWebsite(temp)) {
							Website.setForeground(Color.red);
						} else {
							Website.setForeground(Color.black);
						}
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getWebsite().equals(new Supplier().getWebsite())) {
						Website.setText("");
					}
				}
			});
			Email.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = Email.getText();
					if (temp != null) {
						if (obj.setEmail(temp)) {
							Email.setForeground(Color.red);
						} else {
							Email.setForeground(Color.black);
						}
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getEmail().equals(new Supplier().getEmail())) {
						Email.setText("");
					}
				}
			});
			Adress.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = Adress.getText();
					if (temp != null) {
						if (obj.setAddress(temp)) {
							Adress.setForeground(Color.red);
						} else {
							Adress.setForeground(Color.black);
						}
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getAddress().equals(new Supplier().getAddress())) {
						Adress.setText("");
					}
				}
			});
			PhoneNumber.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					String temp = PhoneNumber.getText();
					if (obj.setPhoneNumber(temp)) {
						PhoneNumber.setForeground(Color.red);
					} else {
						PhoneNumber.setForeground(Color.black);
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getPhoneNumber() == new Supplier().getPhoneNumber()) {
						PhoneNumber.setText("");
					}
				}
			});
//===========================================================ActionListener===========================================================
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
			Addproduct.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String temp = JOptionPane.showInputDialog(null, "enter Product ID");
					Product temppro = Product.searchProduct(temp);
					if (temppro == null) {
						JOptionPane.showMessageDialog(null, "The ID you entered don't match any Product", "Failed to find Product", ERROR_MESSAGE);
					} else {
						obj.addToProductsList(temppro);
						JOptionPane.showMessageDialog(null, "Supplier  " + temppro.getName() + "  Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
//===========================================================Add===========================================================
			add(Name);
			add(Bankacc);
			add(Website);
			add(Email);
			add(Adress);
			add(PhoneNumber);
			add(Addproduct);
			add(Save);

		}

	}
}
