package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.ReservationDAO;
import com.library.DAO.impl.ReservationDAOImpl;
import com.library.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	private ReservationDAO reservationDAO;
	private BufferedReader br;
	private String memId;
	
	public ReservationServiceImpl(BufferedReader br, String memId) {
		this.br = br;
		this.memId = memId;
		this.reservationDAO = new ReservationDAOImpl(memId);
	}

	// 예약하기 처리
	@Override
	public void handleReservation(int bookNum) throws IOException {
	    String s;
	    boolean flag = false;
	    
	    do {
	        System.out.print("예약하시겠습니까?(Y/N) : ");
	        s = br.readLine();

	        if (s.equalsIgnoreCase("N")) {
	            System.out.println("\n예약을 취소하셨습니다.");
	            System.out.println("이전화면으로 돌아갑니다.");
	            break;
	        } else if (s.equalsIgnoreCase("Y")) {
	            if (reservationDAO.isDuplicatedReserve(bookNum, memId)) {
	                System.out.println("\n이미 해당 책을 예약하신 기록이 존재합니다.");
	            } else {
	                System.out.println("\n예약을 진행합니다.");
	                reservationDAO.insertReserve(memId, bookNum);
	            }
	            System.out.println("이전화면으로 돌아갑니다.");
	            return;
	        } else {
	            flag = true;
	        }
	    } while (!s.equalsIgnoreCase("N") && !s.equalsIgnoreCase("Y"));
	}
	
	// 예약 취소 메서드
	@Override
	public void cancelReservation(String memId) throws IOException {
	    System.out.println("\n예약취소를 선택하셨습니다.");
	    System.out.println("-".repeat(90));
	    System.out.println("\t\t\t\t\t\t예약 현황");
	    System.out.println("-".repeat(90));

	    // 회원의 예약 현황을 조회합니다.
	    reservationDAO.selectUserNowReserveInfo(memId);

	    System.out.println("-".repeat(90));
	    System.out.println();

	    String s;
	    int sNum = -1;

	    // 예약 번호를 입력받습니다.
	    while(true) {
	        System.out.print("예약취소하실 번호를 입력해주세요.\n뒤로가기를 원하실 경우 q(Q)를 입력해주세요\n > ");
	        s = br.readLine();
	        if(util.Util.goBack(s)) break;
	        try {
	            sNum = Integer.parseInt(s);  // 입력받은 예약 번호를 정수로 변환
	        } catch (NumberFormatException e) {
	            System.out.println("[숫자만 입력 가능]");
	            continue;  // 잘못된 입력에 대해 다시 입력 받기
	        }
	        boolean isValid = reservationDAO.checkReserveReNum(sNum, memId);
	        if(!isValid) {
	        	System.out.println("유효하지 않은 예약 번호입니다. 다시 입력해주세요.");
	        	continue;
	        }
	        
	        // 취소 여부 확인
	        while (true) {
	            System.out.print("\n" + sNum + "번을 선택하셨습니다. 정말 취소하시겠습니까?(Y/N): ");
	            String confirm = br.readLine().trim().toUpperCase();

	            if (confirm.equals("Y")) {
	                reservationDAO.deleteReserveReNum(sNum);  // 실제 예약 취소 처리
	                System.out.println("\n예약이 취소되었습니다.");
	                return;  // 메서드 종료
	            } else if (confirm.equals("N")) {
	                System.out.println("\n예약 취소를 취소합니다.");
	                return;  // 취소 후 종료
	            } else {
	                System.out.println("Y 또는 N을 입력해주세요.");
	            }
	        }
	    }
	}
	
	
}
