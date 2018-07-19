package coupons_system.facades;

import coupons_system.exceptions.ClientNotFoundException;

public interface CouponClientFacade {
	public CouponClientFacade login(String userName, String password, ClientType type) throws ClientNotFoundException;
}
