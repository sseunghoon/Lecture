package solutions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

class MyPanel8 extends JPanel implements ActionListener, Runnable
{
	int time = 0;				// in second
	JButton hour, min, sec;
	JToggleButton toggle;
	
	MyPanel8()
	{
		hour = new JButton("Hour"); hour.addActionListener(this);
		min = new JButton("Min");	min.addActionListener(this);
		sec = new JButton("Sec"); 	sec.addActionListener(this);
		toggle = new JToggleButton("Digit Clock");
		toggle.addActionListener(this);
		
		add(hour);
		add(min);
		add(sec);
		add(toggle);
		
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
		if(e.getSource() == toggle)
		{
			if(toggle.isSelected())
				toggle.setText("Round Clock");
			else 
				toggle.setText("Digit Clock");
			
		}
		
		time %= (3600*24);

		repaint();		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

        Dimension d = getSize();
        
		
		int t = time;
		int h = t/3600;	t %= 3600;	h = h%24;
		int m = t/60;	t %= 60;
		int s = t;
		
		if(!toggle.isSelected())
		{
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
	       	g.drawString(str, 10, (int)(d.height/2+50));
		}
		else
		{
			int offset = 50;
			int cx = d.width/2;
			int cy = d.height/2-offset;
			int r = Math.min(cx, cy);
			cy += offset;
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(2));

			g2.setColor(Color.white);
			g2.fillOval(cx-r,  cy-r, 2*r, 2*r);
			g2.setColor(Color.black);
			g2.drawOval(cx-r,  cy-r, 2*r, 2*r);
					
			double x1,y1,x2,y2;
			double r1,r2;
			double theta;
			for(int i=0; i<60; i++)
			{
				theta = 360.0f/60.0f*i/180.0f*3.141592f;
				r1 = r;
				r2 = r*0.95;
				if(i%5 == 0) r2 = r*0.9;
				x1 = r1*Math.cos(theta)+cx;
				y1 = r1*Math.sin(theta)+cy;
				x2 = r2*Math.cos(theta)+cx;
				y2 = r2*Math.sin(theta)+cy;
				g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
			}
			
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.blue);
			
			theta = s/60.0*3.141592*2-3.141592/2;
			r1 = r*0.9;
			x1 = r1*Math.cos(theta)+cx;
			y1 = r1*Math.sin(theta)+cy;
			g2.drawLine(cx, cy, (int)x1, (int)y1);		
			
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.green);
			
			theta = m/60.0*3.141592*2-3.141592/2;
			r1 = r*0.7;
			x1 = r1*Math.cos(theta)+cx;
			y1 = r1*Math.sin(theta)+cy;
			g2.drawLine(cx, cy, (int)x1, (int)y1);		

			g2.setStroke(new BasicStroke(9));
			g2.setColor(Color.red);
			
			theta = (h%12*60+m)/(60.0*12.0)*3.141592*2-3.141592/2;
			r1 = r*0.5;
			x1 = r1*Math.cos(theta)+cx;
			y1 = r1*Math.sin(theta)+cy;
			g2.drawLine(cx, cy, (int)x1, (int)y1);		
			
		}
		
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

public class Example8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setTitle("Example 8");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel8 p = new MyPanel8();
		f.add(p);
		f.setVisible(true);				
	}

}
