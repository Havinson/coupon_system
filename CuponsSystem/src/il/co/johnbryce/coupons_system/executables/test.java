package il.co.johnbryce.coupons_system.executables;

import java.util.Collection;
import java.util.Date;

import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;
import il.co.johnbryce.coupons_system.exceptions.CouponAlreadyExistException;
import il.co.johnbryce.coupons_system.facades.AdminFacade;
import il.co.johnbryce.coupons_system.facades.ClientType;
import il.co.johnbryce.coupons_system.facades.CompanyFacade;
import il.co.johnbryce.coupons_system.facades.CouponSystem;
import il.co.johnbryce.coupons_system.facades.CustomerFacade;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
import il.co.johnbryce.coupons_system.javabeans.Customer;

public class test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		AdminFacade admin = null;
		Collection<Company> allCompanies;
		try {
			admin = (AdminFacade) CouponSystem.getCouponSystem().login("Admin", "12345", ClientType.ADMIN);
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}

		admin.createCompany(new Company(1, "TestCompany1", "123", "company1@email.com"));
		allCompanies = admin.getAllCompanies();
		for(Company curr: allCompanies) {
			System.out.println(curr);
		}
		CompanyFacade company1 = null;
		try {
			company1 = (CompanyFacade) CouponSystem.getCouponSystem().login("TestCompany1", "123", ClientType.COMPANY);
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		Coupon coupon1 = new Coupon(1, "TestCoupon1", new Date(2018, 05, 12), new Date(2019, 05, 12), 10,
				"CAMPING", "The testCompany1 message", 29.9, "url to image testCompany1");
		try {
			company1.createCoupon(coupon1);
		} catch (CouponAlreadyExistException e) {
			e.printStackTrace();
		}
		admin.createCustomer(new Customer(1, "TestCustomer", "123"));
		CustomerFacade customer = null;
		try {
			customer = (CustomerFacade) CouponSystem.getCouponSystem().login("TestCustomer", "123",
					ClientType.CUSTOMER);
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		customer.purchaseCoupon(coupon1);
			
	}// main
}// Test
