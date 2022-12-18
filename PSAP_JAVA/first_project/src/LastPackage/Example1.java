package LastPackage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Example1Panel extends JPanel {
	int px1, px2;
	int py1, py2;
	boolean rightBtn;
	
	ArrayList<Point> ovalArr = new ArrayList<>();
	JLabel label = new JLabel("Count Oval : 0");
	
	public Example1Panel() {
		// TODO Auto-generated constructor stub
		add(label);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (e.getButton() == MouseEvent.BUTTON3) {
					px1 = e.getX();
					px2 = e.getX();
					py1 = e.getY();
					py2 = e.getY();
					rightBtn = true;
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				if (SwingUtilities.isRightMouseButton(e)) {
					px2 = e.getX();
					py2 = e.getY();
					int x = px1;
					int y = py1;
					int w = px2 - px1; if (w < 0) { w = -w; x = px2;}
					int h = py2 - py1; if (h < 0) { h = -h; y = py2;}
					Rectangle2D rectangle2d = new Rectangle2D.Float(x,y,w,h);
					Iterator<Point> iterator = ovalArr.iterator();
					while (iterator.hasNext()) {
						Point point = iterator.next();
						if (rectangle2d.contains(point)) {
							iterator.remove();
						}
					}
					rightBtn = false;
				}
				repaint();
				
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				
				if (SwingUtilities.isLeftMouseButton(e)) {
					ovalArr.add(new Point(e.getX(), e.getY()));
					repaint();
				}
				if (SwingUtilities.isRightMouseButton(e)) {
					px2 = e.getX();
					py2 = e.getY();
					repaint();
				}
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		label.setText("Count Point : " + ovalArr.size());
		
		for (var oval:ovalArr) {
			g.drawOval(oval.x, oval.y, 2, 2);
		}
		if (rightBtn) {
			int x = px1;
			int y = py1;;
			int w = px2 - px1; if (w < 0) {w = -w; x = px2; }
			int h = py2 - py1; if (h < 0) {h = -h; y = py2; }
			g.drawRect(x, y, w, h);
		}
	}
}

public class Example1 extends JFrame{
	public Example1() {
		// TODO Auto-generated constructor stub
		setTitle("Example1");
		setSize(500, 500);
		
		add(new Example1Panel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example1();
	}
}
