package coupons_system.javabeans;

import java.util.Collection;

public class Customer {
	private long _id;
	private String _customerName;
	private String _password;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_customerName() {
		return _customerName;
	}

	public void set_customerName(String _customerName) {
		this._customerName = _customerName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public Collection<Coupon> get_coupons() {
		return _coupons;
	}

	public void set_coupons(Collection<Coupon> _coupons) {
		this._coupons = _coupons;
	}

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
