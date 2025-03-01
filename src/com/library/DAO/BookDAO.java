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

	
}
