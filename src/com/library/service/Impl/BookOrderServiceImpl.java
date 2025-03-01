package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.BookOrderDAO;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.service.BookOrderService;
import com.library.service.ReservationService;

public class BookOrderServiceImpl implements BookOrderService {
	private BookOrderDAO bookOrderDAO = new BookOrderDAOImpl();
	private BufferedReader br;
	private String memId;
	private ReservationService reservationService;

    public BookOrderServiceImpl(BufferedReader br, String memId, BookOrderDAO bookOrderDAO, ReservationService reservationService) {
        this.br = br;
        this.memId = memId;
        this.reservationService = reservationService;
    }

	// 대여하기 처리
	@Override
	public void handleRental(int bookNum) throws IOException {
	    int res = bookOrderDAO.canOrder(memId, bookNum);
	    if (res == 1) {
	        System.out.println("\n대여가 가능합니다.");
	        bookOrderDAO.insertBookOrder(memId, bookNum);
	        System.out.println("이전화면으로 돌아갑니다.");
	    } else if (res == 0) {
	        System.out.println("\n해당 번호의 책의 권수가 0권입니다.");
	        reservationService.handleReservation(bookNum);  // 예약하기 처리로 이동
	    } else if (res == -1) {
	        System.out.println("\n대여권수가 이미 3권입니다.");
	        System.out.println("이전화면으로 돌아갑니다.");
	    } else {
	        System.out.println("프로그램 오류");
	    }
	}
	
	// 대여 및 예약 메뉴 선택 처리
	@Override
	public void orderOrReserveMenu(int bookNum) throws IOException {
	    System.out.print("1.대여하기 2.예약하기 3.뒤로가기\n >");
	    try {
	        int no = Integer.parseInt(br.readLine());
	        switch (no) {
	            case 1: handleRental(bookNum);break;
	            case 2: reservationService.handleReservation(bookNum);break;
	            case 3: System.out.println("\n뒤로가기를 선택하셨습니다.");return;
	            default: System.out.println("잘못 입력하셨습니다.");
	        }
	    } catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");}
	}
}
