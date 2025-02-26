package com.library.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface QnaService {
	void manageQNA(BufferedReader br) throws NumberFormatException, IOException;
	void insertQNA(BufferedReader br) throws IOException;
	void deleteQNA(BufferedReader br) throws IOException;
}
