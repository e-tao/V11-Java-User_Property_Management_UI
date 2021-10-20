package application.Queryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.Connection.DBCon;
import application.UIComponent.ViewGenerator;
import javafx.scene.control.TableView;

public class User implements Queryable {

	private String username, userFirstName, userLastName, mailAddr, emaiAddr;
	private String mailAddrSuitNo, mailAddrStNo, mailAddrSt, mailAddrCity, mailAddrProv, mailAddrCountry,
			mailPostalCode;

	private LinkedHashMap<String, String> tableAttribute;

	private Queryable user;
	private TableView<Queryable> userTable;

	public User() {

	}

	public User(String username, String userFirstName, String userLastName, String mailAddr, String emaiAddr) {
		this.username = username;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.mailAddr = mailAddr;
		this.emaiAddr = emaiAddr;
	}

	public static ArrayList<User> GetUser() throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement(
				"SELECT `userName` AS `Username`, `userFirstName` AS `First Name`, `userLastName` AS `Last Name`, "
						+ "CONCAT_WS(', ', mailAddrSuitNo, mailAddrStreetNo, mailAddrStreet, mailAddrCity, mailAddrProv, mailAddrCountry, mailPostalCode) AS `Address`, "
						+ "email AS `Email` FROM user;",

				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ResultSet result = q.executeQuery();

		ArrayList<User> users = new ArrayList<>();

		while (result.next()) {

			users.add(new User(result.getString("Username"), result.getString("First Name"),
					result.getString("Last Name"), result.getString("Address"), result.getString("Email")));
		}

		return users;

	}

	public static void Delete() throws SQLException {
		Connection conn = DBCon.getDbConn();
		PreparedStatement q = conn.prepareStatement("DELETE FROM `user` WHERE accountActivation= ?;");
		q.setInt(1, 0);

		q.execute();
	}

	@Override
	public LinkedHashMap<String, String> getTableAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("username", "Username");
		tableAttribute.put("userFirstName", "First Name");
		tableAttribute.put("userLastName", "Last Name");
		tableAttribute.put("mailAddr", "Address");
		tableAttribute.put("emaiAddr", "Email Address");

		return tableAttribute;
	}

	@Override
	public TableView<Queryable> tableGenerator() throws SQLException {
		user = new User();
		userTable = new TableView<>();

		userTable = ViewGenerator.getView(user);

		DBCon.init();
		ArrayList<User> userList = User.GetUser();

		// DBCon.close();

		userTable.getItems().addAll(userList);
		return userTable;

	}

	public String getUsername() {
		return username;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public String getMailAddr() {
		return mailAddr;
	}

	public String getEmaiAddr() {
		return emaiAddr;
	}

}
