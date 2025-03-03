package com.library.DAO;

import java.sql.Date;

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
	boolean checkOrderByUser(int orderNum);
	
	void selectOrderAdmin();
	void selectDetailOrderAdmin(int orderNum);
	void insertOrderAdmin(String mem_id, int book_num);
	void updateOrderAdmin(int order_num, String mem_id, int book_num, Date order_date, Date return_date, int is_add, int is_return);
	void deleteOrderAdmin(int orderNum);
	void selectBookOrderStatsAdmin();
	void selectMemberOrderStatsAdmin();
	int checkOrderRecord(int orderNum);
			
	


}
