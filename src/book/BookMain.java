package book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookMain {
	private BufferedReader br;
	private BookDAO dao;
	
	public BookMain() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new BookDAO();
			callMenu();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null) try {br.close();}catch(IOException e) {}
		}
	} // BookMain
	
	// 메뉴
	private void callMenu() throws IOException {
		while(true) {
			System.out.print("1.도서추가,2.목록보기,3.상세정보보기,4.정보수정,5.도서삭제,6.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					// 도서추가
					System.out.print("책제목:");
					String title = br.readLine();
					System.out.print("저자:");
					String author = br.readLine();
					System.out.print("출판사:");
					String publisher = br.readLine();
					System.out.print("출판년도:");
					
					Integer publication_year = null;
					String pyStr = br.readLine();
					if(!pyStr.isEmpty()) publication_year = Integer.parseInt(pyStr);
					
					System.out.print("카테고리:");
					String category = br.readLine();
					
					System.out.print("추천순위");
					Integer rank = null;
					String rankStr = br.readLine();
					if(!rankStr.isEmpty()) rank = Integer.parseInt(rankStr);
					
					System.out.print("남은 권수");
					Integer volm_cnt = null;
					String volmCntStr = br.readLine();
					if(!volmCntStr.isEmpty()) volm_cnt = Integer.parseInt(volmCntStr);
					
					dao.insertInfo(title, author, publisher, publication_year, category, rank, volm_cnt);
				} //no1
				
				else if(no==2) {
					// 목록보기
					dao.selectInfo();
				} //no2
				
				else if(no==3) {
					// 상세정보보기
					dao.selectInfo();
					System.out.print("선택한 책의 번호:");
					int num = Integer.parseInt(br.readLine());
					System.out.println("-".repeat(50));
					dao.selectDetailInfo(num);
				} //no3
				
				else if(no==4) {
					// 정보수정
					dao.selectInfo();
					System.out.print("수정할 책의 번호:");
					int num = Integer.parseInt(br.readLine());
					// 레코드 존재 여부 체크 1:존재,0:미존재,-1:오류
					int count = dao.checkRecord(num);
					if(count==1) {
						dao.selectDetailInfo(num);
						System.out.println("-".repeat(50));
						
						System.out.print("책제목:");
						String title = br.readLine();
						System.out.print("저자:");
						String author = br.readLine();
						System.out.print("출판사:");
						String publisher = br.readLine();
						System.out.print("출판년도:");
						
						Integer publication_year = null;
						String pyStr = br.readLine();
						if(!pyStr.isEmpty()) publication_year = Integer.parseInt(pyStr);
						
						System.out.print("카테고리:");
						String category = br.readLine();
						
						System.out.print("추천순위");
						Integer rank = null;
						String rankStr = br.readLine();
						if(!rankStr.isEmpty()) rank = Integer.parseInt(rankStr);
						
						System.out.print("남은 권수");
						Integer volm_cnt = null;
						String volmCntStr = br.readLine();
						if(!volmCntStr.isEmpty()) volm_cnt = Integer.parseInt(volmCntStr);
						
						dao.updateInfo(num, title, author, publisher, publication_year, category, rank, volm_cnt);
					} else if(count==0) System.out.println("번호를 잘못 입력했습니다.");
					else System.out.println("정보 처리 중 오류 발생");
				} //no4
				
				else if(no==5) {
					dao.selectInfo();
					System.out.print("삭제할 책의 번호:");
					int num = Integer.parseInt(br.readLine());
					// 레코드 존재 여부 체크 1:존재,0:미존재,-1:오류
					int count = dao.checkRecord(num);
					if(count==1) dao.deleteInfo(num);
					else if(count==0) System.out.println("번호를 잘못 입력했습니다.");
					else System.out.println("정보 처리 중 오류 발생");
				} //no5
								
				else if(no==6) {
					// 종료
					System.out.println("프로그램을 종료합니다.");
					break; 
				} // no6
				else System.out.println("잘못 입력했습니다.");				
			} catch (NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	} // callMenu
	
	public static void main(String[] args) {
		new BookMain();
	}
	
}
