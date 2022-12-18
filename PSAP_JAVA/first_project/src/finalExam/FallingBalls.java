package finalExam;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Ball{
	float x;
	float y;
	float vy = 0;
	float ay = 1.0f;
	float r = 30;
	int start_time;
	
	void update(Dimension d) {
		start_time--;
		if (start_time < 0) {
			vy += ay;
			y += vy;
			if (y + r > d.height) {
				y = d.height - r;
				vy = -0.8f * vy;
			}
		}
		
	}
}

class FallingBallsPanel extends JPanel implements Runnable{
	ArrayList<Ball> ballArr = new ArrayList<>();
	
	public FallingBallsPanel() {
		// TODO Auto-generated constructor stub
		setFocusable(true);
		requestFocus();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				System.out.println(e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					addBall();
				}
			}
		});
		Thread thread = new Thread(this);
		thread.start();
	}
	
	void addBall() {
		ballArr.add(new Ball());
		int size= ballArr.size();
		int width = getWidth();
		int height = getHeight();
		
		for (int i = 0; i < size; i++) {
			Ball ball = ballArr.get(i); 
			ball.x = (i+1) * width / (size + 1); 
			ball.y = height * 0.2f;
			ball.start_time = i * 5;
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(30);
				Dimension dimension = getSize();
				for (var ball : ballArr) {
					ball.update(dimension);
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
		int height = getHeight();
		for (var ball: ballArr) {
			
			g.fillOval((int)ball.x, (int)ball.y, 30, 30);			
		}
	}
}

public class FallingBalls extends JFrame{
	public FallingBalls() {
		// TODO Auto-generated constructor stub
		setTitle("Falling Balls");
		setSize(600, 600);
		
		add(new FallingBallsPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new FallingBalls();
	}
}
