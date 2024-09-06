package com.board;

import com.glassis.util.Ci;
import com.glassis.util.Cw;
import com.glassis.util.Db;

public class ProcRead {

	// 글 읽기 기능을 수행하는 메서드
	static public void run() {

		// 사용자가 읽을 글 번호를 입력받음
		String readNo = Ci.r("읽으실 글 번호를 입력해주세요:");

		try {
			// 입력된 글 번호에 해당하는 게시글을 데이터베이스에서 조회하는 SQL 쿼리 실행
			Db.result = Db.st.executeQuery("select * from board where b_no=" + readNo);

			// 쿼리 결과의 첫 번째 행으로 이동 (해당 글이 존재하는지 확인)
			Db.result.next();

			// 게시글의 제목과 내용을 가져옴
			String title = Db.result.getString("b_title");
			String content = Db.result.getString("b_text");

			// 게시글 제목과 내용을 출력
			Cw.wn("글제목:" + title);
			Cw.wn("글내용:" + content);

			// 해당 게시글에 대한 댓글 목록을 출력
			ProcReply.list(Integer.parseInt(readNo));

			// 무한 루프를 사용하여 사용자의 명령을 처리
			loop: while (true) {

				// 사용자로부터 명령을 입력받음
				String cmd = Ci.r("명령[x:나가기 r:글쓰기]");

				// 입력된 명령어에 따라 처리
				switch (cmd) {

				case "x": // 'x'를 입력하면 루프를 빠져나가며 종료
					break loop;

				case "r": // 'r'을 입력하면 해당 글에 댓글 작성 기능을 호출
					ProcReply.write(Integer.parseInt(readNo));
					break;

				default: // 잘못된 명령을 입력한 경우 경고 메시지 출력
					Cw.wn("장난하지 마세요");
				}
			}
		} catch (Exception e) {
			// 예외 발생 시 처리하지 않고 넘어감 (개선 필요)
		}
	}

}
