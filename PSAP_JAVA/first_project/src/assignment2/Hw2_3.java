package assignment2;


import java.util.Scanner;

abstract class Shape {
	private Shape next;
	public Shape() { next = null; }
	public void setNext(Shape obj) { next = obj; }
	public Shape getNext() { return next; }
	public abstract void draw();

}

class Line extends Shape{
	@Override
	public void draw() {
		System.out.println("Line");
	}
}

class Rect extends Shape{
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}

class Circle extends Shape{
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}

class GraphicEditor {
	private Shape	head;
	private int		len = 0;
	public Scanner	scan;
	
	GraphicEditor() {
		System.out.println("그래픽 에디터 beauty을 실행합니다.");
		scan = new Scanner(System.in);
	}
	
	public void command() throws Exception{
		int	cmd;
		
		System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4)>>");
		cmd = scan.nextInt();
		switch (cmd) {
		case 1:
			insertShape();
			break;
		case 2:
			deleteShape();
			break;
		case 3:
			printAll();
			break;
		case 4:
			shutdown();
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도 하십시오.");
		}
	}
	
	public Shape insertShape() throws Exception{
		System.out.print("Line(1), Rect(2), Circle(3)>>");
		int shapeNum = scan.nextInt();
		Shape newShape = null;
		Shape node;
		
		switch(shapeNum) {
		case 1:
			newShape = new Line();
			break;
		case 2:
			newShape = new Rect();
			break;
		case 3:
			newShape = new Circle();
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도 하십시오.");
		}
		if (head == null) {
			head = newShape;
			len++;
			return head;
		}
		node = head;
		while (node.getNext() != null) {
			node = node.getNext();
		}
		node.setNext(newShape);
		len++;
		return newShape;
	}
	
	public Shape deleteShape() throws Exception{
		System.out.print("삭제할 도형의 위치>>");
		int shapeLoc = scan.nextInt();
		Shape delShape = null;
		Shape node = head;
		
		if (len < shapeLoc) {
			System.out.println("삭제할 수 없습니다.");
			return null;
		}
		if (shapeLoc == 1) {
			delShape = node;
			node = null;
			head = head.getNext();
			len--;
			return delShape;
		}
		for (int i = 2; i < shapeLoc; i++) {
			node = node.getNext();
		}
		delShape = node.getNext();
		node.setNext(node.getNext().getNext());
		len--;
		return delShape;
	}
	
	public void printAll() {
		Shape node = head;
		
		while (node != null) {
			node.draw();
			node = node.getNext();
		}
	}
	
	public void shutdown() {
		scan.close();
		System.out.println("beauty를 종료합니다.");
		System.exit(0);
	}
}

public class Hw2_3 {
	
	public static void main(String args[]) {
		GraphicEditor ge = new GraphicEditor();
		while (true) {
			try {
				ge.command();
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
				ge.shutdown();
			}
			
		}
	}
}
