package finalExam;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CharClockPanel extends JPanel implements ActionListener, Runnable{
	int time = 0;
	JButton hour = new JButton("Hour");
	JButton min = new JButton("Min");
	JButton sec = new JButton("Sec");
	
	public CharClockPanel() {
		// TODO Auto-generated constructor stub
		
		
		add(hour, BorderLayout.NORTH);
		add(min, BorderLayout.NORTH);
		add(sec, BorderLayout.NORTH);
		hour.addActionListener(this);
		min.addActionListener(this);
		sec.addActionListener(this);
		
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
		repaint();
	}
	
	
}

public class CharClock extends JFrame{
	public CharClock() {
		// TODO Auto-generated constructor stub
		setTitle("CharClock");
		setSize(500, 500);
		
		add(new CharClockPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CharClock();
	}
}
