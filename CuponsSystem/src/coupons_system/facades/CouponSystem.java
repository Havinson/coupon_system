package coupons_system.facades;

import coupons_system.exceptions.ClientNotFoundException;
import coupons_system.utils.ConnectionPool;
import coupons_system.utils.DailyCouponExpirationTask;

public class CouponSystem {
	private static CouponSystem _instance = new CouponSystem();
	Thread dailyExpirationTask = new Thread(new DailyCouponExpirationTask());

	private CouponSystem() {
		dailyExpirationTask.start();
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
			throw new ClientNotFoundException("Login failed!");
		}
		return client;
	}// login

	public void shutdown() {
		dailyExpirationTask.interrupt();
		ConnectionPool.getConnectionPool().closeAllConnections();
	}// shutdown
}// CouponSystem
