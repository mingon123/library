package kr.library;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import util.DBUtil;

public class BookDAO_Jw {

	//랜덤 책 정보 - 숫자 입력받아 하나로 재사용
	public void randomBookInfo(int num) {
		BookDAO_mg mg = new BookDAO_mg();
		
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
				System.out.println("-".repeat(70));
				mg.selectDetailBook(randNum);
				System.out.println("-".repeat(70));
			} // for randNum
			/*
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
			 */
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


	// 대여번호가 현황기록에 존재하는지 확인하며, 해당 유저의 것인지 확인하는 함수 
	//	- 존재:true / 존재X 또는 에러:false
	public boolean checkNowOrderNum(int order_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND IS_RETURN=0 AND MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
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
	}//checkNowOrderNum


	// 책번호가 해당유저로 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	public boolean checkReserveBookNum(int book_num, String mem_id) {
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

		if(check == -1)
			System.out.println("에러 발생!");
		return check > 0? true:false;

	}//checkReserveBookNum


	// 예약번호가 해당 유저의 것이며, 예약테이블에 존재하는지 확인하는 함수 - 존재:true / 존재X 또는 에러:false 
	public boolean checkReserveReNum(int re_num, String mem_id) {
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

	}//checkReserveReNum


	// 예약테이블 행 삭제 - 예약테이블 num
	public void deleteReserveReNum(int re_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM RESERVATION WHERE RE_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);

			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("예약취소를 성공했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteReserveReNum


	// 예약테이블 행 삭제 - book num
	public void deleteReserveBookNum(int book_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			pstmt.setString(2, mem_id);

			int count = pstmt.executeUpdate();
			if(count > 0) System.out.println("예약한 책의 대여로 인해 예약목록에서 해당 책을 삭제합니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteReserveBookNum


	// 대여번호가 연장이 가능한지 확인하는 함수
	//	- 먼저 대여번호가 유효한지 체크하기때문에 연장유무만 TODO -> 본인 것이여만 함
	public boolean checkOrderAdd(int order_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM BOOK_ORDER WHERE ORDER_NUM=? AND MEM_ID=? AND IS_ADD=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
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


	// 공지사항 번호가 유효한지 확인해주는 함수  / 유효 : true , 유효X : false 
	public boolean checkNoticeNum(int notice_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int check = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM NOTICE WHERE NOTICE_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();

			if(rs.next()) check = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(check == -1) System.out.println("에러 발생!");

		return check >= 1? true: false; 

	}//checkNoticeNum


	// 공지사항 조회수 조정 함수 (조회시 + 1) 
	public void updateNoticeViewCount(int notice_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE NOTICE SET NOTICE_VIEW = NOTICE_VIEW + 1 WHERE NOTICE_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			int count = pstmt.executeUpdate();

			if(count <= 0) {
				System.out.println("조회수 업데이트 중 오류발생!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	} // updateNoticeViewCount


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
		int check = -9999;
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
		if(check == -9999) System.out.println("에러 발생!");

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


	// book_num으로 book_volm_cnt 구하는 함수
	public int selectBookCount(int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int res = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT BOOK_VOLM_CNT FROM BOOK WHERE BOOK_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();

			if(rs.next()) res = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("에러 발생!");
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		if(res == -1) System.out.println("에러 발생!");

		return res;

	}//checkNowOrderNum


	// 대여(insert book_order) - 대여 추가후 책 갯수 조정
	public void insertBookOrder(String mem_id,int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			boolean bookReserve = checkReserveBookNum(book_num, mem_id);

			if(bookReserve) {
				int re_num = selectBookNumToReNum(book_num, mem_id);
				int rank = calcReserveRank(re_num, 2);
				int bookCount = selectBookCount(book_num);

				if(rank <= bookCount) { // 예약 중인 책이 있고, 예약 순위가 높아서 대여 가능한 경우
					conn = DBUtil.getConnection();
					sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					pstmt.setInt(2, book_num);

					int count = pstmt.executeUpdate();	

					if(count > 0) {
						System.out.println(book_num+"번책 대여 성공!");
						updateBookCount(book_num, -1);
						deleteReserveBookNum(book_num, mem_id);
					}
				} else {
					System.out.println("예약 순위가 낮은 관계로 대여가 불가능합니다.");
				}


				//}else if(!bookReserve) { 
				// 해당 책을 예약한 기록이 없는 경우  -> 예약 안했는데 갑자기 책 빌려가게 되면 어떡하지? TODO 
				//	-> 예약이 생길경우 책 테이블에 isReserve ? 같은 컬럼 추가?

			}else {
				conn = DBUtil.getConnection();
				sql = "INSERT INTO book_order (ORDER_NUM, MEM_ID, BOOK_NUM)VALUES(book_order_seq.nextval,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				pstmt.setInt(2, book_num);

				int count = pstmt.executeUpdate();	

				if(count > 0) {
					System.out.println(book_num+"번책 대여 성공!");
					updateBookCount(book_num, -1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertBookOrder


	// 책갯수 조정 함수 (book_volm_cnt + k)
	public void updateBookCount(int book_num, int k) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE book SET book_volm_cnt = book_volm_cnt + ? WHERE book_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, k);
			pstmt.setInt(2, book_num);
			int count = pstmt.executeUpdate();

			if(count <= 0) {
				System.out.println("책갯수 조정 중 오류발생!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	} // updateBookCount


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
				System.out.printf("%5s \t%s \t\t%5s \t%s\n", "번호", "대여일자", "반납여부(O/X)", "책제목");
				do {
					System.out.printf("%5d \t%7s \t%5s \t\t%s\n", 
							rs.getInt("ORDER_NUM"),
							rs.getDate("ORDER_DATE").toString(),
							rs.getString("RETURN"),
							rs.getString("BOOK_TITLE")
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
				System.out.printf("%5s \t%7s \t\t%7s \t%5s \t\t%5s\n", 
						"대여번호", "대여일자", "반납예정일자","연장가능", "책제목");
				do {
					System.out.printf("%5s \t\t%s \t%s \t%5s \t\t%s\n", 
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
				System.out.printf("%5s \t%s  %s \t%s \n", 
						"번호", "총 예약인원", "예약순위", "책제목");
				int reNum = rs.getInt("RE_NUM");

				do {
					System.out.printf("%5d \t\t%5d \t%10d \t%-27s \n", 
							rs.getInt("RE_NUM"),
							calcReserveRank(reNum, 1),
							calcReserveRank(reNum, 2),
							rs.getString("BOOK_TITLE")
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


	// Book_num 이랑 Mem_id 로 Re_num 구하는 함수
	public int selectBookNumToReNum(int book_num, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int res = -1;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT RE_NUM FROM RESERVATION WHERE BOOK_NUM=? AND MEM_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			pstmt.setString(2, mem_id);

			rs = pstmt.executeQuery();
			if(rs.next()) res = rs.getInt("RE_NUM");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
		return res;
	} // selectBookNumToReNum


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


	// order_num -> 책이름, 저자 출력
	public void selectOrderNumToBookInfo(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT BOOK_TITLE, BOOK_AUTHOR FROM book WHERE BOOK_NUM = "
					+ "(SELECT BOOK_NUM FROM BOOK_ORDER WHERE ORDER_NUM = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("제목 : " + rs.getString("BOOK_TITLE"));
				System.out.println("저자 : " + rs.getString("BOOK_AUTHOR"));
			} else {
				System.out.println("책 정보를 불러오는 것에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // selectOrderNumToBookInfo


	// order_num -> book_num
	public int selectOrderNumToBookNum(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		int res = -1;

		try {
			conn = DBUtil.getConnection();
			sql =  "SELECT BOOK_NUM FROM BOOK_ORDER WHERE ORDER_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if(rs.next()) res = rs.getInt("BOOK_NUM");

			if(res == -1)
				System.out.println("book_num을 불러오던중 오류 발생!");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("book_num을 불러오던중 오류 발생");
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

		return res;
	} // selectOrderNumToBookNum


	// 리뷰 등록
	public void insertReviewInfo(int book_num, String content, int rate, String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO REVIEW(REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,MEM_ID) VALUES (review_seq.nextval,?,?,?,?)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			pstmt.setString(2, content);
			pstmt.setInt(3, rate);
			pstmt.setString(4, mem_id);

			int count = pstmt.executeUpdate();

			if(count > 0) System.out.println("리뷰등록이 완료되었습니다.");
			else System.out.println("리뷰 등록 오류!");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("리뷰 등록 오류");
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 한글은 2칸, 영어/숫자는 1칸으로 계산하는 함수
	// 한글 2칸, 영어/숫자/특수문자는 1칸으로 계산하는 함수
	public int consoleWidth(String str) {
		int width = 0;
		for (char ch : str.toCharArray()) {
			if (ch >= '가' && ch <= '힣') { 
				width += 2; // 한글은 2칸
			} else if (Character.isWhitespace(ch)) { 
				width += 1; // 공백은 1칸
			} else if (ch >= '!' && ch <= '~') { 
				width += 1; // 영어, 숫자, 일반 특수문자는 1칸
			} else { 
				width += 2; // 기타 유니코드 문자(한자, 일본어 등)는 2칸
			}
		}
		return width;
	}


	// 콘솔 너비 기준으로 문자열을 자르는 함수 (한글 2칸 고려)
	public String cutTitle(String str, int maxWidth) {
		int width = 0;
		StringBuilder sb = new StringBuilder();
		for (char ch : str.toCharArray()) {
			int charWidth = (ch >= '가' && ch <= '힣') ? 2 : 1;
			if (width + charWidth > maxWidth - 3) { // "..." 포함하여 최대 길이 초과 방지
				sb.append("...");
				break;
			}
			sb.append(ch);
			width += charWidth;
		}
		return sb.toString();
	}


}
