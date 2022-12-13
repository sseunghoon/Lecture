package finalExam;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ExpandPicturePanel extends JPanel {
	int x = 0;
	int y = 0;
	BufferedImage img;
	int size = 100;
	public ExpandPicturePanel() {
		try {
			img = ImageIO.read(new File("lena.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				x = e.getX() - size;
				if (x < 0) x = 0; if (x > img.getWidth() - size) x = img.getWidth() - size;
				y = e.getY() - size;
				if (y < 0) y = 0; if (y > img.getHeight() - size) y = img.getHeight() - size;
				repaint();
			}
			
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(4));
		g2.drawRect(x, y, size, size);
		
		g2.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), getBackground(), getFocusCycleRootAncestor());
		
		int window = 300;
		g2.drawImage(img, img.getWidth(), 0, img.getWidth() + window, window, x, y, x + size, y + size, getFocusCycleRootAncestor());
	}
}

public class ExpandPicture extends JFrame{
	public ExpandPicture() {
		// TODO Auto-generated constructor stub
		setTitle("Example10");
		setSize(1000, 800);
		
		add(new ExpandPicturePanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ExpandPicture();
	}

}
