package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.DAO.WishBookDAO;
import com.library.DTO.wishBook;

import util.DBUtil;

public class WishBookDAOImpl implements WishBookDAO {

	public WishBookDAOImpl() {}
	
	// 희망도서 신청
	@Override
    public void insertWishBook(String title, String author, String publisher, String memId) {
		String sql = "INSERT INTO wish_book(wish_num, wish_title, wish_author, wish_publisher, wish_date, mem_id) VALUES (wish_book_seq.nextval, ?, ?, ?, SYSDATE, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setString(4, memId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	// 희망도서 확인
	@Override
    public boolean checkWishBookRecord(String title, String author) {
        String sql = "SELECT COUNT(*) FROM book WHERE book_title=? AND book_author=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	// 희망도서 신청 목록 확인
	@Override
	public List<wishBook> selectWishBookInfo() {
		String sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date,mem_id FROM wish_book ORDER BY wish_date DESC";
		List<wishBook> wishBooks = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
				wishBooks.add(new wishBook(
						rs.getInt("wish_num"),
						rs.getString("wish_title"),
						rs.getString("wish_author"),
						rs.getString("wish_publisher"),
						rs.getDate("wish_date"),
						rs.getString("mem_id")
				));
			}
		} catch (Exception e) {	e.printStackTrace();}
		return wishBooks;
	}
	// 내 희망도서 신청 목록 확인
	@Override
	public List<wishBook> selectMyWishBookInfo(String memId) {
		String sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date,mem_id FROM wish_book WHERE mem_id=? ORDER BY wish_date DESC";
		List<wishBook> wishBooks = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					wishBooks.add(new wishBook(
							rs.getInt("wish_num"),
							rs.getString("wish_title"),
							rs.getString("wish_author"),
							rs.getString("wish_publisher"),
							rs.getDate("wish_date"),
							rs.getString("mem_id")
					));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wishBooks;
	}
	// 희망도서 신청 삭제
	@Override
	public void deleteWishMyBookInfo(String memId, int wishNum) {
		String sql = "DELETE FROM wish_book WHERE mem_id=? AND wish_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			pstmt.setInt(2, wishNum);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("희망도서를 삭제했습니다.");
			else System.out.println("희망도서를 삭제할 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	// 희망도서 레코드 확인
	@Override
	public int checkWishBookRecordNumId(int wishNum, String memId){
	    String sql = "SELECT COUNT(*) FROM wish_book WHERE wish_num = ? AND mem_id = ?";
	    try (Connection conn = DBUtil.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {      
	        pstmt.setInt(1, wishNum);
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
	//조회하는 희망도서 레코드 존재 여부 체크
	@Override
	public int checkWishRecord(int wishNum) {
		String sql = "SELECT * FROM wish_book WHERE wish_num=?";
		int count = 0;		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, wishNum);
			try(ResultSet rs = pstmt.executeQuery();){
			if (rs.next()) count = 1; //레코드가 존재할 때 1 저장				
			}		
		} catch (Exception e) {count = -1;} //오류 발생
		return count; 
	} // checkWishRecord(대여번호)
	
	// 희망도서 신청 시 동일한 제목+저자인 도서가 있으면 알림
	@Override
	public boolean checkWishBook(String bookTitle,String bookAuthor) {
		String sql = "SELECT COUNT(*) FROM book WHERE book_title=? AND book_author=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, bookAuthor);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					int count = rs.getInt(1);
					if(count>0)	return true;
				}
			}
		}catch (Exception e) {e.printStackTrace();}
		return false;
	} // isWishBook
	
	// 희망도서정보 수정 
	@Override
	public void updateWish(String wishTitle, String wishAuthor, String wishPublisher,String memId,int wishNum) {
		String sql = "UPDATE wish_book SET wish_title=?, wish_author=?, wish_publisher=?, mem_id=? WHERE wish_num=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, wishTitle);
			pstmt.setString(++cnt, wishAuthor); 
			pstmt.setString(++cnt, wishPublisher); 
			pstmt.setString(++cnt, memId); 
			pstmt.setInt(++cnt, wishNum); 
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 희망도서 정보를 수정했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateWish()
	
	// 희망도서정보 삭제
	@Override
	public void deleteWish(int wishNum) {
		String sql = "DELETE FROM wish_book WHERE wish_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, wishNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 희망도서정보를 삭제했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // deleteWish()
}
