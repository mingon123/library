package com.library.DTO;

import java.util.Date;

public class Notice {
	private int noticeNum;
	private String noticeTitle;
	private String noticeContent;
	private int noticeView;
	private Date noticeRegDate;
	
	public Notice() {}

	public Notice(int noticeNum, String noticeTitle, Date noticeRegDate, int noticeView) {
		super();
		this.noticeNum = noticeNum;
		this.noticeTitle = noticeTitle;
		this.noticeRegDate = noticeRegDate;
		this.noticeView = noticeView;
	}

	public Notice(int noticeNum, String noticeTitle, String noticeContent, int noticeView, Date noticeRegDate) {
		super();
		this.noticeNum = noticeNum;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeView = noticeView;
		this.noticeRegDate = noticeRegDate;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeView() {
		return noticeView;
	}

	public void setNoticeView(int noticeView) {
		this.noticeView = noticeView;
	}

	public Date getNoticeRegDate() {
		return noticeRegDate;
	}

	public void setNoticeRegDate(Date noticeRegDate) {
		this.noticeRegDate = noticeRegDate;
	}
	
	
}
