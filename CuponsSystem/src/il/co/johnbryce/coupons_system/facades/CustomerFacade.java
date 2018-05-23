package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;

public class CustomerFacade implements CouponClientFacade {
	private CouponDAO _coupons;
	
	public CustomerFacade() {
		_coupons = new CouponDBDAO();
	}// c-tor
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		return this;
	}// login

}// Customer facade
