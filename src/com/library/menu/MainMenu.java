package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainMenu {
	private BufferedReader br;
	private AdminMenu adminMenu;
	private UserMenu userMenu;
	
	public MainMenu() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			adminMenu = new AdminMenu();
			userMenu = new UserMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e ) {}
		}
	}
	
	public void callMenu()throws IOException {
		
	}
}
