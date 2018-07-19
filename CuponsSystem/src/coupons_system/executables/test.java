package coupons_system.executables;

import java.util.Collection;
import java.util.Date;

import coupons_system.exceptions.ClientNotFoundException;
import coupons_system.exceptions.CouponAlreadyExistException;
import coupons_system.facades.AdminFacade;
import coupons_system.facades.ClientType;
import coupons_system.facades.CompanyFacade;
import coupons_system.facades.CouponSystem;
import coupons_system.facades.CustomerFacade;
import coupons_system.javabeans.Company;
import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.Customer;

public class test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		AdminFacade admin = null;
		CouponSystem system = CouponSystem.getCouponSystem();
		Collection<Company> allCompanies;
		try {
			admin = (AdminFacade) system.login("Admin", "12345", ClientType.ADMIN);
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}

		admin.createCompany(new Company(1, "TestCompany1", "123", "company1@email.com"));
		allCompanies = admin.getAllCompanies();
		for (Company curr : allCompanies) {
			System.out.println(curr);
		}
		CompanyFacade company1 = null;
		try {
			company1 = (CompanyFacade) system.login("TestCompany1", "123", ClientType.COMPANY);
			System.out.println(system.login("TestCompany1", "123", ClientType.COMPANY));
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		Coupon coupon1 = new Coupon(1, "TestCoupon1", new Date(2018, 05, 12), new Date(2019, 05, 12), 10, "CAMPING",
				"The testCompany1 message", 29.9, "url to image testCompany1");
		try {
			company1.createCoupon(coupon1);
			Collection<Coupon> coupons = company1.getAllCoupons();
			for (Coupon curr : coupons) {
				System.out.println(curr);
			}
		} catch (CouponAlreadyExistException e) {
			e.printStackTrace();
		}
		admin.createCustomer(new Customer(1, "TestCustomer", "123"));
		admin.createCustomer(new Customer(2, "TestCustomer2", "123"));
		admin.createCustomer(new Customer(3, "TestCustomer3", "123"));
		admin.createCustomer(new Customer(4, "TestCustomer4", "123"));
		CustomerFacade customer1 = null;
		CustomerFacade customer2 = null;
		CustomerFacade customer3 = null;
		CustomerFacade customer4 = null;
		try {
			customer1 = (CustomerFacade) system.login("TestCustomer", "123", ClientType.CUSTOMER);
			customer2 = (CustomerFacade) system.login("TestCustomer2", "123", ClientType.CUSTOMER);
			customer3 = (CustomerFacade) system.login("TestCustomer3", "123", ClientType.CUSTOMER);
			customer4 = (CustomerFacade) system.login("TestCustomer4", "123", ClientType.CUSTOMER);
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		customer1.purchaseCoupon(coupon1);
		customer2.purchaseCoupon(coupon1);
		customer3.purchaseCoupon(coupon1);
		customer4.purchaseCoupon(coupon1);
		customer4.purchaseCoupon(coupon1);
		Collection<Coupon> customersCoupons1 = customer1.getAllPurchasedCoupons();
		Collection<Coupon> customersCoupons2 = customer1.getAllPurchasedCoupons();
		Collection<Coupon> customersCoupons3 = customer1.getAllPurchasedCoupons();
		Collection<Coupon> customersCoupons4 = customer1.getAllPurchasedCoupons();
		for (Coupon curr : customersCoupons1) {
			System.out.println(curr);
		}
		for (Coupon curr : customersCoupons2) {
			System.out.println(curr);
		}
		for (Coupon curr : customersCoupons3) {
			System.out.println(curr);
		}
		for (Coupon curr : customersCoupons4) {
			System.out.println(curr);
		}
		company1.removeCoupon(coupon1);
	}// main
}// Test
