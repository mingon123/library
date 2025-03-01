package com.library.DAO;

public interface ReservationDAO {
	boolean isReservationNotification(String memId);
	int canReservation(String memId, int bookNum);
	boolean isDuplicatedReserve(int bookNum, String memId);
	void insertReserve(String memId, int bookNum);
	void selectUserNowReserveInfo(String memId);
	boolean checkReserveReNum(int reNum, String memId);
	void deleteReserveReNum(int reNum);
	int countUserReservations(String memId);
}
