package com.hompy.guest;

import com.glassis.util.Ci;
import com.glassis.util.Cw;
import com.glassis.util.Db;

public class GuestRead {

	// 글 읽기 기능을 수행하는 메서드
	static public void run() {

		// 사용자가 읽을 글 번호를 입력받음
		String readNo3 = Ci.r("읽으실 방명록 번호를 선택해주세요:");

		try {
			// 입력된 글 번호에 해당하는 게시글을 데이터베이스에서 조회하는 SQL 쿼리 실행
			String sql = "select * from guest where g_no=" + readNo3;
			System.out.println(sql);
			Db.result = Db.st.executeQuery(sql);

			// 쿼리 결과의 첫 번째 행으로 이동 (해당 글이 존재하는지 확인)

			// 방명록의 글쓴이와 게시글을 불러옴
			while (Db.result.next()) {
				String user = Db.result.getString("g_id");
				String content = Db.result.getString("g_board");

				// 게시글 제목과 내용을 출력
				Cw.wn("글제목:" + user);
				Cw.wn("글내용:" + content);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
