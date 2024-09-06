package com.board;

import com.glassis.util.Ci;
import com.glassis.util.Cw;
import com.glassis.util.Db;
import com.glassis.util.Disp;

public class ProcList {

	// 한 페이지에 표시할 글 개수
	static public final int PER_PAGE = 3;
	// 현재 페이지의 시작 인덱스 (데이터베이스에서 가져올 시작 지점)
	static int startIndex = 0;
	// 현재 페이지 번호
	static int currentPage = 1;
	// 검색 모드인지 여부를 나타내는 변수
	static boolean isSearchMode = false;
	// 총 페이지 수를 저장하는 변수
	static int totalPage = 0;
	// 게시물의 총 개수를 저장하는 변수
	static int count = 0;
	// 사용자가 입력한 명령어를 저장하는 변수
	static String cmd = "";

	// 글 목록을 출력하는 메서드
	static public void run() {

		// 전체 게시물 수를 가져옴
		count = Db.getPostCount();

		// 총 페이지 수 계산 (게시물 수를 페이지당 글 개수로 나누어 계산)
		if (count % PER_PAGE > 0) {
			totalPage = count / PER_PAGE + 1; // 나머지가 있으면 페이지 하나를 추가
		} else {
			totalPage = count / PER_PAGE; // 정확히 나눠떨어지면 그대로
		}

		// 총 페이지 수를 출력
		Cw.wn("총 페이지 수:" + totalPage);

		// 글 목록을 계속해서 출력하는 루프
		while (true) {

			// 사용자로부터 페이지 번호를 입력받음
			cmd = Ci.r("페이지 번호입력[이전 메뉴로:x]");

			// 'x'를 입력하면 루프 종료
			if (cmd.equals("x")) {
				break; // 이전 메뉴로 돌아가기 위해 종료
			}

			// 현재 페이지의 시작 인덱스 계산 (데이터베이스 쿼리에서 사용)
			startIndex = (currentPage - 1) * PER_PAGE;

			// 글 목록의 헤더를 출력
			Disp.titleList();

			// SQL 쿼리 작성 (해당 페이지의 게시물 목록을 가져옴)
			String sql = "select * from board where b_reply_ori is null limit " + startIndex + "," + PER_PAGE;
			try {

				// 실행할 SQL 쿼리를 출력
				Cw.wn("전송한 sql문:" + sql);

				// SQL 쿼리 실행
				Db.result = Db.st.executeQuery(sql);

				// 쿼리 결과를 한 행씩 처리하며 출력
				while (Db.result.next()) {

					String no = Db.result.getString("b_no"); // 글 번호
					String title = Db.result.getString("b_title"); // 글 제목
					String id = Db.result.getString("b_id"); // 작성자 ID
					String datetime = Db.result.getString("b_datetime"); // 작성 시간
					Cw.wn(no + " " + title + " " + id + " " + datetime); // 게시물 정보 출력
				}

			} catch (Exception e) {
				// 예외 발생 시 아무 처리하지 않음
			}
		}

	}

	// 검색 기능을 실행하는 메서드
	static public void search() {

		// 사용자로부터 검색어를 입력받음
		cmd = Ci.rl("검색어[x:나가기]");

		// 'x'를 입력하면 검색 모드를 종료
		if (cmd.equals("x")) {
			return; // 메서드 종료
		} else {
			// 검색어가 입력된 경우, 검색 목록을 출력하는 메서드를 호출
			searchList(cmd);
		}
	}

	// 검색된 게시물 목록을 출력하는 메서드
	static public void searchList(String searchWord) {

		// 검색어를 기준으로 게시물 수를 가져옴
		count = Db.getPostCountSearch(searchWord);

		// 총 페이지 수 계산
		if (count % PER_PAGE > 0) {
			totalPage = count / PER_PAGE + 1; // 나머지가 있으면 페이지 하나 추가
		} else {
			totalPage = count / PER_PAGE; // 정확히 나눠떨어지면 그대로
		}

		// 검색 모드에서의 총 페이지 수를 출력
		Cw.wn("총 페이지 수<검색모드>:" + totalPage);

		// 검색된 게시물 목록을 계속해서 출력하는 루프
		while (true) {

			// 사용자로부터 페이지 번호를 입력받음
			cmd = Ci.r("페이지번호입력<검색모드>[이전메뉴로:x]");

			// 'x'를 입력하면 루프 종료
			if (cmd.equals("x")) {
				break; // 검색 모드 종료
			}

			// 입력된 페이지 번호를 정수로 변환
			currentPage = Integer.parseInt(cmd);

			// 페이지 범위가 유효하지 않을 경우 다시 입력하도록 유도
			if (currentPage > totalPage || currentPage < 1) {
				Cw.wn("페이지 범위에 맞는 값을 넣어주세요");
				continue;
			}

			// 현재 페이지의 시작 인덱스 계산
			startIndex = (currentPage - 1) * PER_PAGE;

			// 글 목록의 헤더를 출력
			Disp.titleList();

			// SQL 쿼리 작성 (검색어가 포함된 게시물 목록을 해당 페이지에 맞게 가져옴)
			String sql = "select * from board where b_reply_ori is null and b_title like '%" + searchWord + "%' "
					+ "limit " + startIndex + "," + PER_PAGE;

			try {

				// 실행할 SQL 쿼리를 출력
				Cw.wn("전송한 sql문:" + sql);

				// SQL 쿼리 실행
				Db.result = Db.st.executeQuery(sql);

				// 쿼리 결과를 한 행씩 처리하며 출력
				while (Db.result.next()) {

					String no = Db.result.getString("b_no"); // 글 번호
					String title = Db.result.getString("b_title"); // 글 제목
					String id = Db.result.getString("b_id"); // 작성자 ID
					String datetime = Db.result.getString("b_datetime"); // 작성 시간
					Cw.wn(no + " " + title + " " + id + " " + datetime); // 검색된 게시물 정보 출력
				}

			} catch (Exception e) {
				// 예외 발생 시 아무 처리하지 않음
			}

		}
	}

}