package com.hompy.guest;

import com.glassis.util.Ci;
import com.glassis.util.Db;

public class GuestDel {

	public static void run() {

		String delNo = Ci.r("삭제하실 방명록 번호를 입력해주세요");
		Db.dbExecuteUpdate("delete from guest where g_no=" + delNo);
	}

}
