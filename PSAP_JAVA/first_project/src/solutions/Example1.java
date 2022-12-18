package solutions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class MyPanel extends JPanel implements MouseListener, MouseMotionListener
{
	LinkedList<Point> list = new LinkedList<Point>();
	boolean rightBt = false;
	Point pt1, pt2;
	JLabel label;
	MyPanel()
	{
		label = new JLabel("");
		
		add(label);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		label.setText("Num Of Points:"+list.size());
		
		super.paintComponent(g);
		g.setColor(Color.black);
		int r = 2;
		for(Point p: list)
			g.fillOval(p.x-r, p.y-r, 2*r, 2*r);
		if(rightBt)
		{
			g.setColor(Color.red);
			int x = pt1.x;
			int y = pt1.y;
			int w = pt2.x-pt1.x;	if(w<0) { x = pt2.x; w=-w;}
			int h = pt2.y-pt1.y;	if(h<0) { y = pt2.y; h=-h;}
			g.drawRect(x,  y,  w,  h);			
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e))
			list.add(new Point(e.getX(), e.getY()));
		if(SwingUtilities.isRightMouseButton(e))
		{
			pt2.x = e.getX();
			pt2.y = e.getY();
			rightBt = true;
		}		
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			list.add(new Point(e.getX(), e.getY()));
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			pt1 = new Point(e.getX(), e.getY());
			pt2 = new Point(e.getX(), e.getY());
			rightBt = true;
		}
		

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			pt2.x = e.getX();
			pt2.y = e.getY();
			
			int x = pt1.x;
			int y = pt1.y;
			int w = pt2.x-pt1.x;	if(w<0) { x = pt2.x; w=-w;}
			int h = pt2.y-pt1.y;	if(h<0) { y = pt2.y; h=-h;}
			
			Rectangle2D rect = new Rectangle2D.Float(x,y,w,h);
			
			ListIterator<Point> it = list.listIterator();
			
			for(;it.hasNext();)
			{
				Point p = it.next();
				if(rect.contains(p))
					it.remove();
			}
			repaint();
		}
		rightBt = false;
	}
	
}

public class Example1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(800,500);
		f.setTitle("Example 1");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel p = new MyPanel();
		f.add(p);
		f.setVisible(true);		
	}

}
