package il.co.johnbryce.coupons_system.dao;

import java.util.Collection;

import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;

public interface CouponDAO {
	public void createCoupon(Coupon coupon);
	public void removeCoupon(Coupon coupon);
	public void updateCoupon(Coupon coupon);
	public Coupon getCoupon (long id);
	public Collection<Coupon> getAllCoupons();
	public Collection<Coupon> getCouponByType(CouponType type);
	
	
	
}// Coupon DAO
