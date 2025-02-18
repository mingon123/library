package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lilly
 * @date 2025. 2. 18. - 오후 6:00:58
 * @subject	 관리자 페이지
 * @content 
 */
public class LibraryMain_il {
	private BufferedReader br;
	private String me_id; // 로그인한 아이디 저장
	private boolean flag; // 로그인 여부 
	
	public LibraryMain_il() {
		try {
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
			System.out.println("1.로그인,2.회원가입,3.종료:");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					// 로그인
				} else if(no==2) {
					// 회원가입
				} else if(no==3) {
					// 종료
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
		// 로그인 성공 후 회원제 서비스 영역
		while(flag) {
			System.out.print("1.상품목록,2.MY구매상품리스트,3.종료:");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					// 상품목록
					
				} else if(no==2) {
					// MY구매상품리스트
					
				} else if(no==3) {
					//  종료
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch(NumberFormatException e){
				System.out.println("[숫자만 입력 가능]");
			} 

		}
	}
	
	public static void main(String[] args) {
		new LibraryMain_il();
	}
}
