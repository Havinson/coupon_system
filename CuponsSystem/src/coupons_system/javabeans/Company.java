package coupons_system.javabeans;

import java.util.Collection;

public class Company {
	private long _id;
	private String _companyName;
	private String _password;
	private String _email;
	private Collection<Coupon> _coupons;

	public Company(long id, String companyName, String password, String email) {
		_id = id;
		_companyName = companyName;
		_password = password;
		_email = email;

	}// c-tor

	public String getEmail() {
		return _email;
	}// get email

	public void setEmail(String email) {
		_email = email;
	}// set email

	public Collection<Coupon> getCoupons() {
		return _coupons;
	}// get coupons

	public void setCoupons(Collection<Coupon> coupons) {
		_coupons = coupons;
	}// set coupons

	public long getId() {
		return _id;
	}// get id

	public String getCompanyName() {
		return _companyName;
	}// get Company name

	public String getPassword() {
		return _password;
	}// get password

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_companyName() {
		return _companyName;
	}

	public void set_companyName(String _companyName) {
		this._companyName = _companyName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public Collection<Coupon> get_coupons() {
		return _coupons;
	}

	public void set_coupons(Collection<Coupon> _coupons) {
		this._coupons = _coupons;
	}

	@Override
	public String toString() {
		return _id + " " + _companyName;
	}// to String

}// Company
