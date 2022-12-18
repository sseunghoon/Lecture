package LastPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyOval {
	int x, y, r;
	Color color;
	public MyOval(int x, int y, int r, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
		this.color =color;
	}
	
	int getDistance(int x1, int y1) {
		return (int)(Math.hypot(Math.abs(x1- x), Math.abs(y1 -y))); 
	}
	
	boolean isContain(int x1, int y1) {
		if (getDistance(x1, y1) < r) {
			return true;
		}
		return false;
	}
	
	
}

class Example4Panel extends JPanel {
	ArrayList<MyOval> ovalArr = new ArrayList<>();
	ArrayList<MyOval> selected = new ArrayList<>();
	
	int clickX;
	int clickY;
	
	public Example4Panel() {
		// TODO Auto-generated constructor stub
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				clickX = e.getX();
				clickY = e.getY();
				for (var oval: ovalArr) {
					if (oval.isContain(e.getX(), e.getY())) {
						selected.add(oval);
					}
				}
				if (selected.size() == 0) {
					ovalArr.add(new MyOval(e.getX(), e.getY(), 0, Color.red));		
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				selected.clear();
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				if (selected.size() == 0) {
					MyOval oval = ovalArr.get(ovalArr.size()-1);
					oval.r = oval.getDistance(e.getX(), e.getY());
					repaint();
					return;
				}
				int moveX = e.getX() - clickX; clickX = e.getX();
				int moveY = e.getY() - clickY; clickY = e.getY();
				for (var oval: selected) {
					oval.x += moveX;
					oval.y += moveY;
				}
				repaint();
				
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for (var oval:ovalArr) {
			if (selected.contains(oval)) {
				g.setColor(Color.yellow);
			} else {
				g.setColor(Color.red);
			}
			g.fillOval(oval.x - oval.r, oval.y - oval.r, 2 * oval.r, 2 * oval.r);
		}
	}
}

public class Example4 extends JFrame{
	public Example4() {
		// TODO Auto-generated constructor stub
		setTitle("Example4");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example4Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example4();
	}
}




//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//class Example4Panel extends JPanel {
//	public Example4Panel() {
//		// TODO Auto-generated constructor stub
//	}
//}
//
//public class Example4 extends JFrame{
//	public Example4() {
//		// TODO Auto-generated constructor stub
//		setTitle("Example4");
//		setSize(500, 500);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		setVisible(true);
//	}
//	
//	public static void main(String[] args) {
//		new Example4();
//	}
//}
