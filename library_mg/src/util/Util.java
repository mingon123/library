package util;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

	// 뒤로가기
    public static boolean goBack(String input) {
        if (input.equalsIgnoreCase("q")) {
            System.out.println("이전화면으로 돌아갑니다.");
            return true; // 뒤로가기 신호
        }
        return false; // 계속 진행
    }
    
    // Y/N 선택
    public static boolean choiceYN(BufferedReader br, String message) throws IOException {
        System.out.print(message + " (Y/N): ");
        String response = br.readLine();
        return response.equalsIgnoreCase("Y");
    }
}