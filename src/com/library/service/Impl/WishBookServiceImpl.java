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

public class WishBookServiceImpl implements WishBookService{
	private WishBookDAO wishBookDAO = new WishBookDAOImpl(); 
	private MemberDAO memberDAO;
	private String memId;
	
	public WishBookServiceImpl(String memId) {
		this.memId = memId;
	}
	
	@Override
	public void manageWishBook(BufferedReader br) throws IOException {
		while(true) {
			System.out.print("1.희망도서신청 2.희망도서신청내역확인 3.희망도서신청취소 4.뒤로가기\n > ");
			try {
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					insertWishBook(br);
					break;
				case 2:
					showWishBooks();
					break;
				case 3:
					deleteWishBook(br);
					break;
				case 4:
					System.out.println("뒤로가기를 선택하셨습니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}	
	}

	@Override
	public void insertWishBook(BufferedReader br) throws IOException {
		System.out.println("희망도서 신청화면입니다. (뒤로가기:q) ");
		System.out.print("희망도서 제목을 입력하세요: ");
		String title = br.readLine();
		if(title.equalsIgnoreCase("q")) {
			System.out.println("이전 화면으로 돌아갑니다.");
			return;
		}
		System.out.print("저자를 입력하세요: ");
		String author = br.readLine();
		System.out.print("출판사를 입력하세요: ");
		String publisher = br.readLine();
		wishBookDAO.insertWishBook(title, author, publisher, memId);
	}

	public void showWishBooks() {
		List<wishBook> wishBooks = wishBookDAO.selectWishBookInfo();
		if(wishBooks.isEmpty()) {
			System.out.println("신청한 희망도서가 없습니다.");
			return;
		}
		System.out.println("-".repeat(90));
		System.out.println("번호\t제목\t저자\t출판사\t신청일");
		for (wishBook wishBook : wishBooks) {
			System.out.print(wishBook.getWishNum() + "\t");
			System.out.print(wishBook.getWishTitle() + "\t");
			System.out.print(wishBook.getWishAuthor() + "\t");
			System.out.print(wishBook.getWishPublisher() + "\t");
			System.out.println(wishBook.getWishDate() + "\t");
		}
	}

	@Override
	public void deleteWishBook(BufferedReader br) {
		List<wishBook> wishBooks = wishBookDAO.selectWishBookInfo(memId);
		if (wishBooks == null || wishBooks.isEmpty()) {
		    System.out.println("신청한 희망도서가 없습니다.");
		    return;
		}
	    System.out.println("신청한 희망 도서 목록:");
	    for (wishBook book : wishBooks) {
	        System.out.println("번호: " + book.getWishNum() + " | 제목: " + book.getWishTitle() + 
                    " | 저자: " + book.getWishAuthor() + " | 출판사: " + book.getWishPublisher());
	    }
	    while(true) {
	    	try {
	    		System.out.print("삭제할 번호를 입력하세요 (뒤로가기:q) : ");
	    		String del = br.readLine();
	    		if(del!=null && del.equalsIgnoreCase("q")) {
	    			System.out.println("이전 화면으로 돌아갑니다.");
	    			return;
	    		}
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
