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
				if (no==1) dao.selectMemberInfo(); // 회원 관리				
				else if (no==2) { // 도서 관리			
					dao.selectBookInfo(); // 수정,추가,삭제 기능 추가요망
				} else if (no==3) { // 대여 관리				
					dao.selectOrderInfo();
				} else if (no==4) { // 예약 관리
					dao.selectRSVInfo();
				} else if (no==5) { // 희망도서 관리
					dao.selectWishInfo();
				} else if (no==6) { // 리뷰 관리
					dao.selectReview();
				} else if (no==7) { // 공지사항 관리
					System.out.println("공지사항");					
				} else if (no==8) { // Q&A 관리
					System.out.println("Q&A 관리");
				} else if (no==9) { // 통계 관리
					System.out.println("통계 관리");
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
