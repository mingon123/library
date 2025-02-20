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
		out: // 2nd depth 메뉴에서 뒤로가기 호출 후 종료시 바로 빠져나가려고 라벨 붙여놓음.
			while(true) { // flag로 수정요망
				System.out.print("1.회원 관리, 2.도서 관리, 3.대여 관리, 4.예약 관리, 5.희망도서 관리,\n"
						+ "6.리뷰 관리, 7.공지사항 관리, 8.Q&A 관리, 9.통계, 10.종료\n"
						+ "번호를 입력하세요.> ");
				try {
					int no = Integer.parseInt(br.readLine());
					if (no==1) { // 회원 관리
						int num;
						do {
							System.out.print("1.회원 목록보기, 2.회원 상세정보 확인, 3.회원정보 등록, 4.회원정보 수정, 5.회원정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							num = Integer.parseInt(br.readLine());				
							if (num==1) { // 1-1.회원 목록보기
								dao.selectMember();
							} else if (num==2) { // 1-2.회원 상세정보 확인
								dao.selectMember();		
								System.out.print("조회할 회원ID 입력: ");
								String mem_id = br.readLine();
								int count = dao.checkMemberRecord(mem_id);
								do { //잘못 입력하면 다시 입력받음
									if (count==0) {
										System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");
										mem_id = br.readLine();
										count = dao.checkMemberRecord(mem_id);
									} else if (count!=1) {
										System.out.println("정보 처리 중 오류 발생");
									} // if
								} while (count!=1);
								dao.selectDetailMember(mem_id);
							} else if (num==3) { // 1-3.회원정보 등록
								System.out.print("회원 아이디:");		
								String mem_id = br.readLine();
								int count = dao.checkMemberRecord(mem_id);
								//아이디 중복체크 
								do {
									if (count==1) {
										System.out.print("이미 존재하는 아이디 입니다. 다시입력하세요.: ");
										mem_id = br.readLine();
										count = dao.checkMemberRecord(mem_id);
									} else if (count!=0) {
										System.out.println("정보 처리 중 오류 발생");
									} // if
								} while (count!=0);
								System.out.print("회원 비밀번호: "); // 유효성 검사 추가요망	
								String mem_pw = br.readLine();
								System.out.print("이름: ");		
								String mem_name = br.readLine();
								System.out.print("전화번호: ");		
								String mem_cell = br.readLine();
								System.out.print("이메일: ");		
								String mem_email = br.readLine();
								dao.InsertMember(mem_id,mem_pw,mem_name,mem_cell,mem_email);
							} else if (num==4) { // 1-4.회원정보 수정							
								dao.selectMember();		
								System.out.print("수정할 회원ID 입력: ");
								String mem_id = br.readLine();
								int count = dao.checkMemberRecord(mem_id);
								do { //잘못 입력하면 다시 입력받음
									if (count==0) {
										System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");
										mem_id = br.readLine();
										count = dao.checkMemberRecord(mem_id);
									} else if (count!=1) {
										System.out.println("정보 처리 중 오류 발생");
									} // if
								} while (count!=1);
								System.out.print("회원 비밀번호: "); // 유효성 검사 추가요망	
								String mem_pw = br.readLine();
								System.out.print("이름: ");		
								String mem_name = br.readLine();
								System.out.print("전화번호: ");		
								String mem_cell = br.readLine();
								System.out.print("이메일: ");		
								String mem_email = br.readLine();
								dao.updateMember(mem_id,mem_pw,mem_name,mem_cell,mem_email);							
							} else if (num==5) { // 1-5.회원정보 삭제
								dao.selectMember();
								System.out.print("삭제할 회원ID 입력: ");
								String mem_id = br.readLine();
								int count = dao.checkMemberRecord(mem_id);							
								do { //잘못 입력하면 다시 입력받음
									if (count==0) {
										System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");
										mem_id = br.readLine();
										count = dao.checkMemberRecord(mem_id);
									} else if (count!=1) {
										System.out.println("정보 처리 중 오류 발생");
									} // if
								} while (count!=1);
								dao.deleteMember(mem_id);
							} else if (num==6) { // 1-6.뒤로가기
								callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감				
							} else {
								System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
							}
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

					} else if (no==2) { // 도서 관리			
						// dao.selectBook();
						// 수정,등록,삭제 기능 추가요망
						int num;
						do {
							System.out.print("1.도서 목록보기, 2.도서 상세정보 확인, 3.도서정보 등록, 4.도서정보 수정, 5.도서정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							num = Integer.parseInt(br.readLine());				
							if (num==1) { // 2-1.도서 목록보기
								dao.selectBook();
							} else if (num==2) { // 2-2.도서 상세정보 확인
								dao.selectBook();		
								System.out.print("조회할 책번호 입력: ");
								int book_num = Integer.parseInt(br.readLine());
								int count = dao.checkBookRecord(book_num);						
								do { //잘못 입력하면 다시 입력받음
									if (count==0) {
										System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");
										mem_id = br.readLine();
										count = dao.checkBookRecord(book_num);
									} else if (count!=1) {
										System.out.println("정보 처리 중 오류 발생");
									} // if
								} while (count!=1);
								dao.selectDetailBook(book_num);
							} else if (num==3) { //2-3.도서정보 등록 -> TEST중					
								System.out.print("제목: "); // 유효성 검사 추가?	
								String book_title = br.readLine();
								System.out.print("저자: "); // 
								String book_author = br.readLine();
								System.out.print("출판사: "); // 
								String book_publisher = br.readLine();
								System.out.print("출판년도: "); // 
								int book_p_year = Integer.parseInt(br.readLine());
								System.out.print("카테고리: "); // 
								String book_category = br.readLine();
								System.out.print("추천순위: "); // 
								int book_rank = Integer.parseInt(br.readLine());
								System.out.print("보유권수"); // 
								int book_volm_cnt = Integer.parseInt(br.readLine());					
								dao.insertBook(book_title, book_author, book_publisher, book_p_year, 
										book_category, book_rank, book_volm_cnt);		
							} else if (num==4) { //2-4.도서정보 수정

							} else if (num==3) { //2-5.도서정보 삭제	

							} else if (num==6) { //2-6.뒤로가기	

							} else {
								System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
							}
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

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
