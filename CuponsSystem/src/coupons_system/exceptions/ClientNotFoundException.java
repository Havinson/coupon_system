package coupons_system.exceptions;

public class ClientNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException() {
		super();
	}// c-tor
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	
}// ClientNotFoundException
