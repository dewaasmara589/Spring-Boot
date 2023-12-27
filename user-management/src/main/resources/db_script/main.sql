-- Database Name:
CREATE DATABASE user_manager;

USE user_manager;

GRANT ALL PRIVILEGES ON user_manager.* TO 'root'@'localhost';

-- USER TABLE STRUCTURE
CREATE TABLE users(
    userId INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    PRIMARY KEY (userId)
);