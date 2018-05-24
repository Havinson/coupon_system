package il.co.johnbryce.coupons_system.dao;

import java.util.Collection;


public interface CompanyDAO {
	public void createCompany(Company comp);
	public void removeCompany(Company comp);
	public void updateCompany(Company comp);
	public Company getCompany(long id);
	public Collection<Company> getAllCompanies();
	public Collection<Coupon> getCuopons(Company comp);
	public boolean login(String companyName, String password);
}// company DAO
