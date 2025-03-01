package com.library.DAO;

public interface ReviewDAO {
	
	void selectReviewInfo();
	void selectDetailReview(int num);
	int checkReviewRecord(int reviewNum, String memId);
	int checkReviewRecordOfBookTile(String bookTitle);
	void selectSearchReviewOftitle(String bookTitle);
	boolean selectMyReviewInfo(String memId);
	void updateMyReview(int reviewNum, String reviewContent, int reviewRate, String memId);
	void deleteReview(int reviewNum);

}
