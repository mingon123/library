package com.library.DTO;

import java.util.Date;

public class Review {
	private int reviewNum;
	private int bookNum;
	private String reviewContent;
	private int reviewRate;
	private Date reviewRegDate;
	private String memId;
	
	public Review(int bookNum, String reviewContent, int reviewRate, String memId) {
		super();
		this.bookNum = bookNum;
		this.reviewContent = reviewContent;
		this.reviewRate = reviewRate;
		this.memId = memId;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewRate() {
		return reviewRate;
	}

	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}

	public Date getReviewRegDate() {
		return reviewRegDate;
	}

	public void setReviewRegDate(Date reviewRegDate) {
		this.reviewRegDate = reviewRegDate;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	
}
