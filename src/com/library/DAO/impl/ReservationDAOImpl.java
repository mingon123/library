package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.ReservationDAO;

import util.DBUtil;

public class ReservationDAOImpl implements ReservationDAO {
	public ReservationDAOImpl() {}
	
	@Override
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
	
	// 예약가능 여부 판별 함수. 예약가능:1 책남아있는 권수0이 아님:0 예약권수 다 참:-1
	@Override
	public int canReservation(String memId,int bookNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int bookCount = -1;
		int reserveCount = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT BOOK_VOLM_CNT FROM book WHERE BOOK_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookNum);
			rs = pstmt.executeQuery();

			if(rs.next()) bookCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM reservation WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			if(rs.next()) reserveCount = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(bookCount == -1 || reserveCount == -1)
			System.out.println("에러발생!! 대여여부 판별 불가");

		if(reserveCount < 2) {
			if(bookCount == 0) {
				return 1;
			}else return 0;
		}else return -1;
	}//canReservation

	//이미 해당 유저가 같은 책을 예약했는지 확인  - 중복시 :true  중복 아닐시 :false
	@Override
	public boolean isDuplicatedReserve(int bookNum, String memId) {
		String sql = "SELECT COUNT(*) FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
		int check = -1;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) check = rs.getInt(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		if(check == -1) System.out.println("에러 발생!");
		return check >= 1;
	}//checkNowOrderNum
	
	// reservation 테이블에 insert - 예약 테이블에 insert
	@Override
	public void insertReserve(String memId,int bookNum) {
		String sql = "INSERT INTO reservation VALUES(reservation_seq.nextval,?,?)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			pstmt.setInt(2, bookNum);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println(bookNum+"번책 예약 성공!");
			else System.out.println("예약 중 오류발생!");
		} catch (Exception e) {e.printStackTrace();}
	} // insertBookOrder
	
}
