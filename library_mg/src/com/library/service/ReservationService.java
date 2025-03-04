package com.library.service;

import java.io.IOException;

public interface ReservationService {

	void handleReservation(int bookNum) throws IOException;
	void cancelReservation(String memId) throws IOException;

}
