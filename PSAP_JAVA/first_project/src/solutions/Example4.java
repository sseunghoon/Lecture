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


class MyCircle
{
	Point center = new Point(0,0);
	float radius = 0;
	boolean selected = false;
	void setCenter(Point pt)
	{
		center = pt;
	}
	void setRadius(Point pt)
	{
		radius = getDistance(pt);
	}
	float getDistance(Point pt)
	{
		double r = Math.sqrt((double)((pt.x-center.x)*(pt.x-center.x)+(pt.y-center.y)*(pt.y-center.y)));
		return (float)r;		
	}
	void draw(Graphics g)
	{
		if(!selected)
			g.setColor(Color.red);
		else 
			g.setColor(Color.yellow);
		
		g.fillOval((int)(center.x-radius), (int)(center.y-radius), (int)(2*radius), (int)(2*radius));
		g.setColor(Color.black);
		g.drawOval((int)(center.x-radius), (int)(center.y-radius), (int)(2*radius), (int)(2*radius));
	}
	boolean isContain(Point p)
	{
		if(getDistance(p)<radius) return true;
		return false;
	}
	
}

class MyPanel4 extends JPanel implements MouseListener, MouseMotionListener
{
	LinkedList<MyCircle> circleList = new LinkedList<MyCircle>();
	JLabel label;
	int mode = 0;					// 0: drawing;
									// 1: selecting and moving
	
	Point prev_p;					// Previous position					
									
	
	MyPanel4()
	{
		label = new JLabel("");
		
		add(label);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		label.setText("Num Of Circles:"+circleList.size());
		
		super.paintComponent(g);
		
		for(MyCircle c: circleList)
			c.draw(g);		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(mode == 0)
				circleList.getLast().setRadius(e.getPoint());
			else if(mode == 1)
			{
				int dx = (int)(e.getX()-prev_p.getX());
				int dy = (int)(e.getY()-prev_p.getY());
				for(MyCircle c:circleList)
					if(c.selected)
						c.center.translate(dx, dy);
				prev_p = e.getPoint();
			}
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
		{
			prev_p = e.getPoint();
			mode = 0;
			for(MyCircle c:circleList)
				if(c.isContain(e.getPoint()))
				{
					c.selected = true;
					mode = 1;
				}
			if(mode == 0)
			{
				MyCircle c = new MyCircle();
				c.center = prev_p;
				c.radius = 0;
				circleList.add(c);
			}
			repaint();
			
		}
	

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			for(MyCircle c:circleList)
				c.selected = false;
			mode = 0;
			
			repaint();
		}

	}
	
}

public class Example4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(800,500);
		f.setTitle("Example 4");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel4 p = new MyPanel4();
		f.add(p);
		f.setVisible(true);		
	}

}
