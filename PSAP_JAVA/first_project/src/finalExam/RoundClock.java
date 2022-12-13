package finalExam;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class RoundClockPanel extends JPanel implements ActionListener, Runnable{
	int time = 0;
	JButton hour = new JButton("Hour");
	JButton min = new JButton("Min");
	JButton sec = new JButton("Sec");
	JButton mode = new JButton("Digit Clock");
	
	public RoundClockPanel() {
		// TODO Auto-generated constructor stub
		
		
		add(hour);
		add(min);
		add(sec);
		add(mode);
		hour.addActionListener(this);
		min.addActionListener(this);
		sec.addActionListener(this);
		mode.addActionListener(this);
		
	
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		time = time % (3600 * 24);
		String display;
		int htime = time / 3600;
		int mtime = (time % 3600) / 60;
		int stime = time % 60;
		
		if (mode.getText().equals("Digit Clock")) {
			if (htime < 10) {
				display = "0" + htime;
			} else {
				display = "" + htime;
			}
			
			if (mtime < 10) {
				display = display + ":0" + mtime;
			} else {
				display = display + ":" + mtime;
			}
			
			if (stime < 10) {
				display = display + ":0" + stime;
			} else {
				display = display + ":" + stime;
			}
			g.setFont(new Font("name", Font.BOLD, 100));
			g.drawString(display, 0, 300);
		} else {
			int offset = 50;
			int cx = getWidth() / 2;
			int cy = getHeight() / 2 - offset;
			int r1 = Math.min(cx, cy);
			double r2 = r1 * 0.95;
			
			cy += offset;
			
			double x1, x2;
			double y1, y2;
			double theta;
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(2));
			
			g2.drawOval(cx -r1, cy - r1, 2*r1, 2*r1);
			g2.setColor(Color.white);
			g2.fillOval(cx -r1, cy - r1, 2*r1, 2*r1);
			g2.setColor(Color.black);
			
			for(int i = 0; i < 60; i++) {
				if (i % 5 == 0) {
					r2 = r1 * 0.9;
				}
				theta = 2 * 3.141592f / 60.0f * i;
				x1 = r1 * Math.cos(theta) + cx;
				y1 = r1 * Math.sin(theta) + cy;
				x2 = r2 * Math.cos(theta) + cx;
				y2 = r2 * Math.sin(theta) + cy;
				r2 = r1 * 0.95;
				g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			}
			g2.setStroke(new BasicStroke(9));
			g2.setColor(Color.red);
			theta = 2 * 3.141592f / 60.0f * (htime % 12) * 5 - (3.141592/2);
			x2 = r2 * 0.5 * Math.cos(theta) + cx;
			y2 = r2 * 0.5 * Math.sin(theta) + cy;
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
			
			
			g2.setStroke(new BasicStroke(6));
			g2.setColor(Color.blue);
			theta = 2 * 3.141592f / 60.0f * mtime - (3.141592/2);
			x2 = r2 * 0.5 * Math.cos(theta) + cx;
			y2 = r2 * 0.5 * Math.sin(theta) + cy;
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
			
			g2.setStroke(new BasicStroke(4));
			g2.setColor(Color.ORANGE);
			theta = 2 * 3.141592f / 60.0f * stime - (3.141592/2);
			x2 = r2 * 0.5 * Math.cos(theta) + cx;
			y2 = r2 * 0.5 * Math.sin(theta) + cy;
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
			
		}
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == hour) {
			time += 3600;
		}
		if (e.getSource() == min) {
			time += 60;
		}
		if (e.getSource() == sec) {
			time += 1;
		}
		
		if (e.getSource() == mode) {
			if (mode.getText().equals("Digit Clock")) {
				mode.setText("Round Clock");
				
			} else {
				mode.setText("Digit Clock");
			}
		}
		repaint();
	}
	
	
}

public class RoundClock extends JFrame{
	public RoundClock() {
		// TODO Auto-generated constructor stub
		setTitle("Round Clock");
		setSize(500, 500);
		
		add(new RoundClockPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new RoundClock();
		
	}
}
