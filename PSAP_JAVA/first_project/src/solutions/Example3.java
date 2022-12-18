package solutions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;


class MyRect
{
	Point pt1, pt2;
	Color c;
	
	void draw(Graphics g)
	{
		g.setColor(c);
		int x = pt1.x;
		int y = pt1.y;
		int w = pt2.x-pt1.x;	if(w<0) { x = pt2.x; w = -w;}	
		int h = pt2.y-pt1.y;	if(h<0) { y = pt2.y; h = -h;}
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
	}
	
}


class MyPanel3 extends JPanel
{
	LinkedList<MyRect> list = new LinkedList<MyRect>();
	JLabel label;
	Color color;
	MyPanel3()
	{
		label = new JLabel("");
		color = Color.RED; 
		
		add(label);
		
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					MyRect r = new MyRect();
					r.c = color;
							
					r.pt1 = new Point(e.getX(), e.getY());
					r.pt2 = new Point(e.getX(), e.getY());
					
					list.add(r);
					repaint();
				}
			}			
		});
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(SwingUtilities.isLeftMouseButton(e))
				{
					list.getLast().pt2.x = e.getX();
					list.getLast().pt2.y = e.getY();
					repaint();
				}
			}	
	
		});
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		label.setText("Num Of Rects:"+list.size());
		
		super.paintComponent(g);
		for(MyRect r:list)
			r.draw(g);
		
		
	}


}

class MyFrame3 extends JFrame implements ActionListener
{
	JMenuItem item1, item2, item3;
	MyPanel3 p;
	MyFrame3()
	{
		setSize(800,500);
		setTitle("Example 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Color");
		menuBar.add(menu);
		ButtonGroup group = new ButtonGroup();
		
		item1 = new JRadioButtonMenuItem("Red");
		item1.addActionListener(this);
		item1.setSelected(true);
		
		item2 = new JRadioButtonMenuItem("Green");
		item2.addActionListener(this);
		item3 = new JRadioButtonMenuItem("Blue");
		item3.addActionListener(this);
		
		group.add(item1);	menu.add(item1); 	
		group.add(item2);	menu.add(item2);
		group.add(item3);	menu.add(item3);
		
		setJMenuBar(menuBar);		
		
		p = new MyPanel3();
		add(p);
		
		setVisible(true);		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == item1)
			p.color = Color.red;
		if(e.getSource() == item2)
			p.color = Color.green;
		if(e.getSource() == item3)
			p.color = Color.blue;
	
	}	
	
}

public class Example3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame3 f = new MyFrame3();
	}

}
