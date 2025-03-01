package com.library.DAO;

public interface BookOrderDAO {
	boolean isOverReturn(String memId);
	boolean isReturnDateNotification(String memId);
	int canOrder(String memId, int bookNum);
	void insertBookOrder(String memId, int bookNum);
	boolean checkReserveBookNum(int bookNum, String memId);
	int selectBookNumToReNum(int bookNum, String memId);
	int calcReserveRank(int reNum, int num);
	int selectBookCount(int bookNum);
	void updateBookCount(int bookNum, int bookVolmCnt);
	void deleteReserveBookNum(int bookNum, String memId);
	
}
