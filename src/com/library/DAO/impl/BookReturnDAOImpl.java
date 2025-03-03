package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.BookReturnDAO;

import util.DBUtil;

public class BookReturnDAOImpl implements BookReturnDAO{
	private String memId;
	
	public BookReturnDAOImpl(String memId) {
		this.memId = memId;
	}
	
	// 연체알림
	@Override
	public boolean isOverReturn() {
		String sql = "SELECT b.book_title, b.book_author, (SYSDATE - o.return_date) AS over_return " +
				"FROM book_order o, book b WHERE b.book_num = o.book_num " +
				"AND mem_id = ? AND is_return = 0 AND return_date < SYSDATE " +
				"ORDER BY o.return_date DESC";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.print("연체일/책제목 : ");
					do {
						int overReturnDays = rs.getInt("over_return");
						if (overReturnDays > 0) {
							System.out.print(overReturnDays + "일 / ");
							System.out.println(rs.getString("book_title"));
						}
					} while (rs.next());
					System.out.println("-".repeat(90));
					return true;
				}
			}
		}catch (Exception e) {e.printStackTrace();}
		return false;
	} // isOverReturn
	
	// 반납일 알림(가장 임박한 책의 반납일)
	@Override
	public boolean isReturnDateNotification() {
		String sql = "SELECT book_title,book_author,return_date FROM (SELECT b.book_title,b.book_author,o.return_date "
				+ "FROM book b,book_order o WHERE b.book_num=o.book_num "
				+ "AND o.mem_id=? AND o.return_date>=sysdate AND o.is_return=0 ORDER BY o.return_date) WHERE ROWNUM = 1";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					System.out.print("반납예정일/책제목 : ");
					do {
						System.out.print(rs.getDate("return_date"));
						System.out.print(" / ");
						System.out.println(rs.getString("book_title"));
					} while (rs.next());
					System.out.println("-".repeat(90));
					return true;
				}
			}
		}catch (Exception e) {e.printStackTrace();}
		return false;
	} // isReturnDateNotification
	
	// 반납 진행 함수 - update is_return
	@Override
	public void updateOrderReturn(int orderNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE BOOK_ORDER SET IS_RETURN = 1 WHERE ORDER_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,orderNum);
			int count = pstmt.executeUpdate();

			sql = "UPDATE BOOK SET BOOK_VOLM_CNT = BOOK_VOLM_CNT + 1 WHERE BOOK_NUM IN "
					+ "(SELECT BOOK_NUM FROM BOOK_ORDER WHERE ORDER_NUM = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			int count2 = pstmt.executeUpdate();

			if(count > 0 && count2 > 0) System.out.println("반납 진행중입니다.");
		} catch (Exception e) {
			System.out.println("에러발생");
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateOrderReturn
	
	
	
}
