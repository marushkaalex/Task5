CREATE TABLE role (
  id   INT PRIMARY KEY,
  name VARCHAR(255));

INSERT INTO role VALUES (0, 'ADMIN');
INSERT INTO role VALUES (1, 'ARTIST');
INSERT INTO role VALUES (2, 'CLIENT');

CREATE TABLE lang (
  id       INT PRIMARY KEY,
  locale   VARCHAR(5),
  language VARCHAR(50));

INSERT INTO role VALUES (0, 'ADMIN');
INSERT INTO role VALUES (1, 'ARTIST');
INSERT INTO role VALUES (2, 'CLIENT');

CREATE TABLE user (
  id       INT AUTO_INCREMENT,
  username VARCHAR(255),
  email    VARCHAR(255),
  password VARCHAR(255),
  role     INT,
  dob      DATE,
  lang     INT,
  PRIMARY KEY (id),
  FOREIGN KEY (role) REFERENCES role (id),
  FOREIGN KEY (lang) REFERENCES lang (id));

CREATE SCHEMA IF NOT EXISTS LOG;

CREATE TABLE LOG.LOGS (
  time      TIMESTAMP,
  classname VARCHAR(255),
  priority  VARCHAR(5),
  message   VARCHAR(255));

CREATE TABLE painting (
  id          INT AUTO_INCREMENT,
  artist_id   INT,
  likes       INT,
  path        VARCHAR(255),
  name        VARCHAR(255),
  description VARCHAR(1000),
  date        DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (artist_id) REFERENCES user (id));

CREATE TABLE user_painting (
  user_id     INT,
  painting_id INT,
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (painting_id) REFERENCES painting (id));