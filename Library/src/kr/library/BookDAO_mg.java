package kr.library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;

public class BookDAO_mg {
	private BookDAO_Jw jw;
	private BookDAO_il il; //checkBookRecord(int book_num) 사용
	
	// 희망도서신청
	public void insertWishBook(String title,String author,String publisher,String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;		
		try {
			conn = DBUtil.getConnection();
			
			if(isWishBook(title, author)) {
				System.out.println("해당 도서는 보유중입니다.");
				return;
			}
			
			sql = "INSERT INTO wish_book(wish_num,wish_title,wish_author,wish_publisher,wish_date,mem_id) VALUES (wish_book_seq.nextval,?,?,?,SYSDATE,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, title);
			pstmt.setString(++cnt, author);
			pstmt.setString(++cnt, publisher);
			pstmt.setString(++cnt, memId);

			pstmt.executeUpdate();
			System.out.println("희망도서 신청 완료!");
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertWishBook
	
	// 희망도서 신청 시 동일한 제목+저자인 도서가 있으면 알림
	private boolean isWishBook(String book_title,String book_author) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM book WHERE book_title=? AND book_author=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_title);
			pstmt.setString(2, book_author);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count>0)	return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}
		return false;
	} // isWishBook
	
	// 희망도서 목록보기
	public void selectWishBookInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date FROM wish_book ORDER BY wish_date DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			
			if(rs.next()) {
				System.out.println("번호\t제목\t저자\t출판사\t신청일");
				do {
					System.out.print(rs.getInt("wish_num"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_title"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_author"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_publisher"));
					System.out.print("\t");
					System.out.println(rs.getDate("wish_date"));
				} while(rs.next());
			} else {
				System.out.println("대여,예약도서가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectWishBookInfo
	
	// 내 희망도서 목록
	public boolean selectMyWishBookInfo(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean hasBook = false;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT wish_num,wish_title,wish_author,wish_publisher,wish_date FROM wish_book WHERE mem_id=? ORDER BY wish_date DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				hasBook = true;
				System.out.println("번호\t제목\t저자\t출판사\t신청일");
				do {
					System.out.print(rs.getInt("wish_num"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_title"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_author"));
					System.out.print("\t");
					System.out.print(rs.getString("wish_publisher"));
					System.out.print("\t");
					System.out.println(rs.getDate("wish_date"));
				} while(rs.next());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return hasBook;
	} // selectMyWishBookInfo
	
	// 희망도서삭제
	public void deleteWishBookInfo(String memId, int wishNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM wish_book WHERE mem_id=? AND wish_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setInt(2, wishNum);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("희망도서를 삭제했습니다.");
			else System.out.println("희망도서를 삭제할 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteWishBookInfo
	
	
	// Q&A 질문 등록
	public void insertQNA(String qnaTitle, String qnaContent,String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO qna (qna_num,qna_title,qna_content,q_date,mem_id) VALUES (qna_seq.nextval,?,?,SYSDATE,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, qnaTitle);
			pstmt.setString(++cnt, qnaContent);
			pstmt.setString(++cnt, memId);
			pstmt.executeUpdate();
			System.out.println("질문 등록 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertQNA
	
	// Q&A 목록보기
	public void selectQNAInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT qna_num,qna_title,qna_content,q_date FROM qna ORDER BY q_date DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			
			if(rs.next()) {
				System.out.println("Q&A 목록");
				System.out.println("번호\t제목\t내용\t문의일");
				do {
					System.out.print(rs.getInt("qna_num"));
					System.out.print("\t");
					System.out.print(rs.getDate("q_date"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_title"));
					System.out.print("\t");
					System.out.println(rs.getString("qna_content"));
				} while(rs.next());
			} else {
				System.out.println("Q&A 내역이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectQNAInfo
	
	// 내 qna 목록보기
	public boolean selectMyQNAInfo(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean hasQNA = false;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT qna_num,qna_title,q_date,qna_re,a_date FROM qna WHERE mem_id=? ORDER BY q_date DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				hasQNA = true;
				System.out.println("번호\t제목\t등록일\t답변\t답변일");
				do {
					System.out.print(rs.getInt("qna_num"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_title"));
					System.out.print("\t");
					System.out.print(rs.getDate("q_date"));
					System.out.print("\t");
					System.out.print(rs.getString("qna_re"));
					System.out.print("\t");
					System.out.println(rs.getDate("a_date"));
				} while(rs.next());
			} else {
				System.out.println("등록된 qna가 없습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return hasQNA;
	} // selectMyQNAInfo
	
	// qna삭제
	public void deleteQNAInfo(String memId, int QNANum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try { 
			conn = DBUtil.getConnection();
			sql = "DELETE FROM qna WHERE mem_id=? AND qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setInt(2, QNANum);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("QNA를 삭제했습니다.");
			else System.out.println("QNA를 삭제할 수 없습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteQNA	
	
	
	// 회원정보 조회
	public void selectMemberInfo(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_name,mem_cell,mem_date,mem_email FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			
			if(rs.next()) {
				System.out.printf("현재 접속중인 계정은 %s입니다.\n",memId);
				System.out.println("이름\t전화번호\t\t가입일\t\t이메일");
				do {
					System.out.print(rs.getString("mem_name"));
					System.out.print("\t");
					System.out.print(rs.getString("mem_cell"));
					System.out.print("\t");
					System.out.print(rs.getDate("mem_date"));
					System.out.print("\t");
					System.out.println(rs.getString("mem_email"));
				} while(rs.next());
			} else {
				System.out.println("회원정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectMemberInfo
	
	// 조회하는 레코드 존재 여부 체크
	public int checkRecord(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			if(rs.next()) count = 1;
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	} // checkRecord
	
	// 비밀번호 확인
	public int checkPassword(String memId, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) count = 1;
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	} // checkPassword
	
	// 회원정보 수정
	public void updateMemberInfo(String memId,String password,String newName,String newEmail){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			if(checkPassword(memId, password) != 1) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return;
			}
			
			sql = "UPDATE member SET mem_name=?,mem_email=?,mem_mdate=SYSDATE WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, newName);
			pstmt.setString(++cnt, newEmail);
			pstmt.setString(++cnt, memId);
			
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("회원 정보 수정 완료했습니다.");
			else System.out.println("회원 정보를 찾을 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateMemberInfo
	
	// 회원정보 삭제
	public void deleteMemberInfo(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("회원탈퇴를 완료했습니다.");
			else System.out.println("회원 정보를 찾을 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteMemberInfo
	
	
	
	// 정지상태알림
	public boolean isMemStop(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_stop_date FROM member WHERE mem_id=? AND mem_stop_date>sysdate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {					
					System.out.print("정지 상태입니다. 정지해제일 : ");
					System.out.println(rs.getDate("mem_stop_date"));
				} while (rs.next());
				System.out.println("-".repeat(90));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	} // isMemStop
	
	// 연체알림
	public boolean isOverReturn(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
	        sql = "SELECT b.book_title, b.book_author, (SYSDATE - o.return_date) AS over_return " +
	                "FROM book_order o, book b " +
	                "WHERE b.book_num = o.book_num " +
	                "AND mem_id = ? " +
	                "AND is_return = 0 " +
	                "AND return_date < SYSDATE " +
	                "ORDER BY o.return_date DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
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
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	} // isOverReturn
	
	// 반납일 알림(가장 임박한 책의 반납일)
	public boolean isReturnDateNotification(String numId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT book_title,book_author,return_date FROM (SELECT b.book_title,b.book_author,o.return_date "
					+ "FROM book b,book_order o WHERE b.book_num=o.book_num "
					+ "AND o.mem_id=? AND o.return_date>=sysdate AND o.is_return=0 ORDER BY o.return_date) WHERE ROWNUM = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numId);
			rs = pstmt.executeQuery();
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
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	} // isReturnDateNotification
	
	// 예약도서 대여 가능 알림
	public boolean isReservationNotification(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		jw = new BookDAO_Jw(); // 나중에 삭제
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

	
	// 카테고리별 책
	public void selectCategoryOfBook(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql  = "SELECT * FROM ("
					+ "SELECT book_num,book_title,book_author,book_category FROM book WHERE book_category=?"
					+ "ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("책번호\t카테고리\t책제목\t\t\t저자");
				do {
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.print(rs.getString("book_category")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("book_author")+"\t");
				} while(rs.next());
			} else {
				System.out.println("표시할 정보가 없습니다.");
			}
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	} // categoryOfBook
	
	// 추천순위별 책
	public void selectRankOfBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql  = "SELECT * FROM ("
					+ "SELECT book_num,book_title,book_author,book_rank FROM book WHERE book_rank<=200 "
					+ "ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 10 ORDER BY book_rank";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("책번호\t책제목\t\t\t저자");
				do {
					System.out.print(rs.getInt("book_num")+"\t");
//					System.out.print(rs.getInt("book_rank")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("book_author")+"\t");
				} while(rs.next());
			} else {
				System.out.println("표시할 정보가 없습니다.");
			}
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	} // rankOfBook
	
	// 신간책별 책
	public void selectNewOfBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
	        sql = "SELECT * FROM ("
	                + "SELECT book_num, book_title, book_author, book_category, book_p_year "
	                + "FROM book "
	                + "WHERE TO_NUMBER(book_p_year) >= TO_NUMBER(TO_CHAR(ADD_MONTHS(SYSDATE, -24), 'YYYY')) "
	                + "ORDER BY DBMS_RANDOM.VALUE) "
	                + "WHERE ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("책번호\t출판년도\t카테고리\t책제목\t\t\t저자");
				do {
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.print(rs.getString("book_p_year")+"\t");
					System.out.print(rs.getString("book_category")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("book_author")+"\t");
				} while(rs.next());
			} else {
				System.out.println("표시할 정보가 없습니다.");
			}
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	} // newOfBook
	
	// 대여베스트별 책
	public void selectOrderBestOfBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql  = "SELECT * FROM ("
					+ "SELECT b.book_num,b.book_title,b.book_author,b.book_category,count(o.book_num) AS cnt "
					+ "FROM book_order o JOIN book b ON b.book_num=o.book_num "
					+ "GROUP BY b.book_num,book_title, book_author, book_category ORDER BY cnt DESC)"
					+ "WHERE ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("책번호\t대여횟수\t카테고리\t책제목\t\t\t저자");
				do {
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.print(rs.getInt("cnt")+"\t");
					System.out.print(rs.getString("book_category")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("book_author")+"\t");
				} while(rs.next());
			} else {
				System.out.println("표시할 정보가 없습니다.");
			}
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	} // orderBestOfBook
	
	// 리뷰 화면 
	public void selectReviewInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT r.review_num,b.book_num,b.book_title,r.review_content,r.review_rate,r.review_reg_date "
					+ "FROM review r, book b WHERE r.book_num=b.book_num "
					+ "ORDER BY review_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if (rs.next()) {
				System.out.println("리뷰번호\t책번호\t평점\t리뷰등록일\t\t책제목\t\t\t리뷰내용\t");			
				do {
					System.out.print(rs.getInt("review_num")+"\t");							
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.print(rs.getInt("review_rate")+"\t");
					System.out.print(rs.getDate("review_reg_date")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("review_content")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectReviewInfo
	
	// 리뷰 상세 정보
	public void selectDetailReview(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT r.review_num,b.book_num,b.book_title,r.review_content,r.review_rate,r.review_reg_date,r.mem_id "
					+ "FROM review r, book b WHERE r.book_num=b.book_num "
					+ "AND review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("리뷰번호 : "+ rs.getInt("review_num"));
				System.out.println("책번호 : "+ rs.getInt("book_num"));
				System.out.println("책제목 : "+ rs.getString("book_title"));
				System.out.println("리뷰내용 : "+ rs.getString("review_content"));
				System.out.println("평점 : "+ rs.getInt("review_rate"));
				System.out.println("작성일 : "+ rs.getDate("review_reg_date"));
				System.out.println("작성자 : "+ rs.getString("mem_id"));
			} else {
				System.out.println("검색된 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	//조회하는 리뷰 레코드 존재 여부 체크
	public int checkReviewRecord(int reviewNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;		
		int count = 0;		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM review WHERE review_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = 1; //레코드가 존재할 때 1 저장				
			} // if					
		} catch (Exception e) {
			count = -1; //오류 발생
		} finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
		return count; 
	} // checkReviewRecord()
}
