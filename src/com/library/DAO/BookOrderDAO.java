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
	
	boolean checkNowOrderNum(int orderNum, String memId);
	void selectUserNowOrderInfo(String memId);
	boolean checkZeroOrder(String memId);
	void selectOrderNumToBookInfo(int orderNum);
	int selectOrderNumToBookNum(int orderNum);
	void updateOrderReturn(int orderNum);
	void selectLateReturn(int orderNum);
	void selectUserOrderInfo(String memId, int selectNum);
	boolean checkOrderAdd(int orderNum, String memId);
	void updateReturnDate(int orderNum);
	
}
