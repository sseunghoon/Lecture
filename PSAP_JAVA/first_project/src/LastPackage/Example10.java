package LastPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Example10Panel extends JPanel {
	BufferedImage img;
	Point point = new Point(0,0);
	int size = 100;
	
	public Example10Panel() {
		// TODO Auto-generated constructor stub
		try {
			img = ImageIO.read(new File("lena.jpg"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				point = new Point(e.getX(), e.getY());
				repaint();
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				int rt = e.getWheelRotation();
				size += rt * 5;
				if (size < 10) {
					size =10;
				}
				repaint();
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0, 0, getBackground(), getFocusCycleRootAncestor());
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.red);
		
		if (point.x - size/2 < 0 ) {
			point.x = size/2;
		}
		if (point.x + size/2 > img.getWidth()) {
			point.x = img.getWidth() - size/2;
		}
		
		if (point.y - size/2 < 0 ) {
			point.y = size/2;
		}
		if (point.y + size/2 > img.getHeight()) {
			point.y = img.getHeight() - size/2;
		}
		
		g2.drawRect(point.x - size/2, point.y - size/2, size, size);
		
		
		int window = 300;
		g2.drawImage(img, img.getWidth(), 0, img.getWidth() + window, window, point.x - size/2, point.y - size/2,point.x + size/2, point.y + size/2, null);
	}
}

public class Example10 extends JFrame{
	public Example10() {
		// TODO Auto-generated constructor stub
		setTitle("Example10");
		setSize(1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example10Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example10();
	}
}