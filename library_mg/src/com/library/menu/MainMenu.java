package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.library.DAO.BookDAO;
import com.library.DAO.BookOrderDAO;
import com.library.DAO.MemberDAO;
import com.library.DAO.NoticeDAO;
import com.library.DAO.impl.BookDAOImpl;
import com.library.DAO.impl.BookOrderDAOImpl;
import com.library.DAO.impl.MemberDAOImpl;
import com.library.DAO.impl.NoticeDAOImpl;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.NoticeService;
import com.library.service.Impl.BookServiceImpl;
import com.library.service.Impl.MemberServiceImpl;
import com.library.service.Impl.NoticeServiceImpl;

public class MainMenu {
	private BufferedReader br;
	private String memId;
	
	private BookDAO bookDAO;
	private MemberDAO memberDAO;
	private NoticeDAO noticeDAO;
	
    private BookService bookService;
    private NoticeService noticeService;
    private MemberService memberService;
    
	private BookMenu bookMenu; 
	

	public MainMenu() {
		try {
			this.br = new BufferedReader(new InputStreamReader(System.in));
			this.memId = memId;
			this.memberDAO = new MemberDAOImpl(memId);
            this.noticeDAO = new NoticeDAOImpl();
            this.bookDAO = new BookDAOImpl();
            
            this.bookService = new BookServiceImpl(br, memId);
            this.memberService = new MemberServiceImpl(memId, br); 
	        this.noticeService = new NoticeServiceImpl(noticeDAO, br);
	        
	        this.bookMenu = new BookMenu(br, bookService, bookDAO);
	        
			callMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    public void callMenu() throws NumberFormatException, IOException {
        while (true) {
            System.out.println("===== Main Menu =====");
            for (MainMenuEnum menu : MainMenuEnum.values()) {
                System.out.println(menu.getNumber() + ". " + menu.getTitle());
            }
            System.out.print("메뉴를 선택하세요: ");
            int choice = -1;
            boolean validInput = false;
            
            while(!validInput) {
            	try {
            		choice = Integer.parseInt(br.readLine());
            		validInput = true;
            	} catch (NumberFormatException e) {
					System.out.println("[숫자만 입력 가능]");
				}
            }
            
            switch (choice) {
                case 1: // 도서 목록
                    bookMenu.showBookList();
                    break;
                case 2: // 도서 검색
                    bookMenu.searchBooks();
                    break;
                case 3: // 공지사항 확인
                	System.out.println("공지사항 메뉴로 이동");
                	noticeService.showNotices();
                    break;
                case 4: // 회원가입
					System.out.println("회원가입 페이지입니다.");
					memberService.signUpProcess();
                    break;
                case 5: // 로그인
                    if (memberService.login()) {
                        System.out.println("로그인 성공");
                    } else {
                        System.out.println("로그인 실패");
                    }
                    break;
                case 6: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}