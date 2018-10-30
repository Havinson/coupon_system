package coupons_system.facades;

import java.util.Collection;

import coupons_system.dao.CompanyDAO;
import coupons_system.dao.CompanyDBDAO;
import coupons_system.dao.CouponDAO;
import coupons_system.dao.CouponDBDAO;
import coupons_system.dao.CustomerDAO;
import coupons_system.dao.CustomerDBDAO;
import coupons_system.exceptions.ClientNotFoundException;
import coupons_system.exceptions.CompanyExistsException;
import coupons_system.exceptions.CompanyNotFoundException;
import coupons_system.exceptions.CouponNotFoundException;
import coupons_system.exceptions.CustomerAlreadyExistsException;
import coupons_system.exceptions.CustomerNotFoundException;
import coupons_system.javabeans.Company;
import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.Customer;

/**
 * @author Igor Khavinson
 * 
 */
public class AdminFacade implements CouponClientFacade {
	private CompanyDAO _companyDao;
	private CustomerDAO _customerDao;
	private CouponDAO _couponDao;

	/**
	 * Class constructor
	 * 
	 */
	public AdminFacade() {
		_companyDao = new CompanyDBDAO();
		_customerDao = new CustomerDBDAO();
		_couponDao = new CouponDBDAO();
	}// c-tor

	@Override
	/**
	 * @param Strign     user name
	 * @param String     password
	 * @param ClientType type of client
	 * @return AdminFacade
	 */
	public AdminFacade login(String userName, String password, ClientType type) throws ClientNotFoundException {
		AdminFacade ret = null;
		if (userName.equalsIgnoreCase("Admin") && password.equals("12345")) {
			ret = this;
		} else {
			throw new ClientNotFoundException("User name or password is incorrect!");
		}
		return ret;
	}// login

	/**
	 * Creates new company
	 * 
	 * @param company object
	 * @throws CompanyExistsException
	 */
	public void createCompany(Company company) throws CompanyExistsException {
		if (!_companyDao.checkCompanyExisting(company)) {
			_companyDao.createCompany(company);
		} else {
			throw new CompanyExistsException("The company already exists");
		}

	}// create company

	/**
	 * Removes a specified company from database
	 * 
	 * @param Company object
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public void removeCompany(Company company) throws CompanyNotFoundException {
		Collection<Coupon> coupons = company.getCoupons();
		if (_companyDao.checkCompanyExisting(company)) {

			if (coupons != null) {
				for (Coupon curr : coupons) {
					if (curr != null) {
						_couponDao.removeCoupon(curr);
						_couponDao.removeCouponFromJoinTables(curr);
					}
				}
			}
			_companyDao.removeCompany(company);
			System.out.println("The " + company.getCompanyName() + " was removed!");
		} else {
			throw new CompanyNotFoundException("The company that you want to remove is not exist in the system!");
		}
	}// remove company

	/**
	 * Updates a specified company in the database
	 * 
	 * @param Company object
	 * @throws CompanyNotFoundException
	 */
	public void updateCompany(Company company) throws CompanyNotFoundException {
		if (_companyDao.checkCompanyExisting(company)) {
			_companyDao.updateCompany(company);
		} else {
			throw new CompanyNotFoundException("The company that you want to update is not exists in the system!");
		}
	}// update company

	/**
	 * Returns a company from database by Id number
	 * 
	 * @param long id
	 * @return Company object
	 * @throws CompanyNotFoundException
	 */
	public Company getCompany(long id) throws CompanyNotFoundException {
		Company company = _companyDao.getCompany(id);
		if (company != null) {
			return company;
		} else {
			throw new CompanyNotFoundException("The company is not exists in the system!");
		}

	}// get company

	/**
	 * Returns collection of all companies from the database
	 * 
	 * @return
	 */
	public Collection<Company> getAllCompanies() throws CompanyNotFoundException {
		Collection<Company> companies = _companyDao.getAllCompanies();
		if (companies != null) {
			return companies;
		} else {
			throw new CompanyNotFoundException("There is no companies in the system!");
		}
	}// get all companies

	/**
	 * Creates new customer in the database
	 * 
	 * @param customer object
	 * @throws CustomerAlreadyExistsException
	 */
	public void createCustomer(Customer customer) throws CustomerAlreadyExistsException {
		if (_customerDao.checkCustomerExisting(customer)) {
			throw new CustomerAlreadyExistsException(
					"The customer that you want to create already exists in the system!");
		} else {
			_customerDao.createCustomer(customer);
		}
	}// create customer

	/**
	 * Removes customer from the database
	 * 
	 * @param customer object
	 * @throws CustomerNotFoundException
	 */
	public void removeCustomer(Customer customer) throws CustomerNotFoundException {
		if (_customerDao.checkCustomerExisting(customer)) {
			_customerDao.removeCustomer(customer);
		} else {
			throw new CustomerNotFoundException("The customer that you want to remove is not exists in the system!");
		}
	}// remove customer

	/**
	 * Updates customer in the database
	 * 
	 * @param customer object
	 * @throws CustomerNotFoundException
	 */
	public void updateCustomer(Customer customer) throws CustomerNotFoundException {
		if (_customerDao.checkCustomerExisting(customer)) {
			_customerDao.updateCustomer(customer);
		} else {
			throw new CustomerNotFoundException("The custumer that you want to update is not exsits in the system!");
		}

	}// update customer

	/**
	 * Returns customer object by Id number
	 * 
	 * @param long id
	 * @return customer object
	 * @throws CustomerNotFoundException
	 */
	public Customer getCustomer(long id) throws CustomerNotFoundException {
		Customer customer = _customerDao.getCustomer(id);
		if (customer != null) {
			return customer;
		} else {
			throw new CustomerNotFoundException("The customer not found!");
		}
	}// get customer

	/**
	 * Returns collection of all of customers
	 * 
	 * @return Collection<Customer>
	 */
	public Collection<Customer> getAllCustomers() throws CustomerNotFoundException {
		Collection<Customer> customers = _customerDao.getAllCustomers();
		if (customers != null) {
			return customers;
		} else {
			throw new CustomerNotFoundException("There is no custumers in the system!");
		}
	}// get all customers

}// Admin Facade
