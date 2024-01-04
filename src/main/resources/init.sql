
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

ALTER TABLE user MODIFY COLUMN id INT AUTO_INCREMENT;

INSERT INTO user(userid, password, name, phone_number)
VALUES('60215678', '1234', '김명지', '01012345678');
INSERT INTO user(userid, password, name, phone_number)
VALUES('60205678', '1234', '최명지', '01012345677');
INSERT INTO user(userid, password, name, phone_number)
VALUES('60195678', '1234', '박명지', '01022345678');
