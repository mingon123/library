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
	@Override
	public void insertQNA(String qnaTitle, String qnaContent,String memId) {
		String sql = "INSERT INTO qna (qna_num,qna_title,qna_content,q_date,mem_id) VALUES (qna_seq.nextval,?,?,SYSDATE,?)";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, qnaTitle);
			pstmt.setString(++cnt, qnaContent);
			pstmt.setString(++cnt, memId);
			pstmt.executeUpdate();
			System.out.println("질문 등록 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // insertQNA
	
	// Q&A 목록보기
	@Override
	public void selectQNAInfo() {
		String sql = "SELECT qna_num,qna_title,qna_content,q_date,a_date,qna_re FROM qna ORDER BY q_date DESC";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("Q&A 목록");
				System.out.printf("번호\t문의일\t\t %-20s      %-20s   %s\t\t%-20s \n","제목","내용","답변일","답변내용");
				do {
					System.out.printf("%d\t%s\t %-20s   %-20s ",
							rs.getInt("qna_num"),
							rs.getDate("q_date"),
							rs.getString("qna_title"),
							rs.getString("qna_content"));
					if(rs.getString("qna_title").startsWith("RE:")) {
						System.out.print(rs.getDate("a_date")+"\t");
						System.out.println(rs.getString("qna_re"));
					} else System.out.println();
				} while(rs.next());
			} else {
				System.out.println("Q&A 내역이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // selectQNAInfo
	
	// 내 qna 목록보기
	@Override
	public boolean selectMyQNAInfo(String memId) {
		String sql = "SELECT qna_num,qna_title,q_date,qna_re,a_date FROM qna WHERE mem_id=? ORDER BY q_date DESC";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				System.out.println("-".repeat(90));
				if(rs.next()) {
					System.out.printf("번호\t등록일\t\t%-20s   답변일\t\t답변 \n","제목");
					do {
						System.out.printf("%d\t%s\t%-20s ",
								rs.getInt("qna_num"),
								rs.getDate("q_date"),
								rs.getString("qna_title"));
						if(rs.getString("qna_title").startsWith("RE:")) {
							System.out.print(rs.getDate("a_date"));
							System.out.print("\t");
							System.out.println(rs.getString("qna_re"));
						} else System.out.println();
					} while(rs.next());
					return true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} // selectMyQNAInfo
	
	// qna삭제
	@Override
	public void deleteQNAInfo(String memId, int QNANum) {
		String sql = "DELETE FROM qna WHERE mem_id=? AND qna_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){ 
			pstmt.setString(1, memId);
			pstmt.setInt(2, QNANum);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("QNA를 삭제했습니다.");
			else System.out.println("QNA를 삭제할 수 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteQNA	
	
	// qna 레코드 확인
	@Override
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
