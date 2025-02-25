package com.library.service.Impl;

import java.util.List;

import com.library.DAO.BookDAO;
import com.library.DTO.Book;
import com.library.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO;
	
	public BookServiceImpl() {
		this.bookDAO = new BookDAO();
	}
	
	@Override
	public List<Book> BookService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showBookList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchBooks() {
		// TODO Auto-generated method stub
		
	}
	
}
