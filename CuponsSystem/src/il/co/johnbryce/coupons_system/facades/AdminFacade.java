package il.co.johnbryce.coupons_system.facades;

import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CompanyDAO;
import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;
import il.co.johnbryce.coupons_system.dao.CouponDAO;
import il.co.johnbryce.coupons_system.dao.CouponDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Customer;

public class AdminFacade implements CouponClientFacade {
	private CompanyDAO _companies;
	private CouponDAO _coupons;
	private CustomerDAO _customers;
	
	
	public AdminFacade() {
		_companies = new CompanyDBDAO();
		_coupons = new CouponDBDAO();
		_customers = new CustomerDBDAO();
	}// c-tor
	
	@Override
	public CouponClientFacade login(String userName, String password, ClientType type) {
		AdminFacade ret = null;
		if(userName == "Admin" && password == "12345") {
			ret = this;
		}
		return ret;
	}// login

	public void createCompany(Company company) {
		_companies.createCompany(company);
	}// create company
	
	public void removeCompany(Company company) {
		_companies.removeCompany(company);
	}// remove company
	
	public void updateCompany(Company company) {
		_companies.updateCompany(company);
	}//update company
	
	public Company getCompany(long id) {
		return _companies.getCompany(id);
	}// get company
	
	public Collection<Company> getAllCompanies(){
		return _companies.getAllCompanies();
	}// get all companies
	
	public void createCustomer(Customer customer) {
		_customers.createCustomer(customer);
	}// create customer
	
	public void removeCustomer(Customer customer) {
		_customers.removeCustomer(customer);
	}// remove customer
	
	public void updateCustomer(Customer customer) {
		_customers.updateCustomer(customer);
	}// update customer
	
	public Customer getCustomer(long id) {
		return _customers.getCustomer(id);
	}// get customer
	
	public Collection<Customer> getAllCustomers() {
		return _customers.getAllCustomers();	
	}// get all customers
	
	
}// Admin Facade
