package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.BookOrderDAO;

import util.DBUtil;

public class BookOrderDAOImpl implements BookOrderDAO {
	private int bookNum;
	private String memId;
	
	public BookOrderDAOImpl() {}
	
	public BookOrderDAOImpl(int bookNum,String memId) {
		this.bookNum = bookNum;
		this.memId = memId;
	}

	// 연체알림
	@Override
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
	@Override
	public boolean isReturnDateNotification(String memId) {
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

	// 대여가능 여부 판별 함수. 대여가능:1 책남아있는 권수0:0 대여권수 다 참:-1
	@Override
	public int canOrder(String mem_id,int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int bookCount = -1;
		int orderCount = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT BOOK_VOLM_CNT FROM book WHERE BOOK_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();

			if(rs.next()) bookCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM book_order WHERE MEM_ID=? AND IS_RETURN = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) orderCount = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(bookCount == -1 || orderCount == -1)
			System.out.println("에러발생!! 대여여부 판별 불가");

		if(bookCount > 0) {
			if(orderCount < 3) {
				return 1;
			}else return -1;
		}else return 0;
	}//canOrder
	
	// 대여(insert book_order) - 대여 추가후 책 갯수 조정
	@Override
	public void insertBookOrder(String memId,int bookNum) {
		String sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			boolean bookReserve = checkReserveBookNum(bookNum, memId);

			if(bookReserve) {
				int re_num = selectBookNumToReNum(bookNum, memId);
				int rank = calcReserveRank(re_num, 2);
				int bookCount = selectBookCount(bookNum);

				if(rank <= bookCount) { // 예약 중인 책이 있고, 예약 순위가 높아서 대여 가능한 경우
					pstmt.setString(1, memId);
					pstmt.setInt(2, bookNum);

					int count = pstmt.executeUpdate();	

					if(count > 0) {
						System.out.println(bookNum+"번책 대여 성공!");
						updateBookCount(bookNum, -1);
						deleteReserveBookNum(bookNum, memId);
					}
				} else {
					System.out.println("예약 순위가 낮은 관계로 대여가 불가능합니다.");
				}

				//}else if(!bookReserve) { 
				// 해당 책을 예약한 기록이 없는 경우  -> 예약 안했는데 갑자기 책 빌려가게 되면 어떡하지? TODO 
				//	-> 예약이 생길경우 책 테이블에 isReserve ? 같은 컬럼 추가?

			}else {
				sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
				pstmt.setString(1, memId);
				pstmt.setInt(2, bookNum);
				int count = pstmt.executeUpdate();	
				if(count > 0) {
					System.out.println(bookNum+"번책 대여 성공!");
					updateBookCount(bookNum, -1);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // insertBookOrder
	
	// 책번호가 해당유저로 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	@Override
	public boolean checkReserveBookNum(int bookNum, String memId) {
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
	public int selectBookNumToReNum(int bookNum, String memId) {
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
		String sql = "SELECT COUNT(*) FROM RESERVATION WHERE BOOK_NUM = "
				+ "(SELECT BOOK_NUM FROM RESERVATION WHERE RE_NUM = ?)";
		int allCount = -1; // 같은 책을 예약한 총 인원 수 
		int lowCount = -1; // 해당 유저보다 같은 책을 더 늦게 예약한 사람의 수 
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, reNum);
			try(ResultSet rs1 = pstmt.executeQuery();){
				if(rs1.next()) allCount = rs1.getInt(1);
			}
			sql = "SELECT COUNT(*) FROM RESERVATION WHERE RE_NUM > ? AND "
					+ "BOOK_NUM = (SELECT BOOK_NUM FROM RESERVATION WHERE RE_NUM = ?)";
			pstmt.clearParameters();
			pstmt.setInt(1, reNum);
			pstmt.setInt(2, reNum);
			try(ResultSet rs2 = pstmt.executeQuery();){
				if(rs2.next()) lowCount = rs2.getInt(1);
				if(lowCount == -1 || allCount == -1) System.out.println("에러발생!");
			}
		} catch (Exception e) {System.out.println("에러발생");}
		if(num == 1) return allCount;
		else if(num == 2) return allCount - lowCount;
		else return -1;
	} // calcReserveRank
	
	// book_num으로 book_volm_cnt 구하는 함수
	@Override
	public int selectBookCount(int bookNum) {
		String sql = "SELECT BOOK_VOLM_CNT FROM BOOK WHERE BOOK_NUM=?";
		int res = -1;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) res = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("에러 발생!");
		}
		if(res == -1) System.out.println("에러 발생!");
		return res;

	}//checkNowOrderNum
	
	// 책갯수 조정 함수 (book_volm_cnt + k)
	@Override
	public void updateBookCount(int bookNum, int bookVolmCnt) {
		String sql = "UPDATE book SET book_volm_cnt = book_volm_cnt + ? WHERE book_num = ?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookVolmCnt);
			pstmt.setInt(2, bookNum);
			int count = pstmt.executeUpdate();
			if(count <= 0) {System.out.println("책갯수 조정 중 오류발생!");}
		} catch (Exception e) {e.printStackTrace();}
	} // updateBookCount
	
	// 예약테이블 행 삭제 - book num
	@Override
	public void deleteReserveBookNum(int bookNum, String memId) {
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



}
