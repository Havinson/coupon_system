package il.co.johnbryce.coupons_system.utils;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;

public class DailyCouponExpirationTask implements Runnable {
	CouponDAO _couponDao;
	
	public DailyCouponExpirationTask() {
		_couponDao = new CouponDBDAO();
	}// c-tor
	
	@Override
	public void run() {
		_couponDao.couponExpirationTask();
	}// run

}// DailyCouponExpirationTask
