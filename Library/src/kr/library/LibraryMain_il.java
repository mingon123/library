package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lilly
 * @date 2025. 2. 19. - 오후 2:40:51
 * @subject	 관리자 페이지
 * @content 
 */
public class LibraryMain_il {
	private BufferedReader br;
	private BookDAO_il dao;
	private String mem_id; // 로그인한 아이디 저장
	private boolean flag; // 로그인 여부 

	public LibraryMain_il() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new BookDAO_il();			
			callAdminMenu(); // 메뉴 호출
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			// 자원정리
			if(br!=null) try {br.close();} catch(IOException e) {}	
		}
	} // LibraryMain_il()

	// 관리자 메뉴 호출
	private void callAdminMenu() throws IOException {		
		// 로그인 성공후 관리자 메뉴 호출
		while(true) { // flag로 수정요망
			System.out.print("1.회원 관리, 2.도서 관리, 3.대여 관리, 4.예약 관리, 5.희망도서 관리,\n"
					+ "6.리뷰 관리, 7.공지사항 관리, 8.Q&A 관리, 9.통계, 10.종료\n"
					+ "번호를 입력하세요.> ");
			try {
				int no = Integer.parseInt(br.readLine());
				if (no==1) { // 회원 관리
					System.out.print("1.회원 목록보기, 2.회원 상세정보 확인, 3.회원정보 삭제\n"
							+ "번호를 입력하세요.> ");
					int num = Integer.parseInt(br.readLine());				
					if (num==1) { // 1-1.회원 목록보기
						// 목록보기
						dao.selectMember();
					} else if (num==2) { // 1-2.회원 상세정보 확인
						dao.selectMember();		
						System.out.print("조회할 회원ID 입력: ");
						String mem_id = br.readLine();
						int count = dao.checkMemberRecord(mem_id);
						//잘못 입력하면 다시 입력받음
						do {
							if (count==0) {
								System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");
								mem_id = br.readLine();
								count = dao.checkMemberRecord(mem_id);
							} else if (count!=1) {
								System.out.println("정보 처리 중 오류 발생");
							} // if
						} while (count!=1);
						dao.selectDetailMember(mem_id);
					} else if (num==3) { // 1-3.회원 삭제
						dao.selectMember();
						System.out.print("삭제할 회원ID 입력: ");
						String mem_id = br.readLine();
						int count = dao.checkMemberRecord(mem_id);
						//잘못 입력하면 다시 입력받음
						do {
							if (count==0) {
								System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");
								mem_id = br.readLine();
								count = dao.checkMemberRecord(mem_id);
							} else if (count!=1) {
								System.out.println("정보 처리 중 오류 발생");
							} // if
						} while (count!=1);
						dao.deleteMember(mem_id);
					}

				} else if (no==2) { // 도서 관리			
					// dao.selectBook();
					// 수정,등록,삭제 기능 추가요망
					System.out.print("1.도서 목록보기, 2.도서 상세정보 확인, 3.도서정보 등록, 4.도서정보 수정, 5.도서정보 삭제\n"
							+ "번호를 입력하세요.> ");
					int num = Integer.parseInt(br.readLine());				
					if (num==1) { // 2-1.도서 목록보기
						// 목록보기
						dao.selectBook();
					} else if (num==2) { // 2-2.도서 상세정보 확인
						dao.selectBook();		
						System.out.print("조회할 책번호 입력: ");
						int book_num = Integer.parseInt(br.readLine());
						int count = dao.checkBookRecord(book_num);
						//잘못 입력하면 다시 입력받음
						do {
							if (count==0) {
								System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");
								mem_id = br.readLine();
								count = dao.checkBookRecord(book_num);
							} else if (count!=1) {
								System.out.println("정보 처리 중 오류 발생");
							} // if
						} while (count!=1);
						dao.selectDetailBook(book_num);
					} else if (num==3) { //2-3.도서정보 등록	
						//추가중~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~			
					} // 2-4.도서정보 수정, 2-5.도서정보 삭제 추가 예정

				} else if (no==3) { // 대여 관리				
					dao.selectOrder();
				} else if (no==4) { // 예약 관리
					dao.selectRSV();
				} else if (no==5) { // 희망도서 관리
					dao.selectWish();
				} else if (no==6) { // 리뷰 관리
					dao.selectReview();
				} else if (no==7) { // 공지사항 관리
					dao.selectNotice();
				} else if (no==8) { // Q&A 관리
					dao.selectQnA();
				} else if (no==9) { // 통계 관리
					System.out.println("통계 관리: 작성 요망");
				} else if (no==10) { // 프로그램 종료 
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");				
				} // if
			} catch(NumberFormatException e){
				System.out.println("[숫자만 입력 가능]");
			} 
		} // while
	} // callAdminMenu()

	public static void main(String[] args) {
		new LibraryMain_il();
	} // main

} // class
