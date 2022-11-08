package assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

class Reservation {
	Scanner scan = new Scanner(System.in);
	String[][] seats = new String[3][10];
	
	Reservation() {
		System.out.println("명품콘서트홀 예약 시스템입니다.");
	}
	
	public void command() throws InputMismatchException{
		int cmd;

		System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
		cmd = scan.nextInt();
		switch(cmd) {
		case 1:
			reservate();
			break;
		case 2:
			printSeat(0);
			break;
		case 3:
			cancelReservation();
			break;
		case 4:
			scan.close();
			System.exit(0);
			break;
		default:
			System.out.println("없는 메뉴입니다. 다시 시도해주세요.");
			command();
		}
	}
	
	public void reservate() throws InputMismatchException{
		int seatNum;
		String	name;
		int		number;
		
		while (true) {
			System.out.print("좌석구분 S(1), A(2), B(3)>>");
			seatNum = scan.nextInt();
			if (seatNum < 1 || seatNum > 3) {
				System.out.println("없는 좌석구분입니다. 다시 시도 해주세요");
				continue;
			}
			break;
		}
		printSeat(seatNum);
		System.out.print("이름>>");
		name = scan.next();
		
		while (true) {
			System.out.print("번호>>");
			number = scan.nextInt();
			if (number < 1 || number > 10) {
				System.out.println("없는 번호입니다. 다시 시도 해주세요");
				continue;
			}
			break;
		}
		seats[seatNum - 1][number - 1] = name;
	}
	
	public void printSeat(int seatNum) {
		if (seatNum == 0) {
			printSeat(1);
			printSeat(2);
			printSeat(3);
			System.out.println("<<<조회를 완료하였습니다.>>>");
			return;
		}
		switch (seatNum) {
		case 1: 
			System.out.print("S>>");
			break;
		case 2:
			System.out.print("A>>");
			break;
		case 3:
			System.out.print("B>>");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + seatNum);
		}
		for(int i = 0; i < 10 ; i++) {
			if (seats[seatNum - 1][i] == null)
				System.out.print(" ---");
			else
				System.out.print(" " + seats[seatNum - 1][i]);
		}
		System.out.println();
	}
	
	public void cancelReservation() throws InputMismatchException{
		String	name;
		int		seatNum;
		
		while (true) {
			System.out.print("좌석 S:1, A:2, B:3>>");
			seatNum = scan.nextInt();
			if (seatNum < 1 || seatNum > 3)
				System.out.println("없는 좌석입니다. 다시 시도해주세요.\n(첫 화면으로 돌아가시려면 \'끝\'을 입력해주세요.");
			else
				break;
		}
		
		while (true) {
			printSeat(seatNum);
			System.out.print("이름>>");
			name = scan.next();
			if (name.equals("끝"))
				break;
			for (int i = 0; i < 10; i++) {
				if(seats[seatNum - 1][i] != null && seats[seatNum - 1][i].equals(name)) {
					seats[seatNum - 1][i] = null;
					return;
				}
			}
			System.out.println("없는 이름입니다. 다시 시도해주세요.\n(첫 화면으로 돌아가시려면 \'끝\'을 입력해주세요.");
		}
		
		
	}
}

public class Hw2_1 {
	
	public static void main(String args[]) {
		Reservation reservation = new Reservation();
		while (true) {
			try {
				reservation.command();
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 첫 화면으로 돌아갑니다.");
				reservation.scan.close();
				reservation.scan = new Scanner(System.in);
			} catch (Exception e) {
				System.out.println("Exception Occur: " + e);
				System.exit(1);
			}
		}
			
	}
}
