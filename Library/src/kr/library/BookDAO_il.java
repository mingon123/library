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
	public void selectMemberInfo() {
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
				// PW,정보수정일 제외 출력
				System.out.println("ID\t이름\t전화번호\t\te-mail\t\t회원가입일\t\t대여정지기한");
				do {
					System.out.print(rs.getString("mem_id")+"\t");
					System.out.print(rs.getString("mem_name")+"\t");
					System.out.print(rs.getString("mem_cell")+"\t");	
					System.out.print(rs.getString("mem_email")+"\t");	
					System.out.print(rs.getDate("mem_date")+"\t");		
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
	} // selectMemberInfo()

	//도서 관리 -> OK: 도서 목록 조회(20개만) / TODO: 수정,추가,삭제 기능
	public void selectBookInfo() {
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
	} // selectBookInfo()

	// 대여 관리
	public void selectOrderInfo() {
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
	} // selectOrderInfo()

	// 예약 관리
	public void selectRSVInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM reservation ORDER BY re_num";
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
	} // selectRSVInfo()

	// 희망도서 관리
	public void selectWishInfo() {
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
	} // selectWishInfo()

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
//				review_num            리뷰번호
//				book_num              책번호
//				review_content        리뷰내용
//				review_rate           평점
//				review_reg_date       리뷰등록일
//				mem_id				  회원 아이디
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
