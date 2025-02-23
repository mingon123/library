package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;

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
						int num = 0;
						do {
							System.out.print("1.회원 목록보기, 2.회원 상세정보 확인, 3.회원정보 등록, 4.회원정보 수정, 5.회원정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
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
									System.out.print("회원 아이디: ");		
									String mem_id = br.readLine();
									int count = dao.checkMemberRecord(mem_id);
									//아이디 중복체크 
									do {
										if (count==1) {
											System.out.print("이미 존재하는 아이디 입니다. 다시입력하세요.: ");										
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
								} // if
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");								
							}
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

					} else if (no==2) { // 도서 관리					
						int num=0;
						do {
							System.out.print("1.도서 목록보기, 2.도서 상세정보 확인, 3.도서정보 등록, 4.도서정보 수정, 5.도서정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
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
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									dao.selectDetailBook(book_num);

								} else if (num==3) { //2-3.도서정보 등록 (추천순위 입력 안받음.-> 재검토요망)
									System.out.print("제목: "); // 유효성 검사 추가? 이미 존재하는 책인지?
									String book_title = br.readLine();
									System.out.print("저자: "); // 
									String book_author = br.readLine();
									System.out.print("출판사: "); // 
									String book_publisher = br.readLine();
									System.out.print("출판년도: "); // 
									int book_p_year = Integer.parseInt(br.readLine());
									System.out.print("카테고리: "); // 
									String book_category = br.readLine();
									System.out.print("보유권수: "); // 
									int book_volm_cnt = Integer.parseInt(br.readLine());					
									dao.insertBook(book_title, book_author, book_publisher, book_p_year, 
											book_category, book_volm_cnt);	

								} else if (num==4) { //2-4.도서정보 수정
									dao.selectBook();		
									System.out.print("수정할 책번호 입력: ");
									int book_num = Integer.parseInt(br.readLine());
									int count = dao.checkBookRecord(book_num);
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									System.out.print("제목: ");		
									String book_title = br.readLine();
									System.out.print("저자: ");		
									String book_author = br.readLine();
									System.out.print("출판사: ");		
									String book_publisher = br.readLine();
									System.out.print("출판년도: ");		
									int book_p_year = Integer.parseInt(br.readLine());							
									System.out.print("카테고리: ");		
									String book_category = br.readLine();								
									System.out.print("추천순위: ");		
									int book_rank = Integer.parseInt(br.readLine());								
									System.out.print("보유권수: ");							
									int book_volm_cnt = Integer.parseInt(br.readLine());

									dao.updateBook(book_num,book_title, book_author, book_publisher, book_p_year, 
											book_category, book_rank, book_volm_cnt);

								} else if (num==5) { //2-5.도서정보 삭제	
									dao.selectBook();		
									System.out.print("삭제할 책번호 입력: ");
									int book_num = Integer.parseInt(br.readLine());
									int count = dao.checkBookRecord(book_num);
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									dao.deleteBook(book_num);

								} else if (num==6) { //2-6.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

					} else if (no==3) { // 대여 관리				

						//  대여정보 테이블 book_order
						//						order_num        number          대여번호     시퀀스
						//						mem_id           varchar2        회원아이디	
						//						book_num         number          책번호
						//						order_date       date            대여일		sysdate
						//						return_date      date            반납일		sysdate+14
						//						is_add           number          연장유무	 defalut 0
						//						is_return        number          반납유무	 defalut 0

						int num=0;
						do {
							System.out.print("1.대여 목록보기, 2.대여 상세정보 확인, 3.대여정보 등록, 4.대여정보 수정, 5.대여정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { // 3-1.대여 목록보기
									dao.selectOrder();

								} else if (num==2) { // 3-2.대여 상세정보 확인
									dao.selectOrder();		
									System.out.print("조회할 대여번호 입력: ");
									int order_num = Integer.parseInt(br.readLine());
									int count = dao.checkOrderRecord(order_num);						
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("대여번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											order_num = Integer.parseInt(br.readLine());
											count = dao.checkOrderRecord(order_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									dao.selectDetailOrder(order_num);

								} else if (num==3) { //3-3.대여정보 등록 (수정 필요)
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									int count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);							
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);									
									dao.insertOrder(mem_id, book_num); //대여정보 추가시 해당도서 재고 -1 하되 재고0인 책은 빌릴 수 없도록 해야함.

								} else if (num==4) { //3-4.대여정보 수정 (수정 필요)
									dao.selectOrder();		
									System.out.print("수정할 대여번호 입력: ");
									int order_num = Integer.parseInt(br.readLine());
									int count = dao.checkBookRecord(order_num);
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("대여번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											order_num = Integer.parseInt(br.readLine());
											count = dao.checkOrderRecord(order_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);							
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("대여일: ");
									java.sql.Date order_date = Date.valueOf(br.readLine()); 
									// 자료형 재확인요망 (yyyy-[m]m-[d]d 형식으로 입력해야함. or IllegalArgumentException 예외 발생.)
									System.out.print("반납기한일: ");
									java.sql.Date return_date = Date.valueOf(br.readLine()); 
									// 자료형 재확인요망 (yyyy-[m]m-[d]d 형식으로 입력해야함. or IllegalArgumentException 예외 발생.)										
									System.out.print("연장유무: ");
									int is_add = br.readLine().equals("O") ? 1 : 0; // 연장기한+7 필요(수정요망)									
									System.out.print("반납유무: ");
									int is_return = br.readLine().equals("O") ? 1 : 0;	// 해당 책 재고+1 필요(수정요망)				

									dao.updateOrder(order_num, mem_id, book_num, order_date, return_date, is_add, is_return);

								} else if (num==5) { //3-5.대여정보 삭제 (수정 전)	 
									dao.selectOrder();		
									System.out.print("삭제할 대여번호 입력: ");
									int order_num = Integer.parseInt(br.readLine());
									int count = dao.checkBookRecord(order_num);
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("대여번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											order_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(order_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);
									dao.deleteOrder(order_num);

								} else if (num==6) { //3-6.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);					

					} else if (no==4) { // 예약 관리
						int num=0;
						do {
							System.out.print("1.예약 목록보기, 2.예약정보 등록, 3.예약정보 수정, 4.예약정보 삭제, 5.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { // 4-1.예약 목록보기
									dao.selectRSV();		

								} else if (num==2) { // 4-2.예약정보 등록 //재고 있는 책 입력시 예약 못하도록 조건 추가해야함!!!
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									int count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);							
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.InsertRSV(mem_id, book_num);

								} else if (num==3) { // 4-3.예약정보 수정
									dao.selectRSV();
									System.out.print("수정할 예약번호 입력: "); 
									int re_num = Integer.parseInt(br.readLine());
									int count = dao.checkRSVRecord(re_num); // 예약번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("예약번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											re_num = Integer.parseInt(br.readLine());
											count = dao.checkRSVRecord(re_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);							
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.updateRSV(re_num, mem_id, book_num);

								} else if (num==4) { // 4-4.예약정보 삭제
									dao.selectRSV();
									System.out.print("삭제할 예약번호 입력: ");
									int re_num = Integer.parseInt(br.readLine());
									int count = dao.checkRSVRecord(re_num);							
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("예약번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											re_num = Integer.parseInt(br.readLine());
											count = dao.checkRSVRecord(re_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);									
									dao.deleteRSV(re_num);

								} else if (num==5) { //4-5.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5);		

					} else if (no==5) { // 희망도서 관리
						int num=0;
						do {
							System.out.print("1.희망도서 목록보기, 2.희망도서정보 등록, 3.희망도서정보 수정, 4.희망도서정보 삭제, 5.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { //4-1.희망도서 목록보기
									dao.selectWish();

								} else if (num==2) { //4-2.희망도서정보 등록
									System.out.print("희망도서 제목: ");
									String wish_title = br.readLine();
									System.out.print("희망도서 저자: ");
									String wish_author = br.readLine();
									System.out.print("희망도서 출판사: ");
									String wish_publisher = br.readLine();								
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									int count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);										
									dao.InsertWish(wish_title, wish_author, wish_publisher, mem_id);

									//wish_book 테이블
									//									wish_num		number                 희망도서 번호
									//									wish_title		varchar2(2000 byte)    제목
									//									wish_author		varchar2(2000 byte)    저자
									//									wish_publisher	varchar2(2000 byte)    출판사
									//									wish_date		date                   희망도서 신청일
									//									mem_id			varchar2(12 byte)      회원 아이디	
								} else if (num==3) { //4-3.희망도서정보 수정
									dao.selectWish();
									System.out.print("수정할 희망도서번호 입력: "); 
									int wish_num = Integer.parseInt(br.readLine());
									int count = dao.checkWishRecord(wish_num); // 희망도서번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("희망도서번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											wish_num = Integer.parseInt(br.readLine());
											count = dao.checkWishRecord(wish_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);										
									System.out.print("희망도서 제목: "); 
									String wish_title = br.readLine();
									System.out.print("희망도서 저자: "); 
									String wish_author = br.readLine();
									System.out.print("희망도서 출판사: "); 
									String wish_publisher = br.readLine();
									System.out.print("회원아이디: ");
									String mem_id = br.readLine(); 
									count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);		
									dao.updateWish(wish_title, wish_author, wish_publisher, mem_id, wish_num);

								} else if (num==4) { //4-4.희망도서정보 삭제
									dao.selectWish();
									System.out.print("삭제할 희망도서번호 입력: "); 
									int wish_num = Integer.parseInt(br.readLine()); 
									int count = dao.checkWishRecord(wish_num); // 희망도서번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("희망도서번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											wish_num = Integer.parseInt(br.readLine());
											count = dao.checkWishRecord(wish_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.deleteWish(wish_num);

								} else if (num==5) { //4-5.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5);

					} else if (no==6) { // 리뷰 관리						
						int num=0;
						do {
							System.out.print("1.리뷰 목록보기, 2.리뷰정보 상세보기 3.리뷰정보 등록, 4.리뷰정보 수정, 5.리뷰정보 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { //5-1.리뷰 목록보기
									dao.selectReview();
									
								} else if (num==2) { // 5-2.리뷰정보 상세보기
									dao.selectReview();
									System.out.print("조회할 리뷰번호 입력: "); 
									int review_num = Integer.parseInt(br.readLine());
									int count = dao.checkReviewRecord(review_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("리뷰번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											review_num = Integer.parseInt(br.readLine());
											count = dao.checkReviewRecord(review_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);		
									dao.selectDetailReview(review_num);									
									
									//review 테이블
//									review_num		number               리뷰번호
//									book_num		number               책번호
//									review_content	varchar2(4000 byte)  리뷰내용
//									review_rate		number(1,0)          평점
//									review_reg_date	date                 리뷰등록일
//									mem_id			varchar2(12 byte)    회원아이디
								} else if (num==3) { // 5-3.리뷰정보 등록									
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									int count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("리뷰내용: "); 
									String review_content = br.readLine();									
									System.out.print("평점: ");
									// 1~5만 입력받도록 조건 추가 필요 (SQLIntegrityConstraintViolationException 예외처리 필요)
									int review_rate = Integer.parseInt(br.readLine());																
									System.out.print("회원아이디: "); 
									String mem_id = br.readLine(); 
									count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.InsertReview(book_num, review_content, review_rate, mem_id);
									
								} else if (num==4) { // 5-4.리뷰정보 수정
									dao.selectReview();
									System.out.print("수정할 리뷰번호 입력: ");
									int review_num = Integer.parseInt(br.readLine());
									int count = dao.checkReviewRecord(review_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("리뷰번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											review_num = Integer.parseInt(br.readLine());
											count = dao.checkReviewRecord(review_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("책번호: "); 
									int book_num = Integer.parseInt(br.readLine());
									count = dao.checkBookRecord(book_num); // 책번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											book_num = Integer.parseInt(br.readLine());
											count = dao.checkBookRecord(book_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("리뷰내용: "); 
									String review_content = br.readLine();									
									System.out.print("평점: ");
									// 1~5만 입력받도록 조건 추가 필요 (SQLIntegrityConstraintViolationException 예외처리 필요)
									int review_rate = Integer.parseInt(br.readLine());																
									System.out.print("회원아이디: "); 
									String mem_id = br.readLine(); 
									count = dao.checkMemberRecord(mem_id); // 회원아이디 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("회원ID를 잘못 입력했습니다. 다시입력하세요.: ");										
											mem_id = br.readLine();
											count = dao.checkMemberRecord(mem_id);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.updateReview(review_num, book_num, review_content, review_rate, mem_id);
								} else if (num==5) { // 5-5.리뷰정보 삭제
									dao.selectReview();
									System.out.print("삭제할 리뷰번호 입력: ");
									int review_num = Integer.parseInt(br.readLine());
									int count = dao.checkReviewRecord(review_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("리뷰번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											review_num = Integer.parseInt(br.readLine());
											count = dao.checkReviewRecord(review_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.deleteReview(review_num);
								} else if (num==6) { // 5-5.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

					} else if (no==7) { // 공지사항 관리
						int num=0;
						do {				
							System.out.print("1.공지사항 목록보기, 2.공지사항 상세정보 확인, 3.공지사항 등록, 4.공지사항 수정, 5.공지사항 삭제, 6.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { //7-1.공지사항 목록보기
									dao.selectNotice();

								} else if (num==2) { //7-2.공지사항 상세정보 확인
									dao.selectNotice();
									System.out.print("조회할 공지사항 번호 입력: ");
									int notice_num = Integer.parseInt(br.readLine());
									int count = dao.checkNoticeRecord(notice_num); // 공지사항 번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("공지사항 번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											notice_num = Integer.parseInt(br.readLine());
											count = dao.checkNoticeRecord(notice_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.selectDetailNotice(notice_num);
									
								} else if (num==3) { //7-3.공지사항 등록									
									System.out.print("공지사항 제목: "); 
									String notice_title = br.readLine();
									System.out.print("공지사항 내용: "); 
									String notice_content = br.readLine();		
									dao.InsertNotice(notice_title, notice_content);									
									
								} else if (num==4) { //7-4.공지사항 수정
									dao.selectNotice();
									System.out.print("수정할 공지사항 번호 입력: ");
									int notice_num = Integer.parseInt(br.readLine());
									int count = dao.checkNoticeRecord(notice_num); // 공지사항 번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("공지사항 번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											notice_num = Integer.parseInt(br.readLine());
											count = dao.checkNoticeRecord(notice_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("공지사항 제목: "); 
									String notice_title = br.readLine();
									System.out.print("공지사항 내용: "); 
									String notice_content = br.readLine();	
									dao.updateNotice(notice_num, notice_title, notice_content);
									
								} else if (num==5) { //7-5.공지사항 삭제
									dao.selectNotice();
									System.out.print("삭제할 공지사항 번호 입력: ");
									int notice_num = Integer.parseInt(br.readLine());
									int count = dao.checkNoticeRecord(notice_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("공지사항 번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											notice_num = Integer.parseInt(br.readLine());
											count = dao.checkNoticeRecord(notice_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.deleteNotice(notice_num);
									
								} else if (num==6) { //7-6.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5||num!=6);

					} else if (no==8) { // Q&A 관리						
						int num=0;
						do {				
							System.out.print("1.Q&A 목록보기, 2.Q&A 상세정보 확인, 3.Q&A 답변 등록, 4.Q&A 삭제, 5.뒤로가기\n"
									+ "번호를 입력하세요.> ");
							try {
								num = Integer.parseInt(br.readLine());				
								if (num==1) { //8-1.Q&A 목록보기
									dao.selectQnA();
								} else if (num==2) { //8-2.Q&A 상세정보 확인
									dao.selectQnA();
									System.out.print("조회할 qna번호 입력: ");
									int qna_num = Integer.parseInt(br.readLine());
									int count = dao.checkQnARecord(qna_num); // qna번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("공지사항 번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											qna_num = Integer.parseInt(br.readLine());
											count = dao.checkQnARecord(qna_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.selectDetailQnA(qna_num);
									
								} else if (num==3) { //8-3.Q&A 답변 등록
									dao.selectQnA();
									System.out.print("답변할 qna번호 입력: ");
									int qna_num = Integer.parseInt(br.readLine());
									int count = dao.checkQnARecord(qna_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("qna 번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											qna_num = Integer.parseInt(br.readLine());
											count = dao.checkQnARecord(qna_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									System.out.print("답변내용 입력: ");
									String qna_re = br.readLine();								
									dao.updateQnA(qna_num, qna_re);
									
								} else if (num==4) { //8-4.Q&A 삭제
									dao.selectQnA();
									System.out.print("삭제할 qna번호 입력: ");
									int qna_num = Integer.parseInt(br.readLine());
									int count = dao.checkQnARecord(qna_num); // 리뷰번호 존재하는지 확인
									do { //잘못 입력하면 다시 입력받음
										if (count==0) {
											System.out.print("qna번호를 잘못 입력했습니다. 다시입력하세요.: ");										
											qna_num = Integer.parseInt(br.readLine());
											count = dao.checkQnARecord(qna_num);
										} else if (count!=1) {
											System.out.println("정보 처리 중 오류 발생");
										} // if
									} while (count!=1);	
									dao.deleteQnA(qna_num);
									
								} else if (num==5) { //8-5.뒤로가기	
									callAdminMenu(); break out; // admin 메뉴 완전히 빠져나감
								} else {
									System.out.println("잘못 입력했습니다. 출력된 메뉴 번호 중 하나를 입력하세요.");
								}								
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능] 다시 입력하세요.");
							}							
						} while (num!=1||num!=2||num!=3||num!=4||num!=5);
						
					} else if (no==9) { // 통계 관리
						System.out.println("통계 관리: 작성 요망");
						

					} else if (no==10) { // 프로그램 종료 
						System.out.println("프로그램 종료");
						break;

					} else {
						System.out.println("잘못 입력했습니다. 다시 입력하세요.");				
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
