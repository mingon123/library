package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import com.library.DAO.BookDAO;
import com.library.DAO.BookOrderDAO;
import com.library.DAO.BookReturnDAO;
import com.library.DAO.MemberDAO;
import com.library.DAO.ReservationDAO;
import com.library.DAO.ReviewDAO;
import com.library.DAO.impl.BookDAOImpl;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.BookReturnDAOImpl;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.DAO.impl.ReservationDAOImpl;
import com.library.DAO.impl.ReviewDAOImpl;
import com.library.service.BookOrderService;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.QnaService;
import com.library.service.ReservationService;
import com.library.service.ReviewService;
import com.library.service.WishBookService;
import com.library.service.Impl.BookOrderServiceImpl;
import com.library.service.Impl.BookServiceImpl;
import com.library.service.Impl.MemberServiceImpl;
import com.library.service.Impl.QnaServiceImpl;
import com.library.service.Impl.ReservationServiceImpl;
import com.library.service.Impl.ReviewServiceImpl;
import com.library.service.Impl.WishBookServiceImpl;


public class UserMenu {
	private String memId;
	private BufferedReader br;

	private BookDAO bookDAO;
	private MemberDAO memberDAO;
	private BookOrderDAO bookOrderDAO;
	private ReservationDAO reservationDAO;
	private ReviewDAO reviewDAO;
	private BookReturnDAO bookReturnDAO;

	private BookService bookService;
	private BookOrderService bookOrderService;
	private MemberService memberService;
	private WishBookService wishBookService;
	private QnaService qnaService;
	private ReviewService reviewService;
	private ReservationService reservationService;

	private BookMenu bookMenu;
	
	public UserMenu(String memId, BufferedReader br) throws IOException {
		this.memId = memId;
		this.br = br;

		// DAO 객체 초기화
		this.bookDAO = new BookDAOImpl();
		this.memberDAO = new MemberDAOImpl(memId);
		this.bookOrderDAO = new BookOrderDAOImpl(memId);
		this.reservationDAO = new ReservationDAOImpl(memId);
		this.reviewDAO = new ReviewDAOImpl();
		this.bookReturnDAO = new BookReturnDAOImpl(memId);

		// Service 객체 초기화
		this.bookService = new BookServiceImpl(br, this.bookDAO, memId);
		this.bookOrderService = new BookOrderServiceImpl(br, memId);
		this.memberService = new MemberServiceImpl(this.memberDAO, memId, br);
		this.wishBookService = new WishBookServiceImpl(br, memId);
		this.qnaService = new QnaServiceImpl(br, memId);
		this.reviewService = new ReviewServiceImpl(br, memId);
		this.reservationService = new ReservationServiceImpl(br, memId);

		this.bookMenu = new BookMenu(br, bookService, bookDAO);
		
		showUserMenu();
	}

	private void showUserMenu() throws IOException {
		while (true) {
			Open.main(null);
			System.out.println("\n===== 사용자 메뉴 =====");
			for (UserMenuEnum menu : UserMenuEnum.values()) {
				System.out.println(menu.getNumber() + ". " + menu.getTitle());
			}
			System.out.print("메뉴 선택: ");

			try {
				int choice = Integer.parseInt(br.readLine().trim());
				if (choice == UserMenuEnum.LOGOUT.getNumber()) {
					System.out.println("로그아웃합니다. \n");
					new MainMenu();
					return;
				} else if (choice == UserMenuEnum.EXIT.getNumber()) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				} else {
					handleMenu(choice);
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}
	}

	private void handleMenu(int choice) throws IOException {
		// 선택한 메뉴에 맞는 기능을 호출
		switch (choice) {
		case 1:	showUserAlerts();break;  // 사용자 알림 화면
		case 2:	bookMenu.showBookList();break; // 도서 목록 화면
		case 3: bookMenu.searchBooks();break; // 도서 검색 화면
		case 4: showReviewPage();break; // 리뷰 화면
		case 5:	showRentReservePage();break;  // 대여/예약 화면
		case 6: showReturnPage(memId);break; // 반납 화면
		case 7:	showOtherFunctions();break;  // 기타 기능 화면
		default: System.out.println("잘못된 선택입니다.");
		}
	}

	// 사용자 알림 화면(1)
	private void showUserAlerts() throws IOException {
		System.out.println("사용자 알림 화면입니다.");
		System.out.println("정지상태/연체/반납일/예약도서알림");
		System.out.println("-".repeat(90));
		Date memStop = memberDAO.checkMemStop(memId);
		boolean overReturn = bookReturnDAO.isOverReturn();
		boolean returnDateNotification = bookReturnDAO.isReturnDateNotification();
		boolean reservationNotification = reservationDAO.isReservationNotification(memId);

		if((memStop!=null||overReturn||returnDateNotification||reservationNotification)) {
			if(memStop!=null) {
				System.out.printf("정지 상태입니다. 정지해제일 : %s \n", memStop);
				System.out.println("-".repeat(90));
			}
			if(overReturn)	
				if(returnDateNotification)
					if(reservationNotification) System.out.println();
		} else {
			System.out.println("알림이 없습니다.");
			System.out.println("-".repeat(90));
		}
		return;
	}
	
	// 리뷰 화면(4)
	private void showReviewPage() throws IOException {
		System.out.println("리뷰 화면입니다.");
		while(true) {
			try {
				System.out.print("1.전체리뷰 2.책제목검색 3.내리뷰 4.뒤로가기\n > ");
				int no = Integer.parseInt(br.readLine());
				switch (no) {
				case 1:
					reviewDAO.selectReviewInfo();
					reviewService.selectDetailReview();
					break;
				case 2: reviewService.selectBookTitleReview();break;
				case 3:	showMyReviewMenu();break;
				case 4: System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");return;
				default: System.out.println("잘못 입력하셨습니다.");
				}
			} catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	private void showMyReviewMenu() throws IOException {
		while(true) {
			System.out.print("1.내리뷰확인 2.리뷰수정 3.리뷰삭제 4.뒤로가기\n > ");
			int num = Integer.parseInt(br.readLine());
			switch (num) {
			case 1: reviewDAO.selectMyReviewInfo(memId);break;
			case 2: reviewService.updateMyReview();break;
			case 3: reviewService.deleteMyReview();break;
			case 4: System.out.println("뒤로가기를 선택하셨습니다.");return;
			default: System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	// 대여/예약 화면(5)
	private void showRentReservePage() throws IOException {
		if (memberDAO.checkMemStop(memId)!=null) {
	        System.out.println("현재 정지상태입니다. 대여/예약이 불가능합니다.");
	        System.out.println("홈화면으로 돌아갑니다.\n");
	        return;
	    }
	    System.out.print("1.대여/예약하기 2.대여/예약 내역확인 3.예약취소 4.뒤로가기\n >");
	    try {
	        int no = Integer.parseInt(br.readLine());
	        switch (no) {
	            case 1: bookOrderService.handleBookOrder();break;
	            case 2: bookOrderService.viewOrderHistory();break;
	            case 3: reservationService.cancelReservation(memId);break;
	            case 4: System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");break;
	            default: System.out.println("잘못 입력하셨습니다.");
	        }
	    } catch (NumberFormatException e) { System.out.println("[숫자만 입력 가능]");}
	}

	// 반납 화면(6)
	public void showReturnPage(String memId) throws IOException {
	    System.out.println("\n반납 메뉴를 선택하셨습니다.");
	    System.out.println("-".repeat(90));
	    System.out.println("\t\t\t\t\t\t대여 현황");
	    System.out.println("-".repeat(90));
	    bookOrderDAO.selectUserNowOrderInfo();
	    System.out.println("-".repeat(90));

	    if (bookOrderDAO.checkZeroOrder()) {
	        System.out.println("\n대여기록이 존재하지 않습니다.");
	        System.out.println("이전화면으로 돌아갑니다.\n");
	        return;
	    }

	    int orderNum = bookOrderService.getValidBookNumber(memId, true);  // 대여번호 입력 받기
	    if (orderNum == -1) return;  // 대여번호가 유효하지 않으면 종료

	    // 반납 여부 확인
	    if (bookOrderService.confirmReturn()) {
	        bookOrderService.processReturn(orderNum);
	        if (reviewService.confirmReview()) {
	            reviewService.processReview(orderNum, memId);
	        }
	    }

	    System.out.println("\n이전화면으로 돌아갑니다.");
	}

	// 기타 기능 화면(7)
	private void showOtherFunctions() throws IOException {
		System.out.println("기타 기능 화면입니다.");
		while(true) {
			try {
				System.out.print("1.희망도서 2.Q&A 3.회원정보관리 4.뒤로가기\n > ");
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:	wishBookService.manageWishBook();break;
				case 2:	qnaService.manageQNA();break;
				case 3: memberService.manageMemberInfo();break;
				case 4: System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");return;
				default: System.out.println("잘못 입력하셨습니다.");
				} 
			} catch (NumberFormatException e) {System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {e.printStackTrace();}
		}
	}
}
