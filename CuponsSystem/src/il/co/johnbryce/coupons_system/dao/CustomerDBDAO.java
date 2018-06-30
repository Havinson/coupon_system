package il.co.johnbryce.coupons_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.Customer;
import il.co.johnbryce.coupons_system.utils.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {
	private ConnectionPool _pool;

	public CustomerDBDAO() {
		_pool = ConnectionPool.getConnectionPool();
	}// c-tor

	@Override
	public void createCustomer(Customer customer) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("INSERT INTO Customer (ID, CustomerName, Password) " + "VALUES (?, ?, ?)");
			prepStm.setLong(1, customer.getId());
			prepStm.setString(2, customer.getCustomerName());
			prepStm.setString(3, customer.getPassword());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("A customer has not been created!");
			System.out.println("Maybe this customer already exist in database,");
			System.out.println("or You have trouble whith connection to sql server.");
			System.out.println("Please check if a customer is exist or not.");
		} catch (Exception e) {
			// TODO take care for exception
			e.printStackTrace();
		}
	}// create customer

	@Override
	public void removeCustomer(Customer customer) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("DELETE FROM Customer WHERE ID = ?");
			prepStm.setLong(1, customer.getId());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("A customer has not been deleted!");
			System.out.println("Maybe the customer is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check customer ID and your`s connection.");
		} catch (Exception e) {
			// TODO: take care of exception
		}
	}// remove customer

	@Override
	public void updateCustomer(Customer customer) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"UPDATE Customer SET " + "ID = ?, " + "CompanyName = ?," + "Password = ?" + " WHERE ID = ?;");
			prepStm.setLong(1, customer.getId());
			prepStm.setString(2, customer.getCustomerName());
			prepStm.setString(3, customer.getPassword());
			prepStm.setLong(4, customer.getId());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("A customer has not been updated!");
			System.out.println("Maybe a customer is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you customer ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please turn to your administrtor.");
			// TODO: take care of exception
		}
	}// update customer

	@Override
	public Customer getCustomer(long id) {
		Customer customer = null;
		PreparedStatement prepStm;
		ResultSet resultSet;
		Connection conn;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Customer WHERE ID =?");
			prepStm.setLong(1, id);
			resultSet = prepStm.executeQuery();
			resultSet.next();
			customer = new Customer(resultSet.getLong("CustomerID"), resultSet.getString("CustomerName"),
					resultSet.getString("Password"));
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}// get customer

	@Override
	public Collection<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn;

		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT ID FROM Customer");
			while (resultSet.next()) {
				customers.add(this.getCustomer(resultSet.getLong("ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}// get all customers

	@Override
	public Collection<Coupon> getCoupons(Customer customer) {
		List<Coupon> coupons = new ArrayList<>();
		PreparedStatement prepStm;
		ResultSet resultSet;
		Connection conn;
		CouponDBDAO couponDb = new CouponDBDAO();

		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT Coupon_ID FROM CustomerCoupon WHERE Customer_ID = ?");
			prepStm.setLong(1, customer.getId());
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
			System.out.println("This is not SQL server problem, please turn to administrator");
		}
		return coupons;
	}// get coupons

	@Override
	public boolean login(String customerName, String password) {
		boolean ret = false;
		List<Customer> allCustomers = (ArrayList<Customer>)getAllCustomers();
		for(Customer curr: allCustomers) {
			if(curr.getCustomerName().equalsIgnoreCase(customerName) && curr.getPassword().equals(password)) {
				ret = true;
			}
		}
		return ret;
	}// login

	@Override
	public Customer getCustomerByLogin(String customerName, String password) {
		Customer customer = null;
		List<Customer> allCustomers = (ArrayList<Customer>)getAllCustomers();
		for(Customer curr: allCustomers) {
			if (curr.getCustomerName().equals(customerName) && curr.getPassword().equals(password)) {
				customer = curr;
			}
		}
		return customer;
	}// get logged in customer
	
	public void addToCustomerCoupon(Customer customer, Coupon coupon) {
		PreparedStatement stm;
		Connection conn;
		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("insert into CustomerCuopon (Customer_ID, Coupon_ID) values (?, ?)");
			stm.setLong(1, customer.getId());
			stm.setLong(2, coupon.get_id());
			stm.executeUpdate();
			_pool.returnConnection(conn);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}// addToCustomerCoupon
}// Customer DBDAO
