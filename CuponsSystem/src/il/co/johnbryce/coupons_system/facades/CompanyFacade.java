package il.co.johnbryce.coupons_system.facades;

import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CompanyDAO;
import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;
import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;

public class CompanyFacade implements CouponClientFacade {
	private CouponDAO _couponDAO;
	private CompanyDAO _companyDao;
	private Company _currentCompany;
	
	public CompanyFacade(){
		_couponDAO = new CouponDBDAO();
		_companyDao = new CompanyDBDAO();
	}// c-tor
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		CompanyFacade ret = null;
		if(_companyDao.login(userName, password)) {
			ret = this;
			_currentCompany = _companyDao.getLoggedInCompany(userName, password);
		}else {
			System.out.println("The company is not exist!");
		}
		return ret;
	}//login
	
	public void updateCoupon(Coupon coupon) {
		_couponDAO.updateCoupon(coupon);
	}//updateCoupon
	
	public void removeCoupon(Coupon coupon) {
		_couponDAO.removeCoupon(coupon);
		_couponDAO.removeCouponAndCompanyJoin(coupon, _currentCompany.getId());
	}//removeCoupon
	public Coupon getCoupon(long id) {
		return _couponDAO.getCoupon(id);
	}// get coupon
	
	public Collection<Coupon> getAllCoupons(){
		return _couponDAO.getAllCompanyCoupons(_currentCompany.getId());
	}// get all coupons
	
	public Collection<Coupon> getCouponByType(CouponType type){
		Collection<Coupon> couponsByType = null;
		Collection<Coupon> allCoupons = getAllCoupons();
		for(Coupon curr: allCoupons) {
			if(curr.get_type() == type) {
				couponsByType.add(curr);
			}
		}
		return couponsByType;
	}// get coupons by type
	
	public void createCoupon(Coupon coupon) {
		_couponDAO.createCoupon(coupon);
		_couponDAO.addCouponAndCompanyJoin(coupon, _currentCompany.getId());
	}//create coupon
}// Company facade
