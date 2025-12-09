package com.example.finalApp.repository;

import java.util.Optional;

import com.example.finalApp.dto.UserDTO;

public interface UserRepository {
	//Repository가 가져야할 기능(메소드 목록)만 정의
	//구현은 jdbcUserRepository.java에서 구현
	
	//회원가입(새로운 사용자 저장: 반환값이 필요하지 않으므로 void)
	void insertUser(UserDTO user);
	//DB삽입 성공> 정상실행
	//DB삽입 실패 > 예외
	
	//로그인 시 유저번호 조회(PK)
	Optional<Long> findUserNumber(String loginId, String password);
	//로그인 할 때 로그인 성공 > 해당 사용자의 user_id(PK)를 반환
	//로그인 실패 > 아무런 값도 반환 하지 않음.
	
	//Optional<Long>을 쓰는 이유
	//로그인 성공 user_id가 존재하므로 Optional.of(값)이 반환
	//로그인 실패 일치하는 사용자가 없으므로 Optional.empty()반환
}
