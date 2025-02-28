package util;

public class Util {
    public static boolean goBack(String input) {
        if (input.equalsIgnoreCase("q")) {
            System.out.println("이전화면으로 돌아갑니다.");
            return true; // 뒤로가기 신호
        }
        return false; // 계속 진행
    }
}