package solutions;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel11 extends JPanel implements Runnable
{
	float x, y;
	float vx=0, vy=0;
	int speed;
	int size = 100;

	MyPanel11()
	{
		Dimension d = getSize();
		x = 350;
		y = 200;
		speed = 20;
		
		requestFocus();
		setFocusable(true);

		Thread t = new Thread(this);
		t.start();
		
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode())
				{
				case KeyEvent.VK_LEFT:	{vx =-speed; vy = 0; break;}
				case KeyEvent.VK_RIGHT: {vx = speed; vy = 0; break;}
				case KeyEvent.VK_UP:	{vx = 0; vy =-speed; break;}
				case KeyEvent.VK_DOWN:  {vx = 0; vy = speed; break;}
				case KeyEvent.VK_SPACE: {vx = 0; vy = 0    ; break;}
				default:	break;
				}
			}			
		});
		
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Dimension d = getSize();
					
		g2.fillRect((int)x, (int)y, size, size);	
		
	
	}
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
			updatePosition();
		}		
	}
	void updatePosition()
	{
		Dimension d = getSize();
		
		x += vx;
		y += vy;
		
		if(x>d.width-size) { x=d.width-size; vx = -vx;}
		if(x<0)			   { x=0; vx = -vx;}
		if(y>d.height-size){ y=d.height-size; vy = -vy;}
		if(y<0)			   { y=0; vy = -vy;}

		repaint();
	
	}

}


public class Example11 {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setSize(800,500);
		f.setTitle("Example 11");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel11 p = new MyPanel11();
		f.add(p);
		f.setVisible(true);					
	}

}
