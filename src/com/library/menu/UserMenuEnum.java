package com.library.menu;

public enum UserMenuEnum {
    USER_NOTIFICATION(1, "사용자 알림"),
    BOOK_LIST(2, "도서 목록"),
    BOOK_SEARCH(3, "도서 검색"),
    REVIEW(4, "리뷰 화면"),
    RESERVATION(5, "대여/예약"),
    RETURN(6, "반납 화면"),
    OTHER_FUNCTIONS(7, "기타기능"),
    LOGOUT(8, "로그아웃"),
    EXIT(9, "종료");
	
	private final int number;
	private final String title;
	
	private UserMenuEnum(int number, String title) {
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
