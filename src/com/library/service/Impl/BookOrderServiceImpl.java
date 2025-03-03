package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.BookDAO;
import com.library.DAO.BookOrderDAO;
import com.library.DAO.MemberDAO;
import com.library.DAO.ReservationDAO;
import com.library.DAO.impl.BookDAOImpl;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.BookReturnDAOImpl;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.DAO.impl.ReservationDAOImpl;
import com.library.service.BookOrderService;
import com.library.service.ReservationService;

public class BookOrderServiceImpl implements BookOrderService {
	private BufferedReader br;
	private String memId;
	private BookOrderDAO bookOrderDAO;
	private BookDAO bookDAO;
	private MemberDAO memberDAO;
	private ReservationDAO reservationDAO;
	private ReservationService reservationService;

    public BookOrderServiceImpl(BufferedReader br, String memId) {
        this.br = br;
        this.memId = memId;
        
        this.bookOrderDAO = new BookOrderDAOImpl(memId);
        this.bookDAO = new BookDAOImpl();
        this.reservationDAO = new ReservationDAOImpl(memId);
        this.memberDAO = new MemberDAOImpl(memId);

        this.reservationService = new ReservationServiceImpl(br, memId);
    }

	// 대여하기 처리
	@Override
	public void handleRental(int bookNum) throws IOException {
	    int res = bookOrderDAO.canOrder(bookNum);
	    if (res == 1) {
	        System.out.println("\n대여가 가능합니다.");
	        bookOrderDAO.insertBookOrder(bookNum);
	        System.out.println("이전화면으로 돌아갑니다.");
	    } else if (res == 0) {
	        System.out.println("\n해당 번호의 책의 권수가 0권입니다.");
	        reservationService.handleReservation(bookNum);  // 예약하기 처리로 이동
	    } else if (res == -1) {
	        System.out.println("\n대여권수가 이미 3권입니다.");
	        System.out.println("이전화면으로 돌아갑니다.");
	        return;
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
	            case 2: {
	    	        int reservationCount = reservationDAO.countUserReservations(memId);
	    	        if (reservationCount >= 2) {
	    	            System.out.println("예약은 최대 2권까지 가능합니다. 기존 예약을 취소한 후 다시 시도해주세요.");
	    	            return;
	    	        }
	            	reservationService.handleReservation(bookNum);
	            	break;
	            }
	            case 3: System.out.println("\n뒤로가기를 선택하셨습니다.");return;
	            default: System.out.println("잘못 입력하셨습니다.");
	        }
	    } catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");}
	}
	
	// 대여/예약하기 처리
	@Override
	public void handleBookOrder() throws IOException {
	    while (true) {
	        System.out.println("랜덤 책 목록");
	        bookDAO.randomBookInfo(5);
	        int bookNum = getValidBookNumber(memId, false);
	        orderOrReserveMenu(bookNum);
	        
	        System.out.print("다시 검색하시겠습니까? (Y/N): ");
	        String choice = br.readLine();
	        if (choice.equalsIgnoreCase("n")) {
	            System.out.println("뒤로 가기를 선택하셨습니다.");
	            break;
	        }
	    }
	}
	
	// 유효한 책 번호 입력 받기
	@Override
	public int getValidBookNumber(String memId, boolean isReturn) throws IOException {
	    boolean flag = false;
	    int bookNum = -1;
	    while (true) {
	        try {
	            if (flag) System.out.println("존재하지 않는 책 번호입니다.");
	            String actionMessage = isReturn ? "반납하실 책의 대여번호를 입력해주세요" : "대여/예약 하실 책 번호를 입력해주세요";
	            System.out.print(actionMessage + " (뒤로가기:q) : ");
	            
	            String q = br.readLine();
	            if(util.Util.goBack(q)) break;
	            bookNum = Integer.parseInt(q);
	            if (bookDAO.checkBookRecord("book_num", String.valueOf(bookNum)) == 1) break;
	        } catch (NumberFormatException e) {
	            System.out.println("[숫자만 입력 가능]");
	        }
	    }
	    return bookNum;
	}

	// 대여/예약 처리
	@Override
	public void handleBookAction(String memId, int bookNum, int no) throws IOException {
	    switch (no) {
	        case 1: handleRental(bookNum);break;
	        case 2: reservationService.handleReservation(bookNum);break;
	        case 3: System.out.println("뒤로가기를 선택하셨습니다.");return;
	        default: System.out.println("잘못 입력하셨습니다.");
	    }
	}
	
	// 대여/예약 내역 보기
	@Override
	public void viewOrderHistory() throws IOException {
	    while (true) {
	        System.out.print("1.대여/예약 현황확인 2.대여 내역확인 3.뒤로가기\n >");
	        int no = Integer.parseInt(br.readLine());
	        switch (no) {
	            case 1: {
	            	viewOrderAndReservationInfo(memId);
	            	System.out.print("연장할 대여 번호를 입력해주세요 (뒤로가기:q) : ");
	            	String q = br.readLine();
	            	if(util.Util.goBack(q)) break;
	                try {
	                    int num = Integer.parseInt(q);
	                    handleExtension(num, memId); // 연장 처리
	                } catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");}
	                break;
	            }
	            case 2: viewOrderHistoryByDate(memId);break;
	            case 3: System.out.println("뒤로가기를 선택하셨습니다.");return;
	            default: System.out.println("잘못 입력하셨습니다.");
	        }
	    }
	}
	
	// 대여 연장 기능을 처리하는 메서드
	public void handleExtension(int orderNum, String memId) throws IOException {
	    if (bookOrderDAO.checkOrderAdd(orderNum)) {
	        System.out.print("연장하시겠습니까? (Y/N): ");
	        String choice = br.readLine();
	        if (choice.equalsIgnoreCase("Y")) bookOrderDAO.updateReturnDate(orderNum);
	        else System.out.println("연장을 취소하셨습니다.");
	    } else System.out.println("연장이 불가능한 도서입니다.");
	}
	
	// 로그인한 유저의 대여내역을 최신순/오래된 순으로 조회
	@Override
	public void viewOrderHistoryByDate(String memId) throws IOException {
	    while (true) {
	        System.out.print("1.최신순 2.오래된순 3.뒤로가기\n >");
	        try {
	            int selectNum = Integer.parseInt(br.readLine());
	            if (selectNum == 3) {
	                System.out.println("뒤로가기를 선택하셨습니다.");
	                return;
	            }
	            if (selectNum == 1 || selectNum == 2) {
	                System.out.println("-".repeat(90));
	                bookOrderDAO.selectUserOrderInfo(selectNum);
	                System.out.println("-".repeat(90));
	                return;
	            }
	            System.out.println("잘못 입력하셨습니다.");
	        } catch (NumberFormatException e) {
	            System.out.println("[숫자만 입력 가능]");
	        }
	    }
	}
	
	// 대여 및 예약 현황 보기
	@Override
	public void viewOrderAndReservationInfo(String memId) {
		System.out.println("-".repeat(90));
	    System.out.println("대여 현황");
	    System.out.println("-".repeat(90));
	    bookOrderDAO.selectUserNowOrderInfo();
	    System.out.println("-".repeat(90));
	    System.out.println("예약 현황");
	    System.out.println("-".repeat(90));
	    reservationDAO.selectUserNowReserveInfo(memId);
	    System.out.println("-".repeat(90));
	}
	
	// 반납 여부 체크
	@Override
	public boolean confirmReturn() throws IOException {
	    return util.Util.choiceYN(br, "반납하시겠습니까?");
	}
	
	// 반납
	@Override
	public void processReturn(int orderNum) {
	    System.out.println();
	    new BookReturnDAOImpl(memId).updateOrderReturn(orderNum);
	    memberDAO.updateStopDate(orderNum);
	    bookOrderDAO.selectLateReturn(orderNum);
	}
	
}
