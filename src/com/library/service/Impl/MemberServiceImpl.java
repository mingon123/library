package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.library.DAO.MemberDAO;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.service.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public MemberServiceImpl() {
		this.memberDAO = new MemberDAOImpl();
	}

	public MemberServiceImpl(MemberDAO memberDAO) {
		super();
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean isValid(String fieldType, String value) {
		switch (fieldType) {
		case "mem_id":
			return value.matches("^[a-zA-Z0-9]{6,12}$"); // 6~12자의 알파벳, 숫자만 허용
		case "mem_pw":
			return value.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,16}$"); // 영문자 + 숫자 + 특수문자 포함, 8~16자리
		case "mem_name":
			return value.matches("^[a-zA-Z가-힣0-9]+$"); // 문자+숫자
		case "mem_cell":
			return value.matches("^010-\\d{4}-\\d{4}$"); // 10~11자리 숫자
		case "mem_email":
			return value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$"); // 이메일 형식
		default:
			return false;
		}
	}

	@Override
	public boolean signUp(String id, String passwd, String name, String cell, String email) {
		if (memberDAO.isDuplicate("mem_id", id) || memberDAO.isDuplicate("mem_cell", cell)) return false;

		if (!isValid("mem_id", id)) return false;
		if (!isValid("mem_pw", passwd)) return false;
		if (!isValid("mem_name", name)) return false;
		if (!isValid("mem_cell", cell)) return false;
		if (!isValid("mem_email", email)) return false;
		return memberDAO.insertMember(id, passwd, name, cell, email);
	}

	@Override
	public void signUpProcess() throws IOException {
		String id;
		boolean isValidId;
		do {
			System.out.print("아이디(영문,숫자 최소 6~12자) (뒤로가기:q) :");
			id = br.readLine().trim();
			if(id.equalsIgnoreCase("q")) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
				return;
			}
			isValidId = isValid("mem_id", id);

			if (!isValidId) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidId);

		String passwd;
		boolean isValidPw;
		do {
			System.out.print("비밀번호(영문,숫자,특수문자 포함 최소8자 이상) (뒤로가기:q) :");
			passwd = br.readLine();
			if(passwd.equalsIgnoreCase("q")) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
				return;
			}
			isValidPw = isValid("mem_pw", passwd);

			if(!isValidPw) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidPw);

		String name;
		boolean isValidName;
		do {
			System.out.print("이름(한글,영문,숫자만 입력 가능) (뒤로가기:q) :");
			name = br.readLine();
			if(name.equalsIgnoreCase("q")) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
				return;
			}
			isValidName = isValid("mem_name", name);

			if(!isValidName) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidName);

		String cell;
		boolean isValidCell;
		do {
			System.out.print("전화번호(010-0000-0000 형식) (뒤로가기:q) :");
			cell  = br.readLine();
			if(cell.equalsIgnoreCase("q")) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
				return;
			}
			isValidCell = isValid("mem_cell", cell);

			if(!isValidCell) System.out.println("형식에 맞지 않습니다.");
		} while(!isValidCell);

		String email;
		boolean isValidEmail;
		do {
			System.out.print("이메일(test@test.com 형식) (뒤로가기:q) :");
			email = br.readLine();
			if(email.equalsIgnoreCase("q")) {
				System.out.println("뒤로가기를 선택하셨습니다. 홈으로 이동합니다.");
				return;
			}
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
	public void login() throws IOException {
	    while (true) { // 로그인 반복 (로그인 실패 시 재시도 가능)
	        System.out.print("아이디(입력취소:q): ");
	        String mem_id = br.readLine().trim();
	        if (mem_id.equalsIgnoreCase("q")) {
	            System.out.println("로그인을 취소했습니다.");
	            return;
	        }
	        System.out.print("비밀번호: ");
	        String passwd = br.readLine().trim();
	        // 로그인 검증
	        boolean flag = memberDAO.loginCheck(mem_id, passwd);
	        if (flag) {
	            System.out.println(mem_id + "님 로그인 되었습니다.");
//	            // 관리자 로그인 분기
//	            if (mem_id.equals("admin")) {
//	                // 관리자 메인 메뉴 이동
//	                main_il = new LibraryMain_il(mem_id);
//	            } else {
//	                // 일반 사용자 메인 메뉴 이동
//	                main_mg = new LibraryMain_mg(mem_id);
//	            }
	            return;  // 로그인 성공하면 메서드 종료
	        } else {
	            System.out.println("아이디 또는 비밀번호가 불일치합니다.");
	        }
	    }
	}
}	
