package coupons_system.exceptions;

public class CouponNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponNotFoundException() {
		super();
	}

	public CouponNotFoundException(String message) {
		super(message);
	}
}
