package LastPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Ball {
	int x,y;
	int vx= -10;
	int vy = 0;
	int ay = 1;
	int start;
	int size = 10;
	
	public Ball() {
		// TODO Auto-generated constructor stub
	}
	
	public Ball(int x, int y, int vx, int vy, int ay, int start, int size) {
		super();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ay = ay;
		this.start = start;
		this.size = size;
	}

	void update(Dimension d) {
		start--;
		if (start > 0) return;
		vy += ay;
		y += vy;
		x += vx;
		
		if (y > d.height -10) {
			y = d.height -10;
			vy= (int)(-vy *0.8);
		}
		if (x < 0) {
			Example12Panel.balls.add(makeClone());
			x = 0;
			vx = -vx;
		}
		if (x > d.width) {
			Example12Panel.balls.add(makeClone());
			x = d.width;
			vx = -vx;
		}
	}
	
	Ball makeClone() {
		Ball clone = new Ball(x,y,vx,vy,ay,start,size);
		clone.vy = -vy;
		clone.vx = -vx;
		return clone;
	}
}

class Example12Panel extends JPanel implements Runnable{
	static ArrayList<Ball> balls = new ArrayList<>(); 
	
	public Example12Panel() {
		setFocusable(true);
		requestFocus();
		// TODO Auto-generated constructor stub
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					balls.add(new Ball());
					for (int i = 0; i < balls.size(); i++) {
						Ball b = balls.get(i);
						b.x = getWidth() * (i + 1) / (1+ balls.size());
						b.y = (int)(getHeight() * 0.2);
						b.vy = 0;
						b.start = i *5;
						
					}
				}
			}
		});
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(30);
				Dimension d = getSize();
				
				Iterator<Ball> iterator = balls.iterator();
				while (iterator.hasNext()) {
					Ball b = iterator.next();
					b.update(d);
				}
				
				repaint();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.red);
		Iterator<Ball> iterator = balls.iterator();
		while (iterator.hasNext()) {
			Ball b = iterator.next();
			g.fillOval(b.x - 10, b.y - 10, 10, 10);
		}
		
	}
	
}

public class Example12 extends JFrame{
	public Example12() {
		// TODO Auto-generated constructor stub
		setTitle("Example12");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example12Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example12();
	}
}
