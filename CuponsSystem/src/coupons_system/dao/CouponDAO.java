package coupons_system.dao;

import java.util.Collection;

import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.CouponType;
import coupons_system.javabeans.Customer;

public interface CouponDAO {
	/**
	 * A method add new coupon to the database
	 * 
	 * @param Coupon coupon
	 */
	public void createCoupon(Coupon coupon);

	/**
	 * A method is remove coupon from the database
	 * 
	 * @param Coupon coupon
	 */
	public void removeCoupon(Coupon coupon);

	/**
	 * A method is update existing coupon in the database
	 * 
	 * @param Coupon coupon
	 */
	public void updateCoupon(Coupon coupon);

	/**
	 * A method is return specified coupon from the database
	 * 
	 * @param long id
	 * @return Coupon
	 */
	public Coupon getCoupon(long id);

	/**
	 * A method is return collection of all coupons from the database
	 * 
	 * @return Collection<Coupon>
	 */
	public Collection<Coupon> getAllCoupons();

	/**
	 * A method is return all coupons of specified company
	 * 
	 * @param long id
	 * @return Collection<Coupon>
	 */
	public Collection<Coupon> getAllCompanyCoupons(long id);

	/**
	 * A method is return all coupons by selected type
	 * 
	 * @param CouponType type
	 * @return Collection<Coupon>
	 */
	public Collection<Coupon> getCouponByType(CouponType type);

	/**
	 * A method check if the coupon already purchased by selected customer
	 * 
	 * @param Coupon   coupon
	 * @param Customer customer
	 * @return true if the coupon already purchased. Else return false
	 */
	public boolean checkCouponExisting(Coupon coupon, Customer customer);

	/**
	 * A method check how many specified coupons left in the system
	 * 
	 * @param Coupon coupon
	 * @return int quantity of coupons
	 */
	public int checkCouponAmount(Coupon coupon);

	/**
	 * 
	 * @param coupon
	 * @param companyId
	 */
	public void addCouponAndCompanyJoin(Coupon coupon, long companyId);

	public void removeCouponAndCompanyJoin(Coupon coupon, long companyId);

	public boolean checkCompanyCouponExisting(Coupon coupon, long companyId);
}// Coupon DAO
