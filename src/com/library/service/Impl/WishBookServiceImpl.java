package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.library.DAO.BookDAO;
import com.library.DAO.MemberDAO;
import com.library.DAO.WishBookDAO;
import com.library.DAO.impl.WishBookDAOImpl;
import com.library.DTO.wishBook;
import com.library.service.WishBookService;

import util.DBUtil;

public class WishBookServiceImpl implements WishBookService{
	private WishBookDAO wishBookDAO = new WishBookDAOImpl(); 
	private BufferedReader br;
	private String memId;
	
	public WishBookServiceImpl(BufferedReader br, String memId) {
		this.br = br;
		this.memId = memId;
		
	}
	
	// 희망도서 관리
	@Override
	public void manageWishBook() throws IOException {
		
		
		while(true) {
			System.out.print("1.희망도서신청 2.희망도서신청내역확인 3.희망도서신청취소 4.뒤로가기\n > ");
			try {
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:	insertWishBook();break;
				case 2:	showWishBooks();break;
				case 3:	deleteWishBook();break;
				case 4:	System.out.println("뒤로가기를 선택하셨습니다.");return;
				default: System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}	
	}
	// 희망도서 신청
	@Override
	public void insertWishBook() throws IOException {
		System.out.println("희망도서 신청화면입니다. (뒤로가기:q) ");
		System.out.print("희망도서 제목을 입력하세요: ");
		String title = br.readLine();
		if(util.Util.goBack(title)) return;
		System.out.print("저자를 입력하세요: ");
		String author = br.readLine();
		System.out.print("출판사를 입력하세요: ");
		String publisher = br.readLine();
		wishBookDAO.insertWishBook(title, author, publisher, memId);
	}
	// 희망도서 목록 
	@Override
	public void showWishBooks() {
		List<wishBook> wishBooks = wishBookDAO.selectWishBookInfo();
		if(wishBooks.isEmpty()) {
			System.out.println("신청한 희망도서가 없습니다.");
			return;
		}
		System.out.println("-".repeat(90));
		System.out.printf("번호\t신청일\t\t %-20s   %-15s   %-10s\n","제목","저자","출판사");
		for (wishBook wishBook : wishBooks) {
			System.out.printf("%d\t%s\t %-20s   %-15s   %-10s\n",
					wishBook.getWishNum(),
					wishBook.getWishDate(),
					wishBook.getWishTitle(),
					wishBook.getWishAuthor(),
					wishBook.getWishPublisher(),
					wishBook.getWishDate());
		}
	}
	
	// 내 희망도서 목록
	@Override
	public void showMyWishBooks(BufferedReader br, String memId) {
		List<wishBook> wishBooks = wishBookDAO.selectMyWishBookInfo(memId);
		if(wishBooks.isEmpty()) {
			System.out.println("신청한 희망도서가 없습니다.");
			return;
		}
		System.out.println("-".repeat(90));
		System.out.printf("번호\t신청일\t\t %-20s   %-15s   %-10s\n","제목","저자","출판사");
		for (wishBook wishBook : wishBooks) {
			System.out.printf("%d\t%s\t %-20s   %-15s   %-10s\n",
					wishBook.getWishNum(),
					wishBook.getWishDate(),
					wishBook.getWishTitle(),
					wishBook.getWishAuthor(),
					wishBook.getWishPublisher(),
					wishBook.getWishDate());
		}
	}
	// 희망도서 삭제
	@Override
	public void deleteWishBook() {
		List<wishBook> wishBooks = wishBookDAO.selectMyWishBookInfo(memId);
		if (wishBooks == null || wishBooks.isEmpty()) {
		    System.out.println("신청한 희망도서가 없습니다.");
		    return;
		}
	    System.out.println("신청한 희망 도서 목록:");
	    showMyWishBooks(br, memId);
	    while(true) {
	    	try {
	    		System.out.print("삭제할 번호를 입력하세요 (뒤로가기:q) : ");
	    		String del = br.readLine();
	    		if(util.Util.goBack(del)) return;
	    		int num = Integer.parseInt(del);
	    		int count = wishBookDAO.checkWishBookRecordNumId(num, memId);
	    		if(count==1) {
	    			wishBookDAO.deleteWishMyBookInfo(memId, num);
	    			System.out.println("희망도서 신청을 취소했습니다.");
	    		}
	    		else if(count==0) System.out.println("번호를 잘못입력했습니다.");
	    		else if(count==-1) System.out.println("정보 처리 중 오류 발생");
	    		else System.out.println("삭제할 도서를 찾지 못하였습니다.");
	    	} catch (NumberFormatException e) {
	    		System.out.println("[숫자만 입력 가능]");
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }	
	}	
}
