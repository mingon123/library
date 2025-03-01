package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.BookOrderDAO;
import com.library.DAO.ReservationDAO;

import util.DBUtil;

public class ReservationDAOImpl implements ReservationDAO {
	private BookOrderDAO bookOrderDAO;
	
	public ReservationDAOImpl() {
		this.bookOrderDAO = new BookOrderDAOImpl();
	}
	
	@Override
	public boolean isReservationNotification(String memId) {
		String sql = "SELECT b.book_title,b.book_volm_cnt "
				+ "FROM reservation r,book b WHERE r.book_num=b.book_num "
				+ "AND r.mem_id=? "
				+ "AND b.book_volm_cnt>0 "
				+ "AND r.re_num=(SELECT min(re_num) FROM reservation WHERE re_num=r.re_num)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
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
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return false;
	} // isReservationNotification
	
	// 예약가능 여부 판별 함수. 예약가능:1 책남아있는 권수0이 아님:0 예약권수 다 참:-1
	@Override
	public int canReservation(String memId,int bookNum) {
		String sql = "SELECT BOOK_VOLM_CNT FROM book WHERE BOOK_NUM=?";
	    int bookCount = 0;  // 책의 남은 권수
	    int reserveCount = 0;  // 사용자의 예약 수
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        // 책의 남은 권수 조회
	        pstmt.setInt(1, bookNum);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) bookCount = rs.getInt(1);
	        }
	        // 사용자의 예약 수 조회
	        sql = "SELECT COUNT(*) FROM reservation WHERE mem_id=?";
	        try (PreparedStatement pstmt2 = conn.prepareStatement(sql)) {
	            pstmt2.setString(1, memId);
	            try (ResultSet rs = pstmt2.executeQuery()) {
	                if (rs.next()) reserveCount=rs.getInt(1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;  // 에러가 발생하면 -1 반환
	    }
	    
	    if (bookCount == 0) {
	        return 1;  // 예약 가능한 경우 (책이 남아있지 않음)
	    } else if (reserveCount >= 2) {
	        return -1;  // 예약 수가 이미 2개 이상인 경우
	    } else {
	        return 0;  // 예약 불가 (책 남은 수량 있음)
	    }
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
	
	// 로그인한 유저의 예약 현황 출력
	@Override
	public void selectUserNowReserveInfo(String memId) {
		String sql = "SELECT RE_NUM, BOOK_TITLE FROM RESERVATION JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.printf("%5s \t%s     %s \t%s \n", 
							"번호", "총 예약인원", "예약순위", "책제목");
					int reNum = rs.getInt("RE_NUM");
					do {
						System.out.printf("%5d \t%5d %10d \t%-27s \n", 
								rs.getInt("RE_NUM"),
								bookOrderDAO.calcReserveRank(reNum, 1),
								bookOrderDAO.calcReserveRank(reNum, 2),
								rs.getString("BOOK_TITLE")
								);
					} while(rs.next());

				} else {System.out.println("표시할 데이터가 없습니다.");}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectUserNowReserveInfo

	// 예약번호가 해당 유저의 것이며, 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	@Override
	public boolean checkReserveReNum(int reNum, String memId) {
		String sql = "SELECT COUNT(*) FROM RESERVATION WHERE re_num=? AND mem_id=?";
		int check = -1;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, reNum);
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) check = rs.getInt(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		if(check == -1) System.out.println("에러 발생!");
		return check >= 1? true:false;
	}//checkReserveReNum
	
	// 예약테이블 행 삭제 - 예약테이블 num
	@Override
	public void deleteReserveReNum(int reNum) {
		String sql = "DELETE FROM RESERVATION WHERE RE_NUM=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, reNum);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("예약취소를 성공했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // deleteReserveReNum
	
	// 사용자의 현재 예약 개수 확인 (최대 2권 제한)
	@Override
	public int countUserReservations(String memId) {
	    String sql = "SELECT COUNT(*) FROM reservation WHERE mem_id = ?";
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, memId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) return rs.getInt(1); // 예약 개수 반환
	        }
	    } catch (Exception e) {e.printStackTrace();}
	    return 0; // 오류 시 0 반환
	}
	
}
