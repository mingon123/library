package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.BookOrderDAO;
import com.library.DAO.ReservationDAO;

import util.DBUtil;

public class ReservationDAOImpl implements ReservationDAO {
	private String memId;
	
	public ReservationDAOImpl() {}

	public ReservationDAOImpl(String memId) {
		this.memId = memId;
	}
	

	@Override
	public boolean isReservationNotification() {
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
	public int canReservation(int bookNum) {
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
	public boolean isDuplicatedReserve(int bookNum) {
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
	public void insertReserve(int bookNum) {
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
	public void selectUserNowReserveInfo() {
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
								calcReserveRank(reNum, 1),
								calcReserveRank(reNum, 2),
								rs.getString("BOOK_TITLE")
								);
					} while(rs.next());

				} else {System.out.println("표시할 데이터가 없습니다.");}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectUserNowReserveInfo

	// 예약번호가 해당 유저의 것이며, 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	@Override
	public boolean checkReserveReNum(int reNum) {
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
	public int countUserReservations() {
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
	
	// 책번호가 해당유저로 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	@Override
	public boolean checkReserveBookNum(int bookNum) {
		String sql = "SELECT COUNT(*) FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);			
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) return rs.getInt(1)>0;
			}
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}//checkReserveBookNum

	// Book_num 이랑 Mem_id 로 Re_num 구하는 함수
	@Override
	public int selectBookNumToReNum(int bookNum) {
		String sql = "SELECT RE_NUM FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
		int res = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) res = rs.getInt("RE_NUM");
			}
		} catch (Exception e) {e.printStackTrace();}
		return res;
	} // selectBookNumToReNum
	
	// 예약 순위 관련 함수 - num==1 : 총 인원수, num==2 예약 순위
	@Override
	public int calcReserveRank(int reNum, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int allCount = -1; // 같은 책을 예약한 총 인원 수 
		int lowCount = -1; // 해당 유저보다 같은 책을 더 늦게 예약한 사람의 수 
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM RESERVATION WHERE BOOK_NUM = "
					+ "(SELECT BOOK_NUM FROM RESERVATION WHERE RE_NUM = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reNum);
			rs = pstmt.executeQuery();

			if(rs.next()) allCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM RESERVATION WHERE RE_NUM > ? AND "
					+ "BOOK_NUM = (SELECT BOOK_NUM FROM RESERVATION WHERE RE_NUM = ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reNum);
			pstmt.setInt(2, reNum);
			rs = pstmt.executeQuery();

			if(rs.next()) lowCount = rs.getInt(1);

			if(lowCount == -1 || allCount == -1) System.out.println("에러발생!");

		} catch (Exception e) {
			System.out.println("에러발생");
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
		if(num == 1) return allCount;
		else if(num == 2) return allCount - lowCount;
		else return -1;
	} // calcReserveRank
	
	// 예약테이블 행 삭제 - book num
	@Override
	public void deleteReserveBookNum(int bookNum) {
		String sql = "DELETE FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			pstmt.setString(2, memId);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("예약한 책의 대여로 인해 예약목록에서 해당 책을 삭제합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // deleteReserveBookNum
	
	
	// admin
	// 예약정보 조회
	@Override
	public void selectRSVAdmin() {	
		String sql = "SELECT * FROM reservation ORDER BY book_num, re_num"; // 1.책번호별 2.예약번호별 정렬 (test요망)
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("예약번호\t회원아이디\t책번호");
				System.out.println("-".repeat(100));
				do {
					System.out.print(rs.getInt("re_num")+"\t");							
					System.out.print(rs.getString("mem_id")+"\t");
					System.out.println(rs.getInt("book_num")+"\t");
				} while (rs.next());
			} else System.out.println("표시할 데이터가 없습니다.");	
			System.out.println("-".repeat(100));
		} catch (Exception e) {e.printStackTrace();} 	
	} // selectRSV()
	
	//조회하는 예약 레코드 존재 여부 체크
	@Override
	public int checkRSVRecordAdmin(int reNum) { // re_num로 조회
		String sql = "SELECT * FROM reservation WHERE re_num=?";	
		int count = 0;		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, reNum);
			try(ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) count = 1;	
			}
		} catch (Exception e) {count = -1;} //오류 발생
		return count; 
	} // checkRSVRecord	
	
	// 예약 정보 등록 (수정 필요)
	@Override
	public void InsertRSVAdmin(String memId, int bookNum) {
		String sql = "INSERT INTO reservation (re_num, mem_id, book_num) "
				+ "VALUES (reservation_seq.nextval,?,?)"; //전체를 넣을땐 컬럼명 생략 가능
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, memId);
			pstmt.setInt(++cnt, bookNum); // 재고 있는 책 입력시 예약 못하도록 조건 추가해야함!!!			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 예약정보를 등록했습니다.");
		} catch (Exception e) {e.printStackTrace();} 
	} // insertRSV()

	// 예약정보 수정
	@Override
	public void updateRSV(int reNum, String memId, int bookNum) {
		String sql = "UPDATE reservation SET mem_id=?,book_num=? WHERE re_num=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, memId); // 유효성 검사 추가요망
			pstmt.setInt(++cnt, bookNum);
			pstmt.setInt(++cnt, reNum);			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 예약정보를 수정했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateRSV()

	
}
