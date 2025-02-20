package kr.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

/**
 * @author Lilly
 * @date 2025. 2. 19. - 오후 2:23:39
 * @subject	 관리자 메뉴
 * @content 
 */
public class BookDAO_il {
	//회원 관리
	//회원 목록보기
	public void selectMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member ORDER BY mem_name"; // 이름순으로 정렬
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				// 출력항목: ID,이름,전화번호,e-mail,대여정지기한 (나머지는 상세보기에서 확인)
				System.out.println("ID\t이름\t전화번호\t\te-mail\t\t대여정지기한");
				do {
					System.out.print(rs.getString("mem_id")+"\t");
					System.out.print(rs.getString("mem_name")+"\t");
					System.out.print(rs.getString("mem_cell")+"\t");	
					System.out.print(rs.getString("mem_email")+"\t");						
					System.out.println(rs.getDate("mem_stop_date"));
					//Date mem_stop_date = rs.getDate("mem_stop_date"); // null일 경우 공란 처리 필요
					//if(mem_stop_date == null) mem_stop_date = ""; // 수정요망
					//System.out.println(mem_stop_date);				
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectMember()

	//조회하는 회원 레코드 존재 여부 체크
	public int checkMemberRecord(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;		
		int count = 0;		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = 1; //레코드가 존재할 때 1 저장				
			} // if					
		} catch (Exception e) {
			count = -1; //오류 발생
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
		return count; 
	} // checkMemberRecord()

	//회원 상세정보 확인
	public void selectDetailMember(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 비밀번호만 미출력
				System.out.println("회원ID : " + rs.getString("mem_id"));
				System.out.println("이름 : " + rs.getString("mem_name"));	
				System.out.println("전화번호 : " + rs.getString("mem_cell"));						
				System.out.println("이메일 : " + rs.getString("mem_email"));
				System.out.println("가입일 : " + rs.getDate("mem_date"));
				System.out.println("마지막 정보수정일 : " + rs.getDate("mem_mdate"));
				System.out.println("정지일 : " + rs.getDate("mem_stop_date"));
			} else {
				System.out.println("검색된 정보가 없습니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
	} // selectDetailMember

	// 회원 정보 등록
	public void InsertMember(String mem_id, String mem_pw, 
			String mem_name, String mem_cell, String mem_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO member (mem_id, mem_pw, mem_name, mem_cell, mem_email, mem_date, mem_mdate, mem_stop_date) "
					+ "VALUES (?,?,?,?,?,SYSDATE,null,null)"; //전체를 넣을땐 컬럼명 생략 가능
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, mem_id);
			pstmt.setString(++cnt, mem_pw); // 유효성 검사 추가요망
			pstmt.setString(++cnt, mem_name);
			pstmt.setString(++cnt, mem_cell);
			pstmt.setString(++cnt, mem_email);				
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 회원정보를 등록했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertMember()

	// 회원 정보 수정
	public void updateMember(String mem_id, String mem_pw, 
			String mem_name, String mem_cell, String mem_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE member SET mem_pw=?,mem_name=?,mem_cell=?,mem_email=?,mem_mdate=SYSDATE WHERE mem_id=?"; //전체를 넣을땐 컬럼명 생략 가능
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, mem_pw); // 유효성 검사 추가요망
			pstmt.setString(++cnt, mem_name);
			pstmt.setString(++cnt, mem_cell);
			pstmt.setString(++cnt, mem_email);	
			pstmt.setString(++cnt, mem_id);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 회원정보를 수정했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateMember()

	// 회원 정보 삭제
	public void deleteMember(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM member WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 회원정보를 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteMember()

	//도서 목록 조회(우선 20개만-> 조회 범위 재검토요망)
	public void selectBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book WHERE book_num<=20 ORDER BY book_num DESC"; // 20개만 출력(랜덤으로 출력되게 수정요망)
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				// 출력항목 검토요망 - 추천순위 제외 출력
				System.out.println("책번호\t제목\t저자\t출판사\t출판년도\t카테고리\t보유권수\t등록일"); // 수정요망
				do {
					System.out.print(rs.getInt("book_num")+"\t"); 								
					System.out.print(rs.getString("book_title")+"\t"); // 제목길이 제한출력여부 검토요망				
					System.out.print(rs.getString("book_author")+"\t");					
					System.out.print(rs.getString("book_publisher")+"\t");					
					System.out.print(rs.getInt("book_p_year")+"\t");					
					System.out.print(rs.getString("book_category")+"\t");					
					System.out.print(rs.getInt("book_volm_cnt")+"\t");					
					System.out.println(rs.getDate("book_reg_date"));
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectBook()

	//조회하는 책 레코드 존재 여부 체크
	public int checkBookRecord(int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;		
		int count = 0;		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book WHERE book_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = 1; //레코드가 존재할 때 1 저장				
			} // if					
		} catch (Exception e) {
			count = -1; //오류 발생
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
		return count; 
	} // checkBookRecord()

	//책 정보 상세보기
	public void selectDetailBook(int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book WHERE book_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("책번호 : " + rs.getInt("book_num"));
				System.out.println("제목 : " + rs.getString("book_title"));
				System.out.println("저자 : " + rs.getString("book_author"));
				System.out.println("출판사 : " + rs.getString("book_publisher"));
				System.out.println("출판년도 : " + rs.getInt("book_p_year"));
				System.out.println("카테고리 : " + rs.getString("book_category"));
				System.out.println("추천순위 : " + rs.getInt("book_rank"));
				System.out.println("보유권수 : " + rs.getInt("book_volm_cnt"));
				System.out.println("등록일 : " + rs.getDate("book_reg_date"));		
			} else {
				System.out.println("검색된 정보가 없습니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}		
	} // selectDetailBook()

	//책 정보 등록
	//추천순위 정보 안받음.
	public void insertBook(String book_title, String book_author, String book_publisher, 
			int book_p_year, String book_category, int book_volm_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO book (book_num, book_title, book_author, book_publisher, "
					+ "book_p_year, book_category, book_volm_cnt, book_reg_date) "
					+ "VALUES (book_seq.nextval,?,?,?,?,?,?,SYSDATE)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, book_title);
			pstmt.setString(++cnt, book_author);
			pstmt.setString(++cnt, book_publisher);
			pstmt.setInt(++cnt, book_p_year);	
			pstmt.setString(++cnt, book_category);			
			pstmt.setInt(++cnt, book_volm_cnt);				
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();			
			System.out.println(count + "개의 행을 삽입했습니다.");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);			
		} 		
	} // insertBook()

	// 책 정보 수정
	// 추천순위 정보까지 다 받음.??? book_rank
	public void updateBook(int book_num, String book_title, String book_author, String book_publisher, 
			int book_p_year, String book_category, int book_rank, int book_volm_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE book SET book_title=?,book_author=?,book_publisher=?,book_p_year=?,book_category=?, book_rank=?, book_volm_cnt=? WHERE book_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, book_title); // 유효성 검사 추가요망
			pstmt.setString(++cnt, book_author);
			pstmt.setString(++cnt, book_publisher);
			pstmt.setInt(++cnt, book_p_year);	
			pstmt.setString(++cnt, book_category);
			pstmt.setInt(++cnt, book_rank);
			pstmt.setInt(++cnt, book_volm_cnt);
			pstmt.setInt(++cnt, book_num);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서정보를 수정했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // updateBook()

	// 책 정보 삭제
	public void deleteBook(int book_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM book WHERE book_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_num);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서정보를 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteBook()

	// 대여정보 조회
	public void selectOrder() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book_order ORDER BY order_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("대여번호\t회원아이디\t책번호\t대여일\t\t반납기한일\t\t연장유무\t반납유무");
				do {
					System.out.print(rs.getInt("order_num")+"\t");		
					System.out.print(rs.getString("mem_id")+"\t");		
					System.out.print(rs.getInt("book_num")+"\t");		
					System.out.print(rs.getDate("order_date")+"\t");
					System.out.print(rs.getDate("return_date")+"\t");
					String is_add = rs.getInt("is_add")==1 ? "O" : "X";					
					System.out.print(is_add+"\t"); // 연장여부 O,X로 출력					
					String is_return = rs.getInt("is_return")==1 ? "O" : "X";
					System.out.println(is_return); // 반납여부 O,X로 출력
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectOrder()

	//조회하는 대여 레코드 존재 여부 체크
	public int checkOrderRecord(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;		
		int count = 0;		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book_order WHERE order_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = 1; //레코드가 존재할 때 1 저장				
			} // if					
		} catch (Exception e) {
			count = -1; //오류 발생
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
		return count; 
	} // checkOrderRecord()
	
	//대여 정보 상세보기
	public void selectDetailOrder(int order_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM book_order WHERE order_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				System.out.println("대여번호 : " + rs.getInt("order_num"));
				System.out.println("회원아이디 : " + rs.getString("mem_id"));
				System.out.println("책번호 : " + rs.getInt("book_num"));
				System.out.println("대여일 : " + rs.getDate("order_date"));
				System.out.println("반납일 : " + rs.getDate("return_date"));			
				String is_add = rs.getInt("is_add")==1 ? "O" : "X";	
				System.out.println("연장유무 : " + rs.getInt(is_add)); // 연장여부 O,X로 출력	
				String is_return = rs.getInt("is_return")==1 ? "O" : "X";
				System.out.println("반납유무 : " + rs.getInt(is_return)); // 반납여부 O,X로 출력		
			} else {
				System.out.println("검색된 정보가 없습니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}		
	} // selectDetailOrder()

	// 대여정보 등록
	public void insertOrder(String mem_id, int book_num) {
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
			pstmt1.setString(++cnt, mem_id);			
			pstmt1.setInt(++cnt, book_num);				
			
			int count = pstmt1.executeUpdate();
			System.out.println(count + "개의 대여정보를 등록했습니다.");	
			
			// 대여정보 추가시 해당도서 재고 -1;
			sql = "UPDATE book SET book_volm_cnt = book_volm_cnt-1 WHERE book_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, book_num);
			
			count = pstmt2.executeUpdate();
			System.out.println(count + "개의 도서재고정보를 수정했습니다.");	
			
			//정상적으로 작업 완료되면 commit
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

	// 예약 관리
	public void selectRSV() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM reservation ORDER BY book_num, re_num"; // 1.책번호별 2.예약번호별 정렬 (test요망)
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("예약번호\t회원아이디\t책번호");
				do {
					System.out.print(rs.getInt("re_num")+"\t");							
					System.out.print(rs.getString("mem_id")+"\t");
					System.out.println(rs.getInt("book_num")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectRSV()

	// 희망도서 관리
	public void selectWish() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM wish_book ORDER BY wish_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {			
				System.out.println("희망도서번호\t제목\t저자\t출판사\t희망도서신청일");				
				do {
					System.out.print(rs.getInt("wish_num")+"\t\t");							
					System.out.print(rs.getString("wish_title")+"\t");
					System.out.print(rs.getString("wish_author")+"\t");
					System.out.print(rs.getString("wish_publisher")+"\t");
					System.out.println(rs.getDate("wish_date")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectWish()

	// 리뷰 관리
	public void selectReview() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM review ORDER BY review_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("리뷰번호\t책번호\t리뷰내용\t평점\t리뷰등록일\t회원아이디");			
				do {
					System.out.print(rs.getInt("review_num")+"\t");							
					System.out.print(rs.getInt("book_num")+"\t");
					System.out.print(rs.getString("review_content")+"\t");
					System.out.print(rs.getInt("review_rate")+"\t");
					System.out.print(rs.getDate("review_reg_date")+"\t");
					System.out.println(rs.getString("mem_id")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectReview()

	// 공지사항 관리
	public void selectNotice() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM notice ORDER BY notice_num DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {			
				System.out.println("공지사항번호\t공지사항제목\t공지사항내용\t\t공지사항조회수\t공지사항등록일");		
				do {
					System.out.print(rs.getInt("notice_num")+"\t\t");							
					System.out.print(rs.getString("notice_title")+"\t");
					System.out.print(rs.getString("notice_content")+"\t");
					System.out.print(rs.getInt("notice_view")+"\t\t");					
					System.out.println(rs.getDate("notice_reg_date")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectNotice()

	// Q&A 관리
	public void selectQnA() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM qna ORDER BY qna_num"; // 수정요망
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("qna번호\t질문제목\t질문내용\t조회수\t답변내용\t질문날짜\t\t답변날짜");		
				do {
					System.out.print(rs.getInt("qna_num")+"\t");							
					System.out.print(rs.getString("qna_title")+"\t");
					System.out.print(rs.getString("qna_content")+"\t");
					System.out.print(rs.getInt("qna_view")+"\t");	
					System.out.print(rs.getString("qna_re")+"\t");	
					System.out.print(rs.getDate("q_date")+"\t");	
					System.out.println(rs.getDate("a_date")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");	
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectQnA()

	// 통계 관리
	public void selectStatistics() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM qna ORDER BY qna_num"; // 수정요망!!!!!
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("-".repeat(100));
			if (rs.next()) {
				System.out.println("항목출력\t");		
				do {
					//					System.out.print(rs.getInt("")+"\t");						
					//					:
					//					System.out.println(rs.getDate("")+"\t");
				} while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			} //if-else
			System.out.println("-".repeat(100));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectStatistics()

	/*
	//관리자(admin) 로그인 체크(로그인 성공 true, 로그인 실패 false 반환)
	public boolean loginCheck(String mem_id, String mem_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?"; // SELECT * 해도 됨.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true; //로그인 성공
			} // if 			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);			
		}
		return flag;	
	} // loginCheck()
	 */

} // class
