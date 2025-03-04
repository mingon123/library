package com.library.DAO;

public interface BookDAO {
	void selectCategoryOfBook(String category);
	void selectRankOfBook();
	void selectNewOfBook();
	void selectOrderBestOfBook();
	int checkBookRecord(String columnName, String value);
	void selectDetailBook(int bookNum);
	void selectSearchBook(String searchType, String searchValue);
	void randomBookInfo(int num);
	int selectBookCount(int bookNum);
	void updateBookCount(int bookVolmCnt, int bookNum);
	
	void selectBookAdmin();
	void insertBookAdmin(String bookTitle, String bookAuthor, String bookPublisher, int bookPYear, String bookCategory,
			int bookVolmCnt);
	void updateBookAdmin(int bookNum, String bookTitle, String bookAuthor, String bookPublisher, int bookPYear,
			String bookCategory, int bookRank, int bookVolmCnt);
	void deleteBookAdmin(int bookNum);

}
