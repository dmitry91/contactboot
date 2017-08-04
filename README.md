# contactboot
share project

[![Build Status](https://travis-ci.org/dmitry91/contactboot.svg?branch=master)

1.Создать базу MySQL
create database contacts;

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

INSERT INTO user (name)VALUES('ivan');
INSERT INTO user (name)VALUES('vasya'); и тд.

2.Изменить application.propperties: указать порт, имя базы, имя пользователя и пароль.

3.Запускал на Tomcat 9.0.0.M1
