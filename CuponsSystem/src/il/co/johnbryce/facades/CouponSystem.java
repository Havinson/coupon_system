package il.co.johnbryce.facades;

import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;

public class CouponSystem {
	private static CouponSystem _instance = new CouponSystem();
	
	private CouponSystem() {
		
	}// c-tor
	
	public static CouponSystem getCouponSystem() {
		return _instance;
	}// get instance
	
	public CouponClientFacade login(String userName, String password, ClientType type) {
		CouponClientFacade client;
		switch (type) {
		case ADMIN:
			client = new AdminFacade().login(userName, password, type);
			break;
		case COMPANY:
			CompanyDAO company = new CompanyDBDAO();
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
