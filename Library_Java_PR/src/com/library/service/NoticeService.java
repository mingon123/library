package com.library.service;

import java.util.List;

import com.library.DTO.Notice;

public interface NoticeService {
    List<Notice> getNoticeList();
    boolean checkNoticeRecord(int num);
    Notice selectDetailNotice(int num);
    void updateNoticeViewCount(int num);
    void showNotices();
}
