package il.co.johnbryce.coupons_system.javabeans;

import java.util.Collection;

public class Customer {
	private long _id;
	private String _customerName;
	private String _password;
	private Collection<Coupon> _coupons;
	
	public Customer(long id, String customerName, String password) {
		_id = id;
		_customerName = customerName;
		_password = password;
	}// c-tor

	public String getPassword() {
		return _password;
	}// get password

	public void setPassword(String _password) {
		this._password = _password;
	}// set password

	public long getId() {
		return _id;
	}// get id

	public String getCustomerName() {
		return _customerName;
	}// get customer name

	public Collection<Coupon> getCoupons() {
		return _coupons;
	}// get coupons

	@Override
	public String toString() {
		return _customerName + " " + _id;
	}// to string
	
	
	
	
	
}// Customer
