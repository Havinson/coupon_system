package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.dao.CompanyDAO;
import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;
import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;

public class AdminFacade implements CouponClientFacade {
	private CompanyDAO _companies;
	private CouponDAO _coupons;
	private CustomerDAO _customers;
	
	
	public AdminFacade() {
		_companies = new CompanyDBDAO();
		_coupons = new CouponDBDAO();
		_customers = new CustomerDBDAO();
	}// c-tor
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		return this;
	}// login

}// Admin Facade
