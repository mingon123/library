package com.library.DAO;

public interface BookOrderDAO {
	boolean isOverReturn(String memId);
	boolean isReturnDateNotification(String numId);
}
