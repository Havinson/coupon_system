package il.co.johnbryce.coupons_system.facades;

import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;

public class CouponSystem {
	private static CouponSystem _instance = new CouponSystem();
	
	private CouponSystem() {
		
	}// c-tor
	
	public static CouponSystem getCouponSystem() {
		return _instance;
	}// get instance
	
	public CouponClientFacade login(String userName, String password, ClientType type) throws ClientNotFoundException {
		CouponClientFacade client;
		switch (type) {
		case ADMIN:
			client = new AdminFacade().login(userName, password, type);
			break;
		case COMPANY:
			client = new CompanyFacade().login(userName, password, type);
			break;
		case CUSTOMER:
			client = new CustomerFacade().login(userName, password, type);
			break;
		default:
			client = null;
			System.out.println("The client is not exist!");
			break;
		}
		return client;
	}// login
	
	public void shutdown() {
		
	}// shutdown
}// CouponSystem
