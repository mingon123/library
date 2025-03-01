package com.library.service;

import java.io.IOException;

public interface BookOrderService {
	void orderOrReserveMenu(int bookNum) throws IOException;
	void handleRental(int bookNum) throws IOException;

}
