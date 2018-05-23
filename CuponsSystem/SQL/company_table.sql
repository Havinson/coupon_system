CREATE DATABASE CouponDB;
USE CouponDB;
CREATE TABLE `Company` (
	`ID` int(15) NOT NULL UNIQUE,
	`CompanyName` varchar(255),
	`Password` varchar(255),
	`Email` varchar(255),
	PRIMARY KEY (`ID`));
CREATE TABLE `Customer` (
	`ID` int(15) NOT NULL UNIQUE,
	`CustomerName` varchar(255),
	`Password` varchar(255),
	PRIMARY KEY (`ID`));
CREATE TABLE `Coupon` (
	`ID` int(15) NOT NULL UNIQUE,
	`Title` varchar(255) ,
	`StartDate` DATE,
	`EndDate` DATE,
	`Amount`INTEGER,
	`Type` varchar(255),
	`Message` varchar(255),
	`Price` double precision,
	`Image` varchar(255),
	PRIMARY KEY (`ID`));
CREATE TABLE `CustomerCoupon` (
	Customer_ID NUMERIC,
	Coupon_ID NUMERIC,
	PRIMARY KEY (Customer_ID, Coupon_ID));
CREATE TABLE `CompanyCoupon` (
	Company_ID NUMERIC,
    Coupon_ID NUMERIC,
    PRIMARY KEY(Company_ID, Coupon_ID));