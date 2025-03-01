package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.ReviewDAO;

import util.DBUtil;

public class ReviewDAOImpl implements ReviewDAO {
	
	// 리뷰 화면
	@Override
	public void selectReviewInfo() {	
		String sql = "SELECT r.review_num,b.book_num,b.book_title,r.review_content,r.review_rate,r.review_reg_date "
				+ "FROM review r, book b WHERE r.book_num=b.book_num "
				+ "ORDER BY review_num";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(90));
			if (rs.next()) {
				System.out.printf("리뷰번호\t책번호\t평점\t리뷰등록일\t\t %-30s %-30s \n","책제목","리뷰내용");
				do {
					System.out.printf("%d\t%d\t%.1f\t%s\t %-30s %-30s \n",
							rs.getInt("review_num"),
							rs.getInt("book_num"),
							rs.getDouble("review_rate"),
							rs.getDate("review_reg_date"),
							rs.getString("book_title"),
							rs.getString("review_content"));
				} while (rs.next());
			} else {System.out.println("표시할 데이터가 없습니다.");}
			System.out.println("-".repeat(90));
		} catch (Exception e) {e.printStackTrace();}
	} // selectReviewInfo
	
	// 리뷰 상세 정보(book_num 기준)
	@Override
	public void selectDetailReview(int num) {
		String sql = "SELECT r.review_num,b.book_num,b.book_title,r.review_content,r.review_rate,r.review_reg_date,r.mem_id "
				+ "FROM review r, book b WHERE r.book_num=b.book_num "
				+ "AND review_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, num);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.println("리뷰번호 : "+ rs.getInt("review_num"));
					System.out.println("책번호 : "+ rs.getInt("book_num"));
					System.out.println("책제목 : "+ rs.getString("book_title"));
					System.out.println("리뷰내용 : "+ rs.getString("review_content"));
					System.out.println("평점 : "+ rs.getDouble("review_rate"));
					System.out.println("작성일 : "+ rs.getDate("review_reg_date"));
					System.out.println("작성자 : "+ rs.getString("mem_id"));
				} else {System.out.println("검색된 정보가 없습니다.");}
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	//조회하는 리뷰 레코드 존재 여부 체크
	@Override
	public int checkReviewRecord(int reviewNum, String memId) {
		int count = 0;
	    String sql = memId == null ? "SELECT COUNT(*) FROM review WHERE review_num=?" 
	                               : "SELECT COUNT(*) FROM review WHERE review_num=? AND mem_id=?";
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, reviewNum);
	        if (memId != null) pstmt.setString(2, memId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) return rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        count = -1;
	    }
	    return count; // 리뷰 없음
	}
	
	//조회하는 리뷰 레코드 존재 여부 체크(제목기준)
	@Override
	public int checkReviewRecordOfBookTile(String bookTitle) {
		String sql = "SELECT COUNT(*) FROM review r,book b WHERE r.book_num=b.book_num AND b.book_title LIKE ?";	
		int count = 0;		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, "%"+bookTitle+"%");
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {count = rs.getInt(1);} //레코드가 존재할 때 1 저장							
			}
		} catch (Exception e) {
			count = -1; //오류 발생
			e.printStackTrace();
		}
		return count; 
	} // checkReviewRecordOfBookTile()
	
	// 리뷰검색(책제목 기준)
	@Override
	public void selectSearchReviewOftitle(String bookTitle) {
		String sql = "SELECT r.review_num,b.book_title,b.book_author,r.review_rate,review_reg_date FROM review r JOIN book b ON r.book_num=b.book_num "
				+ "WHERE b.book_title LIKE ?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, "%"+bookTitle+"%");
			try(ResultSet rs = pstmt.executeQuery();){
				System.out.println("-".repeat(90));
				if(rs.next()) {
					System.out.printf("리뷰번호\t등록일\t\t평점\t %-30s %-20s \n","책제목","저자");
					do {
						System.out.printf("%d\t%s\t %.1f\t %-30s %-30s \n",
								rs.getInt("review_num"),
								rs.getDate("review_reg_date"),
								rs.getDouble("review_rate"),
								rs.getString("book_title"),
								rs.getString("book_author"));
					} while(rs.next());				
				} else {System.out.println("검색된 책에 대한 리뷰가 없습니다.");}
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	// 내 리뷰 보기
	@Override
	public boolean selectMyReviewInfo(String memId) {
		String sql = "SELECT r.review_num,b.book_title,r.review_content,r.review_rate,r.review_reg_date "
				+ "FROM review r, book b WHERE r.book_num=b.book_num "
				+ "AND r.mem_id=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				System.out.println("-".repeat(90));				
				if(rs.next()) {
					System.out.println("내 리뷰");
					System.out.println("번호\t리뷰등록일\t\t평점\t책제목\t\t\t리뷰내용");
					do {
						System.out.print(rs.getInt("review_num")+"\t");
						System.out.print(rs.getDate("review_reg_date")+"\t");
						System.out.print(rs.getDouble("review_rate")+"\t");
						System.out.print(rs.getString("book_title")+"\t");
						System.out.println(rs.getString("review_content")+"\t");
					} while(rs.next());
				} else {
					System.out.println("작성한 리뷰가 없습니다.");
					System.out.println("-".repeat(90));
					return false;
				}
				System.out.println("-".repeat(90));
				return true;
			}
		} catch (Exception e) {e.printStackTrace();}
		return false; 
	} // selectMyReviewInfo
	
	// 리뷰정보 수정
	@Override
	public void updateMyReview(int reviewNum, String reviewContent, int reviewRate, String memId) {
		String sql = "UPDATE review SET review_content=?, review_rate=? WHERE review_num=? AND mem_id=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(++cnt, reviewContent);
			pstmt.setInt(++cnt, reviewRate);
			pstmt.setInt(++cnt, reviewNum);
			pstmt.setString(++cnt, memId);
			int count = pstmt.executeUpdate();
	        if (count > 0) System.out.println(count + "개의 리뷰 정보를 수정했습니다.");
	        else System.out.println("리뷰 수정에 실패했습니다. 리뷰 번호를 확인하세요.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateMyReview()
	
	// 리뷰정보 삭제
	@Override
	public void deleteReview(int reviewNum) {
		String sql = "DELETE FROM review WHERE review_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, reviewNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 리뷰 정보를 삭제했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // deleteReview()
	
	// 리뷰 등록
	@Override
	public void insertReviewInfo(int bookNum, String content, int rate, String memId) {
		String sql = "INSERT INTO REVIEW(REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,MEM_ID) VALUES (review_seq.nextval,?,?,?,?)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			pstmt.setString(2, content);
			pstmt.setInt(3, rate);
			pstmt.setString(4, memId);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("리뷰등록이 완료되었습니다.");
			else System.out.println("리뷰 등록 오류!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("리뷰 등록 오류");
		}
	}
	
	
	
	
}
