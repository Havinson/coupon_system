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
	public Collection<Coupon> getCoupons(Customer customer);
	public boolean login(String customerName, String password);
	public Customer getCustomerByLogin(String customerName, String password);
	public void addToCustomerCoupon(Customer customer, Coupon coupon);
}// Customer DAO