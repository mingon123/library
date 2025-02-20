package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibraryMain_jw {
	private BufferedReader br;
	private String mem_id = "test2"; // 로그인한 아이디 저장
	private boolean isSelectFive = false;
	private boolean isStart = true;
	private BookDAO_Jw dao;

	//TODO 예약도서 대여시 rank가 책 갯수이내(!=0)인지 확인하고 대여할때 예약테이블에서 삭제
	
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
					if(!dao.checkMemStop(mem_id) && no==5 ) {
						System.out.println("현재 정지상태입니다. 대여/예약이 불가능합니다.");
						System.out.println("홈화면으로 돌아갑니다.\n");
						continue;
					}else if(no==5) { // 완
						isStart = false;
						isSelectFive = true;
						System.out.println("\n대여/예약 메뉴를 선택하셨습니다.");
					} else if(no==6) {
						// 반납
						System.out.println("\n반납 메뉴를 선택하셨습니다.");
						System.out.println("-".repeat(90));
						System.out.println("\t\t\t\t\t\t대여 현황");
						System.out.println("-".repeat(90));
						dao.selectUserNowOrderInfo(mem_id);
						System.out.println("-".repeat(90));
						
						if(dao.checkZeroOrder(mem_id)) {
							System.out.println("\n대여기록이 존재하지 않습니다.");
							System.out.println("이전화면으로 돌아갑니다.\n");
							continue;
						};
						
						boolean flag = false; int order_num = -1; String s = "";
						do {
							try {
								if(flag) {
									System.out.println("\n올바르지않은 번호입력입니다.");
								} 
								System.out.println();
								System.out.print("반납하실 대여번호를 입력해주세요 : ");
								order_num = Integer.parseInt(br.readLine());
								flag = true;
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능]");
							}
						} while (!dao.checkNowOrderNum(order_num));
						
						System.out.println();
						System.out.println(order_num+"번을 선택하셨습니다.");
						flag = false;
						do {
							if(flag) {
								System.out.println("Y/N(y/n) 중 입력해주세요.");
							}
							System.out.print("반납하시겠습니까?(Y/N) : ");
							s = br.readLine();
							if(s.equals("N")||s.equals("n")) {
								System.out.println("\n반납을 취소하셨습니다.");
								System.out.println("이전화면으로 돌아갑니다.");
								continue;
							}else if(s.equals("Y")||s.equals("y")) {
								System.out.println();
								dao.updateOrderReturn(order_num);
								dao.updateStopDate(order_num);
								dao.selectLateReturn(order_num);
								
								System.out.println("\n이전화면으로 돌아갑니다.");
								continue;
							}
							flag = true;
						} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
					
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
				System.out.print("1.대여/예약하기 2.대여/예약 내역확인 3.예약취소 4.뒤로가기\n >");
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
									if(res == 1) {//대여가능
										System.out.println("\n대여가 가능합니다.");
										dao.insertBookOrder(mem_id, book_num);
										System.out.println("이전화면으로 돌아갑니다.");
										System.out.println();
										isOrder = false;
										continue;
									}else if(res == 0) {//책권수가 0
										System.out.println("\n해당 번호의 책의 권수가 0권입니다.");
										String s;
										do {
											if(flag) {
												System.out.println("Y/N(y/n) 중 입력해주세요.");
											}
											System.out.print("예약하시겠습니까?(Y/N) : ");
											s = br.readLine();
											if(s.equals("N")||s.equals("n")) {
												System.out.println("\n예약을 취소하셨습니다.");
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else if(s.equals("Y")||s.equals("y")) {
												if(dao.isDuplicatedReserve(book_num, mem_id)) {
													System.out.println("\n이미 해당 책을 예약하신 기록이 존재합니다.");
												}
												else {
													System.out.println("\n예약을 진행합니다.");
													dao.insertReserve(mem_id, book_num);		
												}
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else {
												flag = true;
											}
										} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
									}else if(res == -1) {
										System.out.println("\n대여권수가 이미 3권입니다.");
										System.out.println("이전화면으로 돌아갑니다.");
										isOrder = false;
										continue;
									}else {
										System.out.println("프로그램 오류");
									}

								}else if(no==2) {
									flag = false;
									if(res == -1) {
										System.out.println("\n대여권수가 이미 3권입니다.");
										System.out.println("예약이 불가능하니 이전화면으로 돌아갑니다.");
										isOrder = false;
										continue;
									}else if(res == 1) {
										System.out.println("\n책 권수가 1권 이상입니다.");
										String s;
										do {
											if(flag) {
												System.out.println("Y/N(y/n) 중 입력해주세요.");
											}
											System.out.print("대여하시겠습니까?(Y/N) : ");
											s = br.readLine();
											if(s.equals("N")||s.equals("n")) {
												System.out.println("\n대여를 취소하셨습니다.");
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else if(s.equals("Y")||s.equals("y")) {
												System.out.println("\n대여를 진행합니다.");
												dao.insertBookOrder(mem_id, book_num);
												System.out.println();
												System.out.println("이전화면으로 돌아갑니다.");
												isOrder = false;
												continue;
											}else {
												flag = true;
											}
										} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
									}else if(res == 0) {// 책이 0권인 경우로 시작
										int resultReserve = dao.canReservation(mem_id, book_num);
										if(resultReserve == 1) {//예약가능
											System.out.println("\n예약이 가능합니다.");
											String s;
											do {
												if(flag) {
													System.out.println("Y/N(y/n) 중 입력해주세요.");
												}
												System.out.print("예약하시겠습니까?(Y/N) : ");
												s = br.readLine();
												if(s.equals("N")||s.equals("n")) {
													System.out.println("\n예약을 취소하셨습니다.");
													System.out.println("이전화면으로 돌아갑니다.");
													isOrder = false;
													continue;
												}else if(s.equals("Y")||s.equals("y")) {
													if(dao.isDuplicatedReserve(book_num, mem_id)) {
														System.out.println("\n이미 해당 책을 예약하신 기록이 존재합니다.");
													}
													else {
														System.out.println("\n예약을 진행합니다.");
														dao.insertReserve(mem_id, book_num);		
													}
													System.out.println("이전화면으로 돌아갑니다.");
													isOrder = false;
													continue;
												}else {
													flag = true;
												}
											} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
										}else if(resultReserve == -1) {// 예약 권수 다참
											System.out.println("\n예약 권수가 이미 2권입니다.");
											System.out.println("이전화면으로 돌아갑니다.");
											isOrder = false;
											continue;
										}else if(resultReserve == 0) {
											System.out.println("오류발생!!");
										}
									} 

								}else if(no==3) {
									System.out.println("\n뒤로가기를 선택하셨습니다.");
									isOrder = false;
									continue;
								}else {
									System.out.println("잘못 입력하셨습니다.");
								}
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능]");
							}
						}// while(isOrder)
					} else if(no==2) {
						System.out.println("\n대여/예약 내역 확인을 선택하셨습니다.");
						boolean isSelectView = true;
						while (isSelectView) {
							System.out.print("1.대여/예약 현황확인 2.대여 내역확인 3.뒤로가기\n >");
							try {
								no = Integer.parseInt(br.readLine());
								if(no==1) {
									System.out.println("\n현황확인을 선택하셨습니다.");
									System.out.println("-".repeat(90));
									System.out.println("\t\t\t\t\t\t대여 현황");
									System.out.println("-".repeat(90));
									dao.selectUserNowOrderInfo(mem_id);
									System.out.println("-".repeat(90));
									System.out.println("\t\t\t\t\t\t예약 현황");
									System.out.println("-".repeat(90));
									dao.selectUserNowReserveInfo(mem_id);
									System.out.println("-".repeat(90));
									boolean flag = false; String s; int sNum = -1;
									do {
										if(flag) {
											System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
										}
										System.out.print("\n연장을 원하실 경우 대여번호, 뒤로가기를 원하실 경우 q(Q)를 입력해주세요\n > ");
										s = br.readLine();
										if(s.equals("Q") || s.equals("q")) {
											System.out.println("뒤로가기를 선택하셨습니다.");
											isSelectView = false;
											continue;
										}
										else {
											try {
												flag = true;
												sNum = Integer.parseInt(s);
											} catch (NumberFormatException e) {

											}
										}
									} while (!dao.checkNowOrderNum(sNum) && !s.equals("q") && !s.equals("Q"));
									
									System.out.println();
									
									if(dao.checkOrderAdd(sNum)) {
										if(!dao.checkOrderAddReturnDate(sNum)) {
											System.out.println(sNum +"번의 반납기한이 지났으므로 연장이 불가능합니다.");
											System.out.println("이전화면으로 돌아갑니다.");
											isSelectView = false;
											continue;
										}else {
											System.out.println(sNum +"번은 연장이 가능합니다.");
											flag = false;
											do {
												if(flag) {
													System.out.println("Y/N(y/n) 중 입력해주세요.");
												}
												System.out.print("연장하시겠습니까?(Y/N) : ");
												s = br.readLine();
												if(s.equals("N")||s.equals("n")) {
													System.out.println("\n연장을 취소하셨습니다.");
													System.out.println("이전화면으로 돌아갑니다.");
													isSelectView = false;
													continue;
												}else if(s.equals("Y")||s.equals("y")) {
													System.out.println("\n연장을 진행합니다.");
													dao.updateReturnDate(sNum);
													System.out.println();
													System.out.println("이전화면으로 돌아갑니다.");
													isSelectView = false;
													continue;
												}else {
													flag = true;
												}
												
											} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
										}
									}
									else if(sNum != -1 && !dao.checkOrderAdd(sNum)){
										System.out.println(sNum +"번은 연장이 불가능합니다.");
										System.out.println("이전화면으로 돌아갑니다.");
										isSelectView = false;
										continue;
									}
									else {
										//
									}
								}else if(no==2) {
									System.out.println("대여 내역확인을 선택하셨습니다.");
									boolean allView = true;
									while(allView) {
										System.out.print("1.최신순 2.오래된순 3.뒤로가기\n >");
										try {
											no = Integer.parseInt(br.readLine());
											if(no==1 || no==2) {
												System.out.println("*".repeat(70));
												dao.selectUserOrderInfo(mem_id, no);
												System.out.println("*".repeat(70));
												System.out.println("출력을 마칩니다. 이전 화면으로 돌아갑니다.");
												allView = false;
												continue;
											}else if(no==3) {
												System.out.println("뒤로가기를 선택하셨습니다.");
												allView = false;
												continue;
											}else {
												System.out.println("잘못 입력하셨습니다.");
											}
										} catch (NumberFormatException e) {
											System.out.println("[숫자만 입력 가능]");
										}
									}// while(allView) - 대여내역 확인
								}else if(no==3) {
									System.out.println("뒤로가기를 선택하셨습니다.");
									isSelectView = false;
									continue;
								}else {
									System.out.println("잘못 입력하셨습니다.");
								}
							} catch (NumberFormatException e) {
								System.out.println("[숫자만 입력 가능]");
							}
						} // while(isSelectView)
					}else if(no==3) { 
						System.out.println("\n예약취소를 선택하셨습니다.");
						System.out.println("-".repeat(90));
						System.out.println("\t\t\t\t\t\t예약 현황");
						System.out.println("-".repeat(90));
						dao.selectUserNowReserveInfo(mem_id);
						System.out.println("-".repeat(90));
						//TODO
						// 예약도서에 해당 번호 있는지 확인
						// 삭제
						System.out.println();
						boolean flag = false; String s; int sNum = -1;
						do {
							if(flag) {
								System.out.println("\n잘못 입력하셨습니다. 다시 입력해주세요.");
							}
							System.out.print("예약취소하실 번호를 입력해주세요.\n뒤로가기를 원하실 경우 q(Q)를 입력해주세요\n > ");
							s = br.readLine();
							if(s.equals("Q") || s.equals("q")) {
								System.out.println("뒤로가기를 선택하셨습니다.");
								continue;
							}
							
							else {
								try {
									flag = true;
									sNum = Integer.parseInt(s);
								} catch (NumberFormatException e) {

								}
							}
						} while (!dao.checkReserveNum(sNum,mem_id) && !s.equals("q") && !s.equals("Q"));
						
						flag = false;
						if(dao.checkReserveNum(sNum,mem_id)) {
							do {
								if(flag) System.out.println("Y/N(y/n) 중 입력해주세요.");
								System.out.print(sNum + "번을 선택하셨습니다. \n정말 취소하시겠습니까?(Y/N) : ");
								s = br.readLine();
								if(s.equals("Y")||s.equals("y")) {
									System.out.println("\n예약을 취소합니다.");
									dao.deleteReserve(sNum);
									System.out.println("이전화면으로 돌아갑니다.");
									continue;
								}else if(s.equals("N")||s.equals("n")) {
									System.out.println("\n예약취소를 취소합니다.");
									System.out.println("이전화면으로 돌아갑니다.");
									continue;
								}else {
									flag = true;
								}
								
							} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
							
						}
						
						
					}else if(no==4) {//완
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
