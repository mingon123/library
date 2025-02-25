package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.library.DAO.NoticeDAO;
import com.library.DAO.impl.NoticeDAOImpl;
import com.library.DTO.Notice;
import com.library.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{
	private NoticeDAO noticeDAO;
	private BufferedReader br;

	public NoticeServiceImpl() {
		this.noticeDAO = new NoticeDAOImpl();
	}

	public NoticeServiceImpl(NoticeDAO noticeDAO, BufferedReader br) {
		super();
		this.noticeDAO = noticeDAO;
		this.br = br;
	}

	@Override
	public List<Notice> getNoticeList() {
		return noticeDAO.selectNotice();
	}

	@Override
	public boolean checkNoticeRecord(int num) {
		return noticeDAO.checkNoticeRecord(num)>0;
	}

	@Override
	public Notice selectDetailNotice(int num) {
		return noticeDAO.selectDetailNotice(num);
	}

	@Override
	public void updateNoticeViewCount(int num) {
		NoticeDAO noticeDAO = new NoticeDAOImpl();
		noticeDAO.updateNoticeViewCount(num);
	}

	@Override
	public void showNotices() {
		List<Notice> notices = noticeDAO.selectNotice();
		if (notices.isEmpty()) {
			System.out.println("공지사항이 없습니다.");
		} else {
			for (Notice notice : notices) {
				System.out.println("번호: " + notice.getNoticeNum() + ", 제목: " + notice.getNoticeTitle());
			}
		}
		int num = -1; 
		boolean flag = false;
		do {
			try {
				if(flag) {
					System.out.print("\n존재하지 않는 글 번호 입니다.\n다시 ");
				}
				System.out.print("조회하실 글의 번호 입력 : ");
				num = Integer.parseInt(br.readLine());
				flag = true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!checkNoticeRecord(num));

		System.out.println("-".repeat(90));
		updateNoticeViewCount(num); // 상세정보 보면서 조회수 증가 위해 먼저 
		Notice noticeDetail = selectDetailNotice(num);
		if (noticeDetail != null) {
			System.out.println("상세 공지사항");
			System.out.println("제목: " + noticeDetail.getNoticeTitle());
			System.out.println("내용: " + noticeDetail.getNoticeContent());
			System.out.println("조회수: " + noticeDetail.getNoticeView());
			System.out.println("등록일: " + noticeDetail.getNoticeRegDate());
		} else {
			System.out.println("상세 공지사항을 찾을 수 없습니다.");
		}
		System.out.println("-".repeat(90));
	}


}
