package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;

public class DBCon {

	private static Connection dbConn;

	public static Connection getDbConn() {
		return dbConn;
	}

	public static void init() {

		try {
			dbConn = DriverManager.getConnection("jdbc:mysql://192.168.1.15:3306/firernr", "ethan", "Ethan818213!");
			// System.out.println("Database connected!");
		} catch (SQLException e) {
			// e.printStackTrace();
			MessageBox message = new MessageBox(AlertType.WARNING, "SQL Connection Failure",
					"Cannot connect to SQL Database.", "");
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
