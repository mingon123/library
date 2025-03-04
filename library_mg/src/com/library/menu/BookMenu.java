package com.library.menu;

import java.io.BufferedReader;

import com.library.DAO.BookDAO;
import com.library.service.BookService;

public class BookMenu {
	private BufferedReader br;
	private BookService bookService;
	private BookDAO bookDAO;
	
	public BookMenu(BufferedReader br,BookService bookService, BookDAO bookDAO) {
		this.br = br;
		this.bookService = bookService;
		this.bookDAO = bookDAO;
	}
	
	// 도서 목록 화면(2)
	public void showBookList() {
		System.out.println("도서 목록 화면입니다.");
		while (true) {			
			try {
				System.out.print("1.카테고리별 2.추천순위 3.신간책 4.대여베스트 5.뒤로가기\n > ");
				int no = Integer.parseInt(br.readLine());
				switch(no) {
				case 1: bookService.selectCategoryOfBook();break;
				case 2: 
					bookDAO.selectRankOfBook();
					bookService.selectDetailBook();
					break;
				case 3:
					bookDAO.selectNewOfBook();
					bookService.selectDetailBook();
					break;
				case 4:
					bookDAO.selectOrderBestOfBook();
					bookService.selectDetailBook();
					break;
				case 5:
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					return;
				default: System.out.println("잘못 입력하셨습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // showTwoMenu


	// 도서 검색 화면(3)
	public void searchBooks() {
		System.out.println("도서 검색 화면입니다.");
		while(true) {
			System.out.println("도서 검색");
			try {
				System.out.print("1.제목 2.저자 3.뒤로가기\n > ");
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					System.out.print("검색할 책 제목 검색 (뒤로가기:q) : ");
					String title = br.readLine();
					bookService.selectSearchBook("book_title",title);
				}
				else if(no==2) {
					System.out.print("검색할 책 저자 검색 (뒤로가기:q) : ");
					String author = br.readLine();
					bookService.selectSearchBook("book_author",author);
				}
				else if(no==3) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					return;
				} else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
