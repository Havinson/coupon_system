package il.co.johnbryce.coupons_system.facades;

import java.util.Collection;

import il.co.johnbryce.coupons_system.dao.CompanyDAO;
import il.co.johnbryce.coupons_system.dao.CompanyDBDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDAO;
import il.co.johnbryce.coupons_system.dao.CustomerDBDAO;
import il.co.johnbryce.coupons_system.exceptions.ClientNotFoundException;
import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Customer;
/** 
 * @author Igor Khavinson
 *	
 */
public class AdminFacade implements CouponClientFacade {
	private CompanyDAO _companies;
	private CustomerDAO _customers;
	
	/**
	 * Class constructor
	 * 
	 */
	public AdminFacade() {
		_companies = new CompanyDBDAO();
		_customers = new CustomerDBDAO();
	}// c-tor
	
	@Override
	/**
	 * @param Strign user name
	 * @param String password
	 * @param ClientType type of client
	 * @return AdminFacade
	 */
	public AdminFacade login(String userName, String password, ClientType type) throws ClientNotFoundException{
		AdminFacade ret = null;
		if(userName.equalsIgnoreCase("Admin") && password.equals("12345")) {
			ret = this;
		}else {
			throw new ClientNotFoundException("User name or password is incorrect!");
		}
		return ret;
	}// login
	/**
	 * Creates new company
	 * @param company object
	 */
	public void createCompany(Company company) {
		_companies.createCompany(company);
	}// create company
	/**
	 * Removes a specified company from database
	 * @param Company object
	 */
	public void removeCompany(Company company) {
		_companies.removeCompany(company);
	}// remove company
	/**
	 * Updates a specified company in the database
	 * @param Company object
	 */
	public void updateCompany(Company company) {
		_companies.updateCompany(company);
	}//update company
	/**
	 * Returns a company from database by Id number
	 * @param long id
	 * @return Company object
	 */
	public Company getCompany(long id) {
		return _companies.getCompany(id);
	}// get company
	/**
	 * Returns collection of all companies from the database
	 * @return 
	 */
	public Collection<Company> getAllCompanies(){
		return _companies.getAllCompanies();
	}// get all companies
	/**
	 * Creates new customer in the database
	 * @param customer object
	 */
	public void createCustomer(Customer customer) {
		_customers.createCustomer(customer);
	}// create customer
	/**
	 * Removes customer from the database
	 * @param customer object
	 */
	public void removeCustomer(Customer customer) {
		_customers.removeCustomer(customer);
	}// remove customer
	/**
	 * Updates customer in the database
	 * @param customer object
	 */
	public void updateCustomer(Customer customer) {
		_customers.updateCustomer(customer);
	}// update customer
	/**
	 * Returns customer object by Id number
	 * @param long id
	 * @return customer object
	 */
	public Customer getCustomer(long id) {
		return _customers.getCustomer(id);
	}// get customer
	/**
	 * Returns collection of all of customers
	 * @return Collection<Customer>
	 */
	public Collection<Customer> getAllCustomers() {
		return _customers.getAllCustomers();	
	}// get all customers
	
	
}// Admin Facade
