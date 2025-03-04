package com.library.service;

import java.util.List;

import com.library.DTO.Notice;

public interface NoticeService {
    List<Notice> getNoticeList(); // 공지 목록 조회
    boolean checkNoticeRecord(int num); // 공지 레코드 존재 여부 체크
    Notice selectDetailNotice(int num); // 공지 상세보기
    void updateNoticeViewCount(int num);
    void showNotices();
}
