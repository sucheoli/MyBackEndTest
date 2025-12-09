package com.example.finalApp.dto;

import lombok.Data;

@Data
public class UserDTO {
//	CREATE TABLE USERS(
//			user_id NUMBER,
//			login_id varchar2(50) NOT NULL,
//			password varchar2(200) NOT NULL,
//			gender varchar2(10),
//			email varchar2(100),
//			address varchar2(200),
//			address_detail varchar2(200),
//			zipcode varchar2(10),
//			CONSTRAINT pk_users PRIMARY KEY (user_id)
//			);
	
	private Long userId;
	private String loginId;
	private String password;
	private String gender;
	private String email;
	private String address;
	private String addressDetail;
	private String zipcode;
}
