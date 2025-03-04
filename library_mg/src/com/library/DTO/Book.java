package com.library.DTO;

import java.util.Date;

public class Book {
	private int bookNum;
	private String bookTitle;
	private String bookAuthor;
	private String publisher;
	private int bookPYear;
	private String bookCategory;
	private int bookRank;
	private int bookVolmCnt;
	private Date bookRegDate;
	
	public Book(String bookTitle, String bookAuthor, String publisher, int bookPYear, String bookCategory,	int bookRank, int bookVolmCnt) {
		super();
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.publisher = publisher;
		this.bookPYear = bookPYear;
		this.bookCategory = bookCategory;
		this.bookRank = bookRank;
		this.bookVolmCnt = bookVolmCnt;
	}
	
	public int getBookNum() {
		return bookNum;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getBookPYear() {
		return bookPYear;
	}
	public void setBookPYear(int bookPYear) {
		this.bookPYear = bookPYear;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public int getBookRank() {
		return bookRank;
	}
	public void setBookRank(int bookRank) {
		this.bookRank = bookRank;
	}
	public int getBookVolmCnt() {
		return bookVolmCnt;
	}
	public void setBookVolmCnt(int bookVolmCnt) {
		this.bookVolmCnt = bookVolmCnt;
	}
	public Date getBookRegDate() {
		return bookRegDate;
	}
	public void setBookRegDate(Date bookRegDate) {
		this.bookRegDate = bookRegDate;
	}
	
	
	
	
	
}
