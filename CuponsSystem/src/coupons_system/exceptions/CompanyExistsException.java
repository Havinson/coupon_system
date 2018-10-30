package coupons_system.exceptions;

public class CompanyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyExistsException() {
		super();
	}// c-tor

	public CompanyExistsException(String message) {
		super(message);
	}
}
