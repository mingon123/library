package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.library.DAO.BookDAO;

import util.DBUtil;

public class BookDAOImpl implements BookDAO {
	public BookDAOImpl() {}

	// 카테고리별 책 
	@Override
	public void selectCategoryOfBook(String category) {
		String sql  = "SELECT * FROM ("
				+ "SELECT book_num,book_title,book_author,book_category FROM book WHERE book_category=?"
				+ "ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 10";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, category);
			try(ResultSet rs = pstmt.executeQuery();){
				System.out.println("-".repeat(130));
				if(rs.next()) {
					System.out.printf("책번호\t카테고리\t %-40s  %-35s \n","책제목","저자");
					do {
						System.out.printf("%d\t%s\t %-40s  %-35s \n",
								rs.getInt("book_num"),
								rs.getString("book_category"),
								rs.getString("book_title"),
								rs.getString("book_author"));
					} while(rs.next());
				} else {System.out.println("표시할 정보가 없습니다.");}
				System.out.println("-".repeat(130));
			}
		} catch (Exception e) {e.printStackTrace();}
	} // categoryOfBook

	// 추천순위별 책
	@Override
	public void selectRankOfBook() {
		String sql  = "SELECT * FROM ("
				+ "SELECT book_num,book_title,book_author,book_rank FROM book WHERE book_rank<=200 "
				+ "ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 10 ORDER BY book_rank";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("책번호\t책제목\t\t\t저자");
				do {
					System.out.print(rs.getInt("book_num")+"\t");
					//					System.out.print(rs.getInt("book_rank")+"\t");
					System.out.print(rs.getString("book_title")+"\t");
					System.out.println(rs.getString("book_author")+"\t");
				} while(rs.next());
			} else {System.out.println("표시할 정보가 없습니다.");}
			System.out.println("-".repeat(90));
		} catch (Exception e) {e.printStackTrace();}
	} // rankOfBook

	// 신간책별 책
	@Override
	public void selectNewOfBook() {
		String sql = "SELECT * FROM ("
				+ "SELECT book_num, book_title, book_author, book_category, book_p_year "
				+ "FROM book "
				+ "WHERE TO_NUMBER(book_p_year) >= TO_NUMBER(TO_CHAR(ADD_MONTHS(SYSDATE, -24), 'YYYY')) "
				+ "ORDER BY DBMS_RANDOM.VALUE) "
				+ "WHERE ROWNUM <= 10";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
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
			} else {System.out.println("표시할 정보가 없습니다.");}
			System.out.println("-".repeat(90));
		} catch (Exception e) {e.printStackTrace();}
	} // newOfBook

	// 대여베스트별 책
	@Override
	public void selectOrderBestOfBook() {
		String sql  = "SELECT * FROM ("
				+ "SELECT b.book_num,b.book_title,b.book_author,b.book_category,count(o.book_num) AS cnt,b.book_rank "
				+ "FROM book_order o JOIN book b ON b.book_num=o.book_num "
				+ "GROUP BY b.book_num,book_title, book_author, book_category, book_rank ORDER BY cnt DESC,b.book_rank)"
				+ "WHERE ROWNUM <= 10";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
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
			} else {System.out.println("표시할 정보가 없습니다.");}
			System.out.println("-".repeat(90));
		} catch (Exception e) {e.printStackTrace();}
	} // orderBestOfBook

	//조회하는 책 레코드 존재 여부 체크
	@Override
	public int checkBookRecord(String columnName, String value) {
		// 허용된 컬럼 목록
		List<String> validColumns = Arrays.asList("book_num","book_title","book_author","book_category");
		if(!validColumns.contains(columnName)) throw new IllegalArgumentException ("잘못된 컬럼명: " + columnName);
		String sql = "SELECT 1 FROM book WHERE " + columnName +" LIKE ?";	
		if(columnName.equals("book_num")) sql = "SELECT 1 FROM book WHERE book_num = ?";
		int count = 0;		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			if(columnName.equals("book_num")) pstmt.setInt(1, Integer.parseInt(value));
			else pstmt.setString(1, "%"+value+"%");
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) count = 1; //레코드가 존재할 때 1 저장		
			}
		} catch (NumberFormatException e) {System.out.println("[숫자만 입력가능]");
		} catch (Exception e) {
			count = -1;
			e.printStackTrace();
		} //오류 발생
		return count; 
	} // checkBookRecord()

	// 책 정보(리뷰,평점 포함)
	@Override
	public void selectDetailBook(int bookNum) {
		String sql = "SELECT b.book_num, b.book_category, "
				+ "COALESCE(AVG(r.review_rate), NULL) AS review_rate, "
				+ "b.book_title, b.book_author, b.book_publisher, b.book_p_year, "
				+ "b.book_rank, b.book_volm_cnt, b.book_reg_date, "
				+ "COALESCE(LISTAGG(r.review_content, ' | ') WITHIN GROUP (ORDER BY r.review_rate), '없음') AS review_content "
				+ "FROM book b LEFT JOIN review r ON b.book_num = r.book_num "
				+ "WHERE b.book_num = ? "
				+ "GROUP BY b.book_num, b.book_category, b.book_title, b.book_author, "
				+ "b.book_publisher, b.book_p_year, b.book_rank, b.book_volm_cnt, b.book_reg_date";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			try(ResultSet rs = pstmt.executeQuery();){
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

					double reviewRate = rs.getDouble("review_rate");
					if(rs.wasNull()) {
						String reviewRateStr = "없음";
						System.out.println("평균 평점 : "+reviewRateStr);
					}
					else System.out.printf("평균 평점 : %.1f \n", reviewRate);

					System.out.println("리뷰 내용 : " + rs.getString("review_content"));
				} else {System.out.println("검색된 정보가 없습니다.");}		
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectDetailBook()

	// 책 제목, 저자 검색 통합
	@Override
	public void selectSearchBook(String searchType, String searchValue) {
		String sql = "SELECT book_num, book_title, book_author, book_publisher, book_category FROM book WHERE " + searchType + " LIKE ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + searchValue + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				System.out.println("-".repeat(90));
				if (rs.next()) {
					System.out.println("책번호\t카테고리\t책제목\t\t\t\t\t\t저자");
					do {
						System.out.printf("%d\t%s\t%-40s\t%-30s\n",
								rs.getInt("book_num"),
								rs.getString("book_category"),
								rs.getString("book_title"),
								rs.getString("book_author"));
					} while (rs.next());
				} else {System.out.println("표시할 정보가 없습니다.");}
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	//랜덤 책 정보 - 숫자 입력받아 하나로 재사용
	@Override
	public void randomBookInfo(int num) {
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
				selectDetailBook(randNum);
				System.out.println("-".repeat(70));
			} // for randNum
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // randomBookInfo
	
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
	public void updateBookCount(int bookVolmCnt, int bookNum) {
		String sql = "UPDATE book SET book_volm_cnt = book_volm_cnt + ? WHERE book_num = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookVolmCnt);
			pstmt.setInt(2, bookNum);
			int count = pstmt.executeUpdate();
			if(count <= 0) {System.out.println("책갯수 조정 중 오류발생!");}
		} catch (Exception e) {e.printStackTrace();}
	} // updateBookCount

	
	// admin
	//도서 목록 조회
	@Override
	public void selectBookAdmin() {
		String sql = "SELECT * FROM (SELECT * FROM book ORDER BY book_num DESC) WHERE ROWNUM <= 10"; 
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			System.out.println("-".repeat(110));
			if (rs.next()) {
				// 출력항목 검토요망 - 추천순위 제외 출력
				System.out.println("책번호\t제목\t\t\t저자\t\t\t출판사\t\t출판년도\t카테고리\t보유권수\t등록일");
				System.out.println("-".repeat(110));
				do {
					System.out.print(rs.getInt("book_num")+"\t");
					// 제목 길이 제한출력(20자)
					String title=rs.getString("book_title");					
					if (title.length()>=15) System.out.printf("%-15s..\t", title.substring(0, 15));
					else System.out.printf("%-15s\t", title);
					// 저자 길이 제한출력(10자)
					String author=rs.getString("book_author");	
					if (author.length()>=10) System.out.printf("%-10s..\t\t", author.substring(0, 10));
					else System.out.printf("%-10s\t\t", author);
					System.out.printf("%-10s\t", rs.getString("book_publisher"));			
					System.out.print(rs.getInt("book_p_year")+"\t");					
					System.out.print(rs.getString("book_category")+"\t");					
					System.out.print(rs.getInt("book_volm_cnt")+"\t");					
					System.out.println(rs.getDate("book_reg_date"));
				} while (rs.next());
			} else System.out.println("표시할 데이터가 없습니다.");	
			System.out.println("-".repeat(110));
		} catch (Exception e) {e.printStackTrace();}
	} // selectBook()
	
	//책 정보 등록
	//추천순위 정보 안받음.
	@Override
	public void insertBookAdmin(String bookTitle, String bookAuthor, String bookPublisher, 
			int bookPYear, String bookCategory, int bookVolmCnt) {
		String sql = "INSERT INTO book (book_num, book_title, book_author, book_publisher, "
				+ "book_p_year, book_category, book_volm_cnt, book_reg_date) "
				+ "VALUES (book_seq.nextval,?,?,?,?,?,?,SYSDATE)"; 
		int cnt = 0;		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, bookTitle);
			pstmt.setString(++cnt, bookAuthor);
			pstmt.setString(++cnt, bookPublisher);
			pstmt.setInt(++cnt, bookPYear);	
			pstmt.setString(++cnt, bookCategory);			
			pstmt.setInt(++cnt, bookVolmCnt);				
			int count = pstmt.executeUpdate();			
			System.out.println(count + "개의 행을 삽입했습니다.");			
		} catch (Exception e) {e.printStackTrace();}		
	} // insertBook()
	
	// 책 정보 수정
	// 추천순위 정보까지 다 받음.??? book_rank
	@Override
	public void updateBookAdmin(int bookNum, String bookTitle, String bookAuthor, String bookPublisher, 
			int bookPYear, String bookCategory, int bookRank, int bookVolmCnt) {
		String sql = "UPDATE book SET book_title=?,book_author=?,book_publisher=?,book_p_year=?,book_category=?, book_rank=?, book_volm_cnt=? WHERE book_num=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(++cnt, bookTitle); // 유효성 검사 추가요망
			pstmt.setString(++cnt, bookAuthor);
			pstmt.setString(++cnt, bookPublisher);
			pstmt.setInt(++cnt, bookPYear);	
			pstmt.setString(++cnt, bookCategory);
			pstmt.setInt(++cnt, bookRank); // 유효성 검사 필요...?
			pstmt.setInt(++cnt, bookVolmCnt);
			pstmt.setInt(++cnt, bookNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서정보를 수정했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // updateBook()

	// 책 정보 삭제
	@Override
	public void deleteBookAdmin(int bookNum) {
		String sql = "DELETE FROM book WHERE book_num=?";
		
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, bookNum);
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서정보를 삭제했습니다.");
		} catch (Exception e) {e.printStackTrace();}
	} // deleteBook()

	
}
