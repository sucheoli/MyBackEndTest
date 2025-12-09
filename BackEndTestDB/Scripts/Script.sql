CREATE TABLE users(
   user_id NUMBER,
   login_id varchar2(50) NOT null,
   password varchar2(200) NOT NULL,
   gender varchar2(10),
   email varchar2(100),
   address varchar2(200),
   address_detail varchar2(200),
   zipcode varchar2(10),
   CONSTRAINT pk_users PRIMARY KEY(user_id)
);

SELECT * FROM users;

CREATE SEQUENCE seq_users;