package coupons_system.executables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	public static void main(String[] args) throws InterruptedException {
		AdminFacade admin = null;
		// Getting CouponSystem:
		CouponSystem system = CouponSystem.getCouponSystem();
		Collection<Company> allCompanies;
		CompanyFacade companyFacade1 = null;
		Coupon coupon1 = new Coupon(1, "TestCoupon1", new Date(2018, 05, 12), new Date(2019, 05, 12), 10, "CAMPING",
				"The testCompany1 message", 29.9, "url to image testCompany1");
		CustomerFacade customerFacade1 = null;
		CustomerFacade customerFacade2 = null;
		CustomerFacade customerFacade3 = null;
		CustomerFacade customerFacade4 = null;
		Company company1 = new Company(1, "TestCompany1", "123", "company1@email.com");
		Company company2 = new Company(2, "TestCompany2", "123", "company1@email.com");
		Company company3 = new Company(3, "TestCompany", "123", "company1@email.com");
		Customer customer1 = new Customer(1, "TestCustomer", "123");
		Customer customer2 = new Customer(2, "TestCustomer2", "123");
		Customer customer3 = new Customer(3, "TestCustomer3", "123");
		Customer customer4 = new Customer(4, "TestCustomer4", "123");

		try {
			// Login as admin:
			admin = (AdminFacade) system.login("Admin", "12345", ClientType.ADMIN);
			// Creating companies:
			admin.createCompany(company1);
			admin.createCompany(company2);
			admin.createCompany(company3);
			// Getting companies from database:
			allCompanies = admin.getAllCompanies();
			for (Company curr : allCompanies) {
				System.out.println(curr);
			}
			// Updating company
			company2.set_companyName("Updated name company 2");
			admin.updateCompany(company2);

			allCompanies = admin.getAllCompanies();
			for (Company curr : allCompanies) {
				System.out.println(curr);
			}
			// Login as company:
			companyFacade1 = (CompanyFacade) system.login("TestCompany1", "123", ClientType.COMPANY);
			System.out.println(system.login("TestCompany1", "123", ClientType.COMPANY));
			// Creating coupon:
			companyFacade1.createCoupon(coupon1);
			Collection<Coupon> coupons = companyFacade1.getAllCoupons();
			for (Coupon curr : coupons) {
				System.out.println(curr);
			}
			// Creating customers:
			admin.createCustomer(customer1);
			admin.createCustomer(customer3);
			admin.createCustomer(customer2);
			admin.createCustomer(customer4);
			// getting customers from database:
			List<Customer> customers = (ArrayList<Customer>) admin.getAllCustomers();
			for (Customer curr : customers) {
				System.out.println(curr);
			}
			// Login as customer:
			customerFacade1 = (CustomerFacade) system.login("TestCustomer", "123", ClientType.CUSTOMER);
			customerFacade2 = (CustomerFacade) system.login("TestCustomer2", "123", ClientType.CUSTOMER);
			customerFacade3 = (CustomerFacade) system.login("TestCustomer3", "123", ClientType.CUSTOMER);
			customerFacade4 = (CustomerFacade) system.login("TestCustomer4", "123", ClientType.CUSTOMER);
			customer1.set_customerName("Updated name for customer 1");
			customer2.set_customerName("Updated name for customer 2");
			// Updating customers:
			admin.updateCustomer(customer1);
			admin.updateCustomer(customer2);
			customers = (ArrayList<Customer>) admin.getAllCustomers();
			for (Customer curr : customers) {
				System.out.println(curr);
			}
			// Purchasing coupon:
			customerFacade1.purchaseCoupon(coupon1);
			customerFacade2.purchaseCoupon(coupon1);
			customerFacade3.purchaseCoupon(coupon1);
			customerFacade4.purchaseCoupon(coupon1);
			Collection<Coupon> customersCoupons1 = customerFacade1.getAllPurchasedCoupons();
			Collection<Coupon> customersCoupons2 = customerFacade1.getAllPurchasedCoupons();
			coupon1.set_title("Updated for coupon");
			// Updating coupon:
			companyFacade1.updateCoupon(coupon1);
			Collection<Coupon> customersCoupons3 = customerFacade1.getAllPurchasedCoupons();
			Collection<Coupon> customersCoupons4 = customerFacade1.getAllPurchasedCoupons();
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
			// Removing coupon:
			companyFacade1.removeCoupon(coupon1);
			// Removing customers:
			admin.removeCustomer(customer1);
			admin.removeCustomer(customer2);
			admin.removeCustomer(customer3);
			admin.removeCustomer(customer4);
			// Removing companies:
			admin.removeCompany(company1);
			admin.removeCompany(company2);
			admin.removeCompany(company3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// shutdown the system:
			system.shutdown();
		}
	}// main
}// Test
