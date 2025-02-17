package book;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import util.DBUtil;

public class InsertJson {
    public static void main(String[] args) {
        try {
            // JSON 파일 읽기
            FileReader reader = new FileReader("C:\\Class\\JavaClass\\Library_Java_PR\\data\\book_volm_cnt.json");
            JsonElement rootElement = JsonParser.parseReader(reader);
            
            // JSON이 배열 형태일 경우
            if (rootElement.isJsonArray()) {
                JsonArray jsonArray = rootElement.getAsJsonArray();
                
                // 데이터베이스 연결
                Connection conn = DBUtil.getConnection();
                
                // 배열의 각 항목을 반복하며 처리
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();

                        // JSON 객체에서 데이터 추출
                        String rankStr = jsonObject.get("RKI_NO").getAsString();
                        String author = jsonObject.get("AUTHOR_NM_INFO").getAsString().replace(';', ',');
                        if (author == null || author.isEmpty()) author = "저자 정보 없음";
                        String publicationYearStr = jsonObject.get("PUBLCATN_YY").getAsString();
                        String title = jsonObject.get("BOOK_NM_INFO").getAsString(); 
                        String publisher = jsonObject.get("PUBLSHCMPY_NM").getAsString();
                        String category = jsonObject.get("category").getAsString();
                        String volmCntStr = jsonObject.get("VOLM_CNT").getAsString();

                        int publicationYear = (publicationYearStr == null || publicationYearStr.isEmpty()) ? 0 : Integer.parseInt(publicationYearStr);
                        int volmCnt = (volmCntStr == null || volmCntStr.isEmpty()) ? 0 : Integer.parseInt(volmCntStr);
                        int rank = (rankStr == null || rankStr.isEmpty()) ? 0 : Integer.parseInt(rankStr);                 
                                                
                        // SQL INSERT 구문
                        String sql = "INSERT INTO book (book_num, book_title, book_author, book_publisher, book_p_year, book_category, book_rank, book_volm_cnt, book_reg_date)"
                                   + "VALUES (book_seq.nextval, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
                        
                        // PreparedStatement로 데이터 삽입
                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, title);
                            pstmt.setString(2, author);
                            pstmt.setString(3, publisher);
                            pstmt.setInt(4, publicationYear);
                            pstmt.setString(5, category);
                            pstmt.setInt(6, rank);
                            pstmt.setInt(7, volmCnt);

                            // 실행
                            pstmt.executeUpdate();
                            System.out.println("데이터 삽입 완료: " + title);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                // DB 연결 종료
                DBUtil.executeClose(null, null, conn);
            } else {
                System.out.println("JSON이 배열이 아닙니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


