package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
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

	

	
}
