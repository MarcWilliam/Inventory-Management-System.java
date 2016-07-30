package GUI;

import java.awt.*;
import javax.swing.*;

public class GUISplashScreen extends JWindow {

	public GUISplashScreen(int duration) {
		JPanel content = (JPanel) getContentPane();

		int width = 406;
		int height = 220;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		JLabel copyrt = new JLabel("Inventory System ", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.ITALIC, 35));
		JLabel label2 = new JLabel(new ImageIcon(new ImageIcon("ACU_Logo.PNG").getImage().getScaledInstance(406, 168, java.awt.Image.SCALE_SMOOTH)));

		content.add(copyrt, BorderLayout.PAGE_START);
		content.add(label2, BorderLayout.CENTER);

		setBackground(new Color(0, 255, 0, 0));
		setVisible(true);
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
			this.dispose();
		}
		this.dispose();
	}
}
