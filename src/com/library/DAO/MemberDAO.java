package com.library.DAO;

public interface MemberDAO {
	public boolean loginCheck(String memId, String memPw);
	public boolean isDuplicate(String fieldType, String value);
	public boolean insertMember(String memId, String memPw, String memName, String memCell, String memEmail);
	public int checkMemberRecord(String memId);
	public void selectMemberInfo(String memId);
	public int checkPassword(String memId, String password);
	public void updateMemberInfo(String memId,String password,String newName,String newEmail);
	public void deleteMemberInfo(String memId);
	boolean isMemStop(String memId); // 정지상태알림
	
	
}
