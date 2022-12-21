package LastPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Example9Panel extends JPanel implements Runnable{
	int x,y;
	int size;
	
	public Example9Panel() {
		// TODO Auto-generated constructor stub
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				x = e.getX();
				y = e.getY();
			}
		});
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				Thread.sleep(30);
				size+= 10;
				size %= 200;
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
		Graphics2D g2 = (Graphics2D)g;
		int k = Math.abs(size - 100);
		
		g2.setColor(new Color(k, k, k));
		g.fillOval(x - k/2, y - k/2, k, k);
	}
}

public class Example9 extends JFrame{
	public Example9() {
		// TODO Auto-generated constructor stub
		setTitle("Example9");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example9Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example9();
	}
}