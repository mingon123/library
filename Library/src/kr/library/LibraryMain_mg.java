package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class LibraryMain_mg {
	private BufferedReader br;
	private String me_id="test"; // 로그인한 아이디 저장
	private boolean isSelectSeven = false;
	private boolean isStart = true;
	private BookDAO_mg dao;
	
	public LibraryMain_mg() {
		try {
			dao = new BookDAO_mg();
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
				System.out.print("1.사용자알림 7.기타메뉴 9.종료\n > ");
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==1) {
						checkUserNotifications();
						isStart = false;
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
	// 7번누르면 나오는 화면
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
	public void manageWishBook() throws NumberFormatException, IOException {
		System.out.print("1.희망도서신청 2.희망도서신청내역확인 3.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) insertWishBook();
			else if(no==2) {
				selectWishBookInfo();
				System.out.println("-".repeat(50));
			}
			else if(no==3) {
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
	public void insertWishBook() throws IOException {
		System.out.println("희망도서 신청화면입니다. 뒤로가시길 원하시면 q(Q)를 입력하세요.");
		System.out.print("희망도서 제목을 입력하세요: ");
		String title = br.readLine();
		if(title.equalsIgnoreCase("q")) {
			System.out.println("이전 화면으로 돌아갑니다.");
			manageWishBook();
		}
		System.out.print("저자를 입력하세요: ");
		String author = br.readLine();
		System.out.print("출판사를 입력하세요: ");
		String publisher = br.readLine();
		dao.insertWishBook(title, author, publisher);
	} // insertWishBook
	// 희망도서목록확인
	public void selectWishBookInfo() {
		dao.selectWishBookInfo();
	} // selectWishBookInfo
	// qna관리
	public void manageQNA() throws NumberFormatException, IOException {
		System.out.print("1.질문등록 2.질문내역확인 3.뒤로가기\n > ");
		int no = Integer.parseInt(br.readLine());
		if(no==1) insertQNA();
		else if(no==2) {
			selectQNAInfo();
			System.out.println("-".repeat(50));
		}
		else if(no==3) {
			System.out.println("뒤로가기를 선택하셨습니다.");
			showSevenMenu();
		}
		else System.out.println("잘못 입력하셨습니다.");
	} // manageQNA
	// qna등록
	public void insertQNA() throws IOException {
		System.out.println("질문 등록화면입니다. 뒤로가시길 원하시면 q(Q)를 입력하세요.");
		System.out.print("질문 제목을 입력하세요.");
		String qnaTitle = br.readLine();
		if(qnaTitle.equalsIgnoreCase("q")) {
			System.out.println("이전화면으로 돌아갑니다.");
			manageQNA();
		}
		System.out.print("질문 내용을 입력하세요.");
		String qnaContent = br.readLine();
		dao.insertQNA(qnaTitle, qnaContent);
	} // insertQNA
	// qna목록확인
	public void selectQNAInfo() {
		dao.selectQNAInfo();
	} // selectQNAInfo
	// 회원정보관리
	public void manageMemberInfo() throws NumberFormatException, IOException {
		System.out.print("1.회원정보조회 2.회원정보수정 3.회원탈퇴 4.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) {
				dao.selectMemberInfo(me_id);
				System.out.println("-".repeat(50));
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
	public void updateMemberInfo() throws IOException {
		String memId = me_id;
		System.out.printf("현재 계정은 %s입니다. 뒤로가기:q(Q)입력 \n",memId);
		String password;
		while(true) {
			System.out.print("현재 비밀번호를 입력하세요: ");
			password = br.readLine();
			if(isValidPassword(password)) break;
			else if(password.equalsIgnoreCase("q")) {
				System.out.println("이전화면으로 돌아갑니다.");
				manageMemberInfo();
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
				manageMemberInfo();
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
	public void deleteMemberInfo() throws IOException {
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
					dao.deleteMemberInfo(me_id);
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
	
	
	private void checkUserNotifications() throws IOException {
		System.out.println("정지상태/연체/반납일/예약도서알림");
		boolean memStop = dao.isMemStop(me_id);
		boolean overReturn = dao.isOverReturn(me_id);
		boolean returnDateNotification = dao.isReturnDateNotification(me_id);
		boolean reservationNotification = dao.isReservationNotification(me_id);

		if((memStop||overReturn||returnDateNotification||reservationNotification)) {
			if(memStop) System.out.println("-".repeat(90));
			if(overReturn) System.out.println("-".repeat(90));
			if(returnDateNotification) System.out.println("-".repeat(90));
			if(reservationNotification) System.out.println("-".repeat(90));
		} else {
			System.out.println("알림이 없습니다.");
			System.out.println("-".repeat(90));
		}
		callMenu();
	} // checkUserNotifications
	
	
	
	public static void main(String[] args) {
		new LibraryMain_mg();
	} // main
} // class
