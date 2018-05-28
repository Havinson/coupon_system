package il.co.johnbryce.coupons_system.facades;

import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
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
		CustomerFacade ret = null;
		if(_customerDao.login(userName, password)) {
			ret = this;
		};
		_currentCustomer = _customerDao.getLoggedInCustomer(userName, password);
		return ret;
		
	}// login
	
	public void purchaseCoupon(Coupon coupon) {
		if (_coupons.checkCouponExisting(coupon, _currentCustomer) && _coupons.checkCouponAmount(coupon) != 0) {
			_coupons.getCoupon(coupon.get_id()).set_amount(-1);
		};
	}// purchase Coupon
	
	public Collection<Coupon> getAllPurchasedCoupons(){
		Collection<Coupon> coupons = _customerDao.getCoupons(_currentCustomer);
		return coupons;
	}// get all purchased coupons
	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type){
		Collection<Coupon> allCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsByType = null;
		while(allCoupons.iterator().hasNext()) {
			Coupon curr = allCoupons.iterator().next();
			if(curr.get_type() == type) {
				couponsByType.add(curr);
			}
		}
		return couponsByType;
	}// get all purchased coupons by type
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price){
		Collection<Coupon> allCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsByPrice = null;
		while(allCoupons.iterator().hasNext()) {
			Coupon curr = allCoupons.iterator().next();
			if(curr.get_price() == price) {
				couponsByPrice.add(curr);
			}
		}
		return couponsByPrice;
	}// get coupons by price
}// Customer facade
