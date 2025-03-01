package com.library.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.library.DAO.BookOrderDAO;
import com.library.DAO.ReviewDAO;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.ReviewDAOImpl;
import com.library.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	private BufferedReader br;
	private String memId;
	private int orderNum;
	private ReviewDAO reviewDAO;
	private BookOrderDAO bookOrderDAO;
	
	public ReviewServiceImpl(BufferedReader br, String memId) {
		this.br = br;
		this.memId = memId;
		this.reviewDAO = new ReviewDAOImpl();
		this.bookOrderDAO = new BookOrderDAOImpl();
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
	
	// 리뷰 작성
	@Override
	public boolean confirmReview() throws IOException {
	    if (!util.Util.choiceYN(br, "반납하신 책에 대한 리뷰를 작성하시겠습니까?")) {
	        System.out.println("\n리뷰작성을 취소하셨습니다.");
	        return false; // 리뷰 작성 취소
	    }

	    System.out.println("\n리뷰작성을 선택하셨습니다.");
	    System.out.print("다음의 책에 대한 리뷰를 작성합니다.\n\n");
	    System.out.println("-*".repeat(60));
	    bookOrderDAO.selectOrderNumToBookInfo(orderNum);
	    System.out.println("-*".repeat(60));

	    String rate = getValidReviewScore();  // 리뷰 점수 받기
	    String content = getReviewContent();  // 리뷰 내용 받기
	    int book_num = bookOrderDAO.selectOrderNumToBookNum(orderNum);
	    int rateInt = Integer.parseInt(rate);

	    System.out.println("리뷰 등록 중 입니다.");
	    reviewDAO.insertReviewInfo(book_num, content, rateInt, memId);
	    return true;
	}
	
	// 리뷰 범위
	private String getValidReviewScore() throws IOException {
	    boolean flag = false;
	    String rate;
	    String regex = "^[1-5]$";
	    do {
	        if (flag) {
	            System.out.println("\n점수는 1~5 사이의 정수여야 합니다.");
	        }
	        System.out.print("점수 입력(1~5 사이의 정수) : ");
	        rate = br.readLine();
	        flag = true;
	    } while (!rate.matches(regex));
	    return rate;
	}
	
	// 리뷰 내용 입력
	private String getReviewContent() throws IOException {
	    boolean flag = false;
	    String content, rewrite = "";
	    System.out.print("\n리뷰 내용 입력(엔터 누를 시 입력 종료)\n > ");
	    content = br.readLine();
	    do {
	        if (flag) {
	            System.out.println("Y/N(y/n) 중 입력해주세요.");
	        }
	        System.out.print("\n내용을 다시 입력하시겠습니까? (Y/N) : ");
	        rewrite = br.readLine();

	        if (rewrite.equals("Y") || rewrite.equals("y")) {
	            System.out.print("내용을 다시 입력해주세요.(엔터누를시 입력 종료)\n > ");
	            content = br.readLine();
	        } else if (rewrite.equals("N") || rewrite.equals("n")) {
	            System.out.println("리뷰 등록을 그대로 진행합니다.");
	        }
	        flag = true;
	    } while (!rewrite.equals("N") && !rewrite.equals("n") && !rewrite.equals("Y") && !rewrite.equals("y"));
	    return content;
	}
	
	// 리뷰 등록 처리
	@Override
	public void processReview(int orderNum, String memId) throws IOException {
        String rate = getValidReviewScore();  // 리뷰 점수 받기
        String content = getReviewContent();  // 리뷰 내용 받기
        int book_num = bookOrderDAO.selectOrderNumToBookNum(orderNum); // 책 번호 찾기
        int rateInt = Integer.parseInt(rate); // 점수 변환

        // 리뷰 DB에 삽입
        System.out.println("리뷰 등록 중 입니다.");
        reviewDAO.insertReviewInfo(book_num, content, rateInt, memId); // 올바른 메서드 호출
	}
	
}
