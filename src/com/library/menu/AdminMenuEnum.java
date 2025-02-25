package com.library.menu;

public enum AdminMenuEnum {
	MEMBER_MANAGE(1, "회원 관리"),
	BOOK_MANAGE(2, "도서 관리"),
	ORDER_MANAGE(3, "대여 관리"),
	RESERVATION_MANAGE(4, "예약 관리"),
	WISHBOOK_MANAGE(5, "희망도서 관리"),
	REVIEW_MANAGE(6, "리뷰 관리"),
	NOTICE_MANAGE(7, "공지사항 관리"),
	QNA_MANAGE(8, "Q&A 관리"),
	STATISTICS(9, "통계"),
	EXIT(10,"종료");
	
	private final int number;
	private final String title;
	
	private AdminMenuEnum(int number, String title) {
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
