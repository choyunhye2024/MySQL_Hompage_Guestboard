package com.hompy.guest;

import java.util.Scanner;

public class ProcGuest {
	public static void run() {

		xx: while (true) {
			System.out.println("1.방명록 작성 / 2.방명록 읽기 / 3. 방명록 삭제 / e.뒤로가기");
			Scanner sc = new Scanner(System.in);
			String cmd = sc.next();

			switch (cmd) {

			case "1":
				GuestWrite.run();
				break;
			case "2":
				GuestRead.run();
			case "3":
				// 방명록 삭제
				GuestDel.run();
				break;
			case "e":
				break xx;

			}

		}

	}

}
