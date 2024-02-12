-- TODO 2 ~ Setting and Make Table in Database MYSQL
-- Database Name:
CREATE DATABASE user_manager;

USE user_manager;

GRANT ALL PRIVILEGES ON user_manager.* TO 'root'@'localhost';

-- To allow default value updateAt
set session sql_mode=replace(@@sql_mode,'NO_ZERO_DATE','');

-- USER TABLE STRUCTURE
CREATE TABLE users(
    userId INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
    PRIMARY KEY (userId)
);