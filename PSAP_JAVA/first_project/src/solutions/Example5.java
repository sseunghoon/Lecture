package solutions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




class MyRightPanel extends JPanel
{
	LinkedList<Point> pointList = new LinkedList<Point>();
	
	MyLeftPanel leftPanel;
	int selected = -1;
	
	MyRightPanel()
	{
				
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				pointList.add(e.getPoint());
				leftPanel.updateList();
				repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int i=0;
		for(Point p : pointList)
		{
			int r = 50;
			if(i==selected)
				g.setColor(Color.red);
			else
				g.setColor(Color.black);
				
			g.drawOval(p.x-r,  p.y-r,  2*r, 2*r);
			i++;
		}
	}
	void removeSelected()
	{
		if(selected < 0) return;
		Iterator <Point> it = pointList.iterator();
		int i = 0;
		for(; it.hasNext(); )
		{
			it.next();
			if(i==selected)
			{
				it.remove();
				break;
			}
			i++;
		}
		selected = -1;
	}
}

class MyLeftPanel extends JPanel implements ListSelectionListener
{
	MyRightPanel rightPanel;
	JList list;
	DefaultListModel listModel;
	JButton but;
	MyLeftPanel()
	{
		this.setBackground(Color.GRAY);
		listModel = new DefaultListModel();
		list = new JList(listModel);
		
		list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);	
		list.setPreferredSize(new Dimension(200,400));
		
		
		add(list);
		but = new JButton("Delete");
		but.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeSelected();
				updateList();				
			}
	
		});
		add(but);
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = list.getSelectedIndex();
		rightPanel.selected = index;
		rightPanel.repaint();
	
	}
	void updateList()
	{
		listModel.clear();
		
		int i=0;
		for(Point p: rightPanel.pointList)
		{
			String str = new String();
			str = i+": center("+p.x+","+p.y+")";
			listModel.addElement(str);
			i++;
		}		
	}
	
}

public class Example5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame f = new JFrame();
		f.setSize(800,500);
		f.setTitle("Example 5");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyLeftPanel p1 = new MyLeftPanel();
		p1.setPreferredSize(new Dimension(200,500));
		f.add(p1, BorderLayout.WEST);
		MyRightPanel p2 = new MyRightPanel();
		f.add(p2, BorderLayout.CENTER);
		
		p1.rightPanel = p2;
		p2.leftPanel = p1;
		
		f.setVisible(true);			
		}

}
