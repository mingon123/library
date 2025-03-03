package com.library.DTO;

import java.util.Date;

public class wishBook {
	private int wishNum;
	private String wishTitle;
	private String wishAuthor;
	private String wishPublisher;
	private Date wishDate;
	private String memId;
	
	public wishBook(String wishTitle, String wishAuthor, String wishPublisher, String memId) {
		super();
		this.wishTitle = wishTitle;
		this.wishAuthor = wishAuthor;
		this.wishPublisher = wishPublisher;
		this.memId = memId;
	}

	public wishBook(int wishNum, String wishTitle, String wishAuthor, String wishPublisher, Date wishDate, String memId) {
		this.wishNum = wishNum;
		this.wishTitle = wishTitle;
		this.wishAuthor = wishAuthor;
		this.wishPublisher = wishPublisher;
		this.wishDate = wishDate;
		this.memId = memId;
	}


	public int getWishNum() {
		return wishNum;
	}

	public String getWishTitle() {
		return wishTitle;
	}

	public void setWishTitle(String wishTitle) {
		this.wishTitle = wishTitle;
	}

	public String getWishAuthor() {
		return wishAuthor;
	}

	public void setWishAuthor(String wishAuthor) {
		this.wishAuthor = wishAuthor;
	}

	public String getWishPublisher() {
		return wishPublisher;
	}

	public void setWishPublisher(String wishPublisher) {
		this.wishPublisher = wishPublisher;
	}

	public Date getWishDate() {
		return wishDate;
	}

	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	
	
	
}
