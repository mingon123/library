package com.library.service;

import java.util.List;

import com.library.DTO.Book;

public interface BookService {
	List<Book> BookService();
	void showBookList();
	void searchBooks();
}
