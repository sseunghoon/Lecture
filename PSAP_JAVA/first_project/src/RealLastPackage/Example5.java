package RealLastPackage;

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

class Example5LeftPanel extends JPanel implements ListSelectionListener{
	Example5RightPanel rightPanel;
	DefaultListModel listModel = new DefaultListModel();
	JList list = new JList(listModel);
	JButton delete = new JButton("Delete");
	
	public Example5LeftPanel() {
		// TODO Auto-generated constructor stub
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(BorderFactory.createLineBorder(Color.black,1));
		list.setPreferredSize(new Dimension(200, 400));
		list.addListSelectionListener(this);
		
		add(list);
		add(delete);
		
		delete.addActionListener((e) -> {
			rightPanel.removeSelected();
			rightPanel.repaint();
			updateList();
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		rightPanel.selected = list.getSelectedIndex();
		rightPanel.repaint();
	}
	
	void updateList() {
		listModel.clear();
		int i =0;
		for (var o:rightPanel.ovals) {
			listModel.addElement(i+": center(" + o.x+", " + o.y +")");
			i++;
		}
		repaint();
	}
	
	
}

class Example5RightPanel extends JPanel{
	Example5LeftPanel leftPanel;
	int selected = -1;
	ArrayList<Point> ovals = new ArrayList<>();
	
	public Example5RightPanel() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				ovals.add(new Point(e.getX(), e.getY()));
				leftPanel.updateList();
				repaint();
			}
			
		});
		
	}
	void removeSelected() {
		if (selected < 0) return;
		ovals.remove(selected);
		selected = -1;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int i =0;
		for (var o:ovals) {
			g.setColor(Color.black);
			if (i==selected) g.setColor(Color.red);
			g.fillOval(o.x, o.y, 20, 20);
			i++;
		}
	}
}

public class Example5 extends JFrame{
	Example5LeftPanel lPanel = new Example5LeftPanel();
	Example5RightPanel rPanel = new Example5RightPanel();
	
	public Example5() {
		// TODO Auto-generated constructor stub
		setTitle("Example5");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lPanel.setPreferredSize(new Dimension(200, 500));
		
		setLayout(new BorderLayout());
		add(lPanel, BorderLayout.WEST);
		add(rPanel, BorderLayout.CENTER);
		lPanel.rightPanel = rPanel;
		rPanel.leftPanel = lPanel;
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example5();
	}
}
