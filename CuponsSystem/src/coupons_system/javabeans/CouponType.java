package coupons_system.javabeans;

public enum CouponType {
	RESTOURANTS ("Restourants"),
	ELECTRICITY ("Electricity"),
	FOOD ("Food"),
	HEALTH ("Health"),
	SPORTS ("Sports"),
	CAMPING("Camping"),
	TRAVELLING("Travelling");
	
	private final String _type;	
	private CouponType(String type) {
		_type = type;
	}// c-tor

	public String toString() {
		return _type;
	}// to string
}// Coupon type
