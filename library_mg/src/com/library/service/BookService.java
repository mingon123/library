package com.library.service;

import java.io.IOException;
import java.util.List;

import com.library.DTO.Book;

public interface BookService {
	void selectCategoryOfBook();
	void selectDetailBook() throws IOException;
	void selectSearchBook(String searchType, String searchValue) throws IOException;
}
