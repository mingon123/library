package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.library.DAO.MemberDAO;

import util.DBUtil;

public class MemberDAOImpl implements MemberDAO {
	private String memId;
	
	public MemberDAOImpl(String memId) {
		this.memId = memId;
	}
	
	// 정지상태
	@Override
	public Date checkMemStop(String memId) {
		Date stopDate = null;
		String sql = "SELECT mem_stop_date FROM member WHERE mem_id=? AND mem_stop_date>sysdate";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) stopDate = rs.getDate("mem_stop_date");
			}
		} catch (Exception e) {e.printStackTrace();}
		return stopDate;
	} // checkMemStop

	// 로그인 검증
	@Override
	public boolean loginCheck(String memId, String memPw) {
		String sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) return true;
			}
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}

	// 중복검사
	@Override
	public boolean isDuplicate(String fieldType, String value) {
		String sql = "SELECT COUNT(*) FROM member WHERE "+ fieldType +" = ?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, value);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next() && rs.getInt(1) > 0) return true;
			}
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}

	// 회원가입
	@Override
	public boolean insertMember(String memId, String memPw, String memName, String memCell, String memEmail) {
        String sql = "INSERT INTO member(mem_id, mem_pw, mem_name, mem_cell, mem_email, mem_date) VALUES (?, ?, ?, ?, ?, sysdate)";
        int cnt = 0;
        try (Connection conn = DBUtil.getConnection();
   			 PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(++cnt, memId);
            pstmt.setString(++cnt, memPw);
            pstmt.setString(++cnt, memName);
            pstmt.setString(++cnt, memCell);
            pstmt.setString(++cnt, memEmail);
            int rows = pstmt.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {e.printStackTrace();}
        return false;
	}

	// 회원 레코드
	@Override
	public int checkMemberRecord(String memId) {
		String sql = "SELECT * FROM member WHERE mem_id=?";
		int count = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		}
		return count;
	}

	// 회원정보 조회
	@Override
	public void selectMemberInfo(String memId) {
		String sql = "SELECT mem_name,mem_cell,mem_date,mem_email FROM member WHERE mem_id=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			try(ResultSet rs = pstmt.executeQuery();){
				System.out.println("-".repeat(90));
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
			}
		} catch (Exception e) {e.printStackTrace();}
	} // selectMemberInfo
	
	// 비밀번호 확인
	@Override
	public int checkPassword(String memId, String password) {
		String sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
		int count = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			pstmt.setString(2, password);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		}
		return count;
	} // checkPassword

	// 회원정보 수정
	@Override
	public boolean updateMemberInfo(String memId,String password,String newName,String newEmail){
		String sql = "UPDATE member SET mem_name=?,mem_email=?,mem_mdate=SYSDATE WHERE mem_id=?";
		int cnt = 0;
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){			
            if (checkPassword(memId, password)!=1) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return false;
            }
			pstmt.setString(++cnt, newName);
			pstmt.setString(++cnt, newEmail);
			pstmt.setString(++cnt, memId);
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {e.printStackTrace();}
		return false;
	} // updateMemberInfo
	
	// 회원정보 삭제
	@Override
	public void deleteMemberInfo(String memId) {
		String sql = "DELETE FROM member WHERE mem_id=?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, memId);
			int rows = pstmt.executeUpdate();
			if(rows>0) System.out.println("회원탈퇴를 완료했습니다.");
			else System.out.println("회원 정보를 찾을 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // deleteMemberInfo

	// 정지일수 update - 이전에 정지일수가 있는 경우 + , 새로 생길경우 그대로
	@Override
	public void updateStopDate(int orderNum) {
		String sql = "UPDATE MEMBER SET MEM_STOP_DATE = "
				+ "CASE "
				+ "WHEN (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?) >= SYSDATE THEN MEM_STOP_DATE "  
				+ "WHEN NVL(MEM_STOP_DATE, SYSDATE) > SYSDATE "
				+ "THEN MEM_STOP_DATE + (SYSDATE - (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?)) "
				+ "ELSE SYSDATE + (SYSDATE - (SELECT RETURN_DATE FROM BOOK_ORDER WHERE ORDER_NUM = ?)) "
				+ "END "
				+ "WHERE MEM_ID = (SELECT MEM_ID FROM BOOK_ORDER WHERE ORDER_NUM = ?)";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1,orderNum);
			pstmt.setInt(2,orderNum);
			pstmt.setInt(3,orderNum);
			pstmt.setInt(4,orderNum);
			int count = pstmt.executeUpdate();
			if(count <= 0) System.out.println("갱신 실패");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // updateStopDate
}
