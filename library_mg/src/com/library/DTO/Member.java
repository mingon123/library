package com.library.DTO;

import java.util.Calendar;
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
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
		setMemMdate(); // 수정되면 수정일 갱신
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
		setMemMdate();
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
		setMemMdate();
	}

	public Date getMemDate() {
		return memDate;
	}

	public Date getMemMdate() {
		return memMdate;
	}

	public void setMemMdate() {
		this.memMdate = new Date();
	}

	public Date getMemStopDate() {
		return memStopDate;
	}

	public void setMemStopDate(Date memStopDate, int overDays) {
		this.memStopDate = memStopDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(memStopDate);
        cal.add(Calendar.DAY_OF_MONTH, overDays);
        this.memStopDate = cal.getTime();
	}
	
}
    
    
   
