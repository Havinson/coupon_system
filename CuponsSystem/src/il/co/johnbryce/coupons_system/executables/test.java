package il.co.johnbryce.coupons_system.executables;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import il.co.johnbryce.coupons_system.facades.AdminFacade;
import il.co.johnbryce.coupons_system.facades.ClientType;
import il.co.johnbryce.coupons_system.facades.CompanyFacade;
import il.co.johnbryce.coupons_system.facades.CouponSystem;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
import il.co.johnbryce.coupons_system.utils.ConnectionPool;
import il.co.johnbryce.coupons_system.utils.DailyCouponExpirationTask;
public class test {

	public static void main(String[] args) {
		
//		Company company = new Company(1234567, "some company", "0192837465", "company@email.com");
//		AdminFacade comp = (AdminFacade) CouponSystem.getCouponSystem().login("Admin", "12345", ClientType.ADMIN);
//		comp.createCompany(company);
//				comp.createCoupon(coupon);
//		System.out.println(coupon.get_startDate());
//		List<Coupon> coupons = (ArrayList)comp.getCouponByType(CouponType.CAMPING);
//		for(Coupon curr: coupons) {
//			System.out.println(curr);
//		}
		DailyCouponExpirationTask task = new DailyCouponExpirationTask();
		try{task.run();
		
		}catch(Exception e) {
			
		}
	}

}
