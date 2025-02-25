package com.library.DTO;

import java.util.Calendar;
import java.util.Date;

public class BookOrder {
	private int orderNum;
	private String memId;
	private int bookNum;
	private Date orderDate;
	private Date returnDate;
	private int isAdd;
	private int isReturn;
	
	public BookOrder(String memId, int bookNum) {
		super();
		this.memId = memId;
		this.bookNum = bookNum;
		this.orderDate = new Date();
		this.returnDate = calculateReturnDate(this.orderDate, 14);
		this.isAdd = 0;
		this.isReturn = 0;
	}
	
	// 반납 기한 자동 계산
	private Date calculateReturnDate(Date baseDate, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(baseDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	// 연장 시 반납일 변경
	public boolean extendReturnDate() {
		if(this.isAdd==0) {
			this.returnDate = calculateReturnDate(this.returnDate, 7);
			this.isAdd = 1;
			return true;
		}
		return false;
	}
	// 반납 처리
	public void returnBook() {
		this.isReturn = 1;
	}
	
	public int getOrderNum() {
		return orderNum;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}

	public int getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}
	
	
	
}
