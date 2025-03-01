package com.library.service;

import java.io.IOException;

public interface ReviewService {

	void selectDetailReview() throws IOException;
	void selectBookTitleReview() throws IOException;
	void updateMyReview() throws IOException;
	void deleteMyReview() throws IOException;
	
	boolean confirmReview() throws IOException;
	void processReview(int orderNum, String memId) throws IOException;

}
