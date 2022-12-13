package finalExam;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MovingSquarePanel extends JPanel implements Runnable{
	int x = 0;
	int y = 0;
	int vx = 0;
	int vy = 0;
	int size = 50;
	public MovingSquarePanel() {
		// TODO Auto-generated constructor stub
		requestFocus();
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					vx = 20;
					break;
				case KeyEvent.VK_LEFT:
					vx = -20;
					break;
				case KeyEvent.VK_UP:
					vy = -20;
					break;
				case KeyEvent.VK_DOWN:
					vy = 20;
					break;
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
				if (x < 0) {
					x = 0;
					vx *= -1;
				}
				if (x > getWidth() - size) {
					x = getWidth() - size;
					vx *= -1;
				}
				
				y += vy;
				if (y < 0) {
					y = 0;
					vy *= -1;
				}
				if (y > getHeight() - size) {
					y = getHeight() - size;
					vy *= -1;
				}
				repaint();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.fillRect(x, y, size, size);
	}
}

public class MovivngSquare extends JFrame{
	public MovivngSquare() {
		// TODO Auto-generated constructor stub
		setTitle("Moving Square");
		setSize(500, 500);
		
		add(new MovingSquarePanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MovivngSquare();
	}

}
