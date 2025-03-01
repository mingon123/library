package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.ReviewDAO;
import com.library.DAO.impl.ReviewDAOImpl;
import com.library.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	private BufferedReader br;
	private String memId;
	private ReviewDAO reviewDAO;
	
	public ReviewServiceImpl(BufferedReader br, String memId) {
		this.br = br;
		this.memId = memId;
		this.reviewDAO = new ReviewDAOImpl();
	}

	// 4.리뷰 상세정보 확인
	@Override
	public void selectDetailReview() throws IOException {
		System.out.print("조회할 리뷰번호 입력 (뒤로가기:q) : ");
		String q = br.readLine();
		if(util.Util.goBack(q)) return;
		int num = Integer.parseInt(q);
		int count = reviewDAO.checkReviewRecord(num, null);
		do {
			if(count==0) {
				System.out.print("리뷰번호를 잘못 입력했습니다. 다시 입력하세요 : ");
				num = Integer.parseInt(br.readLine());
				count = reviewDAO.checkReviewRecord(num, null);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count!=1);
		reviewDAO.selectDetailReview(num);
		System.out.println("-".repeat(90));
	}

	// 책 제목으로 리뷰 검색
	@Override
	public void selectBookTitleReview() throws IOException {
		System.out.print("리뷰를 확인할 책 제목을 입력하세요(뒤로가기:q) : ");
		String bookTitle = br.readLine();
		if(util.Util.goBack(bookTitle)) return;
		int count = reviewDAO.checkReviewRecordOfBookTile(bookTitle);
		do {
			if(count==0) {
				System.out.print("해당 책에 대한 리뷰가 없습니다. 다시 입력하세요(뒤로가기:q) : ");
				bookTitle = br.readLine();
				if(bookTitle.equalsIgnoreCase("q")) {
					System.out.println("뒤로 돌아갑니다.");
					break;
				}
				count = reviewDAO.checkReviewRecordOfBookTile(bookTitle);
			} else if (count==-1) {
				System.out.println("정보 처리 중 오류 발생");
			}
		} while(count==0);
		reviewDAO.selectSearchReviewOftitle(bookTitle);
		System.out.println("-".repeat(90));
		if(count>=1) selectDetailReview();
	}

	// 리뷰 수정
	@Override
	public void updateMyReview() throws IOException {
		boolean MyReview = reviewDAO.selectMyReviewInfo(memId);
		if(!MyReview) return;
		
		try {
			System.out.print("수정할 번호를 입력하세요 (뒤로가기:q) : ");
			String q = br.readLine();
			if(q.equalsIgnoreCase("q")) return;
			int num = Integer.parseInt(q);
			int count = reviewDAO.checkReviewRecord(num,memId);
			if(count==1) {
				System.out.print("새로운 리뷰내용 입력 : ");
				String content = br.readLine();
				System.out.print("새로운 평점 입력(1~5) : ");
				int rate = Integer.parseInt(br.readLine());
				reviewDAO.updateMyReview(num, content, rate, memId);
			}
			else if(count==0) {
				System.out.println("본인이 작성한 리뷰가 아닙니다.");
				System.out.println("-".repeat(90));
			}
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 내 리뷰 삭제
	@Override
	public void deleteMyReview() throws IOException {
		boolean MyReview = reviewDAO.selectMyReviewInfo(memId);
		if(!MyReview) return;

		try {
			System.out.print("삭제할 번호를 입력하세요 (뒤로가기:q) : ");
			String q = br.readLine();
			int num = Integer.parseInt(q);
			int count = reviewDAO.checkReviewRecord(num,memId);
			if(count>0) {
				reviewDAO.deleteReview(num);
			}
			else if(count==0) {
				System.out.println("본인이 작성한 리뷰가 아닙니다.");
				System.out.println("-".repeat(90));
			}
			else if(count==-1) System.out.println("정보 처리 중 오류 발생");
		} catch (NumberFormatException e) {
			System.out.println("[숫자만 입력 가능]");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // deleteMyReview
	
}
