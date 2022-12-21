package LastPackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Example6Panel extends JPanel {
	public Example6Panel() {
		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea();
		JLabel label = new JLabel("Type at Above TextField");
		JTextField textField = new JTextField();
		
		textArea.setEditable(false);
		
		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.NORTH);
		add(label, BorderLayout.SOUTH);
		
		setFocusable(true);
		requestFocus();
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = textField.getText();
				textField.setText("");
				textArea.setText(textArea.getText() + str+ "\n");
				repaint();
			}
		});
	}
}

public class Example6 extends JFrame{
	public Example6() {
		// TODO Auto-generated constructor stub
		setTitle("Example6");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new Example6Panel());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Example6();
	}
}