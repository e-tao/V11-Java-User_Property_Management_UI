package application.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Log {

	private String employeeNumber;
	private String event;

	public Log(String employeeNumber, String event, LocalDateTime now) {
		this.employeeNumber = employeeNumber;
		this.event = event;

	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public static int addLog(Log log) throws SQLException {
		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement("INSERT INTO `log` (`Employee`, `Event`, `Time`) VALUES (?, ?, ?);",
				Statement.RETURN_GENERATED_KEYS);

		q.setString(1, log.employeeNumber);
		q.setString(2, log.event);
		q.setDate(3, Date.valueOf(LocalDateTime.now().toLocalDate()));

		q.executeUpdate();

		ResultSet genKey = q.getGeneratedKeys();
		genKey.next();
		int logID = genKey.getInt(1);

		return logID;

	}

}
