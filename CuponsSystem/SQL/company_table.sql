drop database if exists CouponDB;
CREATE DATABASE CouponDB;
USE CouponDB;
CREATE TABLE `Company` (
	`ID` bigint(15) NOT NULL UNIQUE,
	`CompanyName` varchar(64),
	`Password` varchar(64),
	`Email` varchar(255),
	PRIMARY KEY (`ID`));
CREATE TABLE `Customer` (
	`ID` bigint(15) NOT NULL UNIQUE,
	`CustomerName` varchar(64),
	`Password` varchar(64),
	PRIMARY KEY (`ID`));
CREATE TABLE `Coupon` (
	`ID` bigint(15) NOT NULL UNIQUE,
	`Title` varchar(64) ,
	`StartDate` DATE,
	`EndDate` DATE,
	`Amount`INTEGER,
	`Type` varchar(64),
	`Message` varchar(255),
	`Price` double ,
	`Image` varchar(255),
	PRIMARY KEY (`ID`));
CREATE TABLE `CustomerCoupon` (
	Customer_ID NUMERIC,
	Coupon_ID NUMERIC,
	constraint pk_customer_coupon PRIMARY KEY (Customer_ID, Coupon_ID));
CREATE TABLE `CompanyCoupon` (
	Company_ID NUMERIC,
    Coupon_ID NUMERIC,
    constraint pk_company_coupon PRIMARY KEY(Company_ID, Coupon_ID));