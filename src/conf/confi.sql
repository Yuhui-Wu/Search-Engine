/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Administrator
 * Created: Dec 1, 2017
 */
CREATE DATABASE IF NOT EXISTS final;
USE final;
CREATE TABLE IF NOT EXISTS users (
userID INT PRIMARY KEY AUTO_INCREMENT,
userName varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS userInfo(
userID INT PRIMARY KEY AUTO_INCREMENT REFERENCES users(userID) ,
email varchar(255) NOT NULL UNIQUE  
);
CREATE TABLE IF NOT EXISTS pdfs(
pdfID INT PRIMARY KEY AUTO_INCREMENT,
pdfName varchar(255) NOT NULL UNIQUE,
addr varchar(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS logs(
logID INT PRIMARY KEY AUTO_INCREMENT,
userID INT REFERENCES users(userID),
time DATETIME NOT NULL UNIQUE,
pdfID INT NOT NULL,
page INT NOT NULL 
);
GRANT ALL PRIVILEGES ON final To 'final'@'localhost' IDENTIFIED BY 'final';