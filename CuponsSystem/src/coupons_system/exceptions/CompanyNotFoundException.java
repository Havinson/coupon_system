package coupons_system.exceptions;

public class CompanyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompanyNotFoundException() {
		super();
	}// c-tor
	
	public CompanyNotFoundException(String message) {
		super(message);
	}
}//CompanyNotFoundException
