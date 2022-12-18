package LastPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Example5_2LeftPanel extends JPanel implements ListSelectionListener{
	Example5_2RightPanel rPanel;
	DefaultListModel listModel;
	JList list;
	
	public Example5_2LeftPanel() {
		// TODO Auto-generated constructor stub
		listModel = new DefaultListModel();
		list = new JList(listModel);
		
		list.setBorder(BorderFactory.createLineBorder(Color.black,1));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setPreferredSize(new Dimension(200, 400));
		
		setBackground(Color.gray);
		
		add(list);
		
		JButton delete = new JButton("Delete");
		add(delete);
		delete.addActionListener((e) -> {
			rPanel.selectedRemove();
			updateList();
		});
		
	}
	void updateList() {
		listModel.clear();
		int i = 0;
		for(var p:rPanel.points) {
			listModel.addElement(i+"center(" + p.x + ", " + p.y + ")");
			i++;
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

class Example5_2RightPanel extends JPanel {
	Example5_2LeftPanel lPanel;
	int select = -1;
	ArrayList<Point> points = new ArrayList<>();
	
	public Example5_2RightPanel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				points.add(new Point(e.getX(), e.getY()));
				lPanel.updateList();
				repaint();
			}
		});
	}
	
	void selectedRemove() {
		if (select < 0) return;
		points.remove(select);
		select = -1;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for (int i = 0; i<points.size(); i++) {
			Point p = points.get(i);
			g.setColor(Color.black);
			if (i == select) g.setColor(Color.red);
			g.fillOval(p.x, p.y, 10, 10);
		}
	}
}

public class Example5_2 extends JFrame{
	Example5_2LeftPanel leftPanel = new Example5_2LeftPanel();
	Example5_2RightPanel rightPanel = new Example5_2RightPanel();
	
	public Example5_2() {
		// TODO Auto-generated constructor stub
		setTitle("Example5_2");
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		leftPanel.rPanel = rightPanel;
		rightPanel.lPanel = leftPanel;
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		leftPanel.setPreferredSize(new Dimension(200, 500));
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example5_2();
	}
}
