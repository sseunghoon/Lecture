package first_project;

import java.util.Scanner;


class Circle {
	int x;
	int y;
	int r;
	public Circle (int _x, int _y, int _r ) {
		this.x = _x;
		this.y = _y;
		this.r = _r;
	}
	
	public static void checkOverlap(Circle c1, Circle c2) {
		int distanceSq;
		int radiusSq;
		
		distanceSq = (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
		radiusSq = (c1.r - c2.r) * (c1.r - c2.r);
		if (distanceSq < radiusSq)
			System.out.println("두 원은 서로 겹치지 않는.");
		else
			System.out.println("두 원은 서로 겹친다.");
	}
}

public class Hw1_1 {
	
	public static void main(String args[]) {
	
		Scanner scanner = new Scanner(System.in);
		int arr1[] = new int[3];
		int arr2[] = new int[3];
	
		System.out.print("첫번째 원의 중심과 반지름 입력>>");
		for (int i = 0; i < 3; i++) {
			arr1[i] = scanner.nextInt();
		}
		System.out.print("두번째 원의 중심과 반지름 입력>>");
		for (int i = 0; i < 3; i++) {
			arr2[i] = scanner.nextInt();
		}
		scanner.close();
	
		Circle c1 = new Circle(arr1[0], arr1[1], arr1[2]);
		Circle c2 = new Circle(arr2[0], arr2[1], arr2[2]);
		Circle.checkOverlap(c1, c2);
	}
}
