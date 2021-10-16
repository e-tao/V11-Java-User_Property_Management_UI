package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	private static Connection dbConn;

	public static Connection getDbConn() {
		return dbConn;
	}

	public static void init() {

		try {
			dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nation", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
