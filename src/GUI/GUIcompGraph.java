/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author marcw
 */
public class GUIcompGraph extends JPanel {

	//axes names
	String XSname;
	String YSname;
	Point Max;		//Maximum values
	Point Scalelines;	// the 10^ something on the axes
	final int Space = 35;//the empty space befor the axes
	ArrayList<Point> poin;

	public GUIcompGraph(ArrayList<Point> poin, String XSname, String YSname) {
		this.poin = poin;
		if (this.poin == null) {
			this.poin = new ArrayList<>();
		} else {
			this.poin.sort(new Comparator<Point>() {
				@Override
				public int compare(Point a, Point b) {
					int xComp = Integer.compare(a.x, b.x);
					if (xComp == 0) {
						return Integer.compare(a.y, b.y);
					} else {
						return xComp;
					}
				}
			});
		}
		this.Max = new Point(0, 0);
		this.Scalelines = new Point(0, 0);

		//Get the Max Values
		for (int i = 0; i < this.poin.size(); i++) {
			if (this.Max.x < this.poin.get(i).x) {
				this.Max.x = this.poin.get(i).x;
			}
			if (Max.y < this.poin.get(i).y) {
				this.Max.y = this.poin.get(i).y;
			}
		}
		//Scale down the max x value to    Max * 10^ something
		while (this.Max.x > 1000) {
			this.Max.x = this.Max.x / 100;
			this.Scalelines.x += 2;
		}
		//Scale down the max y value to    Max * 10^ something
		while (this.Max.y > 1000) {
			this.Max.y = this.Max.y / 100;
			this.Scalelines.y += 2;
		}
		if (this.Scalelines.x == 0) {
			this.XSname = XSname;
		} else {
			this.XSname = XSname + "  in 10^" + this.Scalelines.x;
		}
		if (this.Scalelines.y == 0) {
			this.YSname = YSname;
		} else {
			this.YSname = YSname + "  in 10^" + this.Scalelines.y;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
// Antiialias just to improve the shapes and reduce the pixeling   had to switch to Graphics2D for that
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();
//=========================================================================================================================================

		g2D.drawLine(Space, Space, Space, h - Space);					// Draw y axe
		g2D.drawLine(Space, h - Space, w - Space, h - Space);						// Draw x axe
		g2D.drawString(this.YSname, Space * 2 - Space / 2, Space / 2 + 5);// draw the y axe name
		g2D.drawString(this.XSname, w - (this.XSname.length() * 7) - 5, h - Space - 10); // draw the x axe name and it depends on the string size
//=========================================================================================================================================
		Point ScaleGrid = new Point(w / 50, h / 50);// the number of small lines on the axes
//change the grid scaling if it's a small variabel
		if (Max.x < ScaleGrid.x) {
			ScaleGrid.x = Max.x;
		}
		if (this.Max.y < ScaleGrid.y) {
			ScaleGrid.y = this.Max.y;
		}
//=========================================================================================================================================
		for (int i = 1; i < ScaleGrid.x; i++) {
			//			int x = ((w - Space) / ScalexGrid) * i;
			int x = (w - Space) / ScaleGrid.x * i;
			g2D.drawLine(x + Space, h - Space - 3, x + Space, h - Space + 3);// Draw x gridlines
			g2D.drawString(Integer.toString(i * Max.x / ScaleGrid.x), x + Space, h - Space + 17);// draw the x gridlines values
		}

		for (int i = 1; i < ScaleGrid.y; i++) {
			int y = ((h - 2 * Space) / ScaleGrid.y) * i;
			g2D.drawLine(Space - 3, h - y - Space, Space + 3, h - y - Space);// Draw y gridlines
			String tempvalY = Integer.toString(i * this.Max.y / ScaleGrid.y);
			g2D.drawString(tempvalY, Space - (tempvalY.length() * 8) - 4, h - y - Space); // draw the y gridlines values
		}
//=========================================================================================================================================
		int ArrowSize = 5;

		int[] xarr = {w - Space, w - Space, w - Space / 2 + ArrowSize};
		int[] yarr = {h - Space + ArrowSize, h - Space - ArrowSize, h - Space};
		g2D.fillPolygon(xarr, yarr, 3);// Draw x triangle

		int[] xarr2 = {Space - ArrowSize, Space + ArrowSize, Space};
		int[] yarr2 = {Space, Space, Space - Space / 2 - ArrowSize};
		g2D.fillPolygon(xarr2, yarr2, 3);// Draw y triangle
//=========================================================================================================================================
		// Draw the points & lines bettwen them
		Point old = new Point(0 + Space, h - Space);
		for (int i = 0; i < this.poin.size(); i++) {
			g2D.setColor(Color.red);
			double x = Space + (poin.get(i).x * (w - 2 * Space) / Max.x) / Math.pow(10, Scalelines.x);
			double y = h - Space - (poin.get(i).y * (h - 2 * Space) / this.Max.y) / Math.pow(10, Scalelines.y);
			g2D.fillOval((int) x - 2, (int) y - 2, 4, 4);// -2 so it ill be in the midle of the circle
			g2D.setColor(Color.BLUE);
			g2D.drawLine(old.x, old.y, (int) x, (int) y);
			old = new Point((int) x, (int) y);
		}
	}
}
