package finalExam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel10 extends JPanel
{
	Point p = new Point();
	int size = 100;
	
	BufferedImage img;
	
	MyPanel10()
	{
		try {
			img = ImageIO.read(new File("lena.jpg"));
		} catch (IOException e) {
			System.out.println("Cannot find lena.jpg!");
			System.out.println("Terminate the program.");
			System.exit(ABORT);
		}
		
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				p = e.getPoint();
				repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e)
			{
				p = e.getPoint();
				repaint();
			}			
		});
		
		addMouseWheelListener(new MouseWheelListener()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				int rot = e.getWheelRotation();
				size += rot*5;
				if(size <10 ) size = 10;
				repaint();
				
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, 0, 0, null);
		
		int x = p.x-size/2;	if(x<0) x = 0; if(x>img.getWidth()-size) x= img.getWidth()-size;
		int y = p.y-size/2;	if(y<0) y = 0; if(y>img.getHeight()-size) y= img.getHeight()-size;	
		
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(x, y, size, size);	
		
		int windowSize = 300;
		g2.drawImage(img,  img.getWidth(), 0, img.getWidth()+windowSize, windowSize, 
				           x,  y,  x+size, y+size, null);
	}
}


public class Example10 {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setSize(1000,800);
		f.setTitle("Example 10");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel10 p = new MyPanel10();
		f.add(p);
		f.setVisible(true);					
	}

}