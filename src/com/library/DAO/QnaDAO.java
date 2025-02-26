package com.library.DAO;

public interface QnaDAO {
	void insertQNA(String qnaTitle, String qnaContent,String memId);
	void selectQNAInfo();
	boolean selectMyQNAInfo(String memId);
	void deleteQNAInfo(String memId, int QNANum);
	int checkQnaRecordNumId(int qnaNum, String memId);
}
