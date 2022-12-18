package solutions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel7 extends JPanel implements ActionListener, Runnable
{
	int time = 0;				// in second
	JButton hour, min, sec;
	
	MyPanel7()
	{
		hour = new JButton("Hour"); hour.addActionListener(this);
		min = new JButton("Min");	min.addActionListener(this);
		sec = new JButton("Sec"); 	sec.addActionListener(this);
		add(hour);
		add(min);
		add(sec);
		
		Thread T = new Thread(this);
		T.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == hour)
			time += 3600;
		if(e.getSource() == min)
			time += 60;
		if(e.getSource() == sec)
			time += 1;
		
		time %= (3600*24);

		repaint();		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int t = time;
		int h = t/3600;	t %= 3600;	h = h%24;
		int m = t/60;	t %= 60;
		int s = t;
		
		String str = new String();
		if(h<10)
			str += "0";
		str += new Integer(h).toString() + ":";
		if(m<10)
			str += "0";
		str += new Integer(m).toString() + ":";
		if(s<10)
			str += "0";
		str += new Integer(s).toString();
		
		Font f = new Font("Arial", Font.BOLD, 100);
        g.setFont(f);
        Dimension d = getSize();
       	g.drawString(str, 10, (int)(d.height/2+50));
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			time ++;
			repaint();
		}
	}
	
}

public class Example7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setTitle("Example 7");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel7 p = new MyPanel7();
		f.add(p);
		f.setVisible(true);				
	}

}
