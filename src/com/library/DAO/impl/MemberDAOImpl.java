package com.library.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.DAO.MemberDAO;

import util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

	public MemberDAOImpl() {}
	
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
	public boolean login(String memId, String passWd) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
