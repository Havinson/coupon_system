package il.co.johnbryce.coupons_system.executables;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import il.co.johnbryce.coupons_system.dao.CompanyDAO;
import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;
import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;
import il.co.johnbryce.coupons_system.exceptions.CouponAlreadyExistException;
import il.co.johnbryce.coupons_system.facades.AdminFacade;
import il.co.johnbryce.coupons_system.facades.ClientType;
import il.co.johnbryce.coupons_system.facades.CompanyFacade;
import il.co.johnbryce.coupons_system.facades.CouponClientFacade;
import il.co.johnbryce.coupons_system.facades.CouponSystem;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
import il.co.johnbryce.coupons_system.utils.ConnectionPool;
import il.co.johnbryce.coupons_system.utils.DailyCouponExpirationTask;

public class test {

	public static void main(String[] args) {
//		AdminFacade admin;
//		try {
//			admin = (AdminFacade)CouponSystem.getCouponSystem().login("Admin", "12345", ClientType.ADMIN);
//		} catch (ClientNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			admin = null;
//		}
		Company company = new Company(12345, "Global Travels", "1215", "mail@GlobalTravels.com");
//		((AdminFacade)admin).createCompany(company);
//		admin.createCompany(company);
//
//		CompanyFacade globalTravels;
//		try {
//			globalTravels = (CompanyFacade) CouponSystem.getCouponSystem().login("Global Travels", "1215",
//					ClientType.COMPANY);
//		} catch (ClientNotFoundException e) {
//			e.printStackTrace();
//			globalTravels = null;
//		}
//				Coupon coupon = new Coupon(1, "Vocation in peru", new Date(2015 - 03 - 12), new Date(2018 - 9 - 12), 45,
//				CouponType.TRAVELLING.name(), "Some message", 23.5, "url to image");
//		try {
//			globalTravels.createCoupon(coupon);
//		} catch (CouponAlreadyExistException e) {
//			e.printStackTrace();
//		}
//		
		
//		System.out.println(globalTravels.getAllCoupons().size());
		
		
		
		CompanyDAO companyDao = new CompanyDBDAO();
		List<Company> allCompanies = (ArrayList<Company>) companyDao.getAllCompanies();
		for(Company curr: allCompanies) {
			System.out.println(curr);
		}
		boolean test = false;
		for(Company curr: allCompanies) {
			if(curr.getCompanyName().equals(company.getCompanyName()) && curr.getPassword().equals(company.getPassword())) {
				test = true;
			}
		}
		System.out.println(test);
	
	}

}
