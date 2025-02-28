package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.BookOrderDAO;

import util.DBUtil;

public class BookOrderDAOImpl implements BookOrderDAO {
	public BookOrderDAOImpl() {}
	
	// 연체알림
	public boolean isOverReturn(String memId) {
		String sql = "SELECT b.book_title, b.book_author, (SYSDATE - o.return_date) AS over_return " +
                "FROM book_order o, book b " +
                "WHERE b.book_num = o.book_num " +
                "AND mem_id = ? " +
                "AND is_return = 0 " +
                "AND return_date < SYSDATE " +
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
	public boolean isReturnDateNotification(String numId) {
		String sql = "SELECT book_title,book_author,return_date FROM (SELECT b.book_title,b.book_author,o.return_date "
				+ "FROM book b,book_order o WHERE b.book_num=o.book_num "
				+ "AND o.mem_id=? AND o.return_date>=sysdate AND o.is_return=0 ORDER BY o.return_date) WHERE ROWNUM = 1";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, numId);
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
}
