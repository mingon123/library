package com.library.DAO;

public interface QnaDAO {
	void insertQNA(String qnaTitle, String qnaContent);
	void selectQNAInfo();
	boolean selectMyQNAInfo();
	void deleteQNAInfo(int QNANum);
	int checkQnaRecordMemId(int qnaNum);
	
	void selectQnAAdmin();
	void selectDetailQnA(int qna_num);
	void updateQnA(int qnaNum, String qnaRe);

}
