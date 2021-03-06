package coupons_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.Customer;
import coupons_system.utils.ConnectionPool;

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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
	}// remove customer

	@Override
	public void updateCustomer(Customer customer) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"UPDATE Customer SET " + "ID = ?, " + "CustomerName = ?," + "Password = ?" + " WHERE ID = ?;");
			prepStm.setLong(1, customer.getId());
			prepStm.setString(2, customer.getCustomerName());
			prepStm.setString(3, customer.getPassword());
			prepStm.setLong(4, customer.getId());
			prepStm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
	}// update customer

	@Override
	public Customer getCustomer(long id) {
		Customer customer = null;
		PreparedStatement prepStm;
		ResultSet resultSet;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Customer WHERE ID =?");
			prepStm.setLong(1, id);
			resultSet = prepStm.executeQuery();
			resultSet.next();
			customer = new Customer(resultSet.getLong("ID"), resultSet.getString("CustomerName"),
					resultSet.getString("Password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return customer;
	}// get customer

	@Override
	public Collection<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn = null;

		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT * FROM Customer");
			while (resultSet.next()) {
				customers.add(new Customer(resultSet.getLong("ID"), resultSet.getString("CustomerName"),
						resultSet.getString("Password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return customers;
	}// get all customers

	@Override
	public boolean login(String customerName, String password) {
		boolean ret = false;
		List<Customer> allCustomers = (ArrayList<Customer>) getAllCustomers();
		for (Customer curr : allCustomers) {
			if (curr.getCustomerName().equalsIgnoreCase(customerName) && curr.getPassword().equals(password)) {
				ret = true;
			}
		}
		return ret;
	}// login

	@Override
	public Customer getCustomerByLogin(String customerName, String password) {
		Customer customer = null;
		List<Customer> allCustomers = (ArrayList<Customer>) getAllCustomers();
		for (Customer curr : allCustomers) {
			if (curr.getCustomerName().equals(customerName) && curr.getPassword().equals(password)) {
				customer = curr;
			}
		}
		return customer;
	}// get logged in customer

	@Override
	public void addToCustomerCoupon(Customer customer, Coupon coupon) {
		PreparedStatement stm;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("insert into CustomerCoupon (Customer_ID, Coupon_ID) values (?, ?)");
			stm.setLong(1, customer.getId());
			stm.setLong(2, coupon.get_id());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
	}// addToCustomerCoupon

	@Override
	public boolean checkCustomerExisting(Customer customer) {
		boolean ret = false;
		PreparedStatement stm;
		ResultSet result;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("select ID from customer where ID = ?");
			stm.setLong(1, customer.getId());
			result = stm.executeQuery();
			if (result.next()) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			_pool.returnConnection(conn);
		}
		return ret;
	}
}// Customer DBDAO
