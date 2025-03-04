package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.DAO.NoticeDAO;
import com.library.DTO.Notice;

import util.DBUtil;

public class NoticeDAOImpl implements NoticeDAO {
	private NoticeDAO noticeDAO;
	
	public NoticeDAOImpl() {}
	
	public NoticeDAOImpl(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public List<Notice> selectNotice() {
		String sql = "SELECT * FROM notice ORDER BY notice_num DESC";
		List<Notice> noticeList = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
 			 ResultSet rs = pstmt.executeQuery();){
			while (rs.next()) {
				Notice notice = new Notice(
						rs.getInt("notice_num"),
						rs.getString("notice_title"),
						rs.getDate("notice_reg_date"),
						rs.getInt("notice_view")
						);
				noticeList.add(notice);
			}
		} catch (Exception e) {e.printStackTrace();}
		return noticeList;
	}

	@Override
	public int checkNoticeRecord(int num) {
		String sql = "SELECT * FROM notice WHERE notice_num=?";
		int count = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) count = 1;
			}
		} catch (NumberFormatException e) {count = -1;
		} catch (Exception e) {count = -1;}
		return count;
	}

	@Override
	public Notice selectDetailNotice(int num) {
		String sql = "SELECT * FROM notice WHERE notice_num=?";
		Notice notice = null;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					notice = new Notice(
							rs.getInt("notice_num"),
							rs.getString("notice_title"),
							rs.getString("notice_content"),
							rs.getInt("notice_view"),
							rs.getDate("notice_reg_date")
							);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return notice;
	}

	@Override
	public void updateNoticeViewCount(int noticeNum) {
	    String sql = "UPDATE NOTICE SET NOTICE_VIEW = NOTICE_VIEW + 1 WHERE NOTICE_NUM = ?";
	    try (Connection conn = DBUtil.getConnection();
	    	 PreparedStatement pstmt = conn.prepareStatement(sql);){
	        pstmt.setInt(1, noticeNum);
	        int count = pstmt.executeUpdate();
	        if (count <= 0) System.out.println("조회수 업데이트 중 오류발생!");
	    } catch (Exception e) {e.printStackTrace();}
	}
	
	
	// admin
	// 공지사항 등록
	@Override
	public void InsertNoticeAdmin(String noticeTitle, String noticeContent) {
		String sql = "INSERT INTO notice (notice_num, notice_title, notice_content, notice_reg_date)"
				+ "VALUES (notice_seq.nextval,?,?,SYSDATE)"; 
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, noticeTitle);
			pstmt.setString(++cnt, noticeContent);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 공지사항을 등록했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // insertNotice()

	// 공지사항 수정
	@Override
	public void updateNoticeAdmin(int noticeNum, String noticeTitle, String noticeContent) {
		String sql = "UPDATE notice SET notice_title=?, notice_content=? WHERE notice_num=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, noticeTitle);
			pstmt.setString(++cnt, noticeContent);
			pstmt.setInt(++cnt, noticeNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 공지사항 정보를 수정했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateNotice()

	// 공지사항 삭제
	@Override
	public void deleteNoticeAdmin(int noticeNum) {
		String sql = "DELETE FROM notice WHERE notice_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, noticeNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 공지사항 정보를 삭제했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // deleteNoticeAdmin()

}
