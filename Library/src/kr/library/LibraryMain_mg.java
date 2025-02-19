package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	}
	// 메뉴 호출
	private void callMenu() throws IOException {
		while(true) {
			if(isStart) {
				System.out.print("7.기타메뉴 9.종료\n > ");
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==7) { // 완
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
				}
			}
		}
	}
	
	private void showSevenMenu() throws IOException {
		while(isSelectSeven) {
			System.out.print("1.희망도서 2.Q&A 3.회원 정보 관리 4.뒤로가기\n >");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) manageWishBook(); //희망도서
				else if(no==2) manageQNA(); //qna
				else if(no==3) manageMemberInfo(); //회원정보
				else if(no==4) { //뒤로가기
					System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
					isStart = true;
					isSelectSeven = false;
				}
				else System.out.println("잘못 입력하셨습니다.");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	}
	public void manageWishBook() throws NumberFormatException, IOException {
		System.out.println("1.희망도서 신청, 2.희망도서 신청내역 확인");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) insertWishBook();
			else if(no==2) selectWishBookInfo();
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		}
	}
	public void insertWishBook() throws IOException {
		System.out.print("희망도서 제목을 입력하세요: ");
		String title = br.readLine();
		System.out.print("저자를 입력하세요: ");
		String author = br.readLine();
		System.out.print("출판사를 입력하세요: ");
		String publisher = br.readLine();
		dao.insertWishBook(title, author, publisher);
	}	
	public void selectWishBookInfo() {
		dao.selectWishBookInfo();
	}
	public void manageQNA() throws NumberFormatException, IOException {
		System.out.println("1.질문 등록 2.질문 내역확인");
		int no = Integer.parseInt(br.readLine());
		if(no==1) insertQNA();
		else if(no==2) selectQNAInfo();
	}
	public void insertQNA() throws IOException {
		System.out.print("질문 제목을 입력하세요.");
		String qnaTitle = br.readLine();
		System.out.print("질문 내용을 입력하세요.");
		String qnaContent = br.readLine();
		dao.insertQNA(qnaTitle, qnaContent);
	}
	public void selectQNAInfo() {
		dao.selectQNAInfo();
	}
	public void manageMemberInfo() throws NumberFormatException, IOException {
		System.out.print("1.회원정보 조회, 2.회원정보 수정, 3.회원탈퇴 4.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) dao.selectMemberInfo(me_id);
			else if(no==2) updateMemberInfo();
			else if(no==3) deleteMemberInfo();
			else if(no==4) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
				isStart = true;
				isSelectSeven = false;
			}
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		}
	}
	public void updateMemberInfo() throws IOException {
		String memId = me_id;
		System.out.printf("현재 계정은 %s입니다.\n",memId);
		System.out.print("현재 비밀번호를 입력하세요: ");
		String password = br.readLine();
		System.out.print("변경할 이름을 입력하세요: ");
		String name = br.readLine();
		System.out.print("변경할 이메일을 입력하세요: ");
		String email = br.readLine();
		dao.updateMemberInfo(memId, password, name, email);
	}
	public void deleteMemberInfo() throws IOException {
		System.out.println("계정을 삭제하시겠습니까?");
		System.out.print("1.회원탈퇴 2.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) dao.deleteMemberInfo(me_id);
			else if(no==2) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 돌아갑니다.");
				isStart = true;
				isSelectSeven = false;
			}
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		}
	}
	public static void main(String[] args) {
		new LibraryMain_mg();
	}
}
