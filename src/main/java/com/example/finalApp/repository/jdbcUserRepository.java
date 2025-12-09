package com.example.finalApp.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.finalApp.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Repository // Spring이 이 클래스를 DAO(데이터 접근 레이어)로 관리
@RequiredArgsConstructor // final 필드인 JdbcTmplate을 생성자 주입(DI)
public class jdbcUserRepository implements UserRepository {

   private final JdbcTemplate jdbc;

   // alt + shift + s + v : 오버라이딩 (재정의)
   @Override
   public void insertUser(UserDTO user) {
      // 시퀀스 번호 미리 가져오기
      Long userId = jdbc.queryForObject("SELECT SEQ_USER.NEXTVAL FROM DUAL", Long.class);
      user.setUserId(userId);

      // Insert 실행
      String sql = "INSERT INTO users (USER_ID, LOGIN_ID, PASSWORD, GENDER, EMAIL, ADDRESS, ADDRESS_DETAIL, ZIPCODE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

      jdbc.update(sql, user.getUserId(), user.getLoginId(), user.getPassword(), user.getGender(), user.getEmail(),
            user.getAddress(), user.getAddressDetail(), user.getZipcode());
   }

   @Override
   public Optional<Long> findUserNumber(String loginId, String password) {
      String sql = "SELECT USER_ID FORM USERS WHERE LOGIN_ID = ? AND PASSWORD=?";

      try {
         Long userId = jdbc.queryForObject(sql, Long.class, loginId, password);
         // queryForObject : 결과가 정확히 1행일 때 그 행의 첫 컬럼을 Long으로 반환
         return Optional.ofNullable(userId);

         // 행이 있으면 Optional.ofNullable(userId) Optional로 감싸서 반환
         // USER_ID NUMBER NOT NULL이면 of로만 써도 가능함
      } catch (EmptyResultDataAccessException e) {
         // TODO Auto-generated catch block
         return Optional.empty(); //일치하는 계정이 없음
         // 행이 없으면 queryForObject가 EmptyReusltDataAccessException를 던지므로 Optional.empty()를
         // 반환 => 일치하는 사용자가 없음을 의미
      }

   }

}
