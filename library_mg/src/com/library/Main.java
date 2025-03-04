package com.library;

import java.io.IOException;

import com.library.menu.MainMenu;

public class Main {
	public static void main(String[] args) {
		try {
			MainMenu menu = new MainMenu();
			menu.callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
