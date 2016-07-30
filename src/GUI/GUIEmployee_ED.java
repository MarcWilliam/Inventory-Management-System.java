package GUI;

import User.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUIEmployee_ED extends JFrame {

	Employee obj;
	boolean Edit;
	boolean savebool;

	JLabel picLabel2;
	JLabel Employeeidgui;
	JLabel Name1;
	JLabel password1;
	JLabel Departement1;
	JLabel Jobtitle1;
	JLabel Basicsalary1;
	JLabel grosssalary1;
	JLabel email1;
	JLabel address1;
	JLabel phonenumber1;
	JLabel gender1;

	public GUIEmployee_ED(Employee obj, boolean Edit) {
		this.Edit = Edit;
		setResizable(false);
		//setAlwaysOnTop(rootPaneCheckingEnabled);
		setLocationRelativeTo(null);
		setTitle("Employee");
		setSize(650, 500);
		setLayout(null);

		if (obj.getID() == 0) {
			obj.generateID();
		}

		Font f = new Font("Calibri", Font.PLAIN, 16);

		Employeeidgui = new JLabel("ID:   " + Long.toString(obj.getID()));
		Name1 = new JLabel("Name :");
		password1 = new JLabel("Password :");
		Departement1 = new JLabel("Departement :");
		Jobtitle1 = new JLabel("Job title :");
		Basicsalary1 = new JLabel("Basic Salary :");
		grosssalary1 = new JLabel("Gross Salary :");
		email1 = new JLabel("Email :");
		address1 = new JLabel("Address :");
		phonenumber1 = new JLabel("Phone Number :");
		gender1 = new JLabel("Gender :");

		int space = 8;
		Name1.setBounds(150, 70, 120, 25);
		Employeeidgui.setBounds(250, 35, Name1.getWidth(), Name1.getHeight());
		password1.setBounds(Name1.getX(), Name1.getBounds().y + Name1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		Departement1.setBounds(Name1.getX(), password1.getBounds().y + password1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		Jobtitle1.setBounds(Name1.getX(), Departement1.getBounds().y + Departement1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		Basicsalary1.setBounds(Name1.getX(), Jobtitle1.getBounds().y + Jobtitle1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		grosssalary1.setBounds(Name1.getX(), Basicsalary1.getBounds().y + Basicsalary1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		email1.setBounds(Name1.getX(), grosssalary1.getBounds().y + grosssalary1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		address1.setBounds(Name1.getX(), email1.getBounds().y + email1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		phonenumber1.setBounds(Name1.getX(), address1.getBounds().y + address1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());
		gender1.setBounds(Name1.getX(), phonenumber1.getBounds().y + phonenumber1.getBounds().height + space, Name1.getWidth(), Name1.getHeight());

		Name1.setHorizontalAlignment(JLabel.RIGHT);
		password1.setHorizontalAlignment(JLabel.RIGHT);
		Departement1.setHorizontalAlignment(JLabel.RIGHT);
		Jobtitle1.setHorizontalAlignment(JLabel.RIGHT);
		Basicsalary1.setHorizontalAlignment(JLabel.RIGHT);
		grosssalary1.setHorizontalAlignment(JLabel.RIGHT);
		email1.setHorizontalAlignment(JLabel.RIGHT);
		address1.setHorizontalAlignment(JLabel.RIGHT);
		phonenumber1.setHorizontalAlignment(JLabel.RIGHT);
		gender1.setHorizontalAlignment(JLabel.RIGHT);

		this.setFont(f);
		Employeeidgui.setFont(f);
		Name1.setFont(f);
		password1.setFont(f);
		Departement1.setFont(f);
		Jobtitle1.setFont(f);
		Basicsalary1.setFont(f);
		grosssalary1.setFont(f);
		email1.setFont(f);
		address1.setFont(f);
		phonenumber1.setFont(f);
		gender1.setFont(f);

		add(Name1);
		add(password1);
		add(Departement1);
		add(Jobtitle1);
		add(Basicsalary1);
		add(grosssalary1);
		add(email1);
		add(address1);
		add(phonenumber1);
		add(gender1);
		add(Employeeidgui);

		ImageIcon myPicture3 = new ImageIcon(obj.getImagePath());
		Image image1 = myPicture3.getImage();
		Image newimg1 = image1.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon mypicture2 = new ImageIcon(newimg1);

		picLabel2 = new JLabel(mypicture2);
		picLabel2.setBounds(15, 70, 130, 130);
		add(picLabel2);
//========================================================================================================================================================

		if (Edit == true) {
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (savebool == false && 0 == JOptionPane.showConfirmDialog(null, "Do you want to save befor closing ?", "Exit", YES_NO_OPTION, QUESTION_MESSAGE)) {
						obj.Save();
					}
					dispose();
				}
			});
			JTextField Name = new JTextField(obj.getName());
			JPasswordField password = new JPasswordField(obj.getPW());
			JCheckBox showpass = new JCheckBox();
			JTextField Departement = new JTextField(obj.getDepartment());
			JTextField Jobtitle = new JTextField(obj.getJobTitle());
			JTextField Basicsalary = new JTextField(Float.toString(obj.getBasicSalary()));
			JTextField grosssalary = new JTextField(Float.toString(obj.getGrossSalary()));
			JTextField email = new JTextField(obj.getEmail());
			JTextField address = new JTextField(obj.getAddress());
			JTextField phonenumber = new JTextField(Long.toString(obj.getPhoneNumber()));
			JRadioButton male = new JRadioButton("<html>&#x2642" + " Male");
			JRadioButton female = new JRadioButton("<html>&#x2640" + " Female");
			//	JButton save = new JButton("Save");
			JButton save = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));

			JButton open = new JButton("Browse");

			add(Name);
			add(password);
			add(showpass);
			add(Departement);
			add(Jobtitle);
			add(Basicsalary);
			add(grosssalary);
			add(email);
			add(address);
			add(phonenumber);
			add(male);
			add(female);
			add(save);
			add(open);

			Name.setBounds(Name1.getX() + Name1.getWidth() + space, 70, 300, Name1.getHeight());
			password.setBounds(Name.getX(), Name.getY() + Name.getHeight() + space, Name.getWidth(), Name.getHeight());
			showpass.setBounds(password.getX() + password.getWidth(), Name1.getBounds().y + Name1.getBounds().height + space, 30, Name.getHeight());
			Departement.setBounds(Name.getX(), password.getBounds().y + password.getBounds().height + space, Name.getWidth(), Name.getHeight());
			Jobtitle.setBounds(Name.getX(), Departement.getBounds().y + Departement.getBounds().height + space, Name.getWidth(), Name.getHeight());
			Basicsalary.setBounds(Name.getX(), Jobtitle.getBounds().y + Jobtitle.getBounds().height + space, Name.getWidth(), Name.getHeight());
			grosssalary.setBounds(Name.getX(), Basicsalary.getBounds().y + Basicsalary.getBounds().height + space, Name.getWidth(), Name.getHeight());
			email.setBounds(Name.getX(), grosssalary.getBounds().y + grosssalary.getBounds().height + space, Name.getWidth(), Name.getHeight());
			address.setBounds(Name.getX(), email.getBounds().y + email.getBounds().height + space, Name.getWidth(), Name.getHeight());
			phonenumber.setBounds(Name.getX(), address.getBounds().y + address.getBounds().height + space, Name.getWidth(), Name.getHeight());

			male.setBounds(gender1.getX() + gender1.getWidth(), gender1.getY(), Name.getWidth() / 3, Name.getHeight());
			female.setBounds(male.getX() + male.getWidth(), gender1.getY(), Name.getWidth() / 3, Name.getHeight());
			save.setBounds(25, 280, 40, 40);

			Name.setFont(f);
			password.setFont(f);
			showpass.setFont(f);
			Departement.setFont(f);
			Jobtitle.setFont(f);
			Basicsalary.setFont(f);
			grosssalary.setFont(f);
			email.setFont(f);
			address.setFont(f);
			phonenumber.setFont(f);
			male.setFont(f);
			female.setFont(f);

			Name.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getName().equals(new Employee().getName())) {
						Name.setText("");
					}
					Name.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setName(Name.getText())) {
						Name.setForeground(Color.red);
					} else {
						Name.setForeground(Color.black);
					}

				}
			});

			password.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					password.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setPW(String.valueOf(password.getPassword()))) {
						password.setForeground(Color.red);
					} else {
						password.setForeground(Color.black);
					}
				}
			});

			showpass.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (showpass.isSelected()) {
						password.setEchoChar((char) 0);
					} else {
						password.setEchoChar('●');
					}
				}
			});

			Departement.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getDepartment().equals(new Employee().getDepartment())) {
						Departement.setText("");
					}
					Departement.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setDepartment(Departement.getText())) {
						Departement.setForeground(Color.red);
					} else {
						Departement.setForeground(Color.black);
					}
				}
			});

			Jobtitle.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getJobTitle().equals(new Employee().getJobTitle())) {
						Jobtitle.setText("");
					}
					Jobtitle.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setJobTitle(Jobtitle.getText())) {
						Jobtitle.setForeground(Color.red);
					} else {
						Jobtitle.setForeground(Color.black);
					}
				}
			});

			Basicsalary.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getBasicSalary() == new Employee().getBasicSalary()) {
						Basicsalary.setText("");
					}
					Basicsalary.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setBasicSalary(Basicsalary.getText())) {
						Basicsalary.setForeground(Color.red);
					} else {
						Basicsalary.setForeground(Color.black);
					}
				}
			});

			grosssalary.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getGrossSalary() == new Employee().getGrossSalary()) {
						grosssalary.setText("");
					}
					grosssalary.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setGrossSalary(grosssalary.getText())) {
						grosssalary.setForeground(Color.red);
					} else {
						grosssalary.setForeground(Color.black);
					}

				}
			});

			email.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getEmail().equals(new Employee().getEmail())) {
						email.setText("");
					}
					email.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setEmail(email.getText())) {
						email.setForeground(Color.red);
					} else {
						email.setForeground(Color.black);
					}
				}
			});

			address.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getAddress().equals(new Employee().getAddress())) {
						address.setText("");
					}
					address.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setAddress(address.getText())) {
						address.setForeground(Color.red);
					} else {
						address.setForeground(Color.black);
					}
				}
			});

			phonenumber.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					if (obj.getPhoneNumber() == (new Employee().getPhoneNumber())) {
						phonenumber.setText("");
					}
					phonenumber.setForeground(Color.black);
				}

				@Override
				public void focusLost(FocusEvent e) {
					savebool = false;
					if (obj.setPhoneNumber(phonenumber.getText())) {
						phonenumber.setForeground(Color.red);
					} else {
						phonenumber.setForeground(Color.black);
					}
				}
			});

			male.setSelected(true);
			ActionListener genderevent = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (male.isSelected()) {
						obj.setGender(true);
					} else if (female.isSelected()) {
						obj.setGender(false);
					}
				}
			};
			male.addActionListener(genderevent);
			female.addActionListener(genderevent);

			ButtonGroup gender = new ButtonGroup();
			gender.add(male);
			gender.add(female);

			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e
				) {
					if (savebool == false) {
						if (obj.Save()) {
							JOptionPane.showMessageDialog(null, "Failed Save", "ERROR", ERROR_MESSAGE);
						} else {
							savebool = true;
						}
					}
				}
			});

			open.setBounds(20, 225, 110, 20);
			open.addActionListener(new ActionListener() {

				@Override

				public void actionPerformed(ActionEvent e
				) {

					JFileChooser browse = new JFileChooser();
					browse.setDialogTitle("Browse");
					browse.setRequestFocusEnabled(rootPaneCheckingEnabled);

					browse.setCurrentDirectory(new File("."));
					FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
					browse.setFileFilter(filter);
					if (browse.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						if (browse.getSelectedFile().toString() == null) {
							JOptionPane.showMessageDialog(null, "can't load the image", "Error", JOptionPane.ERROR_MESSAGE);

						}
						JOptionPane.showMessageDialog(null, "photo selected", "", JOptionPane.INFORMATION_MESSAGE);
					}
					File tempf = browse.getSelectedFile();
					String temp = null;
					if (tempf != null) {
						temp = tempf.toString();
						if (temp != null) {
							ImageIcon myPicture = new ImageIcon(temp);
							obj.setImagePath(temp);
							Image image = myPicture.getImage(); // transform it 
							Image newimg = image.getScaledInstance(120, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
							ImageIcon mypicture1 = new ImageIcon(newimg);
							JLabel picLabel = new JLabel(mypicture1);
							add(picLabel);

							picLabel.setBounds(15, 70, 120, 150);
							remove(picLabel2);
							revalidate();
							repaint();
						}
					}
				}
			});

		} else if (Edit == false) {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JLabel Name = new JLabel(obj.getName());
			JLabel password = new JLabel("●●●●●●●●●●");
			JCheckBox showpass = new JCheckBox();
			JLabel Departement = new JLabel(obj.getDepartment());
			JLabel Jobtitle = new JLabel(obj.getJobTitle());
			JLabel Basicsalary = new JLabel(Float.toString(obj.getBasicSalary()));
			JLabel grosssalary = new JLabel(Float.toString(obj.getGrossSalary()));
			JLabel email = new JLabel(obj.getEmail());
			JLabel address = new JLabel(obj.getAddress());
			JLabel phonenumber = new JLabel(Long.toString(obj.getPhoneNumber()));
			JLabel Gender = new JLabel(obj.getGender());
			JButton edit = new JButton("Edit");
			JButton delete = new JButton("Delete");

			Name.setFont(f);
			password.setFont(f);
			showpass.setFont(f);
			Departement.setFont(f);
			Jobtitle.setFont(f);
			Basicsalary.setFont(f);
			grosssalary.setFont(f);
			email.setFont(f);
			address.setFont(f);
			phonenumber.setFont(f);
			Gender.setFont(f);
			edit.setFont(f);
			delete.setFont(f);

			Name.setBounds(Name1.getX() + Name1.getWidth() + space, 70, 300, Name1.getHeight());
			password.setBounds(Name.getX(), Name.getY() + Name.getHeight() + space, Name.getWidth(), Name.getHeight());
			showpass.setBounds(password.getX() + password.getWidth(), Name1.getBounds().y + Name1.getBounds().height + space, 30, Name.getHeight());
			Departement.setBounds(Name.getX(), password.getBounds().y + password.getBounds().height + space, Name.getWidth(), Name.getHeight());
			Jobtitle.setBounds(Name.getX(), Departement.getBounds().y + Departement.getBounds().height + space, Name.getWidth(), Name.getHeight());
			Basicsalary.setBounds(Name.getX(), Jobtitle.getBounds().y + Jobtitle.getBounds().height + space, Name.getWidth(), Name.getHeight());
			grosssalary.setBounds(Name.getX(), Basicsalary.getBounds().y + Basicsalary.getBounds().height + space, Name.getWidth(), Name.getHeight());
			email.setBounds(Name.getX(), grosssalary.getBounds().y + grosssalary.getBounds().height + space, Name.getWidth(), Name.getHeight());
			address.setBounds(Name.getX(), email.getBounds().y + email.getBounds().height + space, Name.getWidth(), Name.getHeight());
			phonenumber.setBounds(Name.getX(), address.getBounds().y + address.getBounds().height + space, Name.getWidth(), Name.getHeight());
			Gender.setBounds(Name.getX(), phonenumber.getBounds().y + phonenumber.getBounds().height + space, Name.getWidth(), Name.getHeight());
			edit.setBounds(this.getWidth() / 2 - 125 - space * 2, Gender.getY() + Gender.getHeight() + space * 2, 125, 40);
			delete.setBounds(edit.getBounds().x + edit.getWidth() + space * 4, edit.getY(), 125, 40);

			add(Name);
			add(password);
			add(showpass);
			add(Departement);
			add(Jobtitle);
			add(Basicsalary);
			add(grosssalary);
			add(email);
			add(address);
			add(phonenumber);
			add(Gender);
			add(edit);
			add(delete);

			showpass.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (showpass.isSelected()) {
						password.setText(obj.getPW());
					} else {
						password.setText("●●●●●●●●●●");
					}
				}
			});
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new GUIEmployee_ED(new Employee(obj), true).setVisible(true);
					dispose();
				}
			});

			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (obj.Delete() == false) {
						JOptionPane.showMessageDialog(null, "Delete Failed", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						dispose();
					}
				}
			});

			JLabel picLabel = new JLabel(new ImageIcon(new ImageIcon(obj.getImagePath()).getImage().getScaledInstance(120, 150, java.awt.Image.SCALE_SMOOTH)));
			add(picLabel);
			picLabel.setBounds(15, 70, 120, 150);
		}

	}

}
