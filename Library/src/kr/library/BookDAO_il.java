package kr.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member ORDER BY mem_name"; // 이름순으로 정렬
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member WHERE mem_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, mem_id);
			//JDBC 수행 4단계
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
	} // checkMemberRecord()

	//회원 상세정보 확인
	public void selectDetailMember(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member WHERE mem_id=?";
			//JDBC 수행 3단계 
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 할당
			pstmt.setString(1, mem_id);
			//JDBC 수행 4단계
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
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);			
		}		
	} // selectDetailMembe

	// 회원 정보 삭제
	public void deleteMember(String mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM member WHERE mem_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, mem_id);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 회원정보를 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // deleteMember()

	//도서 관리 -> OK: 도서 목록 조회(우선 20개만) / TODO: 상세보기,등록,수정,삭제 기능
	public void selectBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM book WHERE book_num<=20 ORDER BY book_num DESC"; // 20개만 출력(랜덤으로 출력되게 수정요망)
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM book WHERE book_num=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, book_num);
			//JDBC 수행 4단계
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
	} // checkBookRecord()
		
	//책 정보 상세보기
		public void selectDetailBook(int book_num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			try {
				//JDBC 수행 1,2단계
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM book WHERE book_num=?";
				//JDBC 수행 3단계 
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 할당
				pstmt.setInt(1, book_num);
				//JDBC 수행 4단계
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("책번호 : " + rs.getInt("book_num"));
					System.out.println("제목 : " + rs.getString("book_title"));
					System.out.println("저자" + rs.getString("book_author"));
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
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);	
			}		
		} // selectDetailBook()
	
	//책 정보 등록
	public void insertBook(String book_title, String book_author, String book_publisher, 
			int book_p_year, String book_category, int book_rank, int book_volm_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;		
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO book (book_num, book_title, book_author, book_publisher, "
					+ "book_p_year, book_category, book_rank, book_volm_cnt, book_reg_date) "
					+ "VALUES (book_seq.nextval,?,?,?,?,?,?,?,SYSDATE)"; //전체를 넣을땐 컬럼명 생략 가능
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, book_title);
			pstmt.setString(++cnt, book_author);
			pstmt.setString(++cnt, book_publisher);
			pstmt.setInt(++cnt, book_p_year);	
			pstmt.setString(++cnt, book_category);
			pstmt.setInt(++cnt, book_rank);	
			pstmt.setInt(++cnt, book_volm_cnt);				
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();			
			System.out.println(count + "개의 행을 삽입했습니다.");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);			
		} 		
	} // insertBook()

	// 대여 관리
	public void selectOrder() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM book_order ORDER BY order_num";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
			DBUtil.executeClose(rs, pstmt, conn);			
		} 	
	} // selectOrder()

	// 예약 관리
	public void selectRSV() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM reservation ORDER BY book_num, re_num"; // 1.책번호별 2.예약번호별 정렬 (test요망)
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM wish_book ORDER BY wish_num";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM review ORDER BY review_num";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM notice ORDER BY notice_num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM qna ORDER BY qna_num"; // 수정요망
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM qna ORDER BY qna_num"; // 수정요망!!!!!
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
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
			// 자원 정리
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
			//JDBC 수행 1,2단계			
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?"; // SELECT * 해도 됨.
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true; //로그인 성공
			} // if 			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);			
		}
		return flag;	
	} // loginCheck()
	 */

} // class
