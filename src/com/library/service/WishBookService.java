package com.library.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface WishBookService {
	void manageWishBook() throws IOException;
	void insertWishBook() throws IOException;
	void showWishBooks();
	void showMyWishBooks(BufferedReader br, String memId);
	void deleteWishBook();
}
