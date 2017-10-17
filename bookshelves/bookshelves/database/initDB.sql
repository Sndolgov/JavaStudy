DROP DATABASE IF EXISTS bookshelves;
CREATE DATABASE bookshelves;
USE bookshelves;

CREATE TABLE `Books`

(
  ID INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(255) NOT NULL,
  author VARCHAR(100) NOT NULL,
  isbn VARCHAR(20) NOT NULL,
  printYear INT NOT NULL,
  `readAlready` BIT(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (ID))
  DEFAULT CHARACTER SET = utf8;
