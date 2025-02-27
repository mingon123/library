package kr.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	// 아이디 검증 (6~12자, 영문+숫자만 허용)
    public boolean checkValidId(String id) {
        return id != null && id.matches("^[a-zA-Z0-9]{6,12}$");
    }
	// 아이디 중복 검사(존재:true, 없:false)
	public boolean isDuplicateId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE mem_id = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}

	// 비밀번호 검증(8자이상, 알파벳,숫자,특수기호 포함)
	public boolean checkValidPassword(String passwd) {
	    return passwd != null && passwd.length() >= 8 
	        && passwd.matches(".*[a-zA-Z].*") 
	        && passwd.matches(".*\\d.*") 
	        && passwd.matches(".*[!@#$%^&*(),.?\":{}|<>].*");  // 특수문자 확장
	}
	
    // 이름 검증(알파벳,한글,숫자만 가능)
    public boolean checkValidName(String name) {
        return name != null && name.matches("^[a-zA-Z가-힣0-9]+$");
    }

    
    public boolean checkValidCell(String phone) {
        return phone != null && phone.matches("^010-\\d{4}-\\d{4}$");
    }
	// 전화번호 중복 검사(중복:true,없:false)
	public boolean isDuplicateCell(String cell) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE mem_cell = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cell);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}

	// 이메일 검증
    public boolean checkValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$");
    }

	// 회원가입
	public boolean insertInfo(String id, String passwd, String name, String cell, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;

		if (isDuplicateId(id) || isDuplicateCell(cell)) return false;
		if (!checkValidId(id)) return false;
		if (!checkValidPassword(passwd)) return false;
		if (!checkValidName(name)) return false;
		if (!checkValidEmail(email)) return false;
		if (!checkValidCell(cell)) return false;
		
		// DB 저장
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO member(mem_id,mem_pw,mem_name,mem_cell,mem_email,mem_date) VALUES (?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, id);
			pstmt.setString(++cnt, passwd);
			pstmt.setString(++cnt, name);
			pstmt.setString(++cnt, cell);
			pstmt.setString(++cnt, email);
			int rows = pstmt.executeUpdate();
			if (rows > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return false;
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
				System.out.println("-".repeat(90));
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
	//조회하는 공지 레코드가 존재하는지 여부 체크
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
	// 공지 상세보기
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
				System.out.println("-".repeat(90));
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

