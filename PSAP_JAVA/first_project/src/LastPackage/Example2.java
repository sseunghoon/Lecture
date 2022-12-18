package LastPackage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Line {
	ArrayList<Point> pointArr = new ArrayList<>();
}

class Example2Panel extends JPanel {
	JLabel label = new JLabel();
	
	ArrayList<Line> lineArr = new ArrayList<>();
	
	public Example2Panel() {
		// TODO Auto-generated constructor stub
		add(label);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				lineArr.add(new Line());
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				Line line = lineArr.get(lineArr.size()-1);
				line.pointArr.add(new Point(e.getX(), e.getY()));
				repaint();
			}
		});
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (int j = 0; j < lineArr.size(); j++) {
			ArrayList<Point> pointArr = lineArr.get(j).pointArr;
			for (int i = 0; i < pointArr.size() - 1; i++) {
				Point p1 = pointArr.get(i);
				Point p2 = pointArr.get(i+1);
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		}
		label.setText("Count Line : " + lineArr.size());
		
		
	}
	
}

public class Example2 extends JFrame{
	public Example2() {
		// TODO Auto-generated constructor stub
		setTitle("Example2");
		setSize(500, 500);
		
		add(new Example2Panel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example2();
	}
}
