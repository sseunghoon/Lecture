package finalExam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

class FollowPointerPanel extends JPanel implements Runnable{
	int x = 0;
	int y = 0;
	int r = 0;
	
	public FollowPointerPanel() {
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
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int r2 = r - 45;
		r2 = Math.abs(r2);
		g.setColor(new Color(r2, r2, r2));
		g.fillOval(x - r2, y - r2, 2*r2, 2*r2);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(50);
				r += 5;
				r = r % 90;
				repaint();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

public class FollowPointer extends JFrame{
	public FollowPointer() {
		// TODO Auto-generated constructor stub
		setTitle("FollowPointer");
		setSize(500, 500);
		
		add(new FollowPointerPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FollowPointer();
	}
}
