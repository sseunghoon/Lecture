package RealLastPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

class Oval {
	int cx, cy;
	int r;
	
	
	
	public Oval(int cx, int cy, int r) {
		super();
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}

	int getDistance(int x, int y) {
		return (int)(Math.hypot(Math.abs(cx-x), Math.abs(cy-y)));
	}
	
	boolean isContain(int x, int y) {
		if (getDistance(x, y) <= r) {
			return true;
		}
		return false;
	}
}

class Example4Panel extends JPanel {
	ArrayList<Oval> ovals = new ArrayList<>();
	ArrayList<Oval> selected = new ArrayList<>();
	
	int clickX, clickY;
	
	public Example4Panel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				clickX = e.getX();
				clickY = e.getY();
				for(var o: ovals) {
					if (o.isContain(clickX, clickY)) selected.add(o);
				}
				if (selected.size() == 0) {
					ovals.add(new Oval(clickX,clickY,0));
				}
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				selected.clear();
				repaint();
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				if (selected.size() == 0) {
					Oval oval = ovals.get(ovals.size()-1);
					oval.r = oval.getDistance(e.getX(), e.getY());
				} else {
					int moveX, moveY;
					moveX = e.getX() - clickX;
					moveY = e.getY() - clickY;
					clickX = e.getX();
					clickY = e.getY();
					for (var o:selected) {
						o.cx += moveX;
						o.cy += moveY;
					}
				}
				
				repaint();
			}
		});
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (var o:ovals) {
			
			if (selected.contains(o)) g.setColor(Color.yellow);
			else g.setColor(Color.red); 
			g.fillOval(o.cx - o.r, o.cy -o.r, o.r * 2, o.r * 2);
		}
	}
}

public class Example4 extends JFrame{
	public Example4() {
		// TODO Auto-generated constructor stub
		setTitle("Example3");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example4Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example4();
	}
}
