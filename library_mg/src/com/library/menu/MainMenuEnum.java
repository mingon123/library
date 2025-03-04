package com.library.menu;

public enum MainMenuEnum {
	BOOK_LIST(1, "도서 목록"),
	BOOK_SEARCH(2, "도서 검색"),
	NOTICE(3, "공지사항 확인"),
	SIGNUP(4, "회원가입"),
	LOGIN(5, "로그인"),
	EXIT(6, "종료");
	
	private final int number;
	private final String title;
	
	private MainMenuEnum(int number, String title) {
		this.number = number;
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public String getTitle() {
		return title;
	}
	
	
}
