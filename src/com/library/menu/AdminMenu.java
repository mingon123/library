package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {
	private BufferedReader br;
	
	public AdminMenu() throws NumberFormatException, IOException {
		this.br = br = new BufferedReader(new InputStreamReader(System.in));
		
		showAdminMenu();
	}
	
    public void showAdminMenu() throws NumberFormatException, IOException {
        while (true) {
            System.out.println("관리자 메뉴");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 목록 조회");
            System.out.println("0. 뒤로 가기");
            System.out.print("선택: ");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1: System.out.println("도서 추가 기능 실행"); break;
                case 2: System.out.println("도서 삭제 기능 실행"); break;
                case 3: System.out.println("도서 목록 조회 기능 실행"); break;
                case 0: return;
                default: System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }
}
