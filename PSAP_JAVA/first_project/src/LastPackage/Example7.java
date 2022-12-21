package LastPackage;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Example7Panel extends JPanel implements Runnable{
	int time = 0;
	int h, m, s;
	
	public Example7Panel() {
		// TODO Auto-generated constructor stub
		
		Thread thread = new Thread(this);
		thread.start();
		
		JButton hBtn = new JButton("Hour");
		JButton mBtn = new JButton("Min");
		JButton sBtn = new JButton("Sec");
		
		add(hBtn);
		add(mBtn);
		add(sBtn);
		hBtn.addActionListener((e)-> {
			time += 3600;
			repaint();
		});
		mBtn.addActionListener((e)-> {
			time += 60;
			repaint();
		});
		sBtn.addActionListener((e)-> {
			time += 1;
			repaint();
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(1000);
				time++;
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
		h = time / 3600;
		m = (time%3600) / 60;
		s = time % 60;
		if (h >= 24) {
			time %= 3600;
			h = 0;
		}
		String string = "";
		if (h < 10) string += "0" + h; else string += h;
		if (m < 10) string += ":0" + m; else string += m;
		if (s < 10) string += ":0" + s; else string += s;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g2.setFont(new Font("Aria",Font.BOLD, 50));
		g2.drawString(string, getWidth()/5, getHeight()/2);
	}
}

public class Example7 extends JFrame{
	public Example7() {
		// TODO Auto-generated constructor stub
		setTitle("Example7");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example7Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example7();
	}
}