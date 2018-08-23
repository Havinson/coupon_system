package coupons_system.facades;

import java.util.Collection;

import coupons_system.dao.CompanyDAO;
import coupons_system.dao.CompanyDBDAO;
import coupons_system.dao.CustomerDAO;
import coupons_system.dao.CustomerDBDAO;
import coupons_system.exceptions.ClientNotFoundException;
import coupons_system.javabeans.Company;
import coupons_system.javabeans.Customer;
/** 
 * @author Igor Khavinson
 *	
 */
public class AdminFacade implements CouponClientFacade {
	private CompanyDAO _companyDao;
	private CustomerDAO _customerDao;
	
	/**
	 * Class constructor
	 * 
	 */
	public AdminFacade() {
		_companyDao = new CompanyDBDAO();
		_customerDao = new CustomerDBDAO();
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
		_companyDao.createCompany(company);
	}// create company
	/**
	 * Removes a specified company from database
	 * @param Company object
	 */
	public void removeCompany(Company company) {
		_companyDao.removeCompany(company);
	}// remove company
	/**
	 * Updates a specified company in the database
	 * @param Company object
	 */
	public void updateCompany(Company company) {
		_companyDao.updateCompany(company);
	}//update company
	/**
	 * Returns a company from database by Id number
	 * @param long id
	 * @return Company object
	 */
	public Company getCompany(long id) {
		return _companyDao.getCompany(id);
	}// get company
	/**
	 * Returns collection of all companies from the database
	 * @return 
	 */
	public Collection<Company> getAllCompanies(){
		return _companyDao.getAllCompanies();
	}// get all companies
	/**
	 * Creates new customer in the database
	 * @param customer object
	 */
	public void createCustomer(Customer customer) {
		_customerDao.createCustomer(customer);
	}// create customer
	/**
	 * Removes customer from the database
	 * @param customer object
	 */
	public void removeCustomer(Customer customer) {
		_customerDao.removeCustomer(customer);
	}// remove customer
	/**
	 * Updates customer in the database
	 * @param customer object
	 */
	public void updateCustomer(Customer customer) {
		_customerDao.updateCustomer(customer);
	}// update customer
	/**
	 * Returns customer object by Id number
	 * @param long id
	 * @return customer object
	 */
	public Customer getCustomer(long id) {
		return _customerDao.getCustomer(id);
	}// get customer
	/**
	 * Returns collection of all of customers
	 * @return Collection<Customer>
	 */
	public Collection<Customer> getAllCustomers() {
		return _customerDao.getAllCustomers();	
	}// get all customers
	
	
}// Admin Facade
