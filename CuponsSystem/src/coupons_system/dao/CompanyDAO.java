package coupons_system.dao;

import java.util.Collection;

import coupons_system.exceptions.CompanyNotFoundException;
import coupons_system.javabeans.Company;
import coupons_system.javabeans.Coupon;

public interface CompanyDAO {
	/**
	 * Create new company in database.
	 * 
	 * @param Company company
	 */
	public void createCompany(Company company);

	/**
	 * remove existing company from database.
	 * 
	 * @param Company company
	 */
	public void removeCompany(Company company);

	/**
	 * update existing company in database.
	 * 
	 * @param Company company
	 */
	public void updateCompany(Company company);

	/**
	 * get company from database
	 * 
	 * @param long id
	 * @return Company
	 */
	public Company getCompany(long id);

	/**
	 * return collection of all companies from database
	 * 
	 * @return Collection<Company>
	 */
	public Collection<Company> getAllCompanies();

	/**
	 * return collection of all coupons of specified company
	 * 
	 * @param Company company
	 * @return
	 */
	public Collection<Coupon> getCuopons(Company company);

	/**
	 * login into system
	 * 
	 * @param String companyName
	 * @param String password
	 * @return true if companyName and password are valid. Else return false
	 */
	public boolean login(String companyName, String password);

	/**
	 * return company with specified name and password
	 * 
	 * @param String companyName
	 * @param String password
	 * @return Company
	 * @throws CompanyNotFoundException
	 */
	public Company getCompanyByLogin(String companyName, String password);

	/**
	 * The method check if the company exists
	 * 
	 * @param company
	 * @return: true if the company exist, false if the company is not exists
	 */
	public boolean checkCompanyExisting(Company company);
}// company DAO
