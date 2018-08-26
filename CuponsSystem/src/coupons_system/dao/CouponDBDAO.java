package coupons_system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupons_system.javabeans.Coupon;
import coupons_system.javabeans.CouponType;
import coupons_system.javabeans.Customer;
import coupons_system.utils.ConnectionPool;

public class CouponDBDAO implements CouponDAO {
	private ConnectionPool _pool;

	public CouponDBDAO() {
		_pool = ConnectionPool.getConnectionPool();
	}// c-tor

	@Override
	public void createCoupon(Coupon coupon) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"INSERT INTO Coupon (ID, Title, StartDate, EndDate, Amount, Type, Message, Price, Image)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			prepStm.setLong(1, coupon.get_id());
			prepStm.setString(2, coupon.get_title());
			prepStm.setDate(3, new Date(coupon.get_startDate().getTime()));
			prepStm.setDate(4, new Date(coupon.get_endDate().getTime()));
			prepStm.setInt(5, coupon.get_amount());
			prepStm.setString(6, coupon.get_type().name());
			prepStm.setString(7, coupon.get_message());
			prepStm.setDouble(8, coupon.get_price());
			prepStm.setString(9, coupon.get_image());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// create Coupon

	@Override
	public void removeCoupon(Coupon coupon) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("DELETE FROM Coupon WHERE ID = ?");
			prepStm.setLong(1, coupon.get_id());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// removeCoupon

	@Override
	public void updateCoupon(Coupon coupon) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement(
					"UPDATE Coupon SET " + "ID = ?, " + "Title = ?," + "StartDate = ?," + "EndDate = ?," + "Amount = ?,"
							+ "Type = ?," + "Message = ?," + "Price = ?," + "Image = ?" + " WHERE ID = ?;");
			prepStm.setLong(1, coupon.get_id());
			prepStm.setString(2, coupon.get_title());
			prepStm.setDate(3, new Date(coupon.get_startDate().getTime()));
			prepStm.setDate(4, new Date(coupon.get_endDate().getTime()));
			prepStm.setInt(5, coupon.get_amount());
			prepStm.setString(6, coupon.get_type().name());
			prepStm.setString(7, coupon.get_message());
			prepStm.setDouble(8, coupon.get_price());
			prepStm.setString(9, coupon.get_image());
			prepStm.setLong(10, coupon.get_id());
			prepStm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// Update coupon

	@Override
	public Coupon getCoupon(long id) {
		Coupon cup = null;
		PreparedStatement prepStm = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Coupon WHERE ID =?");
			prepStm.setLong(1, id);
			resultSet = prepStm.executeQuery();
			resultSet.next();
			cup = new Coupon(resultSet.getLong("ID"), resultSet.getString("Title"), resultSet.getDate("StartDate"),
					resultSet.getDate("EndDate"), resultSet.getInt("Amount"), resultSet.getString("Type"),
					resultSet.getString("Message"), resultSet.getDouble("Price"), resultSet.getString("Image"));
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cup;
	}// get coupon

	@Override
	public synchronized Collection<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn = null;

		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT * FROM Coupon");
			while (resultSet.next()) {
				coupons.add(new Coupon(resultSet.getLong("ID"), resultSet.getString("Title"),
						resultSet.getDate("StartDate"), resultSet.getDate("EndDate"), resultSet.getInt("Amount"),
						resultSet.getString("Type"), resultSet.getString("Message"), resultSet.getDouble("Price"),
						resultSet.getString("Image")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return coupons;
	}// get all coupons

	@Override
	public Collection<Coupon> getCouponByType(CouponType type) {
		List<Coupon> coupons = new ArrayList<>();
		PreparedStatement prepStm = null;
		ResultSet resultSet;
		Connection conn = null;

		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("SELECT * FROM Coupon WHERE Type =?");
			prepStm.setString(1, type.name());
			resultSet = prepStm.executeQuery();
			while (resultSet.next()) {
				coupons.add(new Coupon(resultSet.getLong("ID"), resultSet.getString("Title"),
						resultSet.getDate("StartDate"), resultSet.getDate("EndDate"), resultSet.getInt("Amount"),
						resultSet.getString("Type"), resultSet.getString("Message"), resultSet.getDouble("Price"),
						resultSet.getString("Image")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupons;
	}// get coupons by type

	@Override
	public boolean checkCouponExisting(Coupon coupon, Customer customer) {
		boolean ret = false;
		Connection conn;
		ResultSet resultSet;
		PreparedStatement prepStm;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("select Coupon_ID from CustomerCoupon where Customer_ID = ?");
			prepStm.setLong(1, customer.getId());
			resultSet = prepStm.executeQuery();
			if (resultSet.next()) {
				ret = true;
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}// check coupon existing

	@Override
	public int checkCouponAmount(Coupon coupon) {
		int amount = 0;
		Connection conn;
		ResultSet resultSet;
		PreparedStatement prepStm;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("select Amount from Coupon where ID = ?;");
			prepStm.setLong(1, coupon.get_id());
			resultSet = prepStm.executeQuery();
			resultSet.next();
			amount = resultSet.getInt("Amount");

			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}// check coupon amount

	@Override
	public Collection<Coupon> getAllCompanyCoupons(long companyId) {
		List<Coupon> coupons = new ArrayList<>();
		PreparedStatement stm;
		ResultSet resultSet;
		Connection conn = null;

		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("SELECT Coupon_ID FROM CompanyCoupon where Company_ID = ?");
			stm.setLong(1, companyId);
			resultSet = stm.executeQuery();
			while (resultSet.next()) {
				coupons.add(this.getCoupon(resultSet.getLong("Coupon_ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return coupons;
	}// get all company coupons

	@Override
	public void addCouponAndCompanyJoin(Coupon coupon, long companyId) {
		PreparedStatement stm;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("insert into CompanyCoupon (Coupon_ID, Company_ID) values (?, ?);");
			stm.setLong(1, coupon.get_id());
			stm.setLong(2, companyId);
			stm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// addCouponAndCompanyJoin

	@Override
	public void removeCouponAndCompanyJoin(Coupon coupon, long companyId) {
		PreparedStatement stm;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			stm = conn.prepareStatement("delete from CompanyCoupon where Coupon_ID = ? and Company_ID = ?;");
			stm.setLong(1, coupon.get_id());
			stm.setLong(2, companyId);
			stm.executeUpdate();
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// removeCouponAndCompanyJoin

	/**
	 * Returns true if coupon not exist in this company Returns false if coupon
	 * already exist in this company
	 * 
	 * @param Coupon object
	 * @param        long company ID
	 */
	@Override
	public boolean checkCompanyCouponExisting(Coupon coupon, long companyId) {
		boolean ret = true;
		Connection conn;
		ResultSet resultSet;
		PreparedStatement prepStm;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("select Coupon_ID from CompanyCoupon where Company_ID = ?;");
			prepStm.setLong(1, companyId);
			resultSet = prepStm.executeQuery();
			while (resultSet.next()) {
				if (coupon.get_id() == resultSet.getLong("Coupon_ID")) {
					ret = false;
				}
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}// checkCompanyCouponExisting
}// Coupon DBDAO
