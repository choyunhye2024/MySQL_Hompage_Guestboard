package com.board;

import java.util.Scanner;

public class ProcBoard {

	static public void run() {

		loop: while (true) {

			System.out.println("1.리스트 / 2. 게시물읽기 / 3.게시물쓰기 / 4.게시물삭제 /e.나가기");
			Scanner sc = new Scanner(System.in);
			String cmd = sc.next();

			switch (cmd) {
			case "1":
				// 리스트
				ProcList.run();
				break;
			case "2":
				// 게시문 읽기
				ProcRead.run();
				break;

			case "3":
				// 게시물쓰기
				ProcWrite.run();

				break;

			case "4":
				// 게시물 삭제
				ProcDel.run();
				break;
			case "e":
				// 나가기
				break loop;

			}

		}

	}

}
