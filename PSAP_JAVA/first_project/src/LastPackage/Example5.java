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
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class Example5LeftPanel extends JPanel implements ListSelectionListener{
	DefaultListModel listModel = new DefaultListModel();
	JList list = new JList(listModel);
	Example5RightPanel rightPanel;
	
	
	public Example5LeftPanel() {
		// TODO Auto-generated constructor stub
		list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setPreferredSize(new Dimension(200, 400));
		setBackground(Color.gray);
		add(list);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener((e) -> {
//			Iterator<Point> iterator = rightPanel.points.iterator();
			int idx = list.getSelectedIndex();
			rightPanel.points.remove(idx);
			getParent().repaint();
			updateList();
//			while (iterator.hasNext()) {
//				
//			}
		});
		add(delete);
	}

	void updateList() {
		listModel.clear();
		int i = 0;
		for (var point:rightPanel.points) {
			listModel.addElement(i + ": center:(" + point.x  + ", " + point.y + ")");
			i++;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
	}
	
}

class Example5RightPanel extends JPanel {
	Example5LeftPanel leftPanel;
	ArrayList<Point> points = new ArrayList<>();
	
	public Example5RightPanel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				points.add(new Point(e.getX(), e.getY()));
				leftPanel.updateList();
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (var oval:points) {
			g.drawOval(oval.x, oval.y, 15, 15);
		}
	}
}

public class Example5 extends JFrame{
	Example5LeftPanel leftPanel;
	Example5RightPanel rightPanel;
	
	
	public Example5() {
		// TODO Auto-generated constructor stub
		setTitle("Example5");
		setSize(1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		leftPanel = new Example5LeftPanel(); 
		rightPanel = new Example5RightPanel(); 
		leftPanel.rightPanel = rightPanel;
		rightPanel.leftPanel = leftPanel;
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
		
		leftPanel.setPreferredSize(new Dimension(200, 500));
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example5();
	}

}
