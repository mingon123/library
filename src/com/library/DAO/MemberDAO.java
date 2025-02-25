package com.library.DAO;

public interface MemberDAO {
	public boolean loginCheck(String memId, String memPw);
	public boolean isDuplicate(String fieldType, String value);
	public boolean insertMember(String memId, String memPw, String memName, String memCell, String memEmail);
	public boolean login(String memId, String passWd);
}
