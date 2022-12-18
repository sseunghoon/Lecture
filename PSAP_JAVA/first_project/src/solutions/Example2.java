package solutions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class MyPoint
{
	float x, y;
	MyPoint()
	{
	}
	MyPoint(float _x, float _y)
	{
		x = _x;
		y = _y;
	}
	MyPoint(Point in)
	{
		x = in.x;
		y = in.y;
	}
}

class MyCurve
{
	ArrayList<MyPoint> pointList = new ArrayList<MyPoint>();
	
	void draw(Graphics g, Dimension d)
	{
		float w = d.width;
		float h = d.height;
		for(int i=0 ; i<pointList.size()-1; i++)
		{
			MyPoint p1 = pointList.get(i);
			MyPoint p2 = pointList.get(i+1);
			g.drawLine((int)(p1.x*w),  (int)(p1.y*h),  (int)(p2.x*w), (int)(p2.y*h));
		}
	}
	void add(MyPoint p)
	{
		pointList.add(p);
		
	}
}

class MyPanel2 extends JPanel implements MouseListener, MouseMotionListener
{
	LinkedList<MyCurve> curveList = new LinkedList<MyCurve>();
	JLabel label;
	MyPanel2()
	{
		label = new JLabel("");
		add(label);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		label.setText("Num Of Curves:"+curveList.size());
		Dimension d = getSize();
		g.setColor(Color.black);
		for(MyCurve c:curveList)
			c.draw(g, d);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		MyPoint p = new MyPoint(e.getPoint());
		Dimension d = getSize();
		p.x /=d.width;
		p.y /=d.height;	
		
		curveList.getLast().add(p);
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
			MyCurve c = new MyCurve();
			MyPoint p = new MyPoint(e.getPoint());
			Dimension d = getSize();
			p.x /=d.width;
			p.y /=d.height;	
			c.add(p);
			curveList.add(c);
		}
		

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}

public class Example2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(800,500);
		f.setTitle("Example 2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel2 p = new MyPanel2();
		f.add(p);
		f.setVisible(true);		
	}

}
