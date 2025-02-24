package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class LibraryMain_mg {
	private BufferedReader br;
	private String mem_id="test3"; // 로그인한 아이디 저장

	private boolean isSelectSeven = false;
	private boolean isSelectTwo = false;
	private boolean isSelectThree = false;
	private boolean isSelectFour = false;
	private boolean isStart = true;
	private BookDAO_mg dao;
	private BookDAO_il il;
	private BookDAO_Jw jw;

	public LibraryMain_mg() {
		try {
			dao = new BookDAO_mg();
			il = new BookDAO_il();
			jw = new BookDAO_Jw();
			br = new BufferedReader(new InputStreamReader(System.in));
			// 메뉴 호출
			callMenu();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null) try {br.close();} catch(IOException e) {}	
		}
	} // LibraryMain_mg

	// 메뉴 호출
	private void callMenu() throws IOException {
		while(true) {
			if(isStart) {
				System.out.print("1.사용자알림 2.도서목록 3.도서검색 4.리뷰확인 7.기타메뉴 9.종료\n > ");
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==1) { // 완
						checkUserNotifications();
					} else if(no==2) { // 완
						isStart = false;
						isSelectTwo = true;
						showTwoMenu();
					} else if(no==3) { // 대여/예약 추가
						isStart = false;
						isSelectThree = true;
						showThreeMemu();
					} else if(no==4) { // 범위 지정해야함
						isStart = false;
						isSelectFour = true;
						showFourMenu();
					} else if(no==7) { // 완
						isStart = false;
						isSelectSeven = true;
						System.out.println("기타 메뉴를 선택하셨습니다.");
						showSevenMenu();
					} else if(no==9) {//완
						// 종료
						System.out.println("프로그램 종료");
						break;
					} else {
						System.out.println("잘못 입력하셨습니다.");
					}
				} catch (NumberFormatException e) {
					System.out.println("[숫자만 입력 가능]");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	} // callMenu

	// 1번선택 시 나오는 화면
	private void checkUserNotifications() throws IOException {
		System.out.println("정지상태/연체/반납일/예약도서알림");
		System.out.println("-".repeat(90));
		boolean memStop = dao.isMemStop(mem_id);
		boolean overReturn = dao.isOverReturn(mem_id);
		boolean returnDateNotification = dao.isReturnDateNotification(mem_id);
		boolean reservationNotification = dao.isReservationNotification(mem_id);

		if((memStop||overReturn||returnDateNotification||reservationNotification)) {
			if(memStop)
				if(overReturn)
					if(returnDateNotification)
						if(reservationNotification) System.out.println();
		} else {
			System.out.println("알림이 없습니다.");
			System.out.println("-".repeat(90));
		}
		callMenu();
	} // checkUserNotifications

	// 2번선택 시 나오는 화면
	private void showTwoMenu() throws IOException {
		while (isSelectTwo) {			
			System.out.println("도서목록 확인");
			System.out.print("1.카테고리별 2.추천순위 3.신간책 4.대여베스트 5.뒤로가기\n > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) selectCategoryOfBook();
				else if(no==2) {
					dao.selectRankOfBook();
					selectDetailBook();
				}
				else if(no==3) {
					dao.selectNewOfBook();
					selectDetailBook();
				}
				else if(no==4) {
					dao.selectOrderBestOfBook();
					selectDetailBook();
				}
				else if(no==5) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					isStart = true;
					isSelectTwo = false;
				} else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // showTwoMenu
	// 카테고리별 책목록 표시
	private void selectCategoryOfBook() {
		System.out.print("1.자기계발 2.소설 3.과학 4.역사 5.기타 6.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) {
				dao.selectCategoryOfBook("자기계발");
				selectDetailBook();
			}
			else if(no==2) {
				dao.selectCategoryOfBook("소설");
				selectDetailBook();
			}
			else if(no==3) {
				dao.selectCategoryOfBook("과학");
				selectDetailBook();
			}
			else if(no==4) {
				dao.selectCategoryOfBook("역사");
				selectDetailBook();
			}
			else if(no==5) {
				dao.selectCategoryOfBook("기타");
				selectDetailBook();
			}
			else if(no==6) {
				System.out.println("뒤로가기를 선택하셨습니다.");
				showTwoMenu();
			} else System.out.println("잘못 입력하셨습니다.");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 책 상세정보 확인
	private void selectDetailBook() throws NumberFormatException, IOException {
		System.out.print("조회할 책번호 입력 : ");
		int book_num = Integer.parseInt(br.readLine());
		int count = il.checkBookRecord(book_num);
		do {
			if(count==0) {
				System.out.print("책번호를 잘못 입력했습니다. 다시입력하세요 : ");
				book_num = Integer.parseInt(br.readLine());
				count = il.checkBookRecord(book_num);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			} 
		} while(count!=1);

		il.selectDetailBook(book_num);
		System.out.println("-".repeat(90));

		orderOrReserveMenu(book_num); // 대여 예약
	}
	
	private void orderOrReserveMenu(int book_num) throws IOException {
		
		System.out.print("1.대여하기 2.예약하기 3.뒤로가기\n >");
		int no; boolean flag;
		try {
			no = Integer.parseInt(br.readLine());
			int res = jw.canOrder(mem_id, book_num);
			if(no==1) {
				flag = false;
				if(res == 1) {//대여가능
					System.out.println("\n대여가 가능합니다.");
					jw.insertBookOrder(mem_id, book_num);
					System.out.println("이전화면으로 돌아갑니다.");
					System.out.println();
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
							continue;
						}else if(s.equals("Y")||s.equals("y")) {
							if(jw.isDuplicatedReserve(book_num, mem_id)) {
								System.out.println("\n이미 해당 책을 예약하신 기록이 존재합니다.");
							}
							else {
								System.out.println("\n예약을 진행합니다.");
								jw.insertReserve(mem_id, book_num);		
							}
							System.out.println("이전화면으로 돌아갑니다.");
							continue;
						}else {
							flag = true;
						}
					} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
				}else if(res == -1) {
					System.out.println("\n대여권수가 이미 3권입니다.");
					System.out.println("이전화면으로 돌아갑니다.");
				}else {
					System.out.println("프로그램 오류");
				}

			}else if(no==2) {
				flag = false;
				if(res == -1) {
					System.out.println("\n대여권수가 이미 3권입니다.");
					System.out.println("예약이 불가능하니 이전화면으로 돌아갑니다.");
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
							continue;
						}else if(s.equals("Y")||s.equals("y")) {
							System.out.println("\n대여를 진행합니다.");
							jw.insertBookOrder(mem_id, book_num);
							System.out.println();
							System.out.println("이전화면으로 돌아갑니다.");
							continue;
						}else {
							flag = true;
						}
					} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
				}else if(res == 0) {// 책이 0권인 경우로 시작
					int resultReserve = jw.canReservation(mem_id, book_num);
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
								continue;
							}else if(s.equals("Y")||s.equals("y")) {
								if(jw.isDuplicatedReserve(book_num, mem_id)) {
									System.out.println("\n이미 해당 책을 예약하신 기록이 존재합니다.");
								}
								else {
									System.out.println("\n예약을 진행합니다.");
									jw.insertReserve(mem_id, book_num);		
								}
								System.out.println("이전화면으로 돌아갑니다.");
								continue;
							}else {
								flag = true;
							}
						} while (!s.equals("N") && !s.equals("n") && !s.equals("Y") && !s.equals("y"));
					}else if(resultReserve == -1) {// 예약 권수 다참
						System.out.println("\n예약 권수가 이미 2권입니다.");
						System.out.println("이전화면으로 돌아갑니다.");
					}else if(resultReserve == 0) {
						System.out.println("오류발생!!");
					}
				} 

			}else if(no==3) {
				System.out.println("\n뒤로가기를 선택하셨습니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		}
		
	}

	// 3번 선택 시 나오는 메뉴
	private void showThreeMemu() throws IOException {
		while(isSelectThree) {
			System.out.println("도서 검색");
			System.out.print("1.제목 2.저자 3.뒤로가기\n > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					selectSearchBookTitle();
					selectDetailBook();
				}
				else if(no==2) {
					selectSearchBookAuthor();
					selectDetailBook();
				}
				else if(no==3) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					isStart = true;
					isSelectThree = false;
				} else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 제목으로 도서 검색
	private void selectSearchBookTitle() throws IOException {
		System.out.print("검색할 책 제목 검색 : ");
		String title = br.readLine();
		int count = dao.checkBookTitleRecord(title);
		do {
			if(count==0) {
				System.out.print("입력한 단어가 포함된 도서가 없습니다. 다시입력하세요 : ");
				title = br.readLine();
				count = dao.checkBookTitleRecord(title);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count<=0);
		dao.selectSearchBookTitle(title);
		System.out.println("-".repeat(90));
	}

	// 저자로 도서 검색
	private void selectSearchBookAuthor() throws IOException {
		System.out.print("검색할 책 저자 검색 : ");
		String author = br.readLine();
		int count = dao.checkBookAuthorRecord(author);
		do {
			if(count==0) {
				System.out.print("입력한 단어가 포함된 도서가 없습니다. 다시입력하세요 : ");
				author = br.readLine();
				count = dao.checkBookAuthorRecord(author);
			} else if(count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count<=0);
		dao.selectSearchBookAuthor(author);
		System.out.println("-".repeat(90));
	}

	// 4번 선택시 나오는 화면
	private void showFourMenu() throws IOException {
		while(isSelectFour) {
			System.out.println("리뷰 메뉴입니다.");
			System.out.print("1.전체리뷰 2.책제목검색 3.내리뷰 4.뒤로가기\n > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					dao.selectReviewInfo();
					selectDetailReview();
				} else if(no==2) {
					selectBookTitleReview();
				} else if(no==3) {
					manageMyReview();
				}else if(no==4) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					isStart = true;
					isSelectFour = false;
				} else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 4.리뷰 상세정보 확인
	private void selectDetailReview() throws NumberFormatException, IOException {
		System.out.print("조회할 리뷰번호 입력 : ");
		int num = Integer.parseInt(br.readLine());
		int count = dao.checkReviewRecord(num);
		do {
			if(count==0) {
				System.out.print("리뷰번호를 잘못 입력했습니다. 다시 입력하세요 : ");
				num = Integer.parseInt(br.readLine());
				count = dao.checkReviewRecord(num);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count!=1);
		dao.selectDetailReview(num);
		System.out.println("-".repeat(90));
	}

	// 책 제목으로 리뷰 검색
	private void selectBookTitleReview() throws IOException {
		System.out.print("리뷰를 확인할 책 제목을 입력하세요(뒤로가기:q) : ");
		String bookTitle = br.readLine();
		if(bookTitle.equalsIgnoreCase("q")) {
			System.out.println("메뉴로 돌아갑니다.");
			showFourMenu();
		}
		int count = dao.checkReviewRecordOfBookTile(bookTitle);
		do {
			if(count==0) {
				System.out.print("해당 책에 대한 리뷰가 없습니다. 다시 입력하세요(뒤로가기:q) : ");
				bookTitle = br.readLine();
				if(bookTitle.equalsIgnoreCase("q")) {
					System.out.println("뒤로 돌아갑니다.");
					break;
				}
				count = dao.checkReviewRecordOfBookTile(bookTitle);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count==0);
		dao.selectSearchReviewOftitle(bookTitle);
		System.out.println("-".repeat(90));
		if(count>=1) selectDetailReview();
	}

	// 내 리뷰 관리 
	private void manageMyReview() throws NumberFormatException, IOException {
		System.out.print("1.내리뷰확인 2.리뷰수정 3.리뷰삭제 4.뒤로가기\n > ");
		int no = Integer.parseInt(br.readLine());
		if(no==1) {
			dao.selectMyReviewInfo(mem_id);
		}
		else if(no==2) {
			updateMyReview();
		} else if(no==3) {
			deleteMyReview();
			System.out.println("-".repeat(90));
		} else if(no==4) {
			System.out.println("뒤로가기를 선택하셨습니다.");
			showFourMenu();
		}
		else System.out.println("잘못 입력하셨습니다.");
	}

	private void updateMyReview() throws IOException {
		boolean MyReview = dao.selectMyReviewInfo(mem_id);
		if(!MyReview) {
			System.out.println("작성한 리뷰가 없습니다.");
			return;
		}
		try {
			System.out.print("수정할 번호를 입력하세요: ");
			int num = Integer.parseInt(br.readLine());
			int count = dao.checkReviewRecord(num,mem_id);
			if(count==1) {
				System.out.print("새로운 리뷰내용 입력 : ");
				String content = br.readLine();
				System.out.print("새로운 평점 입력(1~5) : ");
				int rate = Integer.parseInt(br.readLine());
				dao.updateMyReview(num, content, rate, mem_id);
			}
			else if(count==0) System.out.println("본인이 작성한 리뷰가 아닙니다.");
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 내 리뷰 삭제
	private void deleteMyReview() throws IOException {
		boolean MyReview = dao.selectMyReviewInfo(mem_id);
		if(!MyReview) {
			System.out.println("작성한 리뷰가 없습니다.");
			return;
		}
		try {
			System.out.print("삭제할 번호를 입력하세요: ");
			int num = Integer.parseInt(br.readLine());
			int count = dao.checkReviewRecord(num,mem_id);
			if(count==1) il.deleteReview(num);
			else if(count==0) System.out.println("번호를 잘못입력했습니다.");
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteMyReview
	// 7번선택 시 나오는 화면
	private void showSevenMenu() throws IOException {
		while(isSelectSeven) {
			System.out.print("1.희망도서 2.Q&A 3.회원정보관리 4.뒤로가기\n > ");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) manageWishBook(); //희망도서
				else if(no==2) manageQNA(); //qna
				else if(no==3) manageMemberInfo(); //회원정보
				else if(no==4) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					isStart = true;
					isSelectSeven = false;
				} else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // showSevenMenu
	// 희망도서 관리
	private void manageWishBook() throws NumberFormatException, IOException {
		System.out.print("1.희망도서신청 2.희망도서신청내역확인 3.희망도서신청취소 4.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) insertWishBook();
			else if(no==2) {
				selectWishBookInfo();
				System.out.println("-".repeat(90));
			} else if(no==3) {
				deleteWishBook();
				System.out.println("-".repeat(90));
			} else if(no==4) {
				System.out.println("뒤로가기를 선택하셨습니다.");
				showSevenMenu();
			}
			else System.out.println("잘못 입력하셨습니다.");

		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // manageWishBook
	// 희망도서등록
	private void insertWishBook() throws IOException {
		System.out.println("희망도서 신청화면입니다. 뒤로가시길 원하시면 q(Q)를 입력하세요.");
		System.out.print("희망도서 제목을 입력하세요: ");
		String title = br.readLine();
		if(title.equalsIgnoreCase("q")) {
			System.out.println("이전 화면으로 돌아갑니다.");
			return;
		}
		System.out.print("저자를 입력하세요: ");
		String author = br.readLine();
		System.out.print("출판사를 입력하세요: ");
		String publisher = br.readLine();
		dao.insertWishBook(title, author, publisher, mem_id);
	} // insertWishBook
	// 희망도서목록확인
	private void selectWishBookInfo() {
		dao.selectWishBookInfo();
	} // selectWishBookInfo
	// 희망도서 삭제
	private void deleteWishBook() throws IOException {
		boolean hasWishBook = dao.selectMyWishBookInfo(mem_id);
		if(!hasWishBook) {
			System.out.println("신청한 희망도서가 없습니다.");
			return;
		}
		try {
			System.out.print("삭제할 번호를 입력하세요: ");
			int num = Integer.parseInt(br.readLine());
			int count = dao.checkRecord(mem_id);
			if(count==1) dao.deleteWishBookInfo(mem_id, num);
			else if(count==0) System.out.println("번호를 잘못입력했습니다.");
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteWishBook

	// qna관리
	private void manageQNA() throws NumberFormatException, IOException {
		System.out.print("1.질문등록 2.질문내역확인 3.질문삭제 4.뒤로가기\n > ");
		int no = Integer.parseInt(br.readLine());
		if(no==1) insertQNA();
		else if(no==2) {
			dao.selectQNAInfo();
			System.out.println("-".repeat(90));
		} else if(no==3) {
			deleteQNA();
			System.out.println("-".repeat(90));
		} else if(no==4) {
			System.out.println("뒤로가기를 선택하셨습니다.");
			showSevenMenu();
		}
		else System.out.println("잘못 입력하셨습니다.");
	} // manageQNA
	// qna등록
	private void insertQNA() throws IOException {
		System.out.println("질문 등록화면입니다. 뒤로가시길 원하시면 q(Q)를 입력하세요.");
		System.out.print("질문 제목을 입력하세요 : ");
		String qnaTitle = br.readLine();
		if(qnaTitle.equalsIgnoreCase("q")) {
			System.out.println("이전화면으로 돌아갑니다.");
			manageQNA();
		}
		System.out.print("질문 내용을 입력하세요 : ");
		String qnaContent = br.readLine();
		dao.insertQNA(qnaTitle, qnaContent, mem_id);
	} // insertQNA
	// qna목록확인

	// qna 삭제
	private void deleteQNA() throws IOException {
		boolean hasQNA = dao.selectMyQNAInfo(mem_id);
		if(!hasQNA) {
			System.out.println("등록한 Q&A가 없습니다.");
			return;
		}
		try {
			System.out.print("삭제할 번호를 입력하세요: ");
			int num = Integer.parseInt(br.readLine());
			int count = dao.checkRecord(mem_id);
			if(count==1) dao.deleteQNAInfo(mem_id, num);
			else if(count==0) System.out.println("번호를 잘못입력했습니다.");
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteQNA

	// 회원정보관리
	private void manageMemberInfo() throws NumberFormatException, IOException {
		System.out.print("1.회원정보조회 2.회원정보수정 3.회원탈퇴 4.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) {
				dao.selectMemberInfo(mem_id);
				System.out.println("-".repeat(90));
				System.out.println("");
			}
			else if(no==2) updateMemberInfo();
			else if(no==3) deleteMemberInfo();
			else if(no==4) {
				System.out.println("뒤로가기를 선택하셨습니다.");
				showSevenMenu();
			} else System.out.println("잘못 입력하셨습니다.");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // manageMemberInfo
	// 회원정보수정
	private void updateMemberInfo() throws IOException {
		String memId = mem_id;
		System.out.printf("현재 계정은 %s입니다. 뒤로가기:q(Q)입력 \n",memId);
		String password;
		while(true) {
			System.out.print("현재 비밀번호를 입력하세요: ");
			password = br.readLine();
			if(isValidPassword(password)) break;
			else if(password.equalsIgnoreCase("q")) {
				System.out.println("이전화면으로 돌아갑니다.");
				return;
			} else System.out.println("비밀번호 형식이 올바르지 않습니다.");			
		}
		System.out.print("변경할 이름을 입력하세요: ");
		String name = br.readLine();
		String email;
		while(true) {			
			System.out.print("변경할 이메일을 입력하세요: 뒤로가기:q(Q)입력 \n");
			email = br.readLine();
			if(isValidEmail(email)) break;
			else if(email.equalsIgnoreCase("q")) {
				System.out.println("이전화면으로 돌아갑니다.");
				return;
			}
			else System.out.println("이메일 형식이 올바르지 않습니다.");
		}
		dao.updateMemberInfo(memId, password, name, email);
	} // updateMemberInfo
	// 비밀번호 유효성검사
	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$";
		return Pattern.matches(regex, password);
	} // isValidPassword
	// 이메일 유효성검사
	private boolean isValidEmail(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	} // isValidEmail
	// 회원탈퇴
	private void deleteMemberInfo() throws IOException {
		System.out.println("계정을 삭제하시겠습니까?");
		System.out.print("1.회원탈퇴 2.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) {
				if(true) System.out.println("정말로 회원탈퇴하시겠습니까?(Y/N)");
				String s = br.readLine();
				if(s.equalsIgnoreCase("n")) {
					System.out.println("회원탈퇴를 취소하셨습니다.");
					System.out.println("이전화면으로 돌아갑니다.");
					manageMemberInfo();
				}else if(s.equalsIgnoreCase("y")) {
					dao.deleteMemberInfo(mem_id);
					System.out.println("첫화면으로 돌아갑니다.");
					showSevenMenu();
				} else System.out.println("잘못 입력하셨습니다.");
			}
			else if(no==2) {
				System.out.println("뒤로가기를 선택하셨습니다.");
				manageMemberInfo();
			} else System.out.println("잘못 입력하셨습니다.");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteMemberInfo





	public static void main(String[] args) {
		new LibraryMain_mg();
	} // main
} // class
