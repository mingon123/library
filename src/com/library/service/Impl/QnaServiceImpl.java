package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.QnaDAO;
import com.library.DAO.impl.QnaDAOImpl;
import com.library.service.QnaService;

public class QnaServiceImpl implements QnaService {
	private String memId;
	private BufferedReader br;
	private QnaDAO qnaDAO;
	
	public QnaServiceImpl(String memId) {
		this.memId = memId;
		this.qnaDAO = new QnaDAOImpl();
	}
	
	// qna관리
	public void manageQNA(BufferedReader br) throws NumberFormatException, IOException {
		this.br = br;
		System.out.print("1.질문등록 2.질문내역확인 3.질문삭제 4.뒤로가기\n > ");
		int no = Integer.parseInt(br.readLine());
		if(no==1) insertQNA(br);
		else if(no==2) {
			qnaDAO.selectQNAInfo();
			System.out.println("-".repeat(90));
		} else if(no==3) {
			deleteQNA(br);
			System.out.println("-".repeat(90));
		} else if(no==4) {
			System.out.println("뒤로가기를 선택하셨습니다.");
			return;
		}
		else System.out.println("잘못 입력하셨습니다.");
	} // manageQNA
	// qna등록
	public void insertQNA(BufferedReader br) throws IOException {
		System.out.println("질문 등록화면입니다. 뒤로가시길 원하시면 q(Q)를 입력하세요.");
		System.out.print("질문 제목을 입력하세요 : ");
		String qnaTitle = br.readLine();
		if(qnaTitle.equalsIgnoreCase("q")) {
			System.out.println("이전화면으로 돌아갑니다.");
			return;
		}
		System.out.print("질문 내용을 입력하세요 : ");
		String qnaContent = br.readLine();
		qnaDAO.insertQNA(qnaTitle, qnaContent, memId);
	} // insertQNA
	// qna목록확인

	// qna 삭제
	public void deleteQNA(BufferedReader br) throws IOException {
		boolean hasQNA = qnaDAO.selectMyQNAInfo(memId);
		if(!hasQNA) {
			System.out.println("등록한 Q&A가 없습니다.");
			return;
		}
		while(true) {
			try {
				System.out.print("삭제할 번호를 입력하세요 (뒤로가기:q) : ");
				String del = br.readLine();
				if(del.equalsIgnoreCase("q")) return;
				int num = Integer.parseInt(del);
				int count = qnaDAO.checkQnaRecordNumId(num, memId);
				if(count==1) qnaDAO.deleteQNAInfo(memId, num);
				else if(count==0) System.out.println("번호를 잘못입력했습니다.");
				else if(count==-1) System.out.println("정보 처리 중 오류 발생");
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteQNA
}
