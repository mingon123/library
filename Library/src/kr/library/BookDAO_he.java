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

	//회원가입
	public void insertInfo(String id, String passwd, String name, String cell, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int cnt = 0;
		
		//아이디
		 if (id == null || id.isEmpty()) {
	            System.out.println("아이디를 입력해주세요.");
	            return;
	        }
		 //유효성 검사
		 if (!id.matches("^[a-zA-Z0-9]+$")) {  // ^문자열 시작 첫a-z소문자 A-Z대문자 0-9숫자 +앞 문자클래스 1이상 포함 $문자열 끝
	            System.out.println("아이디는 영문자와 숫자만 포함할 수 있습니다.");
	            return;
	        }
		 //중복검사
		 try {
		        conn = DBUtil.getConnection();
		        sql = "SELECT COUNT(*) FROM member WHERE mem_id = ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, id);
		        rs = pstmt.executeQuery();
		        
		        if (rs.next() && rs.getInt(1) > 0) {
		            System.out.println("이미 존재하는 아이디입니다.");
		            return;
		        }// 레코드 정보의 다음 값이 존재하고 1 이상 존재시 true 면 프린트
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DBUtil.executeClose(rs, pstmt, conn);
		    }
	    // 비밀번호
	        if (passwd == null || passwd.isEmpty()) {
	            System.out.println("비밀번호를 입력해주세요.");
	            return;
	        }
	     // 유효성 검사 
	        if (passwd.length() < 8) {
	            System.out.println("비밀번호는 최소 8자 이상이어야 합니다.");
	            return;
	        }
	        if (!passwd.matches(".*[a-z].*") || !passwd.matches(".*\\d.*") || !passwd.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            System.out.println("비밀번호는 소문자, 숫자, 특수문자를 포함해야 합니다.");
	            return;
	        }
	        
	        //이름
	        if (name == null || name.isEmpty()) {
	            System.out.println("이름을 입력해주세요.");
	            return;
	        }
	        if (!name.matches("^[a-zA-Z가-힣]+$")) {  // 이름에 숫자나 특수문자가 포함되지 않도록
	            System.out.println("이름은 한글이나 영문만 가능합니다.");
	            return;
	        }
	        //전화번호
	        if (cell == null || cell.isEmpty()) {
	            System.out.println("전화번호를 입력해주세요.");
	            return;
	        }
	        if (!cell.matches("^[0-9]+$")) {  // 전화번호는 숫자만 포함하도록
	            System.out.println("전화번호는 숫자만 포함해야 합니다.");
	            return;
	        }
	        //이메일
	        if (email == null || email.isEmpty()) {
	            System.out.println("이메일을 입력해주세요.");
	            return;
	        }
	     //유효성 검사 
	        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
	            System.out.println("유효하지 않은 이메일 형식입니다.");
	            return;
	        }
		
		
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

	private boolean checkDuplicateId(String id) {
		// TODO Auto-generated method stub
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
				System.out.println("번호\t조회수\t  등록일\t\t제목");
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

