package il.co.johnbryce.coupons_system.javabeans;

import java.util.Date;

public class Coupon {
	private long _id;
	private String _title;
	private Date _startDate;
	private Date _endDate;
	private int _amount;
	private CouponType _type;
	private String _message;
	private double _price;
	private String _image;

	public Coupon(long id, String title, Date startDate, Date endDate, int amount, String type, String message,
			double price, String image) {
		_id = id;
		_title = title;
		_startDate = startDate;
		_endDate = endDate;
		_amount = amount;
		_type = CouponType.valueOf(type);
		_message = message;
		_price = price;
		_image = image;
	}// c-tor

	public long get_id() {
		return _id;
	}// get id

	public String get_title() {
		return _title;
	}// get title

	public Date get_startDate() {
		return _startDate;
	}// get start date

	public Date get_endDate() {
		return _endDate;
	}// get end date

	public int get_amount() {
		return _amount;
	}// get amount

	public CouponType get_type() {
		return _type;
	}// get type

	public String get_message() {
		return _message;
	}// get message

	public double get_price() {
		return _price;
	}// get price

	public String get_image() {
		return _image;
	}// get image

	public void set_id(long id) {
		_id = id;
	}// set id

	public void set_title(String title) {
		_title = title;
	}// set title

	public void set_startDate(Date startDate) {
		_startDate = startDate;
	}// set start date

	public void set_endDate(Date endDate) {
		_endDate = endDate;
	}// set end date

	public void set_amount(int amount) {
		_amount = _amount + amount;
	}// set amount

	public void set_type(CouponType type) {
		_type = type;
	}// set type

	public void set_message(String message) {
		_message = message;
	}// set message

	public void set_price(double price) {
		_price = price;
	}// set price

	public void set_image(String image) {
		_image = image;
	}// set image

	@Override
	public String toString() {
		return _title + " " + _id + " " + ", Starts in: " + _startDate + ", expired date: " + _endDate + ", Type: " + _type;
	}// to String

} // Coupon