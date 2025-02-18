package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 처음화면에서 6.반납 / 5.대여예약-2.대여/예약 내역확인 / 149줄?에 추가 이어서 구현  => 3가지 구현
 * 이후 테스트후 수정
 */

public class LibraryMain_jw {
	private BufferedReader br;
	private String mem_id; // 로그인한 아이디 저장
	private boolean isSelectFive = false;
	private boolean isStart = true;
	private BookDAO_Jw dao;

	public LibraryMain_jw() {
		try {
			dao = new BookDAO_Jw();
			br = new BufferedReader(new InputStreamReader(System.in));
			// 메뉴 호출
			callMenu();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null) try {br.close();} catch(IOException e) {}	
		}
	}

	// 메뉴 호출
	private void callMenu() throws IOException {
		while(true) {
			if(isStart) {
				System.out.print("5.대여/예약 6.반납 9.종료\n > ");
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==5) { // 완
						isStart = false;
						isSelectFive = true;
						System.out.println("대여/예약 메뉴를 선택하셨습니다.");

					} else if(no==6) {
						// 반납
					} else if(no==9) {//완
						// 종료
						System.out.println("프로그램 종료");
						break;
					} else {
						System.out.println("잘못 입력하셨습니다.");
					}
				} catch (NumberFormatException e) {
					System.out.println("[숫자만 입력 가능]");
				}
			}
			if(isSelectFive) {
				System.out.print("1.대여/예약하기 2.대여/예약 내역확인 3.뒤로가기\n >");
				int book_num = 0;
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==1) {//완
						boolean flag = false;
						boolean isOrder = true;
						while(isOrder) {
							System.out.println("랜덤 책 목록");
							dao.randomBookInfo(5);
							do {
								try {
									if(flag) {
										System.out.println("존재하지않는 책 번호입니다.");
									} 
									System.out.println();
									System.out.print("대여/예약 하실 책 번호를 입력해주세요 : ");
									book_num = Integer.parseInt(br.readLine());
									flag = true;
								} catch (NumberFormatException e) {
									System.out.println("[숫자만 입력 가능]");
								}
							} while (dao.checkBookNum(book_num) != 1);

							System.out.print("1.대여하기 2.예약하기 3.뒤로가기\n >");
							try {
								no = Integer.parseInt(br.readLine());
								int res = dao.canOrder(mem_id, book_num);
								if(no==1) {
									flag = false;
									if(res == 1) {
										System.out.println("대여가 가능합니다.");
										dao.insertBookOrder(mem_id, book_num);
									}else if(res == 0) {
										System.out.println("해당 번호의 책의 권수가 0권입니다.");
										String s;
										do {
											if(flag) {
												System.out.println("Y/N/y/n 중 입력해주세요.");
											}
											System.out.print("예약하시겠습니까?(Y/N) : ");
											s = br.readLine();
											if(s.equals("N")||s.equals("n")) {
												System.out.println("예약을 취소하셨습니다.");
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else if(s.equals("Y")||s.equals("y")) {
												System.out.println("예약을 진행합니다.");
												dao.insertReserve(mem_id, book_num);
											}else {
												flag = true;
											}
										} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
									}else if(res == -1) {
										System.out.println("대여권수가 이미 3권입니다.");
										System.out.println("이전화면으로 돌아갑니다.");
										isOrder = false;
										continue;
									}else {
										System.out.println("프로그램 오류");
									}

								}else if(no==2) {
									flag = false;
									if(res == -1) {
										System.out.println("대여권수가 이미 3권입니다.");
										System.out.println("예약이 불가능하니 이전화면으로 돌아갑니다.");
										isOrder = false;
										continue;
									}else if(res == 1) {
										System.out.println("책 권수가 1권 이상입니다.");
										String s;
										do {
											if(flag) {
												System.out.println("Y/N/y/n 중 입력해주세요.");
											}
											System.out.print("대여하시겠습니까?(Y/N) : ");
											s = br.readLine();
											if(s.equals("N")||s.equals("n")) {
												System.out.println("대여를 취소하셨습니다.");
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else if(s.equals("Y")||s.equals("y")) {
												System.out.println("대여를 진행합니다.");
												dao.insertBookOrder(mem_id, book_num);
											}else {
												flag = true;
											}
										} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
									}else if(res == 0) {} // 이부분 예약 확인용 함수 만들고 예약권수 찼는지, 책 0권인지 확인 후 나뉘는 함수 작성

								}else if(no==3) {
									System.out.println("뒤로가기를 선택하셨습니다.");
									isOrder = false;
									continue;
								}else {
									System.out.println("잘못 입력하셨습니다.");
								}
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능]");
							}
						}
					} else if(no==2) {
						// 대여/예약 내역확인
					} else if(no==3) {//완
						// 뒤로가기
						System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
						isStart = true;
						isSelectFive = false;
					} else {
						System.out.println("잘못 입력하셨습니다.");
					}
				} catch (NumberFormatException e) {
					System.out.println("[숫자만 입력 가능]");
				}
			}
		}

	}

	public static void main(String[] args) {
		new LibraryMain_jw();
	}
}
