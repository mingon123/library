package com.library.model;

import java.util.Date;

public class Member {
    private String memId;
    private String memPw;
    private String memName;
    private String memCell;
    private String memEmail;
    private Date memDate;
    private Date memMdate;
    private Date memStopDate;
    
	public Member(String memId, String memPw, String memName, String memCell, String memEmail) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.memCell = memCell;
		this.memEmail = memEmail;
		this.memDate = new Date();
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemCell() {
		return memCell;
	}

	public void setMemCell(String memCell) {
		this.memCell = memCell;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Date getMemDate() {
		return memDate;
	}

	public void setMemDate(Date memDate) {
		this.memDate = memDate;
	}

	public Date getMemMdate() {
		return memMdate;
	}

	public void setMemMdate(Date memMdate) {
		this.memMdate = memMdate;
	}

	public Date getMemStopDate() {
		return memStopDate;
	}

	public void setMemStopDate(Date memStopDate) {
		this.memStopDate = memStopDate;
	}
	
}
    
    
   
