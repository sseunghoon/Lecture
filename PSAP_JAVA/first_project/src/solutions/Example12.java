package solutions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


class MyBall 
{
	static float ax = 0;
	static float ay = 1.0f;
	
	float x, y;
	float vx=0, vy=0;
	float r;
	float start_time;
	
	MyBall(float _x, float _y, int t)
	{
		x = _x;
		y = _y;		
		r = 10;
		start_time = t;
	}
	void update(Dimension d)
	{
		start_time -= 1;
		if(start_time>0) return;
		
		vx += ax;
		vy += ay;
		
		x+=vx;
		y+=vy;
		
		if(y>d.height - r)
		{
			y = d.height-r;
			vy = -vy*0.9f;
		}
	}
	void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		g.setColor(Color.black);
		g.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
	}
}



class MyPanel12 extends JPanel implements Runnable
{
	ArrayList<MyBall> ballList = new ArrayList<MyBall>();

	MyPanel12()
	{
		requestFocus();
		setFocusable(true);

		Thread t = new Thread(this);
		t.start();
		
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					
					int count = ballList.size();
					ballList.clear();
					count ++;
					
					Dimension d = getSize();
					for(int i = 0; i<count; i++)
					{
						int start_time = i*5;
						int x = (int)(d.width/(float)(count+1)*(i+1));
						MyBall b = new MyBall(x, 100, start_time);
						ballList.add(b);
					}
					
				}
			}			
		});
		
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(MyBall b: ballList)
			b.draw(g);	
	}
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			
			Dimension d = getSize();
			for(MyBall b : ballList)
			{
				b.update(d);
			}
			repaint();
			
		}		
	}

}


public class Example12 {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setSize(800,800);
		f.setTitle("Example 12");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel12 p = new MyPanel12();
		f.add(p);
		f.setVisible(true);					
	}

}
