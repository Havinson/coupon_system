package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.Customer;

public class CustomerFacade implements CouponClientFacade {
	private CouponDAO _coupons;
	private CustomerDAO _customerDao;
	private Customer _currentCustomer;
	
	public CustomerFacade() {
		_coupons = new CouponDBDAO();
		_customerDao = new CustomerDBDAO();
	}// c-tor
	@Override
	public CustomerFacade login(String userName, String password, ClientType type) {
		_customerDao.login(userName, password);
		return this;
		
	}// login
	
	public void purchaseCoupon(Coupon coupon) {
		_coupons.checkCouponExisting(coupon, customer)
	}// purchase Coupon
}// Customer facade
