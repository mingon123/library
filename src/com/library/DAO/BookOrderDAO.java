package com.library.DAO;

public interface BookOrderDAO {

	int canOrder(int bookNum);
	void insertBookOrder(int bookNum);
	boolean checkNowOrderNum(int orderNum);
	void selectUserNowOrderInfo();
	boolean checkZeroOrder();
	void selectOrderNumToBookInfo(int orderNum);
	int selectOrderNumToBookNum(int orderNum);
	void selectLateReturn(int orderNum);
	void selectUserOrderInfo(int selectNum);
	boolean checkOrderAdd(int orderNum);
	void updateReturnDate(int orderNum);

}
