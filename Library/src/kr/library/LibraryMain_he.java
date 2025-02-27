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
	private BookDAO_Jw dao_jw;
	private LibraryMain_il main_il;

	public LibraryMain_he() {
		this.mem_id = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new BookDAO_he();
			dao_jw = new BookDAO_Jw();
			//메뉴 호출
			callMenu();
		} catch (Exception e) {
			e.printStackTrace();
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
					int num; boolean flag = false;
					do {
						if(flag) {
							System.out.println("존재하지 않는 글 번호 입니다. 다시 입력하세요 ");
						}
						System.out.print("조회하실 글의 번호 입력 (뒤로가기:q) : ");
						String q = br.readLine();
						if(q.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
						num = Integer.parseInt(q);
						flag = true;
					} while (!dao_jw.checkNoticeNum(num));
					System.out.println("-".repeat(90));
					dao_jw.updateNoticeViewCount(num); // 상세정보 보면서 조회수 증가 위해 먼저 
					dao.selectDetailNotice(num);
					
				} else if (no==4) {
					//회원가입
					System.out.println("회원가입 페이지입니다.");
					String id;
					boolean isDuplicateId, isValidId;
					do {
						System.out.print("아이디(영문,숫자 최소 6~12자) (뒤로가기:q) :");
						id = br.readLine();
						if(id.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
					    isDuplicateId = dao.isDuplicateId(id);
					    isValidId = dao.checkValidId(id);
						
					    if (isDuplicateId) System.out.println("이미 존재하는 아이디입니다.");
					    else if (!isValidId) System.out.println("형식에 맞지 않습니다.");
					} while(isDuplicateId||!isValidId);
					
					String passwd;
					boolean isValidPw;
					do {
						System.out.print("비밀번호(영문,숫자,특수문자 포함 최소8자 이상) (뒤로가기:q) :");
						passwd = br.readLine();
						if(passwd.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
						isValidPw = dao.checkValidPassword(passwd);
						
						if(!isValidPw) System.out.println("형식에 맞지 않습니다.");
					} while(!isValidPw);
					
					String name;
					boolean isValidName;
					do {
						System.out.print("이름(한글,영문만 입력 가능) (뒤로가기:q) :");
						name = br.readLine();
						if(name.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
						isValidName = dao.checkValidName(name);
						
						if(!isValidName) System.out.println("형식에 맞지 않습니다.");
					} while(!isValidName);

					String cell;
					boolean isDupicateCell,isValidCell;
					do {
						System.out.print("전화번호(010-0000-0000 형식) (뒤로가기:q) :");
						cell  = br.readLine();
						if(cell.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
						isValidCell = dao.checkValidCell(cell);
						isDupicateCell = dao.isDuplicateCell(cell);
						
						if(!isValidCell) System.out.println("형식에 맞지 않습니다.");
						if(isDupicateCell) System.out.println("이미 등록된 전화번호입니다.");
					} while(!isValidCell || isDupicateCell);

					String email;
					boolean isValidEmail;
					do {
						System.out.print("이메일(test@test.com 형식) (뒤로가기:q) :");
						email = br.readLine();
						if(email.equalsIgnoreCase("q")) {
							System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
							callMenu();
						}
						isValidEmail = dao.checkValidEmail(email);
						
						if(!isValidEmail) System.out.println("형식에 맞지 않습니다.");
					} while(!isValidEmail);

					boolean success = dao.insertInfo(id, passwd, name, cell, email);
					if (success) {
					    System.out.println("회원가입이 완료되었습니다.");
					} else {
					    System.out.println("회원가입에 실패했습니다. 다시 시도해주세요.");
					}
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
							//관리자 메인 메뉴 이동칸
							main_il = new LibraryMain_il(mem_id);
						}else {
							main_mg = new LibraryMain_mg(mem_id);
						}
					} else System.out.println("아이디 또는 비밀번호가 불일치합니다.");				

				} else if (no==6) {
					//종료
					System.out.println("프로그램 종료");
					System.exit(0); break;
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
