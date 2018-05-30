package il.co.johnbryce.coupons_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import il.co.johnbryce.coupons_system.javabeans.Company;
import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.utils.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {
	private ConnectionPool _pool;
	private Company _currentCompany;

	public CompanyDBDAO() {
		_pool = ConnectionPool.getConnectionPool();
	}// c-tor

	@Override
	public void createCompany(Company comp) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"INSERT INTO Company (ID, CompanyName, Password, Email) " + "VALUES (?, ?, ?, ?)");
			prepStm.setLong(1, comp.getId());
			prepStm.setString(2, comp.getCompanyName());
			prepStm.setString(3, comp.getPassword());
			prepStm.setNString(4, comp.getEmail());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("A company was no created!");
			System.out.println("Maybe this company already exist in database,");
			System.out.println("or You have trouble whith connection to sql server.");
			System.out.println("Please check if a company is exist or not.");
		} catch (Exception e) {
			// TODO take care for exception
			e.printStackTrace();
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
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("A company has not been deleted!");
			System.out.println("Maybe the company is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you company  ID and your`s connection.");
		} catch (Exception e) {
			// TODO: take care of exception
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
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("A company has not been updated!");
			System.out.println("Maybe a company is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you company ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please turn to your administrtor.");
			// TODO: take care of exception
		}
	}// update company

	@Override
	public Company getCompany(long id) {
		Company comp = null;
		PreparedStatement prepStm = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Company WHERE ID =?");
			prepStm.setLong(1, id);
			resultSet = prepStm.executeQuery();
			resultSet.next();
			comp = new Company(resultSet.getLong("CompanyID"), resultSet.getString("CompanyName"),
					resultSet.getString("Password"), resultSet.getString("Email"));
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("A company whith this ID is not exist!");
			System.out.println("Maybe the company is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you company ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please torn to administrator!");
		}
		return comp;
	}// get company

	@Override
	public Collection<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn;

		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT ID FROM Company");
			while (resultSet.next()) {
				companies.add(this.getCompany(resultSet.getLong("ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("You have trouble with connection to database.");
			System.out.println("Please, check your connection.");
		} catch (Exception e) {
			System.out.println("This is not SQL server problem, please torn to administrator");
		}
		return companies;
	}// get companies

	@Override
	public Collection<Coupon> getCuopons(Company comp) {
		List<Coupon> coupons = new ArrayList<>();
		PreparedStatement prepStm = null;
		ResultSet resultSet;
		Connection conn = null;
		CouponDBDAO couponDb = new CouponDBDAO();

		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT Coupon_ID FROM CompanyCoupon WHERE Company_ID = ?");
			prepStm.setLong(1, comp.getId());
			resultSet = prepStm.executeQuery();
			while (resultSet.next()) {
				coupons.add(couponDb.getCoupon(resultSet.getLong("ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("The are is no coupons of this company.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you company coupons and your`s connection.");
		} catch (Exception e) {
			// TODO: take care of exception
			System.out.println("This is not SQL server problem, please torn to administrator");
		}
		return coupons;
	}// get coupons

	@Override
	public boolean login(String companyName, String password) {
		boolean ret = false;
		PreparedStatement prepStm = null;
		ResultSet resultSet;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("select * from Company where CompanyName = ?, password = ?;");
			prepStm.setString(1, companyName);
			prepStm.setString(2, password);
			resultSet = prepStm.executeQuery();
			if (resultSet.next()) {
				_currentCompany = new Company(resultSet.getLong("ID"), resultSet.getString("CompanyName"),
						resultSet.getString("Password"), resultSet.getString("Email"));
			}else {
				System.out.println("username or password are not correct!");
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
//			TODO: take care of SQLexception
		} catch (Exception e) {
//			TODO: take care of Exception
		}
		return ret;
	}// login

	@Override
	public Company getLoggedInCompany(String companyName, String password) {
		if(login(companyName, password)) {
			return _currentCompany;
		}
		return null;
	}// get logged in company
}// Company DB DAO
