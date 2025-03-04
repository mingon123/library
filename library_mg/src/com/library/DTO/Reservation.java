package com.library.DTO;

public class Reservation {
	private int reNum;
	private String memId;
	private int bookNum;
	
	public Reservation(String memId, int bookNum) {
		super();
		this.memId = memId;
		this.bookNum = bookNum;
	}

	public int getReNum() {
		return reNum;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	
	
	
}
