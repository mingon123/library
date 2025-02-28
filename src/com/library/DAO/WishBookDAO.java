package com.library.DAO;

import java.util.List;

import com.library.DTO.wishBook;

public interface WishBookDAO {
	void insertWishBook(String bookTitle,String bookAuthor,String bookPublisher,String memId);
	boolean checkWishBookRecord(String bookTitle,String bookAuthor);
	List<wishBook> selectWishBookInfo();
	List<wishBook> selectMyWishBookInfo(String memId);
	void deleteWishMyBookInfo(String memId, int wishNum);
	int checkWishBookRecordNumId(int wishBookNum, String memId);
}
