package com.library.DAO;

public interface ReservationDAO {
	boolean isReservationNotification();
	int canReservation(int bookNum);
	boolean isDuplicatedReserve(int bookNum);
	void insertReserve(int bookNum);
	void selectUserNowReserveInfo();
	boolean checkReserveReNum(int reNum);
	void deleteReserveReNum(int reNum);
	int countUserReservations();
	boolean checkReserveBookNum(int bookNum);
	int selectBookNumToReNum(int bookNum);
	int calcReserveRank(int reNum, int num);
	void deleteReserveBookNum(int bookNum);
	
	void selectRSVAdmin();
	int checkRSVRecordAdmin(int reNum);
	void InsertRSVAdmin(String memId, int bookNum);
	void updateRSV(int reNum, String memId, int bookNum);
	
}
