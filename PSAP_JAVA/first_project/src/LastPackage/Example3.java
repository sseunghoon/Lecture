package LastPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

class MyRect {
	int x1;
	int y1;
	int x2;
	int y2;
	Color color;
	
	public MyRect(int x1, int y1, int x2, int y2, Color color) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	
	
	
}

class Example3Panel extends JPanel {
	Color color = Color.red;
	ArrayList<MyRect> arr = new ArrayList<>();
	
	public Example3Panel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				arr.add(new MyRect(e.getX(), e.getY(),e.getX(), e.getY(), color));

			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				MyRect rect = arr.get(arr.size()-1);
				rect.x2 = e.getX();
				rect.y2 = e.getY();
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for (var rect: arr) {
			int x = rect.x1;
			int y = rect.y1;
			int w = rect.x2 - rect.x1; if (w < 0) {w = -w; x = rect.x2;}
			int h = rect.y2 - rect.y1; if (h < 0) {h = -h; y = rect.y2;}
			
			g.setColor(rect.color);
			g.fillRect(x,y,w,h);
		}
		
		
	}
}

public class Example3 extends JFrame implements ActionListener{
	JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
	JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green");
	JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blue");
	
	Example3Panel panel;
	
	public Example3() {
		// TODO Auto-generated constructor stub
		setTitle("Example3");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Color");
		menuBar.add(menu);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		menu.add(red);
		menu.add(green);
		menu.add(blue);
		buttonGroup.add(red);
		buttonGroup.add(blue);
		buttonGroup.add(green);
		
		red.addActionListener(this);
		green.addActionListener(this);
		blue.addActionListener(this);
		red.setSelected(true);
		
		setJMenuBar(menuBar);
		
		panel = new Example3Panel();
		add(panel);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
		if (e.getSource() == red) {
			panel.color = Color.red;
		}
		if (e.getSource() == blue) {
			panel.color = Color.blue;
		}
		if (e.getSource() == green) {
			panel.color = Color.green;
		}
	}



	public static void main(String[] args) {
		new Example3();
	}
}
