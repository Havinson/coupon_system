package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Coupon;

public class CustomerFacade implements CouponClientFacade {
	private CouponDAO _coupons;
	
	public CustomerFacade() {
		_coupons = new CouponDBDAO();
	}// c-tor
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		return this;
	}// login
	
	public void purchaseCoupon(Coupon coupon) {
		
	}// purchase Coupon
}// Customer facade
