package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;

public class LibraryDAO_he {
	
	// 사용자 로그인 체크 (로그인 성공 true, 로그인 실패 false 반환)
	public boolean loginCheck(String mem_id, String mem_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
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
			conn = DBUtil.getConnection();
			sql = "INSERT INTO member(mem_id,mem_pw,mem_name,mem_cell,mem_email,mem_date) VALUES (?,?,?,?,?,sysdate)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(++cnt, id);
			pstmt.setString(++cnt, passwd);
			pstmt.setString(++cnt, name);
			pstmt.setString(++cnt, cell);
			pstmt.setString(++cnt, email);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("회원가입이 완료되었습니다.");
			else System.out.println("회원가입에 실패했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//notice
	public void selectNotice() {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql= "SELECT * FROM notice ORDER BY notice_num DESC";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println("-".repeat(90));
			if(rs.next()) {
				System.out.println("번호\t조회수\t등록일\t\t제목");
				do {
					System.out.print(rs.getInt("notice_num"));
					System.out.print("\t");
					System.out.print(rs.getInt("notice_view"));
					System.out.print("\t");
					System.out.print(rs.getDate("notice_reg_date"));
					System.out.print("\t");
					System.out.println(rs.getString("notice_title"));
				}while (rs.next());
			} else {
				System.out.println("표시할 데이터가 없습니다.");
			}
			System.out.println("-".repeat(90));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//조회하는 레코드가 존재하는지 여부 체크
	public int checkNoticeRecord(int num) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count=0;
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM notice WHERE notice_num=?";
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
			sql="SELECT * FROM notice WHERE notice_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("번호 : " + rs.getInt("notice_num"));
				System.out.println("제목 : " + rs.getString("notice_title"));
				System.out.println("내용 : " + rs.getString("notice_content"));
				System.out.println("조회수 : " + rs.getInt("notice_view"));
				System.out.println("등록일 : " + rs.getDate("notice_reg_date"));
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

