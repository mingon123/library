package com.library.service;

import java.io.IOException;

public interface MemberService {
	boolean isValid(String field, String value);
	boolean signUp(String id, String passwd, String name, String cell, String email);
	void signUpProcess() throws IOException;
	void login() throws IOException;
}
