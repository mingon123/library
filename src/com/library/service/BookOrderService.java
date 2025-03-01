package com.library.service;

import java.io.IOException;

public interface BookOrderService {
	void orderOrReserveMenu(int bookNum) throws IOException;
	void handleRental(int bookNum) throws IOException;


	void handleBookOrder() throws IOException;
	int getValidBookNumber(String memId, boolean isReturn) throws IOException;
	void handleBookAction(String memId, int bookNum, int no) throws IOException;
	void viewOrderHistory() throws IOException;
	void viewOrderAndReservationInfo(String memId);
	boolean confirmReturn() throws IOException;
	void processReturn(int orderNum);
	void viewOrderHistoryByDate(String memId) throws IOException;

}
