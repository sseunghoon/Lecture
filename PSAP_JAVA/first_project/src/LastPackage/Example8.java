package LastPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

class Example8Panel extends JPanel implements Runnable{
	int time = 0;
	int h, m, s;
	JToggleButton mode;
	
	public Example8Panel() {
		// TODO Auto-generated constructor stub
		
		Thread thread = new Thread(this);
		thread.start();
		
		JButton hBtn = new JButton("Hour");
		JButton mBtn = new JButton("Min");
		JButton sBtn = new JButton("Sec");
		mode = new JToggleButton("Digit Clock");
		
		add(hBtn);
		add(mBtn);
		add(sBtn);
		add(mode);
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
		
		
		mode.addActionListener((e)-> {
			if (mode.isSelected()) {
				mode.setText("Round Clock");
			} else {
				mode.setText("Digit Clock");
			}
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
		
		Graphics2D g2 = (Graphics2D) g;
		if (mode.getText().equals("Digit Clock")) {
			String string = "";
			if (h < 10) string += "0" + h; else string += h;
			if (m < 10) string += ":0" + m; else string += m;
			if (s < 10) string += ":0" + s; else string += s;
			
			
			g2.setStroke(new BasicStroke(10));
			g2.setFont(new Font("Aria",Font.BOLD, 50));
			g2.drawString(string, getWidth()/5, getHeight()/2);
		} else {
			int cx = getWidth()/ 2;
			int cy = getHeight()/2 - 50;
			int r = Math.min(cx, cy);
			cy += 50;
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.black);
			g2.drawOval(cx - r, cy - r, 2*r, 2*r);
			g2.setColor(Color.white);
			g2.fillOval(cx -r, cy -r, 2*r, 2*r);
			
			double x1,x2, y1,y2;
			double theta = 3.141592 * 2  /60;
			g2.setColor(Color.black);
			for (int i =0; i<60;i++) {
				x1 = cx + r * 0.9 * Math.cos(theta * i);
				y1 = cy + r * 0.9 * Math.sin(theta * i);
				if (i % 5 == 0) {
					x1 = cx + r * 0.8 * Math.cos(theta * i);
					y1 = cy + r * 0.8 * Math.sin(theta * i);
				}
				x2 = cx + r * Math.cos(theta * i);
				y2 = cy + r * Math.sin(theta * i);
				g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			}
			
			x2 = cx + r * 0.5* Math.cos(theta * h * 5 - 3.141592/2);
			y2 = cy + r * 0.5* Math.sin(theta * h * 5 - 3.141592/2);
			g2.setStroke(new BasicStroke(8));
			g2.setColor(Color.red);
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
			
			x2 = cx + r * 0.7* Math.cos(theta * m - 3.141592/2);
			y2 = cy + r * 0.7* Math.sin(theta * m - 3.141592/2);
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.blue);
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
			
			x2 = cx + r * 0.9 * Math.cos(theta * s - 3.141592/2);
			y2 = cy + r * 0.9 * Math.sin(theta * s - 3.141592/2);
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.green);
			g2.drawLine((int)cx, (int)cy, (int)x2, (int)y2);
		}
		
	}
}

public class Example8 extends JFrame{
	public Example8() {
		// TODO Auto-generated constructor stub
		setTitle("Example8");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example8Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example8();
	}
}

