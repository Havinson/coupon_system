package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;

public interface CouponClientFacade {
	public CouponClientFacade login(String userName, String password, ClientType type) throws ClientNotFoundException;
}
