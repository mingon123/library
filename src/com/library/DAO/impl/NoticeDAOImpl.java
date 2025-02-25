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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice ORDER BY notice_num DESC";
		List<Notice> noticeList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice(
						rs.getInt("notice_num"),
						rs.getString("notice_title"),
						rs.getDate("notice_reg_date"),
						rs.getInt("notice_view")
						);
				noticeList.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return noticeList;
	}

	@Override
	public int checkNoticeRecord(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE notice_num=?";
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) count = 1;
		} catch (Exception e) {
			count = -1;
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	@Override
	public Notice selectDetailNotice(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE notice_num=?";
		Notice notice = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new Notice(
						rs.getInt("notice_num"),
						rs.getString("notice_title"),
						rs.getString("notice_content"),
						rs.getInt("notice_view"),
						rs.getDate("notice_reg_date")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return notice;
	}

	@Override
	public void updateNoticeViewCount(int noticeNum) {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = "UPDATE NOTICE SET NOTICE_VIEW = NOTICE_VIEW + 1 WHERE NOTICE_NUM = ?";
	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, noticeNum);
	        int count = pstmt.executeUpdate();

	        if (count <= 0) {
	            System.out.println("조회수 업데이트 중 오류발생!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.executeClose(null, pstmt, conn);
	    }
	}
	
}
