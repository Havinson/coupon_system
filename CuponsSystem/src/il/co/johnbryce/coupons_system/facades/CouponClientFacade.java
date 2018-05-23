package il.co.johnbryce.coupons_system.facades;

public interface CouponClientFacade {
	public CouponClientFacade login(String userName, String password, ClientType type);
}
