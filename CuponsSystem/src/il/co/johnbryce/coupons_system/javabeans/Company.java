package il.co.johnbryce.coupons_system.javabeans;

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
	}//get id

	public String getCompanyName() {
		return _companyName;
	}// get Company name

	public String getPassword() {
		return _password;
	}// get password

	@Override
	public String toString() {
		return _id + " " + _companyName;
	}// to String
	
	
}// Company
