package first_project;

import java.util.List;
import java.util.Scanner;

public class Hw1_2 {
	
	public static void main(String args[]) {
	
		Scanner scanner = new Scanner(System.in);
		System.out.print("연산>>");
		int n1 = scanner.nextInt();
		String 
		int n2 = scanner.nextInt();
		
		if (list[1].equals("+")) {
			System.out.println(x +" + " + y + "의 계산 결과는 " + x + y);
		}
		if (list[1].equals("-")) {
			System.out.println(x +" - " + y + "의 계산 결과는 " + (x - y));
		}
		if (list[1].equals("*")) {
			System.out.println(x +" * " + y + "의 계산 결과는 " + (x * y));
		}
		if (list[1].equals("/")) {
			System.out.println(x +" / *" + y + "의 계산 결과는 " + x / y);
		}
		scanner.close();
	}
}
