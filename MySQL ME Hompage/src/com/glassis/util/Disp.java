package com.glassis.util;

public class Disp {

	static private String TITLE = "즐거운 하루를 보내자";
	static private String FEAT = "CHO YUN HYE";

	public static void title() {

		Cw.line();
		Cw.dot();
		Cw.space(20);
		Cw.w(TITLE);
		Cw.w(FEAT);
		Cw.space(20);
		Cw.dot();
		Cw.wn();
		Cw.line();

	}

	static public void titleList() {

		Cw.wn("======================================");
		Cw.wn("================글 리스트=================");
		Cw.wn("========================================");

		Cw.wn("글 번호/ 글 제목 / 작성자 아이디 / 작성시간");
	}

	public static void replyBar() {

		Cw.wn("==============댓글리스트===============");

	}

	public static void guestList() {

		Cw.wn("==============방명록==================");
		Cw.wn(" 방명록 아이디 / 방명록 내용 / 방명록 작성간");

	}

}
