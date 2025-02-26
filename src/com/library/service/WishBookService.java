package com.library.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface WishBookService {
	void manageWishBook(BufferedReader br) throws IOException;
	void insertWishBook(BufferedReader br) throws IOException;
	void showWishBooks();
	void deleteWishBook(BufferedReader br);
}
