package il.co.johnbryce.coupons_system.dao;

import java.util.Collection;

import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.Customer;

public interface CustomerDAO {
	public void createCustomer(Customer customer);
	public void removeCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public Customer getCustomer(long id);
	public Collection<Customer> getAllCustomers();
	public Collection<Coupon> getCoupons(Customer customer);
	public boolean login(String customerName, String password);
	
}// Customer DAO
