package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

import com.library.DAO.MemberDAO;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.menu.AdminMenu;
import com.library.menu.MainMenu;
import com.library.menu.UserMenu;
import com.library.service.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;
	private BufferedReader br;
	private String memId;
	
	public MemberServiceImpl(BufferedReader br) {
		this.br = br;
	}

	public MemberServiceImpl(String memId, BufferedReader br) {
		this.memId = memId;
		this.br = br;
		this.memberDAO = new MemberDAOImpl();
	}
	

	@Override
	public boolean isValid(String fieldType, String value) {
		switch (fieldType) {
		case "mem_id": return value.matches("^[a-zA-Z0-9]{6,12}$"); // 6~12자의 알파벳, 숫자만 허용
		case "mem_pw": return value.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,16}$"); // 영문자 + 숫자 + 특수문자 포함, 8~16자리
		case "mem_name": return value.matches("^[a-zA-Z가-힣0-9]+$"); // 문자+숫자
		case "mem_cell": return value.matches("^010-\\d{4}-\\d{4}$"); // 10~11자리 숫자
		case "mem_email": return value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$"); // 이메일 형식
		default: return false;
		}
	}

	@Override
	public boolean signUp(String id, String passwd, String name, String cell, String email) {
		if (memberDAO.isDuplicate("mem_id", id) || memberDAO.isDuplicate("mem_cell", cell)) return false;
	    if (!isValid("mem_id", id) || !isValid("mem_pw", passwd) || !isValid("mem_name", name) || !isValid("mem_cell", cell) || !isValid("mem_email", email)) return false;
		return memberDAO.insertMember(id, passwd, name, cell, email);
	}

	@Override
	public void signUpProcess() throws IOException {
		String id;
		boolean isValidId;
		do {
			System.out.print("아이디(영문,숫자 최소 6~12자) (뒤로가기:q) :");
			id = br.readLine().trim();
			if(util.Util.goBack(id)) return;
			isValidId = isValid("mem_id", id);
			if (!isValidId) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidId);

		String passwd;
		boolean isValidPw;
		do {
			System.out.print("비밀번호(영문,숫자,특수문자 포함 최소8자 이상) (뒤로가기:q) :");
			passwd = br.readLine();
			if(util.Util.goBack(passwd)) return;
			isValidPw = isValid("mem_pw", passwd);

			if(!isValidPw) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidPw);

		String name;
		boolean isValidName;
		do {
			System.out.print("이름(한글,영문,숫자만 입력 가능) (뒤로가기:q) :");
			name = br.readLine();
			if(util.Util.goBack(name)) return;
			isValidName = isValid("mem_name", name);

			if(!isValidName) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidName);

		String cell;
		boolean isValidCell;
		do {
			System.out.print("전화번호(010-0000-0000 형식) (뒤로가기:q) :");
			cell  = br.readLine();
			if(util.Util.goBack(cell)) return;
			isValidCell = isValid("mem_cell", cell);

			if(!isValidCell) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidCell);

		String email;
		boolean isValidEmail;
		do {
			System.out.print("이메일(test@test.com 형식) (뒤로가기:q) :");
			email = br.readLine();
			if(util.Util.goBack(email)) return;
			isValidEmail = isValid("mem_email", email);

			if(!isValidEmail) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidEmail);

		boolean success = signUp(id, passwd, name, cell, email);
		if (success) {
			System.out.println("회원가입이 완료되었습니다.");
		} else {
			System.out.println("회원가입에 실패했습니다. 이미 존재하는 아이디나 전화번호일 수 있습니다.");
		}
	}

	@Override
	public boolean login() throws IOException {
	    while (true) {
	        System.out.print("아이디(입력취소:q): ");
	        String memId = br.readLine().trim();
	        if(util.Util.goBack(memId)) break;
	        System.out.print("비밀번호: ");
	        String passwd = br.readLine().trim();
	        boolean flag = memberDAO.loginCheck(memId, passwd);
	        if (flag) {
	            System.out.println(memId + "님 로그인 되었습니다.");
	            if (memId.equals("admin")) {
	                new AdminMenu(br);
	                return true;
	            } else {
	                // 일반 사용자 메인 메뉴 이동
	                new UserMenu(memId, br);
	                return true;
	            }
	        } else {
	            System.out.println("아이디 또는 비밀번호가 불일치합니다.");
	        }
	    }
		return false;
	}
	
	
	// 회원정보관리
	@Override
	public void manageMemberInfo() {
		System.out.print("1.회원정보조회 2.회원정보수정 3.회원탈퇴 4.뒤로가기\n > ");
		try {
			String input = br.readLine();
			int no = Integer.parseInt(input);
			switch (no) {
			case 1: memberDAO.selectMemberInfo(memId);System.out.println("-".repeat(90));break;
			case 2: updateMemberInfo();break;
			case 3: deleteMemberInfo();break;
			case 4: System.out.println("뒤로가기를 선택하셨습니다.");return;
			default: System.out.println("잘못 입력하셨습니다."); break;
			}
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // manageMemberInfo
	
	// 회원정보수정
	@Override
	public void updateMemberInfo() throws IOException {
		System.out.printf("현재 계정은 %s입니다. 뒤로가기:q(Q)입력 \n",memId);
		String password;
		while(true) {
			System.out.print("현재 비밀번호를 입력하세요(문자,숫자,특수문자 포함 8~15자리) : ");
			password = br.readLine();
			if(isValidPassword(password)) break;
			else if(util.Util.goBack(password)) return;
			else System.out.println("비밀번호 형식이 올바르지 않습니다.");			
		}
		System.out.print("변경할 이름을 입력하세요: ");
		String name = br.readLine();
		String email;
		while(true) {			
			System.out.print("변경할 이메일을 입력하세요: 뒤로가기:q(Q)입력 \n");
			email = br.readLine();
			if(isValidEmail(email)) break;
			else if(util.Util.goBack(email)) return;
			else System.out.println("이메일 형식이 올바르지 않습니다.");
		}
		System.out.println("회원정보가 수정되었습니다.");
		memberDAO.updateMemberInfo(memId, password, name, email);
	} // updateMemberInfo
	
	// 비밀번호 유효성검사
	@Override
	public boolean isValidPassword(String password) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$";
		return Pattern.matches(regex, password);
	} // isValidPassword
	
	// 이메일 유효성검사
	@Override
	public boolean isValidEmail(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	} // isValidEmail
	
	// 회원탈퇴
	@Override
	public void deleteMemberInfo() {
		System.out.println("계정을 삭제하시겠습니까?");
		System.out.print("1.회원탈퇴 2.뒤로가기\n > ");
		try {
			int no = Integer.parseInt(br.readLine());
			if(no==1) {
				System.out.println("정말로 회원탈퇴하시겠습니까?(Y/N)");
				String s = br.readLine();
				if(s.equalsIgnoreCase("n")) {
					System.out.println("회원탈퇴를 취소하셨습니다.");
					System.out.println("이전화면으로 돌아갑니다.");
					return;
				}else if(s.equalsIgnoreCase("y")) {
					memberDAO.deleteMemberInfo(memId);
					System.out.println("첫화면으로 돌아갑니다.");
					new MainMenu();
					return;
				} else System.out.println("잘못 입력하셨습니다.");
			}
			else if(no==2) {
				System.out.println("뒤로가기를 선택하셨습니다.");
				return;
			} else System.out.println("잘못 입력하셨습니다.");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteMemberInfo
	
}	
