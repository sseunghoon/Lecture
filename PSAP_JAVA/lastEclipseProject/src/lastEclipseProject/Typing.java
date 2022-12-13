package lastEclipseProject;

import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TypingPanel extends JPanel {
	TextField textField = new TextField();
	TextArea textArea = new TextArea();
	
	public TypingPanel() {
		// TODO Auto-generated constructor stub
		add(textField);
		add(textArea);
		add(new JLabel("Type at the above TextField"));
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
	
	public static void main() {
		new Typing();
	}

}
