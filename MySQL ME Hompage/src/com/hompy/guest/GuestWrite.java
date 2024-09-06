package com.hompy.guest;

import com.glassis.util.Ci;
import com.glassis.util.Cw;
import com.glassis.util.Db;

public class GuestWrite {

	public static void run() {

		String content = Ci.rl("방명록 내용을 입력해주세요:");
		String id = Ci.rl("작성자 아이디를 입력해주세요");

		try {
			// 입력받은 제목, 작성자 아이디, 글 내용을 데이터베이스에 저장하는 SQL 쿼리 실행
			// 작성 시간은 현재 시간(now()), 조회수(b_hit)는 0으로 설정
			Db.st.executeUpdate("insert into guest (g_id,g_datetime,g_board,g_hit)" + "values('" + id + "',now(),'"
					+ content + "',0)");

			// 글 등록이 완료되었다는 메시지 출력
			Cw.wn("글 등록 완료");

		} catch (Exception e) {
			// 예외 처리 (현재는 아무 처리도 하지 않음)
		}

	}

}
