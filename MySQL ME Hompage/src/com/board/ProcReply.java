package com.board;

import com.glassis.util.Ci;
import com.glassis.util.Cw;
import com.glassis.util.Db;
import com.glassis.util.Disp;

public class ProcReply {

	// 특정 게시글 번호(oriNo)에 해당하는 댓글들을 출력하는 메서드
	static public void list(int oriNo) {

		// 댓글 입력 창 표시
		Disp.replyBar();

		// oriNo에 해당하는 댓글들을 조회하는 SQL 쿼리 생성
		String sql = "select * from board where b_reply_ori=" + oriNo;
		try {

			// 실행된 SQL 문을 콘솔에 출력
			Cw.wn("전송한 sql문:" + sql);

			// SQL 쿼리 실행 및 결과 가져오기
			Db.result = Db.st.executeQuery(sql);

			// 결과 집합에서 각 댓글의 텍스트를 가져와 출력
			while (Db.result.next()) {
				String b_reply_text = Db.result.getString("b_reply_text"); // 댓글 내용 가져오기
				Cw.wn(b_reply_text); // 댓글 내용 출력
			}

		} catch (Exception e) {
			// 예외 처리 (현재 아무 처리도 하지 않음)
		}

	}

	// 특정 게시글(b_reply_ori)에 댓글을 작성하는 메서드
	static public void write(int b_reply_ori) {

		// 사용자로부터 댓글 입력 받기
		String b_reply_text = Ci.rl("댓글입력");

		// 입력받은 댓글을 데이터베이스에 저장하는 SQL 쿼리 실행
		Db.dbExecuteUpdate("insert into board(b_id, b_datetime, b_reply_ori,b_reply_text) values ('댓글러',now(),"
				+ b_reply_ori + ",'" + b_reply_text + "')");

	}

}
