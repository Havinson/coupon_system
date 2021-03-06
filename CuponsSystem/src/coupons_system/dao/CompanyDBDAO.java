package coupons_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupons_system.javabeans.Company;
import coupons_system.javabeans.Coupon;
import coupons_system.utils.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {
	private ConnectionPool _pool;

	public CompanyDBDAO() {
		_pool = ConnectionPool.getConnectionPool();
	}// c-tor

	@Override
	public void createCompany(Company company) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"INSERT INTO Company (ID, CompanyName, Password, Email) " + "VALUES (?, ?, ?, ?)");
			prepStm.setLong(1, company.getId());
			prepStm.setString(2, company.getCompanyName());
			prepStm.setString(3, company.getPassword());
			prepStm.setNString(4, company.getEmail());
			prepStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}

	}// create company

	@Override
	public void removeCompany(Company comp) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("DELETE FROM Company WHERE ID = ?");
			prepStm.setLong(1, comp.getId());
			prepStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
	}// remove company

	@Override
	public void updateCompany(Company comp) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("UPDATE Company SET " + "ID = ?, " + "CompanyName = ?," + "Password = ?,"
					+ "Email = ?" + " WHERE ID = ?;");
			prepStm.setLong(1, comp.getId());
			prepStm.setString(2, comp.getCompanyName());
			prepStm.setString(3, comp.getPassword());
			prepStm.setString(4, comp.getEmail());
			prepStm.setLong(5, comp.getId());
			prepStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
	}// update company

	@Override
	public Company getCompany(long id) {
		Company company = null;
		PreparedStatement prepStm = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Company WHERE ID = ?;");
			prepStm.setLong(1, id);
			resultSet = prepStm.executeQuery();
			resultSet.next();
			company = new Company(resultSet.getLong("ID"), resultSet.getString("CompanyName"),
					resultSet.getString("Password"), resultSet.getString("Email"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return company;
	}// get company

	@Override
	public Collection<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT * FROM Company;");
			while (resultSet.next()) {
				companies.add(new Company(resultSet.getLong("ID"), resultSet.getString("CompanyName"),
						resultSet.getString("Password"), resultSet.getString("Email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return companies;
	}// get companies

	@Override
	public Collection<Coupon> getCuopons(Company company) {
		List<Coupon> coupons = new ArrayList<>();
		PreparedStatement prepStm = null;
		ResultSet resultSet;
		Connection conn = null;
		CouponDBDAO couponDb = new CouponDBDAO();
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT Coupon_ID FROM CompanyCoupon WHERE Company_ID = ?");
			prepStm.setLong(1, company.getId());
			resultSet = prepStm.executeQuery();
			while (resultSet.next()) {
				coupons.add(couponDb.getCoupon(resultSet.getLong("Coupon_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return coupons;
	}// get coupons

	@Override
	public boolean login(String companyName, String password) {
		boolean ret = false;
		List<Company> allCompanies = (ArrayList<Company>) getAllCompanies();
		for (Company curr : allCompanies) {
			if (curr.getCompanyName().equalsIgnoreCase(companyName) && curr.getPassword().equals(password)) {
				ret = true;
			}
		}
		return ret;
	}// login

	@Override
	public Company getCompanyByLogin(String companyName, String password) {
		Company ret = null;
		List<Company> allCompanies = (ArrayList<Company>) getAllCompanies();
		for (Company curr : allCompanies) {
			if (curr.getCompanyName().equals(companyName) && curr.getPassword().equals(password)) {
				ret = curr;
			}
		}
		return ret;
	}// get logged in company

	@Override
	public boolean checkCompanyExisting(Company company) {
		Connection conn = null;
		boolean returnValue = false;
		ResultSet result;
		try {
			conn = _pool.getConnection();
			PreparedStatement prepStm = conn.prepareStatement("SELECT ID FROM Company WHERE ID = ?");
			prepStm.setLong(1, company.getId());
			result = prepStm.executeQuery();
			if (result.next()) {
				returnValue = true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return returnValue;
	}
}// Company DB DAO
