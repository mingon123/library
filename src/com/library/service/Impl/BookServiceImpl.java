package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.library.DAO.BookDAO;
import com.library.DAO.MemberDAO;
import com.library.DAO.impl.BookDAOImpl;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.DTO.Book;
import com.library.service.BookOrderService;
import com.library.service.BookService;

import util.DBUtil;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO;
	private BufferedReader br;
	private String memId;
	private MemberDAO memberDAO; 
	private BookOrderService bookOrderService;
	
	public BookServiceImpl(BufferedReader br,BookDAO bookDAO,String memId) {
		this.bookDAO = bookDAO;
		this.br = br;
		this.memId = memId;
		this.memberDAO = new MemberDAOImpl(memId);
		this.bookOrderService = new BookOrderServiceImpl(br, memId);
		this.bookDAO = new BookDAOImpl();
	}
	
	// 카테고리별 책목록 표시
	@Override
	public void selectCategoryOfBook() {
		System.out.print("1.자기계발 2.소설 3.과학 4.역사 5.기타 6.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			switch (no) {
			case 1: case 2: case 3: case 4: case 5:
                String[] categories = {"자기계발", "소설", "과학", "역사", "기타"};
                bookDAO.selectCategoryOfBook(categories[no - 1]);
                selectDetailBook();
                break;
			case 6: System.out.println("뒤로가기를 선택하셨습니다.");return;
			default: System.out.println("잘못 입력하셨습니다.");
			}
		} catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {e.printStackTrace();}
	}
	
	// 책 상세정보 확인
	@Override
	public void selectDetailBook() throws IOException {
		System.out.print("조회할 책번호 입력 (뒤로가기:q) : ");
		String bookNum = br.readLine();
		if(util.Util.goBack(bookNum)) return;
		int count = bookDAO.checkBookRecord("book_num", bookNum);
		do {
			if(count==0) {
				System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요 (뒤로가기:q) : ");
				bookNum = br.readLine();
				if(util.Util.goBack(bookNum)) return;
				count = bookDAO.checkBookRecord("book_num", bookNum);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
				return;
			} 
		} while(count!=1);
		bookDAO.selectDetailBook(Integer.parseInt(bookNum));
		System.out.println("-".repeat(90));

		if(memId != null && memberDAO.checkMemStop(memId)==null) {
			bookOrderService.orderOrReserveMenu(Integer.parseInt(bookNum)); // 대여 예약
		}else if(memberDAO.checkMemStop(memId)!=null) {
			System.out.println("정지상태입니다. 대여/예약이 불가능합니다.");
		}else if(memId == null) {
			System.out.println("비회원입니다. 대여/예약은 로그인 후 이용바랍니다.");
		}
	}
	
	// 도서 검색
	@Override
	public void selectSearchBook(String searchType, String searchValue) throws IOException {
        if (searchValue.equalsIgnoreCase("q")) return;
        
		int count = bookDAO.checkBookRecord(searchType,searchValue);
		do {
			if(count==0) {
				if(searchType.equals("book_title")) System.out.print("입력한 단어가 포함된 도서가 없습니다. 다시입력하세요 (뒤로가기:q) : ");
				else System.out.println("입력한 단어가 포함된 저자가 없습니다. 다시입력하세요 (뒤로가기:q) : ");
				searchValue = br.readLine();
				if(searchValue.equalsIgnoreCase("q")) return;
				count = bookDAO.checkBookRecord(searchType,searchValue);
			} else if (count==-1) {System.out.println("정보 처리 중 오류 발생");}
		} while(count<=0);
		bookDAO.selectSearchBook(searchType, searchValue);
		System.out.println("-".repeat(90));
		selectDetailBook();
	}


}
