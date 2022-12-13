package finalExam;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TypingPanel extends JPanel {
	
	
	public TypingPanel() {
		// TODO Auto-generated constructor stub
		TextField textField = new TextField();
		TextArea textArea = new TextArea();
		JLabel jLabel = new JLabel("Type At the Above TextField");
		
		setLayout(new BorderLayout());
		
		textArea.setEditable(false);
		
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input = textField.getText();
				textField.setText("");
				textArea.setText(textArea.getText() + input + "\n");
			}
		});
		add(textField, BorderLayout.PAGE_START);
		add(textArea, BorderLayout.CENTER);
		add(jLabel, BorderLayout.PAGE_END);
	}
	
	
}

public class Typing extends JFrame{
	public Typing() {
		// TODO Auto-generated constructor stub
		setTitle("Typing");
		setSize(500, 500);
		
		add(new TypingPanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Typing();
	}
}
