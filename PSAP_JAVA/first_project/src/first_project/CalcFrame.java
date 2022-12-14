package first_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Node {
	private Node leftChild = null;
	private Node rightChild = null;
	private String data;
	
	public Node(String mathExp) {
		this.data = mathExp;
	}
	
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Node getLeft() {
		return this.leftChild;
	}
	public Node getRight() {
		return this.rightChild;
	}
	
	public void makeLeft(String mathExp) {
		this.leftChild = new Node(mathExp);
	}
	public void makeRight(String mathExp) {
		this.rightChild = new Node(mathExp);
	}
}

class UtilLibrary {
	public static boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static Double calculate(Node node) {
		if (node.getData().equals("+")) {
			return UtilLibrary.calculate(node.getLeft()) + UtilLibrary.calculate(node.getRight());
		} else if (node.getData().equals("-")) {
			return UtilLibrary.calculate(node.getLeft()) - UtilLibrary.calculate(node.getRight());
		} else if (node.getData().equals("x")) {
			return UtilLibrary.calculate(node.getLeft()) * UtilLibrary.calculate(node.getRight());
		} else if (node.getData().equals("/")) {
			return UtilLibrary.calculate(node.getLeft()) / UtilLibrary.calculate(node.getRight());
		} else {
			return Double.parseDouble(node.getData());
		}
	}
	
	public static void printTree(Node node) {
		if (node.getLeft() != null)
			printTree(node.getLeft());
		System.out.println(node.getData());
		if (node.getRight() != null)
			printTree(node.getRight());
	}
	
	public static void makeTree(Node node) {
		// node의 데이터에서 가장 우선순위가 낮은 연산자를 찾는다.
		int operIdx;
		String exp = node.getData();
		
		operIdx = UtilLibrary.findOper(exp);
		if (operIdx == -1) {
			exp = exp.replaceAll("\\(", "");
			exp = exp.replaceAll("\\)", "");
			node.setData(exp);
			System.out.println("test: "+exp);
			operIdx = UtilLibrary.findOper(exp);
		}
		if (operIdx != -1) {
			node.setData(exp.substring(operIdx, operIdx + 1));
			node.makeLeft(exp.substring(0, operIdx));
			node.makeRight(exp.substring(operIdx + 1));
			makeTree(node.getLeft());
			makeTree(node.getRight());
		}
	}
	
	public static int findOper(String mathExp) {
		int idx = -1;
		for(int i = mathExp.length() - 1; i >= 0 ; i--) {
			if (mathExp.charAt(i) == ')') {
				while(i > 0 && mathExp.charAt(i) != '(')
					i--;
			}
			if (mathExp.charAt(i) == 'x')
				idx = i;
			if (mathExp.charAt(i) == '/')
				idx = i;
		}
		for(int i = mathExp.length() - 1; i >= 0 ; i--) {
			if (mathExp.charAt(i) == ')') {
				while(i > 0 && mathExp.charAt(i) != '(')
					i--;
			}
			if (mathExp.charAt(i) == '+')
				idx = i;
			if (mathExp.charAt(i) == '-')
				idx = i;
		}
		return idx;
	}
	
	public static boolean checkBracket(String mathExp) {
		int bracketCnt = 0;
		for(int i = 0; i < mathExp.length(); i++) {
			if (mathExp.charAt(i) == '(')
				bracketCnt++;
			if (mathExp.charAt(i) == ')')
				bracketCnt--;
		}
		return (bracketCnt == 0);
	}
}

public class CalcFrame extends JFrame implements ActionListener {
	JLabel label;
	boolean state = false;
	double num1, num2;
	double result;
	String func = "";
	String nInput = "";
	
	String mathExp = new String("");
	boolean numFlag = false;
	
	String btn[] = { "<", "C", "(", ")", "7", "8", "9", "+", "4", "5", "6", "x", "1", "2", "3", "-", ".", "0", "=", "/" };
	
	public CalcFrame() {
		super("Calculator");
		super.setResizable(true);
		
		// JFrame : 메인 프레임
		// JPanel : 보조 프레임
		// JLabel  기능을 수행하는 컴포넌트
		label = new JLabel(mathExp, JLabel.RIGHT); // "첫 출력 값", 위치
		JPanel mainView = new JPanel();
		label.setFont(new Font("Serif", Font.BOLD, 50));
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		
		// 버튼 창 GUI
		JPanel btnView = new JPanel();
		btnView.setLayout(new GridLayout(5, 4, 2, 2));
		
		JButton button[] = new JButton[btn.length];
		for (int i = 0; i < btn.length; i++) {
			button[i] = new JButton(btn[i]);
			button[i].setFont(new Font("Serif", Font.BOLD, 25));
			button[i].addActionListener(this);
			
			if (i == 0 || i == 1 | i == 18)
				button[i].setForeground(Color.RED);
			if (i == 2 || i == 3 || i == 7 || i == 11 || i == 15 || i == 19)
				button[i].setForeground(Color.BLUE);
			
			btnView.add(button[i]);
		}
		
		mainView.setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		add(btnView, BorderLayout.SOUTH);
		
		setBounds(100, 100, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = e.getActionCommand();
		
		if (input.equals("=")) {
			if (UtilLibrary.checkBracket(mathExp)) {
				Node root = new Node(mathExp);
				UtilLibrary.makeTree(root);
				UtilLibrary.printTree(root);
				mathExp = UtilLibrary.calculate(root).toString();
			}
		} else if (input.equals("C")) {
			mathExp = "";
			numFlag = false;
		} else if (input.equals("<")) {
			if (mathExp.length() > 0) {
				mathExp = mathExp.substring(0, mathExp.length() - 1);
				if (mathExp.length() == 0 || !Character.isDigit(mathExp.charAt(mathExp.length() - 1)))
					numFlag = false;
				else
					numFlag = true;
			}
		} else if (UtilLibrary.isNumeric(input)){
			mathExp += input;
			numFlag = true;
		} else if (input.equals("(")) {
			if (numFlag == false)
				mathExp += input;
		} else if (input.equals(")")) {
			if (numFlag == true)
				mathExp += input;
		} else if (numFlag) {
			mathExp += input;
			numFlag = false;
		} 
		label.setText(mathExp);
	}

	public static void main(String[] args) {
		 new CalcFrame();
	}

}
