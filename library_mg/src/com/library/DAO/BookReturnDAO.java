package com.library.DAO;

public interface BookReturnDAO {

	boolean isOverReturn();
	boolean isReturnDateNotification();
	void updateOrderReturn(int orderNum);

}
