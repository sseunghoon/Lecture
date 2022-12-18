package solutions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class MyPanel6 extends JPanel
{
	MyPanel6()
	{

		setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Type At the Above TextField");
		JTextField textField = new JTextField();
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		textField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.getText();
				textArea.setText(textArea.getText()+textField.getText()+"\n");
				textField.setText("");
				
			}
		});			
				
		add(textField, BorderLayout.PAGE_START);
		add(textArea, BorderLayout.CENTER);
		add(label, BorderLayout.PAGE_END);
	}
}

public class Example6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setTitle("Example 6");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel6 p = new MyPanel6();
		f.add(p);
		f.setVisible(true);		
	}

}
