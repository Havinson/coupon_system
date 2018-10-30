package coupons_system.dao;

import java.util.Collection;

import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.Customer;

public interface CustomerDAO {
	public void createCustomer(Customer customer);

	public void removeCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public Customer getCustomer(long id);

	public Collection<Customer> getAllCustomers();

	public boolean login(String customerName, String password);

	public Customer getCustomerByLogin(String customerName, String password);

	public void addToCustomerCoupon(Customer customer, Coupon coupon);

	/**
	 * 
	 * @param customer
	 * @return: true if the customer is exists and false if not exists
	 */
	public boolean checkCustomerExisting(Customer customer);
}// Customer DAO
