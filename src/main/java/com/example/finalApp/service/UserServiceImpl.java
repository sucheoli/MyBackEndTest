package com.example.finalApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.finalApp.dto.UserDTO;
import com.example.finalApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service // 스프링이 서비스 빈으로 등록(트랜잭션, 비즈니스 로직 위치)
@RequiredArgsConstructor // final 필드(userRepository)를 매개변수로 받는 생성자 자동 생성 -> 생성자 주입(DI)
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository; // 실제 DB접근을 수행하는 영속성 계층
   // private final : 반드시 필요하고, 변경되면 안되는 의존성이라는 의미
   // final이라 생성자 에서만 초기화가 가능 -> 필드 주입이 아닌 생성자 주입을 강제하는 패턴
   // NPE(NullPointerException)을 방지, 테스트 코드 작성시 의존성이 명확, 순환 참조를 발견 쉬움

   @Override
   public void join(UserDTO user, String confirmPassword) {
      // 비밀번호 확인 체크
      if (!user.getPassword().equals(confirmPassword)) {
         throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
      }

      // DB 저장 호출
      userRepository.insertUser(user);

   }

   @Override
   public Optional<Long> login(String loginId, String password) {
      //레포지토리에게 조회 위임
      return userRepository.findUserNumber(loginId, password);
   }

}
