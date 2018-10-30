package coupons_system.exceptions;

public class CustomerAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsException() {
		super();
	}

	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
}
