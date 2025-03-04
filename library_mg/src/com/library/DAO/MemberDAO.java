package com.library.DAO;

import java.util.Date;

public interface MemberDAO {
	public boolean loginCheck(String memId, String memPw);
	public boolean isDuplicate(String fieldType, String value);
	public boolean insertMember(String memId, String memPw, String memName, String memCell, String memEmail);
	public int checkMemberRecord(String memId);
	public void selectMemberInfo(String memId);
	public int checkPassword(String memId, String password);
	public boolean updateMemberInfo(String memId, String password,String newName,String newEmail);
	void deleteMemberInfo(String memId);

	Date checkMemStop(String memId); // 정지상태알림
	void updateStopDate(String memId, int orderNum);
	
	void selectMemberAdmin();
	void selectDetailMemberAdmin(String memId);
	void updateMember(String memId, String memPw, String memName, String memCell, String memEmail);

	
	
	
}
