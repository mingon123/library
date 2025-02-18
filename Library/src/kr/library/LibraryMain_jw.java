package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LibraryMain_jw {
	private BufferedReader br;
	private String me_id; // 로그인한 아이디 저장
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
		// 로그인 체크 영역
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
				
				try {
					int no = Integer.parseInt(br.readLine());
					if(no==1) {
						isStart = false;
						isSelectFive = true;
						
						dao.randomBookInfo(5);
						// 책 목록 보여줄 함수 
						// 책번호 입력받기
						// 대여 여부(y또는Y)
						// 대여 시 조건 확인
						// 책이 없을 시 예약
						// 예약 시 조건 확인

					} else if(no==2) {
						// 반납
					} else if(no==3) {
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
