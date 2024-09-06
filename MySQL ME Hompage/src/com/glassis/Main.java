package com.glassis;

import com.board.ProcBoard;
import com.glassis.util.Ci;
import com.glassis.util.Db;
import com.glassis.util.Disp;
import com.hompy.guest.ProcGuest;

public class Main {

	public static void main(String[] args) {

		// 게시판 타이틀 출력
		Disp.title();

		// 데이터베이스 초기화
		Db.dbInit();

		// 게시물 수를 출력
		Db.getPostCount();

		loop: while (true) {

			// 게시물 수를 다시 출력 (메뉴를 볼 때마다 게시물 수 갱신)
			Db.getPostCount();

			String cmd = Ci.r("1.자유게시판 / 2.방명록 / e.프로그램 종료");
			switch (cmd) {

			case "1":
				// 자유게시판
				ProcBoard.run();
				break;
			case "2":
				// 방명록
				ProcGuest.run();
				break;
			case "e":
				// 프로그램 종료
				break loop;
			}

		}
	}

}
