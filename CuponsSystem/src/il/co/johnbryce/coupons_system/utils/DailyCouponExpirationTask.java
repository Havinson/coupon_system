package il.co.johnbryce.coupons_system.utils;

import java.util.Collection;
import java.util.Date;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Coupon;

public class DailyCouponExpirationTask implements Runnable {
	private CouponDAO _couponDao;
	private boolean _quit;
	
	public DailyCouponExpirationTask() {
		_quit = true;
		_couponDao = new CouponDBDAO();
	}// c-tor
	
	public synchronized void stopTask(){
		_quit = false;
	}// stopTask
	
	@Override
	public void run() {
		Collection<Coupon> coupons = _couponDao.getAllCoupons();
		while (_quit) {
			for(Coupon curr : coupons) {
				if(curr.get_endDate().before(new Date(System.currentTimeMillis()))) {
					_couponDao.removeCoupon(curr);
				}
			}
			try {
				wait(86400000);
			} catch (InterruptedException e) {
				// TODO take care of exception in daily coupon expiration class
				e.printStackTrace();
			}
		}
	}// run
}// DailyCouponExpirationTask
