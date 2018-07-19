package coupons_system.facades;

import java.util.ArrayList;
import java.util.Collection;

import coupons_system.dao.CompanyDAO;
import coupons_system.dao.CompanyDBDAO;
import coupons_system.dao.CouponDAO;
import coupons_system.dao.CouponDBDAO;
import coupons_system.exceptions.ClientNotFoundException;
import coupons_system.exceptions.CouponAlreadyExistException;
import coupons_system.javabeans.Company;
import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.CouponType;

public class CompanyFacade implements CouponClientFacade {
	private CouponDAO _couponDAO;
	private CompanyDAO _companyDao;
	private Company _currentCompany;
	
	public CompanyFacade(){
		_couponDAO = new CouponDBDAO();
		_companyDao = new CompanyDBDAO();
	}// c-tor
	@Override
	public CompanyFacade login(String userName, String password, ClientType type) throws ClientNotFoundException{
		CompanyFacade ret = null;
		if(_companyDao.login(userName, password)) {
			_currentCompany = _companyDao.getCompanyByLogin(userName, password);
			ret = this;

		}else {
			throw new ClientNotFoundException("user name or password is incorrect!");
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
		return _companyDao.getCuopons(_currentCompany);
	}// get all coupons
	
	public Collection<Coupon> getCouponByType(CouponType type){
		Collection<Coupon> couponsByType = new ArrayList<>();
		Collection<Coupon> allCoupons = getAllCoupons();
		for(Coupon curr: allCoupons) {
			if(curr.get_type() == type) {
				couponsByType.add(curr);
			}
		}
		return couponsByType;
	}// get coupons by type
	
	public void createCoupon(Coupon coupon) throws CouponAlreadyExistException{
		if(_couponDAO.checkCompanyCouponExisting(coupon, _currentCompany.getId())) {
		_couponDAO.createCoupon(coupon);
		_couponDAO.addCouponAndCompanyJoin(coupon, _currentCompany.getId());
		}else {
			throw new CouponAlreadyExistException("The coupon already exist!");
		}
	}//create coupon
}// Company facade
