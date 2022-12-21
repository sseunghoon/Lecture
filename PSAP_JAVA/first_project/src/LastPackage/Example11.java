package LastPackage;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Example11Panel extends JPanel implements Runnable{
	int x, y;
	int vx, vy, ax, ay;
	
	
	public Example11Panel() {
		
		setFocusable(true);
		requestFocus();
		// TODO Auto-generated constructor stub
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					vx = -10; vy = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					vx = 10; vy = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					vy = 10; vx = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					vy = -10; vx = 0;
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
				x += vx;
				y += vy;
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
		if (x < 0) {x = 0; vx = -vx;}
		if (x + 50 > getWidth()) {x = getWidth() - 50; vx = -vx;}
		if (y < 0) {y = 0; vy = -vy;}
		if (y + 50 > getHeight()) {y = getHeight() - 50; vy = -vy;}
		g.fillRect(x, y, 50, 50);
	}
}

public class Example11 extends JFrame{
	public Example11() {
		// TODO Auto-generated constructor stub
		setTitle("Example11");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example11Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example11();
	}
}