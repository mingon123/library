package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.ReservationDAO;
import com.library.DAO.impl.ReservationDAOImpl;
import com.library.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	private ReservationDAO reservationDAO = new ReservationDAOImpl();
	private BufferedReader br;
	private String memId;
	
	public ReservationServiceImpl(BufferedReader br, String memId) {
		this.br = br;
		this.memId = memId;
	}

	// 예약하기 처리
	@Override
	public void handleReservation(int bookNum) throws IOException {
	    String s;
	    boolean flag = false;
	    
	    do {
	        if (flag) {
	            System.out.println("Y/N(y/n) 중 입력해주세요.");
	        }
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
	            break;
	        } else {
	            flag = true;
	        }
	    } while (!s.equalsIgnoreCase("N") && !s.equalsIgnoreCase("Y"));
	}

}
