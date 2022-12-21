package RealLastPackage;

import java.awt.Color;
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

class Example1Panel extends JPanel {
	ArrayList<Point> points = new ArrayList<>();
	boolean rightBtn;
	int x1, x2, y1, y2;
	JLabel label = new JLabel();
	
	public Example1Panel() {
		// TODO Auto-generated constructor stub
		add(label);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (e.getButton() == MouseEvent.BUTTON3) {
					rightBtn = true;
					x1 = e.getX();
					y1 = e.getY();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				
				
				if (e.getButton() == MouseEvent.BUTTON3) {
					int x = x1;
					int y = y1;
					int w = x2-x1; if (w < 0) {w= -w; x = x2;}
					int h = y2-y1; if (h < 0) {h= -h; y = y2;}
					Rectangle2D rect = new Rectangle2D.Float(x,y,w,h);
					Iterator<Point> iterator = points.iterator();
					while (iterator.hasNext()) {
						Point p = iterator.next();
						if (rect.contains(p)) {
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
				if (e.getButton() == MouseEvent.BUTTON3) {
					x2 = e.getX();
					y2 = e.getY();
				}
				if (e.getButton() == MouseEvent.BUTTON1) {
					points.add(new Point(e.getX(), e.getY()));
				}
				repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		label.setText("Count Oval: " + points.size());
		
		int x = x1;
		int y = y1;
		int w = x2-x1; if (w < 0) {w= -w; x = x2;}
		int h = y2-y1; if (h < 0) {h= -h; y = y2;}
		if (rightBtn) {	
			g.drawRect(x, y, w, h);			
		}
	
		g.setColor(Color.red);
		for(var p:points) {
			g.fillOval(p.x, p.y, 10, 10);
		}
	}
}

public class Example1 extends JFrame{
	public Example1() {
		// TODO Auto-generated constructor stub
		setSize(500, 500);
		setTitle("Example1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example1Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example1();
	}
}


//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//class Example1Panel extends JPanel {
//	public Example1Panel() {
//		// TODO Auto-generated constructor stub
//	}
//}
//
//public class Example1 extends JFrame{
//	public Example1() {
//		// TODO Auto-generated constructor stub
//		setSize(500, 500);
//		setTitle("Example1");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		add(new Example1Panel());
//		
//		setVisible(true);
//	}
//	
//	public static void main(String[] args) {
//		new Example1();
//	}
//}
