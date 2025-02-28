package com.library.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface QnaService {
	void manageQNA() throws NumberFormatException, IOException;
	void insertQNA() throws IOException;
	void deleteQNA() throws IOException;
}
