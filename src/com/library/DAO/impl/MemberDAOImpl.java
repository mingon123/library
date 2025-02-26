package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.MemberDAO;

import util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

	public MemberDAOImpl() {}
	
	@Override
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
				System.out.println("-".repeat(90));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	} // isMemStop

	@Override
	public boolean loginCheck(String memId, String memPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_id FROM member WHERE mem_id=? AND mem_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			rs = pstmt.executeQuery();
			if (rs.next()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}

	@Override
	public boolean isDuplicate(String fieldType, String value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE "+ fieldType +" = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return false;
	}

	@Override
	public boolean insertMember(String memId, String memPw, String memName, String memCell, String memEmail) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        int cnt = 0;
        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO member(mem_id, mem_pw, mem_name, mem_cell, mem_email, mem_date) VALUES (?, ?, ?, ?, ?, sysdate)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(++cnt, memId);
            pstmt.setString(++cnt, memPw);
            pstmt.setString(++cnt, memName);
            pstmt.setString(++cnt, memCell);
            pstmt.setString(++cnt, memEmail);
            int rows = pstmt.executeUpdate();
            if (rows > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
        return false;
	}

	@Override
	public int checkMemberRecord(String memId) {
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
	}


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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		} 
	} // selectMemberInfo
	
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
			
			int passwordCheck = checkPassword(memId, password);
			if (passwordCheck == -1) {
			    System.out.println("비밀번호 검증 중 오류가 발생했습니다.");
			    return;
			} else if (passwordCheck != 1) {
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
	} // deleteMemberInfo
	
	
}
