package il.co.johnbryce.coupons_system.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {
	private static ConnectionPool _instance = new ConnectionPool();
	private Set<Connection> _connection;

	private ConnectionPool() {
		_connection = new HashSet<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			for (int i = 0; i < 9; i++) {
				_connection.add(DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/CouponDB?allowPublicKeyRetrieval=true&useSSL=false&&serverTimezone=UTC",
						"igor", "pretender1988"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// c-tor

	public static ConnectionPool getConnectionPool() {
		return _instance;
	}// getConnectionPool

	public Connection getConnection() throws Exception {// TODO: What exactly an exception
		if (_connection.iterator().hasNext() == false)
			synchronized (_instance) {
				this.wait();
			}
		return _connection.iterator().next();
	}// getConnetion

	public void returnConnection(Connection conn) {
		synchronized (_instance) {
			_connection.add(conn);
			this.notify();
		}
	}// returnConnection

	public void closeAllConnections() {
		for (Connection curr : _connection) {
			try {
				curr.close();
			} catch (SQLException e) {
				// TODO Take care of SQLExeption
			}
		}
	}// closeAllConnections
}// ConnectionPool
