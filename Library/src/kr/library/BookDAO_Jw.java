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

			sql = "SELECT COUNT(*) FROM book_order WHERE mem_id=?";
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

	// book_order 테이블에 insert - 대여 테이블 추가후 책 갯수 조정
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
