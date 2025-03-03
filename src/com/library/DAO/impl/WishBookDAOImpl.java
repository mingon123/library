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
		String sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date FROM wish_book ORDER BY wish_date DESC";
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
						rs.getDate("wish_date")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wishBooks;
	}
	// 내 희망도서 신청 목록 확인
	@Override
	public List<wishBook> selectMyWishBookInfo(String memId) {
		String sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date FROM wish_book WHERE mem_id=? ORDER BY wish_date DESC";
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
							rs.getDate("wish_date")
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
}
