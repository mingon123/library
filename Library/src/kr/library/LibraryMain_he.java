package kr.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibraryMain_he {
	private BufferedReader br;
	private String mem_id;//로그인한 아이디 저장
	private boolean flag;//로그인 여부
	private BookDAO_he dao;
	private LibraryMain_mg main_mg;

	public LibraryMain_he() {
		this.mem_id = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao= new BookDAO_he ();
			//메뉴 호출
			callMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e ) {}

		}
	}
	//메뉴 호출
	private void callMenu()throws IOException {
		//로그인 체크 영역
		while(true) {
			System.out.print("1.도서목록 2.도서검색 3.공지사항확인 4.회원가입 5.로그인 6.종료\n >");
			try {
				int no= Integer.parseInt(br.readLine());
				if(no==1) {
					main_mg = new LibraryMain_mg();
					main_mg.showTwoMenu();
				}else if(no==2) {
					main_mg = new LibraryMain_mg();
					main_mg.showThreeMemu();
				}else if (no==3) {
					//공지사항
					dao.selectNotice();
					System.out.print("선택한 글의 번호:");
					int num = Integer.parseInt(br.readLine());
					System.out.println("-".repeat(90));
					dao.selectDetailNotice(num);
				} else if (no==4) {
					//회원가입
					System.out.println("회원가입 페이지입니다.");
					System.out.print("아이디 (영문,숫자 최소 6~12자):");
					String id = br.readLine();

					System.out.print("비밀번호 (영문,숫자,특수문자 최소8 이상):");
					String passwd = br.readLine();

					System.out.print("이름 (한글, 영문만 입력 가능):");
					String name = br.readLine();

					System.out.print("전화번호 (숫자만 입력 가능):");
					String cell  = br.readLine();

					System.out.print("이메일:");
					String email = br.readLine();

					dao.insertInfo(id, passwd, name, cell, email);

				} else if(no ==5) {
					//로그인
					System.out.print("아이디(입력취소:q):");
					mem_id = br.readLine();

					//0이면 입력 취소
					if(mem_id.equalsIgnoreCase("q")) continue;

					System.out.print("비밀번호:");
					String mem_pw = br.readLine();

					flag = dao.loginCheck(mem_id, mem_pw);
					if(flag) {
						System.out.println(mem_id + "님 로그인 되었습니다.");
						//callMenu();
						if(mem_id.equals("admin")) {
							//관리자 메인 메뉴 이동칸 TODO
						}else {
							main_mg = new LibraryMain_mg(mem_id);
						}
					}
					System.out.println("아이디 또는 비밀번호가 불일치합니다.");				

				} else if (no==6) {
					//종료
					main_mg.closeReader();
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	}
	public static void main(String[] args) {
		new LibraryMain_he();
	}
}
