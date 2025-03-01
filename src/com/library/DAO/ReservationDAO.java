package com.library.DAO;

public interface ReservationDAO {
	boolean isReservationNotification(String memId);
	int canReservation(String memId, int bookNum);
	boolean isDuplicatedReserve(int bookNum, String memId);
	void insertReserve(String memId, int bookNum);
}
