package com.library.DAO;

import java.util.List;

import com.library.DTO.Notice;

public interface NoticeDAO {
	List<Notice> selectNotice();
	int checkNoticeRecord(int num);
    Notice selectDetailNotice(int num);
	void updateNoticeViewCount(int noticeNum);
	
	void InsertNoticeAdmin(String noticeTitle, String noticeContent);
	void updateNoticeAdmin(int noticeNum, String noticeTitle, String noticeContent);
	void deleteNoticeAdmin(int noticeNum);
}
