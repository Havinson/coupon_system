package il.co.johnbryce.coupons_system.facades;

import java.util.ArrayList;
import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;
import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
import il.co.johnbryce.coupons_system.javabeans.Customer;

public class CustomerFacade implements CouponClientFacade {
	private CouponDAO _couponDao;
	private CustomerDAO _customerDao;
	private Customer _currentCustomer;
	
	public CustomerFacade() {
		_couponDao = new CouponDBDAO();
		_customerDao = new CustomerDBDAO();
	}// c-tor
	@Override
	public CustomerFacade login(String userName, String password, ClientType type) throws ClientNotFoundException {
		CustomerFacade ret = null;
		if(_customerDao.login(userName, password)) {
			ret = this;
			_currentCustomer = _customerDao.getCustomerByLogin(userName, password);
		}else{
			throw new ClientNotFoundException("user name or password is incorrect!");
		};
		return ret;
		
	}// login
	
	public void purchaseCoupon(Coupon coupon) {
		if (_couponDao.checkCouponExisting(coupon, _currentCustomer) == false && _couponDao.checkCouponAmount(coupon) > 0) {
			_couponDao.getCoupon(coupon.get_id()).set_amount(-1);
			_customerDao.addToCustomerCoupon(_currentCustomer, coupon);
			_couponDao.updateCoupon(coupon);
			
		};
	}// purchase Coupon
	
	public Collection<Coupon> getAllPurchasedCoupons(){
		Collection<Coupon> coupons = _customerDao.getCoupons(_currentCustomer);
		return coupons;
	}// get all purchased coupons
	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type){
		Collection<Coupon> allCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsByType = new ArrayList<>();
		for(Coupon curr: allCoupons) {
			if(curr.get_type()==type) {
				couponsByType.add(curr);
			}
		}
		return couponsByType;
	}// get all purchased coupons by type
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price){
		Collection<Coupon> allCoupons = getAllPurchasedCoupons();
		Collection<Coupon> couponsByPrice = new ArrayList<>();
		for(Coupon curr: allCoupons) {
			if(curr.get_price()==price) {
				couponsByPrice.add(curr);
			}
		}
		return couponsByPrice;
	}// get coupons by price
}// Customer facade
