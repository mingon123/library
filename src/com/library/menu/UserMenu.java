package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.service.MemberService;
import com.library.service.QnaService;
import com.library.service.WishBookService;
import com.library.service.Impl.MemberServiceImpl;
import com.library.service.Impl.QnaServiceImpl;
import com.library.service.Impl.WishBookServiceImpl;


public class UserMenu {
	private String memId="aasd";
	private BufferedReader br;

	public UserMenu(String memId, BufferedReader br) throws IOException {
		this.memId = memId;
		this.br = br;
		showUserMenu();
	}

	private void showUserMenu() throws IOException {
		while (true) {
			System.out.println("\n===== 사용자 메뉴 =====");
			for (UserMenuEnum menu : UserMenuEnum.values()) {
				System.out.println(menu.getNumber() + ". " + menu.getTitle());
			}
			System.out.print("메뉴 선택: ");

			try {
				int choice = Integer.parseInt(br.readLine().trim());
				if (choice == UserMenuEnum.LOGOUT.getNumber()) {
					System.out.println("로그아웃합니다.");
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
		case 2:	showBookList();break; // 도서 목록 화면
		case 3: searchBooks();break; // 도서 검색 화면
		case 4: showReviewPage();break; // 리뷰 화면
		case 5:	showRentReservePage();break;  // 대여/예약 화면
		case 6: showReturnPage();break; // 반납 화면
		case 7:	showOtherFunctions();break;  // 기타 기능 화면
		default: System.out.println("잘못된 선택입니다.");
		}
	}

	// 사용자 알림 화면
	private void showUserAlerts() throws IOException {
		System.out.println("사용자 알림 화면입니다.");
		MemberService member = new MemberServiceImpl(new MemberDAOImpl(), new BookOrderDAOImpl(), memId, br);
		member.checkUserNotifications();

	}

	// 도서 목록 화면
	private void showBookList() {
		System.out.println("도서 목록 화면입니다.");
		// 실제 도서 목록 기능 구현
	}

	// 도서 검색 화면
	private void searchBooks() {
		System.out.println("도서 검색 화면입니다.");
		// 실제 도서 검색 기능 구현
	}

	// 리뷰 화면
	private void showReviewPage() {
		System.out.println("리뷰 화면입니다.");
		// 실제 리뷰 화면 기능 구현
	}

	// 대여/예약 화면
	private void showRentReservePage() {
		System.out.println("대여/예약 화면입니다.");
		// 실제 대여/예약 기능 구현
	}

	// 반납 화면
	private void showReturnPage() {
		System.out.println("반납 화면입니다.");
		// 실제 반납 기능 구현
	}

	// 기타 기능 화면
	private void showOtherFunctions() throws IOException {
		System.out.println("기타 기능 화면입니다.");
		while(true) {
			System.out.print("1.희망도서 2.Q&A 3.회원정보관리 4.뒤로가기\n > ");
			try {
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					WishBookService wishBook = new WishBookServiceImpl(br,memId);
					wishBook.manageWishBook();
					break;
				case 2:
					QnaService qna = new QnaServiceImpl(br,memId);
					qna.manageQNA();
					break;
				case 3:
					MemberService member = new MemberServiceImpl(new MemberDAOImpl(),new BookOrderDAOImpl(), memId, br);
					member.manageMemberInfo();
					break;
				case 4:
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
				} 
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
