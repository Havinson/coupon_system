package il.co.johnbryce.coupons_system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import il.co.johnbryce.coupons_system.javabeans.Coupon;
import il.co.johnbryce.coupons_system.javabeans.CouponType;
import il.co.johnbryce.coupons_system.utils.ConnectionPool;

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
			System.out.println("A coupon has not been created!");
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
			System.out.println("A coupon has not been deleted!");
			System.out.println("Maybe the coupon is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you coupons ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please turn to your administrtor.");
		}
	}// removeCoupon

	@Override
	public void updateCoupon(Coupon coupon) {
		PreparedStatement prepStm = null;
		Connection conn = null;
		try {
			conn = _pool.getConnection();
			prepStm = conn.prepareStatement("UPDATE Coupon SET "
					+ "ID = ?, "
					+ "Title = ?,"
					+ "StartDate = ?,"
					+ "EndDate = ?,"
					+ "Amount = ?,"
					+ "Type = ?,"
					+ "Message = ?,"
					+ "Price = ?,"
					+ "Image = ?"
					+ " WHERE ID = ?;");
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
			System.out.println("A coupon has not been updated!");
			System.out.println("Maybe the coupon is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you coupons ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please turn to your administrtor.");
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
			System.out.println("A coupon whith this ID is not exist!");
			System.out.println("Maybe the coupon is not exist in the database.");
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you coupons ID and your`s connection.");
		} catch (Exception e) {
			System.out.println("It`s not SQL server problem, please torn to administrator!");
		}
		return cup;
	}// get coupon

	@Override
	public Collection<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<>();
		Statement stm;
		ResultSet resultSet;
		Connection conn = null;

		try {
			conn = _pool.getConnection();
			stm = conn.createStatement();
			resultSet = stm.executeQuery("SELECT ID FROM Coupon");
			while (resultSet.next()) {
				coupons.add(this.getCoupon(resultSet.getLong("ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("You have trouble with connection to database.");
			System.out.println("Please, check your connection.");
		} catch (Exception e) {
			System.out.println("This is not SQL server problem, please torn to administrator");
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
			prepStm = conn.prepareStatement("SELECT ID FROM Coupon WHERE Type =?");
			prepStm.setString(1, type.name());
			resultSet = prepStm.executeQuery();
			while (resultSet.next()) {
				coupons.add(this.getCoupon(resultSet.getLong("ID")));
			}
			_pool.returnConnection(conn);
		} catch (SQLException e) {
			System.out.println("The are is no coupons with type: " + type);
			System.out.println("Or you have trouble with connection to database.");
			System.out.println("Please, check you coupons type and your`s connection.");
		} catch (Exception e) {
			System.out.println("This is not SQL server problem, please torn to administrator");
		}
		return coupons;
	}
}// Coupon DBDAO