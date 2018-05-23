package il.co.johnbryce.facades;

import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;

public class CompanyFacade implements CouponClientFacade {
	private CouponDAO _couponDAO;
	
	public CompanyFacade(){
		_couponDAO = new CouponDBDAO();
	}// c-tor
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		return this;
	}//login
	
	public void updateCoupon(Coupon coupon) {
		_couponDAO.updateCoupon(coupon);
	}//updateCoupon
	
	public void removeCoupon(Coupon coupon) {
		_couponDAO.removeCoupon(coupon);
	}
	public Coupon getCoupon(long id) {
		return _couponDAO.getCoupon(id);
	}// get coupon
	
	public Collection<Coupon> getAllCoupons(){
		return _couponDAO.getAllCoupons();
	}// get all coupons
	
	public Collection<Coupon> getCouponByType(CouponType type){
		return _couponDAO.getCouponByType(type);
	}// get coupons by type
	
	public void createCoupon(Coupon coupon) {
		_couponDAO.createCoupon(coupon);
	}//create coupon
}// Company facade
