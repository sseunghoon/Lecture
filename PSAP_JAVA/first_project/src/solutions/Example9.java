package solutions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel9 extends JPanel implements Runnable
{
	Point p = new Point();
	int r = 0;
	int dr = 2;
	
	MyPanel9()
	{
		Thread t = new Thread(this);
		t.start();
				
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				p = e.getPoint();
				repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e)
			{
				p = e.getPoint();
				repaint();
			}			
		});
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(new Color(r*3,r*3,r*3));
		g.fillOval(p.x-r,  p.y-r, 2*r, 2*r);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
			r+=dr;
			
			if(r>50) { dr = -2; r= 50;}
			if(r<0)  { dr = 2;	r = 0;}

			repaint();
		}
		
	}	
}


public class Example9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setTitle("Example 9");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel9 p = new MyPanel9();
		f.add(p);
		f.setVisible(true);					
	}

}
