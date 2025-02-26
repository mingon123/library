package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.DAO.QnaDAO;

import util.DBUtil;

public class QnaDAOImpl implements QnaDAO{

	public QnaDAOImpl() {} 
	
	// Q&A 질문 등록
	public void insertQNA(String qnaTitle, String qnaContent,String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO qna (qna_num,qna_title,qna_content,q_date,mem_id) VALUES (qna_seq.nextval,?,?,SYSDATE,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, qnaTitle);
			pstmt.setString(++cnt, qnaContent);
			pstmt.setString(++cnt, memId);
			pstmt.executeUpdate();
			System.out.println("질문 등록 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertQNA
	
	// Q&A 목록보기
	public void selectQNAInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT qna_num,qna_title,qna_content,q_date FROM qna ORDER BY q_date DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			
			if(rs.next()) {
				System.out.println("Q&A 목록");
				System.out.println("번호\t문의일\t\t제목\t내용");
				do {
					System.out.print(rs.getInt("qna_num"));
					System.out.print("\t");
					System.out.print(rs.getDate("q_date"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_title"));
					System.out.print("\t");
					System.out.println(rs.getString("qna_content"));
				} while(rs.next());
			} else {
				System.out.println("Q&A 내역이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectQNAInfo
	
	// 내 qna 목록보기
	public boolean selectMyQNAInfo(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean hasQNA = false;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT qna_num,qna_title,q_date,qna_re,a_date FROM qna WHERE mem_id=? ORDER BY q_date DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				hasQNA = true;
				System.out.println("번호\t제목\t등록일\t\t답변\t답변일");
				do {
					System.out.print(rs.getInt("qna_num"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_title"));
					System.out.print("\t");
					System.out.print(rs.getDate("q_date"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_re"));
					System.out.print("\t");
					System.out.println(rs.getDate("a_date"));
				} while(rs.next());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return hasQNA;
	} // selectMyQNAInfo
	
	// qna삭제
	public void deleteQNAInfo(String memId, int QNANum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try { 
			conn = DBUtil.getConnection();
			sql = "DELETE FROM qna WHERE mem_id=? AND qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setInt(2, QNANum);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("QNA를 삭제했습니다.");
			else System.out.println("QNA를 삭제할 수 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteQNA	
	
	// qna 레코드 확인
	public int checkQnaRecordNumId(int qnaNum, String memId){
	    String sql = "SELECT COUNT(*) FROM qna WHERE qna_num = ? AND mem_id = ?";
	    try (Connection conn = DBUtil.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {      
	        pstmt.setInt(1, qnaNum);
	        pstmt.setString(2, memId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1);  // 1이면 존재, 0이면 없음
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    return -1;  // 예외 처리
	}

}
