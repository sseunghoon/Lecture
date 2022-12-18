package LastPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Example5_1LeftPanel extends JPanel implements ListSelectionListener{
	Example5_1RightPanel rPanel;
	
	DefaultListModel listModel = new DefaultListModel();
	JList list;
	
	public Example5_1LeftPanel() {
		// TODO Auto-generated constructor stub
		list = new JList(listModel);
		
		list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setPreferredSize(new Dimension(200, 400));
		list.addListSelectionListener(this);
		
		add(list);
		setBackground(Color.gray);
		
		JButton delete = new JButton("Delete");
		add(delete);
		delete.addActionListener((e)-> {
			rPanel.removeSelected();
			updateList();
		});
	}
	
	void updateList() {
		int i = 0;
		
		listModel.clear();
		for (var p:rPanel.arr) {
			listModel.addElement(i + "center(" + p.x + ", " + p.y + ")");
		}
		repaint();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		rPanel.select = list.getSelectedIndex();
		rPanel.repaint();
	}

}

class Example5_1RightPanel extends JPanel {
	Example5_1LeftPanel lPanel;
	ArrayList<Point> arr = new ArrayList<>();
	int select = -1;
	
	
	
	public Example5_1RightPanel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				arr.add(new Point(e.getX(), e.getY()));
				lPanel.updateList();
				repaint();
			}
		});
		
	}
	
	void removeSelected() {
		if (select < 0) 
			return;
		arr.remove(select);
		select  = -1;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int i =0;
		for (var oval:arr) {
			g.setColor(Color.black);
			if (i==select)
				g.setColor(Color.red);
			g.fillOval(oval.x, oval.y, 10, 10);
			i++;
		}
	}
}

public class Example5_1 extends JFrame {
	Example5_1LeftPanel leftPanel = new Example5_1LeftPanel();
	Example5_1RightPanel rightPanel = new Example5_1RightPanel();
	
	public Example5_1() {
		// TODO Auto-generated constructor stub
		setTitle("Example5_1");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		

		leftPanel.rPanel = rightPanel;
		rightPanel.lPanel = leftPanel;
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		
		leftPanel.setPreferredSize(new Dimension(200,500));
		
		setVisible(true);
		
	}
	
	


	public static void main(String[] args) {
		new Example5_1();
	}
}
