package com.example.finalApp.service;

import java.util.Optional;

import com.example.finalApp.dto.UserDTO;

public interface UserService {
	//서비스 계층: 비즈니스 로직 처리
	//회원가입 비즈니스 로직
	//비밀번호 확인, 중복 아이디 검사, 필수값 겁증, Repository를 호출하여 DB에 저장
	void join(UserDTO user, String confirmPassword);
	
	//로그인 비즈니스 로직
	//입력한 로그인 id가 존재하는지 확인, 비밀번호가 일치하는지 확인, 성공하면 사용자 번호 반환, 실패시 Optional,empty반환
	Optional<Long> login(String loginId, String password);
	
}
