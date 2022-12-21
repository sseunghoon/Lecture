package RealLastPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

class Example3Panel extends JPanel {
	Color color;
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
		
		menu.add(red);
		menu.add(green);
		menu.add(blue);
		red.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(red);
		buttonGroup.add(green);
		buttonGroup.add(blue);
		
		red.addActionListener(this);
		green.addActionListener(this);
		blue.addActionListener(this);
		
		setJMenuBar(menuBar);
		
		
		panel = new Example3Panel();
		
		setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==red) {
			panel.color = Color.red;
		}
		if (e.getSource()==green) {
			panel.color = Color.green;
		}
		if (e.getSource()==blue) {
			panel.color = Color.blue;
		}
	}



	public static void main(String[] args) {
		new Example3();
	}
}
