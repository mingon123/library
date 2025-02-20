package kr.library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;

public class BookDAO_mg {
	private BookDAO_Jw jw;
	// 희망도서신청
	public void insertWishBook(String title,String author,String publisher) {
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
			
			sql = "INSERT INTO wish_book(wish_num,wish_title,wish_author,wish_publisher,wish_date) VALUES (wish_book_seq.nextval,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, title);
			pstmt.setString(++cnt, author);
			pstmt.setString(++cnt, publisher);

			pstmt.executeUpdate();
			System.out.println("희망도서 신청 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	} // insertWishBook
	// 희망도서 신청 시 동일한 제목+저자인 도서가 있으면 알림
	private boolean isWishBook(String title,String author) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM book WHERE book_title=? AND book_author=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			rs = pstmt.executeQuery();
			if(rs.next()) return true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}
		return false;
	}
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
			System.out.println("-".repeat(50));
			
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
	
	// Q&A 질문 등록
	public void insertQNA(String qnaTitle, String qnaContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO qna (qna_num,qna_title,qna_content,q_date) VALUES (qna_seq.nextval,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, qnaTitle);
			pstmt.setString(++cnt, qnaContent);
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
			System.out.println("-".repeat(50));
			
			if(rs.next()) {
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
	} // selectInfoQNA
	
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
			System.out.println("-".repeat(50));
			
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
	} // delete
	
	
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
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}
	// 연체알림
	public boolean isOverReturn(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT b.book_title,b.book_author,(SYSDATE-o.return_date) AS over_return FROM book_order o,book b WHERE b.book_num=o.book_num "
					+ "AND mem_id=? AND is_return=0 AND return_date<SYSDATE ORDER BY o.return_date desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.print("연체일/책제목 : ");
				do {
					Date overReturn = rs.getDate("over_return");
					if(overReturn.getDay()<0) {
						System.out.print(Math.abs(overReturn.getDay()));
						System.out.print(" / ");
						System.out.println(rs.getString("b.book_title"));
					}
				} while (rs.next());
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}
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
					+ "AND o.mem_id=? AND o.is_return=0 ORDER BY o.return_date) WHERE ROWNUM = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.print("반납일/책제목 : ");
				do {
					System.out.print(rs.getDate("return_date"));
					System.out.print(" / ");
					System.out.println(rs.getString("book_title"));
				} while (rs.next());
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}
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
				System.out.print("책제목/책권수 : ");
				do {
					System.out.print(rs.getString("book_title"));
					System.out.print(" / ");
					System.out.println(rs.getString("book_volm_cnt"));
				} while (rs.next());
				System.out.println("예약도서 대여 가능!");
				return true;
			} else {
				System.out.println("예약도서가 없습니다.");
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);	
		}
		return false;
	}
	
}
