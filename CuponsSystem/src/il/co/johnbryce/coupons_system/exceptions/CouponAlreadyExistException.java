package il.co.johnbryce.coupons_system.exceptions;

public class CouponAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CouponAlreadyExistException() {
		super();
	}// c-tor
	
	public CouponAlreadyExistException(String message) {
		super(message);
	}// c-tor
}// CouponAlreadyExistException
