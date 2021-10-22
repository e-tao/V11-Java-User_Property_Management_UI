package application.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.View.ViewGenerator;
import javafx.scene.control.TableView;

public class Log implements Queryable {

	private String employee;
	private String event;
	private LocalDate opDate;
	private LocalTime opTime;

	private LinkedHashMap<String, String> tableAttribute;
	private TableView<Queryable> logTable;
	private Queryable log;

	public Log() {

	}

	public Log(String employee, String event, LocalDate opDate, LocalTime opTime) {
		this.employee = employee;
		this.event = event;
		this.opDate = opDate;
		this.opTime = opTime;
	}

	public static ArrayList<Log> GetLog() throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement("SELECT `employee`, `event`, `date`, `time` FROM log;",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rows = q.executeQuery();

		return ReadAll(rows);

	}

	protected static ArrayList<Log> ReadAll(ResultSet rows) throws SQLException {
		ArrayList<Log> logs = new ArrayList<>();

		while (rows.next()) {

			logs.add(new Log(rows.getString("employee"), rows.getString("event"), rows.getDate("date").toLocalDate(),
					rows.getTime("time").toLocalTime()));

		}

		return logs;
	}

	public static int addLog(Log log) {
		try {
			Connection conn = DBCon.getDbConn();
			PreparedStatement q;
			q = conn.prepareStatement("INSERT INTO `log` (`employee`, `event`, `date`, `time`) VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			q.setString(1, log.employee);
			q.setString(2, log.event);
			q.setDate(3, Date.valueOf(LocalDate.now()));
			q.setTime(4, Time.valueOf(LocalTime.now()));

			q.executeUpdate();

			ResultSet genKey = q.getGeneratedKeys();
			genKey.next();
			int logID = genKey.getInt(1);

			return logID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public LinkedHashMap<String, String> getTableAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("employee", "Employee Number");
		tableAttribute.put("event", "Event");
		tableAttribute.put("opDate", "Date");
		tableAttribute.put("opTime", "Time");
		return tableAttribute;
	}

	@Override
	public TableView<Queryable> tableGenerator() throws SQLException {
		log = new Log();
		logTable = new TableView<>();

		logTable = ViewGenerator.getView(log);

		DBCon.init();
		ArrayList<Log> logList = Log.GetLog();

		// DBCon.close();

		logTable.getItems().addAll(logList);
		return logTable;

	}

	public String getEmployee() {
		return employee;
	}

	public String getEvent() {
		return event;
	}

	public LocalDate getOpDate() {
		return opDate;
	}

	public LocalTime getOpTime() {
		return opTime;
	}

}
