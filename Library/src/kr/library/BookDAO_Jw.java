package kr.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashSet;

import util.DBUtil;

public class BookDAO_Jw {

	// insert
	public void insertInfo(String title,String author,String publisher,Integer publication_year,String category,Integer rank,Integer volm_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO book VALUES (book_seq.nextval,?,?,NVL(?,''),?,NVL(?,'기타'),?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(++cnt, title);
			pstmt.setString(++cnt, author);
			pstmt.setString(++cnt, publisher);
			pstmt.setObject(++cnt, (publication_year != null) ? publication_year : 0, Types.INTEGER);
			pstmt.setString(++cnt, category);
			pstmt.setObject(++cnt, (rank != null) ? rank : 0, Types.INTEGER);
			pstmt.setObject(++cnt, (volm_cnt != null) ? volm_cnt : 0, Types.INTEGER);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 삽입했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertInfo

	//랜덤 책 정보 - 숫자 입력받아 하나로 재사용
	public void randomBookInfo(int num) { // 평점 정보 출력하게 추가?
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		HashSet<Integer> hs = new HashSet<Integer>();
		int bookLength = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) bookLength = rs.getInt(1);

			while(hs.size() < num ) {
				hs.add((int) (Math.random() * bookLength) + 1);
			}

			sql = "SELECT * FROM book WHERE BOOK_NUM=?";
			pstmt = conn.prepareStatement(sql);

			for (int randNum : hs) {
				pstmt.setInt(1, randNum);
				rs = pstmt.executeQuery();
				System.out.println();
				if(rs.next()) {
					System.out.println("책번호 : " + rs.getInt("BOOK_NUM"));
					System.out.println("제목 : " + rs.getString("BOOK_TITLE"));
					System.out.println("저자 : " + rs.getString("BOOK_AUTHOR"));

					String publisher = rs.getString("BOOK_PUBLISHER");
					if(publisher == null) publisher = "";
					System.out.println("출판사 : " + publisher);

					String publication_year = rs.getString("BOOK_P_YEAR");
					if(publication_year == null) publication_year = "";
					System.out.println("출판년도 : " + publication_year);

					String category = rs.getString("BOOK_CATEGORY");
					if(category == null) category = "";
					System.out.println("카테고리 : " + category);

					String rank = rs.getString("BOOK_RANK");
					if(rank == null) rank = "";
					System.out.println("추천순위 : " + rank);

					String volm_cnt = rs.getString("BOOK_VOLM_CNT");
					if(volm_cnt == null) volm_cnt = "";
					System.out.println("남은 권수 : " + volm_cnt);
					System.out.println("등록일 : " + rs.getDate("BOOK_REG_DATE"));
				} else {
					System.out.println("오류발생");
				}
			} // for randNum
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // randomBookInfo

	// 대여가능 여부 판별 함수. 대여가능:1 책남아있는 권수0:0 대여권수 다 참:-1
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
		//테스트용. 잘되면 이 부분 지우기
		if(bookCount == -1 || orderCount == -1)
			System.out.println("에러발생!! 대여여부 판별 불가");

		if(bookCount > 0) {
			if(orderCount < 3) {
				return 1;
			}else return -1;
		}else return 0;
	}//canOrder

	// 책번호가 존재하는지 확인하는 함수- 존재:1 존재x:0 에러:-1
	public int checkBookNum(int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM book WHERE BOOK_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		if(check == -1)
			System.out.println("에러 발생! 책 번호 있는지 확인 불가");

		return check;
	}//checkBookNum

	// 대여번호가 현황기록에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false
	public boolean checkNowOrderNum(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND IS_RETURN=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		if(check == -1)
			System.out.println("에러 발생!");
		return check >= 1? true:false;

	}//checkNowOrderNum

	// 예약번호가 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false //TODO
	public boolean checkReserveNum(int re_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM RESERVATION WHERE RE_NUM=? AND MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.setString(2, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		if(check == -1)
			System.out.println("에러 발생!");
		return check >= 1? true:false;

	}//checkReserveNum

	// 예약테이블 행 삭제
	public void deleteReserve(int re_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM RESERVATION WHERE RE_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("취소를 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteReserve

	// 대여번호가 연장이 가능한지 확인하는 함수
	//	- 먼저 대여번호가 유효한지 체크하기때문에 연장유무만
	public boolean checkOrderAdd(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND IS_ADD=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? true: false; 

	}//checkNowOrderNum

	//	연장 이전에 반납기한이 연장하려는 시기보다 같거나 커야함 
	// 연장 불가능 : false , 연장 가능 : true 
	public boolean checkOrderAddReturnDate(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT TRUNC(SYSDATE - RETURN_DATE) FROM BOOK_ORDER WHERE ORDER_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? false: true; 
	}//checkOrderAddReturnDate

	// 정지상태인지 확인 ->  정지상태 : false , 정지X : true
	public boolean checkMemStop(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			//값이 양수여야 정지X
			sql = "SELECT TRUNC(SYSDATE - NVL(MEM_STOP_DATE, SYSDATE-1)) FROM MEMBER WHERE MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? true: false; 
	}//checkMemStop

	//이미 해당 유저가 같은 책을 예약했는지 확인  - 중복시 :true  중복 아닐시 :false
	public boolean isDuplicatedReserve(int book_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			pstmt.setString(2, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? true: false;

	}//checkNowOrderNum

	// 대여(insert book_order) - 대여 추가후 책 갯수 조정
	public void insertBookOrder(String mem_id,int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setInt(2, book_num);
			int count = pstmt.executeUpdate();

			sql = "UPDATE book SET book_volm_cnt = (SELECT book_volm_cnt FROM book WHERE book_num = ?) - 1 WHERE book_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			pstmt.setInt(2, book_num);
			int count2 = pstmt.executeUpdate();

			if(count > 0 && count2 > 0 ) {
				System.out.println(book_num+"번책 대여 성공!");
			}
			else {
				System.out.println("대여 중 오류발생!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertBookOrder

	// reservation 테이블에 insert - 예약 테이블에 insert
	public void insertReserve(String mem_id,int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO reservation VALUES(reservation_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setInt(2, book_num);
			int count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println(book_num+"번책 예약 성공!");
			}
			else {
				System.out.println("예약 중 오류발생!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertBookOrder

	// 예약가능 여부 판별 함수. 예약가능:1 책남아있는 권수0이 아님:0 예약권수 다 참:-1
	public int canReservation(String mem_id,int book_num) {
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
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();

			if(rs.next()) bookCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM reservation WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
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


	// 로그인한 유저의 대여내역보기(현황 + 기록) - 1번 최신순, 2번 오래된순
	public void selectUserOrderInfo(String mem_id, int selectNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT ORDER_NUM, BOOK_TITLE, ORDER_DATE, "
					+ "CASE WHEN IS_RETURN=1 THEN 'O' WHEN IS_RETURN=0 THEN 'X' END AS RETURN "
					+ "FROM BOOK_ORDER  JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ? ORDER BY ORDER_NUM ";
			if(selectNum == 1) sql += "DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.printf("%5s %30s \t\t%10s \t%5s\n", "번호", "책제목", "대여일자", "반납여부(O/X)");
				do {
					System.out.printf("%5d %30s \t%7s \t%5s\n", 
							rs.getInt("ORDER_NUM"),
							rs.getString("BOOK_TITLE"),
							rs.getDate("ORDER_DATE").toString(),
							rs.getString("RETURN")
							);

				} while(rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectUserOrderInfo

	// 로그인한 유저의 대여 현황만 출력
	public void selectUserNowOrderInfo(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT ORDER_NUM, BOOK_TITLE, ORDER_DATE, RETURN_DATE, "
					+"CASE WHEN IS_ADD=1 THEN 'X' WHEN IS_ADD=0 THEN 'O' END AS CAN_ADD FROM BOOK_ORDER "
					+ "JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ? AND IS_RETURN = 0 ORDER BY ORDER_NUM";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.printf("%5s %30s \t\t%10s \t%10s \t%5s\n", 
						"대여번호", "책제목", "대여일자", "반납예정일자","연장가능");
				do {
					System.out.printf("%5s %30s \t%10s \t%10s %5s\n", 
							rs.getInt("ORDER_NUM"),
							rs.getString("BOOK_TITLE"),
							rs.getDate("ORDER_DATE").toString(),
							rs.getDate("RETURN_DATE").toString(),
							rs.getString("CAN_ADD")
							);
				} while(rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectUserNowOrderInfo

	// 로그인한 유저의 예약 현황 출력
	public void selectUserNowReserveInfo(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT RE_NUM, BOOK_TITLE FROM RESERVATION JOIN BOOK USING(BOOK_NUM) WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.printf("%10s\t%30s \t\t%10s \t%10s\n", 
						"번호","책제목", "총 예약인원", "예약순위");
				int reNum = rs.getInt("RE_NUM");

				do {
					System.out.printf("%10d\t%30s \t%10d \t%10d\n", 
							rs.getInt("RE_NUM"),
							rs.getString("BOOK_TITLE"),
							calcReserveRank(reNum, 1),
							calcReserveRank(reNum, 2)
							);
				} while(rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectUserNowReserveInfo


	// 반납 진행 함수 - update is_return
	public void updateOrderReturn(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE BOOK_ORDER SET IS_RETURN = 1 WHERE ORDER_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,order_num);
			int count = pstmt.executeUpdate();

			sql = "UPDATE BOOK SET BOOK_VOLM_CNT = BOOK_VOLM_CNT + 1 WHERE BOOK_NUM IN "
					+ "(SELECT BOOK_NUM FROM BOOK_ORDER WHERE ORDER_NUM = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			int count2 = pstmt.executeUpdate();

			if(count > 0 && count2 > 0) System.out.println("반납 진행중입니다.");
		} catch (Exception e) {
			System.out.println("에러발생");
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateOrderReturn

	// 정지일수 update - 이전에 정지일수가 있는 경우 + , 새로 생길경우 그대로
	public void updateStopDate(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE MEMBER SET MEM_STOP_DATE = "
					+ "CASE "
					+ "WHEN (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?) >= SYSDATE THEN MEM_STOP_DATE "  
					+ "WHEN NVL(MEM_STOP_DATE, SYSDATE) > SYSDATE "
					+ "THEN MEM_STOP_DATE + (SYSDATE - (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?)) "
					+ "ELSE SYSDATE + (SYSDATE - (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?)) "
					+ "END "
					+ "WHERE MEM_ID = (SELECT MEM_ID FROM BOOK_ORDER WHERE ORDER_NUM = ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,order_num);
			pstmt.setInt(2,order_num);
			pstmt.setInt(3,order_num);
			pstmt.setInt(4,order_num);
			int count = pstmt.executeUpdate();

			if(count <= 0) System.out.println("갱신 실패");
		} catch (Exception e) {
			System.out.println("에러발생!!!!");
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateStopDate

	//	해당 유저의 현재 대여 수 	0: true	1이상: false 
	public boolean checkZeroOrder(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE MEM_ID=? AND IS_RETURN=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? false: true; 

	}//checkZeroOrder

	// 지정한 대여번호에 대한 연체일 알림 / 연체가 아닐 시 정상 반납 알림
	public void selectLateReturn(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int res = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT TRUNC(SYSDATE - RETURN_DATE) AS res FROM BOOK_ORDER WHERE ORDER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) res = rs.getInt("res");

			if(res > 0) System.out.println(res+"일 연체되었습니다.");
			else System.out.println("정상 반납 되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectLateReturn

	// 연장 진행 함수 - update return_date
	public void updateReturnDate(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE BOOK_ORDER SET RETURN_DATE = (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?) + 7,IS_ADD=1 WHERE ORDER_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,order_num);
			pstmt.setInt(2,order_num);

			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("연장을 성공했습니다.");
		} catch (Exception e) {
			System.out.println("에러발생");
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateReturnDate

	// 예약 순위 관련 함수 - num==1 : 총 인원수, num==2 예약 순위
	public int calcReserveRank(int re_num, int num) {
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
			pstmt.setInt(1, re_num);
			rs = pstmt.executeQuery();

			if(rs.next()) allCount = rs.getInt(1);

			sql = "SELECT COUNT(*) FROM RESERVATION WHERE RE_NUM > ? AND "
					+ "BOOK_NUM = (SELECT BOOK_NUM FROM RESERVATION WHERE RE_NUM = ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.setInt(2, re_num);
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


	// 목록보기
	public void selectInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(50));

			if(rs.next()) {
				System.out.println("번호\t제목\t저자\t카테고리\t출판년도");
				do {
					System.out.print(rs.getInt("num"));
					System.out.print("\t");
					System.out.print(rs.getString("title"));
					System.out.print("\t");
					System.out.print(rs.getString("author"));
					System.out.print("\t");
					System.out.print(rs.getString("category"));
					System.out.print("\t");
					System.out.println(rs.getInt("publication_year"));
				} while(rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectInfo

	// 상세정보보기
	public void selectDetailInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("책번호 : " + rs.getInt("num"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("저자 : " + rs.getString("author"));

				String publisher = rs.getString("publisher");
				if(publisher == null) publisher = "";
				System.out.println("출판사 : " + publisher);

				String publication_year = rs.getString("publication_year");
				if(publication_year == null) publication_year = "";
				System.out.println("출판년도 : " + publication_year);

				String category = rs.getString("category");
				if(category == null) category = "";
				System.out.println("카테고리 : " + category);

				String rank = rs.getString("rank");
				if(rank == null) rank = "";
				System.out.println("추천순위 : " + rank);

				String volm_cnt = rs.getString("volm_cnt");
				if(volm_cnt == null) volm_cnt = "";
				System.out.println("남은 권수 : " + volm_cnt);
				System.out.println("등록일 : " + rs.getDate("reg_date"));
			} else {
				System.out.println("검색된 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // selectDetailInfo

	// update
	public void updateInfo(int num,String title,String author,String publisher,Integer publication_year,String category,Integer rank,Integer volm_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE book SET title=?,author=?,publisher=?,publication_year=?,category=NVL(?,'기타'),rank=?,volm_cnt=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(++cnt, title);
			pstmt.setString(++cnt, author);
			pstmt.setString(++cnt, publisher);
			pstmt.setObject(++cnt, (publication_year != null) ? publication_year : 0, Types.INTEGER);
			pstmt.setString(++cnt, category);
			pstmt.setObject(++cnt, (rank != null) ? rank : 0, Types.INTEGER);
			pstmt.setObject(++cnt, (volm_cnt != null) ? volm_cnt : 0, Types.INTEGER);
			pstmt.setInt(++cnt, num);

			int count = pstmt.executeUpdate();
			System.out.println(count + "개 행의 정보를 수정했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateInfo

	// delete
	public void deleteInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM book WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();
			System.out.println(count+"개 행을 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // delete

}
