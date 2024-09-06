package com.glassis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Db {

	static private String DB_NAME = "my_cat";
	static private String DB_ID = "root";
	static private String DB_PW = "root";

	static public Statement st = null;
	static public Connection con = null;
	static public ResultSet result = null;

	static public void dbInit() {

		try {

			Db.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			Db.st = Db.con.createStatement();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	static public void dbExecuteUpdate(String query) {

		Cw.wn("전송할 spl" + query);
		try {

			int resultCount = st.executeUpdate(query);
			Cw.wn("처리된 행수:" + resultCount);

		} catch (Exception e) {

		}
	}

	static public int getPostCount() {

		String count = "";
		try {

			Db.result = Db.st.executeQuery("select count(*) from board where b_reply_ori is null");
			Db.result.next();
			count = Db.result.getString("count(*)");

		} catch (Exception e) {

			e.printStackTrace();
		}
		int intCount = Integer.parseInt(count);
		return intCount;

	}

	static public int getPostCountSearch(String searchWord) {

		String count = "";
		try {
			// 검색어가 포함된 게시물의 수를 조회하는 쿼리 실행
			Db.result = Db.st.executeQuery(
					"select count(*) from board where b_reply_ori is null" + "and b_title like '%" + searchWord + "%'");
			Db.result.next(); // 결과의 첫 번째 행으로 이동
			count = Db.result.getString("count(*)"); // 검색 결과 게시물 수를 가져옴
			Cw.wn("글 수:" + count); // 게시물 수 출력

		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}

		int intCount = Integer.parseInt(count); // 게시물 수를 정수로 변환
		return intCount; // 게시물 수 반환
	}

}
