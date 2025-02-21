package kr.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import util.DBUtil;

public class BookDAO_he {
	// 사용자 로그인 체크 (로그인 성공 true, 로그인 실패 false 반환)
	public boolean loginCheck(String mem_id, String mem_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			// JDBC 수행 1,2 단계
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
			// JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
			// JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;// 로그인 성공

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return flag;

	}

	//회원가입
	public void insertInfo(String id, String passwd, String name, String cell, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;

		try {
			//JDBC 수행 1,2 단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO member VALUES (note_seq.nextval,?,?,?,?,?,SYSDATE)";
			//JDBC 수행 3단계
			pstmt= conn.prepareStatement(sql);
			//?에 데어터 바인딩
			pstmt.setString(++cnt, id);
			pstmt.setString(++cnt, passwd);
			pstmt.setString(++cnt, name);
			pstmt.setString(++cnt, cell);
			pstmt.setString(++cnt, email);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count+"개의 행을 삽입했습니다.");


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	public class NoticeDAO {
		//목록보기

		//notice
		public void selectNotice() {
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = null;
			try {
				//JDBC 수행 1,2 단계
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql= "SELECT * FROM notice ORDER BY num DESC";
				//JDBC 수행 3단계
				pstmt = conn.prepareStatement(sql);
				//JDBC 수행 4단계
				rs=pstmt.executeQuery();
				System.out.println("--------------------");
				if(rs.next()) {
					System.out.println("번호\t제목\t조회수\t등록일");
					do {
						System.out.print(rs.getInt("num"));
						System.out.print("\t");
						System.out.print(rs.getString("title"));
						System.out.print("\t");
						System.out.print(rs.getInt("view"));
						System.out.print("\t");
						System.out.println(rs.getDate("reg_date"));
					}while (rs.next());
				} else {
					System.out.println("표시할 데이터가 없습니다.");
				}
				System.out.print("");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
		}
		//조회하는 레코드가 존재하는지 여부 체크
		public int checkRecord(int num) {
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count=0;
			try {
				conn=DBUtil.getConnection();
				sql="SELECT * FROM notice WHERE num=?";
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					count=1;
				}
			} catch (Exception e) {
				count=-1;
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		//상세보기
		public void selectDetailNotice(int num) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = null;
			try {
				conn= DBUtil.getConnection();
				sql="SELECT * FROM notice WHERE num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("번호 : " + rs.getInt("num"));
					System.out.println("제목 : " + rs.getString("title"));
					System.out.println("내용 : " + rs.getString("content"));
					System.out.println("조회수 : " + rs.getInt("view"));
					System.out.println("등록일 : " + rs.getDate("reg_date"));
				}else {
					System.out.println("검색된 정보가 없습니다.");

				}

			} catch (Exception e) {
				e.printStackTrace();	
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
		}
	}

	//도서목록
	public class BookDAO {
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

		// 조회하는 레코드 존재 여부 체크
		public int checkRecord(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM book WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) count = 1;
			} catch (Exception e) {
				count = -1;
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
	}
	
	
	
	
	//notice
	public void selectNotice() {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2 단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql= "SELECT * FROM notice ORDER BY num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs=pstmt.executeQuery();
			System.out.println("--------------------");
			if(rs.next()) {
				System.out.println("번호\t제목\t조회수\t등록일");
				do {
					System.out.print(rs.getInt("num"));
					System.out.print("\t");
					System.out.print(rs.getString("title"));
					System.out.print("\t");
					System.out.print(rs.getInt("view"));
					System.out.print("\t");
					System.out.println(rs.getDate("reg_date"));
				}while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
			System.out.print("");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//조회하는 레코드가 존재하는지 여부 체크
	public int checkRecord(int num) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count=0;
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM notice WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=1;
			}
		} catch (Exception e) {
			count=-1;
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//상세보기
	public void selectDetailNotice(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn= DBUtil.getConnection();
			sql="SELECT * FROM notice WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("번호 : " + rs.getInt("num"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("내용 : " + rs.getString("content"));
				System.out.println("조회수 : " + rs.getInt("view"));
				System.out.println("등록일 : " + rs.getDate("reg_date"));
			}else {
				System.out.println("검색된 정보가 없습니다.");

			}

		} catch (Exception e) {
			e.printStackTrace();	
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	


	
	
	
	
	
	
	
	
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
