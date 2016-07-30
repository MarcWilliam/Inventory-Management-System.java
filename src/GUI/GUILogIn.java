package GUI;

import Core.*;
import User.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author Marc Wafik
 */
public class GUILogIn extends JFrame {

	boolean PWentered;
	boolean IDentered;
	Employee obj;
	JTextField IDtfeild;
	JLabel IDlabel;
	JLabel PWlabel;
	JButton LoginClick;
	JPasswordField PWtfeild;

	public GUILogIn() {
		PWentered = true;
		IDentered = true;

		Font f = new Font("Calibri", Font.PLAIN, 15);
		setTitle("Login");
		setSize(400, 300);
		setLayout(null);
		this.setMinimumSize(new Dimension(350, 270));
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (0 == JOptionPane.showConfirmDialog(null, "are you sure you want to quit ?", "Exit", YES_NO_OPTION, QUESTION_MESSAGE)) {
					dispose();
					System.exit(0);
				}
			}
		});

		obj = new Employee();

		IDlabel = new JLabel("ID :");
		PWlabel = new JLabel("Password :");
		IDtfeild = new JTextField("Enter your ID");
		PWtfeild = new JPasswordField("******");
		LoginClick = new JButton("Login");

		IDlabel.setHorizontalAlignment(JLabel.RIGHT);
		PWlabel.setHorizontalAlignment(JLabel.RIGHT);

		this.setFont(f);
		IDlabel.setFont(f);
		PWlabel.setFont(f);
		IDtfeild.setFont(f);
		PWtfeild.setFont(f);
		LoginClick.setFont(f);

		add(IDlabel);
		add(IDtfeild);
		add(PWlabel);
		add(PWtfeild);
		add(LoginClick);
		PWtfeild.addActionListener(new Clicked());
		LoginClick.addActionListener(new Clicked());

		IDtfeild.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (IDentered) {
					IDtfeild.setText("");
				}
				IDentered = false;
				IDtfeild.setForeground(Color.black);
			}
		});

		PWtfeild.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (PWentered) {
					PWtfeild.setText("");
				}
				PWentered = false;
				PWtfeild.setForeground(Color.black);
			}
		});

	}

	private final class Clicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (obj.LogIn(IDtfeild.getText(), new String(PWtfeild.getPassword()))) {
				JOptionPane.showMessageDialog(null, "The ID and password you entered don't match.", "Failed to Log in", ERROR_MESSAGE);
				IDtfeild.setForeground(Color.red);
				PWtfeild.setForeground(Color.red);
				PWentered = true;
				IDentered = true;
			} else {
				if (obj.getJobTitle().equals("Manger") || obj.getJobTitle().equals("manger") || obj.getJobTitle().equals("MANGER")) {
					new GUIuserManger(new Manger(obj)).setVisible(true);
				} else if (obj.getJobTitle().equals("Accountant") || obj.getJobTitle().equals("accountant") || obj.getJobTitle().equals("ACCOUNTANT")) {
					new GUIuserAccountant(new Accountant(obj)).setVisible(true);
				} else if (obj.getJobTitle().equals("Supervisor") || obj.getJobTitle().equals("supervisor") || obj.getJobTitle().equals("SUPERVISOR")) {
					new GUIuserSupervisor(new Supervisor(obj)).setVisible(true);
				} else if (obj.getJobTitle().equals("Cashier") || obj.getJobTitle().equals("cashier") || obj.getJobTitle().equals("CASHIER")) {
					new GUIuserCashier(new Cashier(obj)).setVisible(true);
				} else {
					new GUIuserEmployee(obj).setVisible(true);
				}
				dispose();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int h = this.getHeight(), w = this.getWidth();
		Dimension Space = new Dimension(10, 20);

		IDlabel.setBounds(Space.width * 3, Space.height * 3, (int) (w * 0.2), (int) (h * 0.1));
		PWlabel.setBounds(
				IDlabel.getBounds().x,
				IDlabel.getBounds().y + IDlabel.getBounds().height + Space.height,// will change 5 later
				IDlabel.getWidth(),
				IDlabel.getHeight());

		IDtfeild.setBounds(IDlabel.getBounds().x + IDlabel.getBounds().width + 5,
				IDlabel.getBounds().y,
				(int) (w * 0.55),
				IDlabel.getHeight());
		PWtfeild.setBounds(PWlabel.getBounds().x + PWlabel.getBounds().width + 5,
				IDtfeild.getBounds().y + IDtfeild.getBounds().height + Space.height,
				IDtfeild.getWidth(),
				IDtfeild.getHeight());

		LoginClick.setBounds(PWtfeild.getBounds().x + PWtfeild.getBounds().width / 2,
				PWtfeild.getBounds().y + PWtfeild.getBounds().height + Space.height * 2,
				IDtfeild.getWidth() / 2,
				IDtfeild.getHeight() * 6 / 4);
	}

	public static void main(String[] args) {
		new GUISplashScreen(2500);
		LookAndFeel("Windows");
		CheckFiles();
		new GUILogIn().setVisible(true);
	}

	public static void LookAndFeel(String Look) {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (Look.equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					Logger.getLogger(GUIInvoice.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	public static void CheckFiles() {
		if (FileManger.CheckFiles()) {
			return;
		}
		int input = JOptionPane.showOptionDialog(null, "Files are Corrupt", "ERROR", YES_NO_OPTION, ERROR_MESSAGE, null, new String[]{"Create new files", "load backup"}, "default");
		if (-1 == input) {
			System.exit(0);
		} else if (0 == input) {
			FileManger.CreatFiles();
			JOptionPane.showMessageDialog(null, "pls login as admin using \n ID : 0 \n PW : Admin@Admin1", "new files created", INFORMATION_MESSAGE);
			Employee temp = new Manger();
			temp.setName("Admin Admin");
			temp.setBasicSalary(0f);
			temp.setGrossSalary(0f);
			temp.setAddress("Admin");
			temp.setDepartment("Admin");
			temp.setJobTitle("Manger");
			temp.setPW("Admin@Admin1");
			temp.setID(0);
			temp.Save();
		} else if (1 == input) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Load Backup");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (FileManger.LoadBackup(chooser.getSelectedFile().toString())) {
					JOptionPane.showMessageDialog(null, "Failed to load files", "Error : FileCorupt", ERROR_MESSAGE);
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "Loaded backup Succsesfully", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
