package com.board;

import com.glassis.util.Ci;
import com.glassis.util.Db;

public class ProcDel {

	// 글 삭제 기능을 수행하는 메서드
	static public void run() {

		// 삭제할 글 번호를 입력받음
		String delNo = Ci.r("삭제할 글 번호를 입력해주세요:");

		// 입력된 글 번호에 해당하는 글을 데이터베이스에서 삭제하는 SQL 쿼리를 실행
		Db.dbExecuteUpdate("delete from board where b_no=" + delNo);

	}

}
