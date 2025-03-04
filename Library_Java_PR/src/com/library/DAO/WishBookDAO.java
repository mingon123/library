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
	boolean checkWishBook(String bookTitle, String bookAuthor);
	int checkWishRecord(int wishNum);
	void updateWish(String wishTitle, String wishAuthor, String wishPublisher, String memId, int wishNum);
	void deleteWish(int wishNum);
}
