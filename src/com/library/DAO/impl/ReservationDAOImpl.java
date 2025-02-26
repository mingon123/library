package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.ReservationDAO;

import util.DBUtil;

public class ReservationDAOImpl implements ReservationDAO {
	public ReservationDAOImpl() {}
	
	public boolean isReservationNotification(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT b.book_title,b.book_volm_cnt "
					+ "FROM reservation r,book b WHERE r.book_num=b.book_num "
					+ "AND r.mem_id=? "
					+ "AND b.book_volm_cnt>0 "
					+ "AND r.re_num=(SELECT min(re_num) FROM reservation WHERE re_num=r.re_num)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.print("예약도서 - 책제목/책권수 : ");
				do {
					System.out.print(rs.getString("book_title"));
					System.out.print(" / ");
					System.out.println(rs.getString("book_volm_cnt"));
				} while (rs.next());
				System.out.println("예약도서 대여 가능!");
				System.out.println("-".repeat(90));
				return true;
			} else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}
		return false;
	} // isReservationNotification
}
