package GUI;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUIGraph extends JFrame {

	public GUIGraph(String title, ArrayList<Point> poin, String XSname, String YSname) {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(new GUIcompGraph(poin, XSname, YSname));
		this.setMinimumSize(new Dimension(200, 200));
		setSize(400, 400);
		setLocation(200, 200);
	}

}
