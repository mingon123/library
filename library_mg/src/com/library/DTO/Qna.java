package com.library.DTO;

import java.util.Date;

public class Qna {
	private int qnaNum;
	private String qnaTitle;
	private String qnaContent;
	private int qnaView;
	private String qnaRe;
	private Date qDate;
	private Date aDate;
	private String memId;
	
	public Qna(String qnaTitle, String qnaContent, String memId) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.memId = memId;
		this.qnaView = 0;
	}

	public int getQnaNum() {
		return qnaNum;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public int getQnaView() {
		return qnaView;
	}

	public void setQnaView(int qnaView) {
		this.qnaView = qnaView;
	}

	public String getQnaRe() {
		return qnaRe;
	}

	public void setQnaRe(String qnaRe) {
		this.qnaRe = qnaRe;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	public Date getaDate() {
		return aDate;
	}

	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	
}
