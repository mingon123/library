package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.DAO.BookDAO;
import com.library.DAO.BookOrderDAO;
import com.library.DAO.ReservationDAO;

import util.DBUtil;

public class BookOrderDAOImpl implements BookOrderDAO {
	private String memId;
	private ReservationDAO reservationDAO = new ReservationDAOImpl(memId);;
	private BookDAO bookDAO;

	public BookOrderDAOImpl() {}

	public BookOrderDAOImpl(String memId) {
		this.memId = memId;
		this.bookDAO = new BookDAOImpl();
	}


	// 대여가능 여부 판별 함수. 대여가능:1 책남아있는 권수0:0 대여권수 다 참:-1
	@Override
	public int canOrder(int bookNum) {
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
			pstmt.setInt(1, bookNum);
			rs = pstmt.executeQuery();
			if(rs.next()) bookCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM book_order WHERE MEM_ID=? AND IS_RETURN = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
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
	public void insertBookOrder(int bookNum) {
		String sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			boolean bookReserve = reservationDAO.checkReserveBookNum(bookNum);

			if(bookReserve) {
				int re_num = reservationDAO.selectBookNumToReNum(bookNum);
				int rank = reservationDAO.calcReserveRank(re_num, 2);
				int bookCount = bookDAO.selectBookCount(bookNum);

				if(rank <= bookCount) { // 예약 중인 책이 있고, 예약 순위가 높아서 대여 가능한 경우
					pstmt.setString(1, memId);
					pstmt.setInt(2, bookNum);
					int count = pstmt.executeUpdate();	
					if(count > 0) {
						System.out.println(bookNum+"번책 대여 성공!");
						bookDAO.updateBookCount(-1,bookNum);
						reservationDAO.deleteReserveBookNum(bookNum);
					}
				} else System.out.println("예약 순위가 낮은 관계로 대여가 불가능합니다.");
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
					bookDAO.updateBookCount(-1,bookNum);
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // insertBookOrder

	// 대여번호가 현황기록에 존재하는지 확인하며, 해당 유저의 것인지 확인하는 함수 
	//	- 존재:true / 존재X 또는 에러:false
	@Override
	public boolean checkNowOrderNum(int orderNum) {
		String sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND IS_RETURN=0 AND MEM_ID=?";
		int check = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, orderNum);
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) check = rs.getInt(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		if(check == -1)	System.out.println("에러 발생!");
		return check >= 1? true:false;
	}//checkNowOrderNum

	// 로그인한 유저의 대여 현황만 출력
	@Override
	public void selectUserNowOrderInfo() {
		String sql = "SELECT ORDER_NUM, BOOK_TITLE, ORDER_DATE, RETURN_DATE, "
				+"CASE WHEN IS_ADD=1 THEN 'X' WHEN IS_ADD=0 THEN 'O' END AS CAN_ADD FROM BOOK_ORDER "
				+ "JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ? AND IS_RETURN = 0 ORDER BY ORDER_NUM";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.printf("%5s \t%7s \t%7s \t%5s \t\t%5s\n", 
							"대여번호", "대여일자", "반납예정일자","연장가능", "책제목");
					do {
						System.out.printf("%5s \t%s \t%s \t%5s \t\t%s\n", 
								rs.getInt("ORDER_NUM"),
								rs.getDate("ORDER_DATE").toString(),
								rs.getDate("RETURN_DATE").toString(),
								rs.getString("CAN_ADD"),
								rs.getString("BOOK_TITLE")
								);
					} while(rs.next());
				} else {
					System.out.println("표시할 데이터가 없습니다.");
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectUserNowOrderInfo

	//	해당 유저의 현재 대여 수 	0: true	1이상: false 
	@Override
	public boolean checkZeroOrder() {
		String sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE MEM_ID=? AND IS_RETURN=0";
		int check = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) check = rs.getInt(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		if(check == -1) System.out.println("에러 발생!");
		return check >= 1? false: true; 
	}//checkZeroOrder

	// order_num -> 책이름, 저자 출력
	@Override
	public void selectOrderNumToBookInfo(int orderNum) {
		String sql = "SELECT book_title, book_author FROM book WHERE book_num = "
				+ "(SELECT book_num FROM book_order WHERE order_num = ?)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, orderNum);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					System.out.println("제목 : " + rs.getString("book_title"));
					System.out.println("저자 : " + rs.getString("book_author"));
				} else System.out.println("책 정보를 불러오는 것에 실패했습니다.");
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectOrderNumToBookInfo

	// order_num -> book_num
	@Override
	public int selectOrderNumToBookNum(int orderNum) {
		String sql =  "SELECT book_num FROM book_order WHERE order_num = ?";
		int res = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNum);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) res = rs.getInt("book_num");
				if(res == -1) System.out.println("book_num을 불러오던중 오류 발생!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("book_num을 불러오던중 오류 발생");
		}
		return res;
	} // selectOrderNumToBookNum

	// 지정한 대여번호에 대한 연체일 알림 / 연체가 아닐 시 정상 반납 알림
	@Override
	public void selectLateReturn(int orderNum) {
		String sql = "SELECT TRUNC(SYSDATE - RETURN_DATE) AS res FROM BOOK_ORDER WHERE ORDER_NUM = ?";
		int res = -1;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, orderNum);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) res = rs.getInt("res");
				if(res > 0) System.out.println(res+"일 연체되었습니다.");
				else System.out.println("정상 반납 되었습니다.");
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectLateReturn

	// 로그인한 유저의 대여내역보기(현황 + 기록) - 1번 최신순, 2번 오래된순
	@Override
	public void selectUserOrderInfo(int selectNum) {
		String sql = "SELECT ORDER_NUM, BOOK_TITLE, ORDER_DATE, "
				+ "CASE WHEN IS_RETURN=1 THEN 'O' WHEN IS_RETURN=0 THEN 'X' END AS RETURN "
				+ "FROM BOOK_ORDER  JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ? ORDER BY ORDER_NUM ";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			if(selectNum == 1) sql += "DESC";
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					System.out.printf("%5s \t\t%s \t\t%5s \t%s\n", "번호", "대여일자", "반납여부(O/X)", "책제목");
					do {
						System.out.printf("%5d \t\t%7s \t%5s \t\t%-30s\n", 
								rs.getInt("ORDER_NUM"),
								rs.getDate("ORDER_DATE").toString(),
								rs.getString("RETURN"),
								rs.getString("BOOK_TITLE")
								);
	
					} while(rs.next());
				} else System.out.println("표시할 데이터가 없습니다.");
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectUserOrderInfo

	// 대여번호가 연장이 가능한지 확인하는 함수
	@Override
	public boolean checkOrderAdd(int orderNum) {
		String sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND MEM_ID=? AND IS_ADD=0";
		int check = -1;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNum);
			pstmt.setString(2, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) check = rs.getInt(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		if(check == -1) System.out.println("에러 발생!");
		return check >= 1? true: false; 
	}//checkNowOrderNum
	
	// 연장 진행 함수 - update return_date
	@Override
	public void updateReturnDate(int orderNum) {
		String sql = "UPDATE BOOK_ORDER SET RETURN_DATE = (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?) + 7,IS_ADD=1 WHERE ORDER_NUM=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1,orderNum);
			pstmt.setInt(2,orderNum);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("연장을 성공했습니다.");
		} catch (Exception e) {
			System.out.println("에러발생");
		}
	} // updateReturnDate
	
	// 반납 레코드 확인
	@Override
	public boolean checkOrderByUser(int orderNum) {
	    String sql = "SELECT COUNT(*) FROM book_order WHERE order_num = ? AND mem_id = ? AND is_return = 0";
	    try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        pstmt.setInt(1, orderNum);
	        pstmt.setString(2, memId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  // 본인의 대여 기록이 존재하면 true
	            }
	        }
	    } catch (Exception e) {e.printStackTrace();}
	    return false;
	}
	
	
	// admin
	// 대여정보 조회
	@Override
	public void selectOrderAdmin() {
		String sql = "SELECT bo.*, (SELECT book_title FROM book WHERE book_num=bo.book_num) 책제목 "
				+ "FROM book_order bo ORDER BY order_num";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("대여번호\t회원아이디\t\t책번호\t책제목\t\t\t대여일\t\t반납기한일\t\t연장유무\t반납유무");
				System.out.println("-".repeat(100));
				do {
					System.out.print(rs.getInt("order_num")+"\t");		
					System.out.print(rs.getString("mem_id")+"   \t");		
					System.out.print(rs.getInt("book_num")+"\t");	
					String title=rs.getString("책제목");					
					if (title.length()>=15) System.out.printf("%-15s..\t", title.substring(0, 15));
					else System.out.printf("%-15s\t", title);
					System.out.print(rs.getDate("order_date")+"\t");
					System.out.print(rs.getDate("return_date")+"\t");
					String is_add = rs.getInt("is_add")==1 ? "O" : "X";					
					System.out.print(is_add+"\t"); // 연장여부 O,X로 출력					
					String is_return = rs.getInt("is_return")==1 ? "O" : "X";
					System.out.println(is_return); // 반납여부 O,X로 출력
				} while (rs.next());
			} else System.out.println("표시할 데이터가 없습니다.");	
			System.out.println("-".repeat(100));
		} catch (Exception e) {e.printStackTrace();}
	} // selectOrder()
	
	//대여 정보 상세보기
	@Override
	public void selectDetailOrderAdmin(int orderNum) {
		String sql = "SELECT bo.*, (SELECT book_title FROM book WHERE book_num=bo.book_num) 책제목 "
				+ "FROM book_order bo WHERE order_num=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, orderNum);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) { 
					System.out.println("대여번호 : " + rs.getInt("order_num"));
					System.out.println("회원아이디 : " + rs.getString("mem_id"));
					System.out.println("책번호 : " + rs.getInt("book_num"));
					//책제목 같이 출력요망
					System.out.println("책제목 : " + rs.getString("책제목"));
					System.out.println("대여일 : " + rs.getDate("order_date"));
					System.out.println("반납기한일 : " + rs.getDate("return_date"));			
					String is_add = rs.getInt("is_add")==1 ? "O" : "X";	
					System.out.println("연장유무 : " + is_add); // 연장여부 O,X로 출력	
					String is_return = rs.getInt("is_return")==1 ? "O" : "X";
					System.out.println("반납유무 : " + is_return); // 반납여부 O,X로 출력		
				} else System.out.println("검색된 정보가 없습니다.");	
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectDetailOrder()
	
	// 대여정보 등록
	@Override
	public void insertOrderAdmin(String memId, int bookNum) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;		
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();		
			//트랜잭션을 수동으로 처리하기 위해 auto commit 해제
			conn.setAutoCommit(false); 
			sql = "INSERT INTO book_order (order_num, mem_id, book_num, order_date, return_date, is_add, is_return) "
					+ "VALUES (book_order_seq.nextval,?,?,SYSDATE,SYSDATE+14,0,0)";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(++cnt, memId);			
			pstmt1.setInt(++cnt, bookNum);				
			int count = pstmt1.executeUpdate();
			System.out.println(count + "개의 대여정보를 등록했습니다.");	
			// 대여정보 추가시 해당도서 재고 -1(적용완료) & 재고0인 책은 대여정보 등록 불가능하게 해야함.(수정요망)
			sql = "UPDATE book SET book_volm_cnt = book_volm_cnt-1 WHERE book_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, bookNum);
			count = pstmt2.executeUpdate();
			System.out.println(count + "개의 도서재고정보를 수정했습니다.");	
			// 정상적으로 작업 완료되면 commit
			conn.commit();
			System.out.println("작업 완료!!");			
		} catch (Exception e) {
			e.printStackTrace();
			// 하나라도 예외가 발생했을 경우 rollback
			try {				
				conn.rollback();	
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} finally { //자원정리(역순으로)
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	} // insertOrder()

	// 대여정보 수정 (수정 필요)
	@Override
	public void updateOrderAdmin(int orderNum, String memId, int bookNum, Date orderDate, Date returnDate, int isAdd, int isReturn) {
		String sql = "UPDATE book_order SET mem_id=?,book_num=?,order_date=?,return_date=?,"
				+ "is_add=?, is_return=? WHERE order_num=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, memId); // 유효성 검사 추가요망
			pstmt.setInt(++cnt, bookNum);
			pstmt.setDate(++cnt, orderDate);
			pstmt.setDate(++cnt, returnDate);	
			pstmt.setInt(++cnt, isAdd);
			pstmt.setInt(++cnt, isReturn);			
			pstmt.setInt(++cnt, orderNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 대여정보를 수정했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateOrder()

	// 대여 정보 삭제
	@Override
	public void deleteOrderAdmin(int orderNum) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			//트랜잭션을 수동으로 처리하기 위해 auto commit 해제
			conn.setAutoCommit(false);
			// 대여정보삭제 전 해당 책 재고 정보 +1 필요 (반납X인 경우만 조건 추가 요망)
			sql = "UPDATE book SET book_volm_cnt = book_volm_cnt+1 "
					+ "WHERE book_num=(SELECT book_num FROM book_order WHERE order_num=?)";	
			//반납여부 X일때만 삭제하도록 수정 필요
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, orderNum);
			int count = pstmt1.executeUpdate();
			System.out.println(count + "개의 도서재고정보를 수정했습니다.");	

			sql = "DELETE FROM book_order WHERE order_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, orderNum);
			count = pstmt2.executeUpdate();
			System.out.println(count + "개의 대여정보를 삭제했습니다."); 
			// 정상적으로 작업 완료되면 commit
			conn.commit();
			System.out.println("작업 완료!!");			
		} catch (Exception e) {
			e.printStackTrace();
			// 하나라도 예외가 발생했을 경우 rollback
			try {				
				conn.rollback();	
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} finally { //자원정리(역순으로)
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	} // DeleteOrder()	
	
	
	// 통계 관리
	//도서별 대여 횟수
	@Override
	public void selectBookOrderStatsAdmin() {	
		String sql = "SELECT book_num, (SELECT book_title FROM book WHERE book_num=bo.book_num) 책제목, "
				+ "COUNT(*) AS 대여횟수 FROM book_order bo GROUP BY book_num ORDER BY 대여횟수 DESC, book_num";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("누적대여횟수\t책번호\t책제목");		
				System.out.println("-".repeat(100));
				do {
					System.out.print(rs.getInt("대여횟수")+"\t\t");
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.println(rs.getString("책제목"));			
				} while (rs.next());
			} else System.out.println("표시할 데이터가 없습니다.");
			System.out.println("-".repeat(100));
		} catch (Exception e) {e.printStackTrace();}
	} // selectBookOrderStats()
	
	//회원별 대여 횟수
	@Override
	public void selectMemberOrderStatsAdmin() {	
		String sql = "SELECT mem_id, COUNT(*) 대여횟수 FROM book_order GROUP BY mem_id "
				+ "ORDER BY 대여횟수 DESC, mem_id";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(100));
			if (rs.next()) { 
				System.out.println("누적대여횟수\t회원아이디");	
				System.out.println("-".repeat(100));
				do {
					System.out.print(rs.getInt("대여횟수")+"\t\t");	
					System.out.println(rs.getString("mem_id"));			
				} while (rs.next());
			} else System.out.println("표시할 데이터가 없습니다.");
			System.out.println("-".repeat(100));
		} catch (Exception e) {e.printStackTrace();}
	} // selectMemberOrderStats()
	
	//조회하는 대여 레코드 존재 여부 체크
	@Override
	public int checkOrderRecord(int orderNum) {
		String sql = "SELECT * FROM book_order WHERE order_num=?";	
		int count = 0;		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNum);
			try(ResultSet rs = pstmt.executeQuery();){
			if (rs.next()) count = 1; //레코드가 존재할 때 1 저장							
			}
		} catch (Exception e) {count = -1;} //오류 발생}	
		return count; 
	} // checkOrderRecord()
}
