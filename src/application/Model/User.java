package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.View.ViewGenerator;
import javafx.scene.control.TableView;

public class User implements Queryable {

	private String username, userFirstName, userLastName, mailAddr, emaiAddr;
	private String phoneNo;

	private LinkedHashMap<String, String> tableAttribute;

	private Queryable user;
	private TableView<Queryable> userTable;

	public User() {

	}

	public User(String username, String userFirstName, String userLastName, String phoneNo, String emaiAddr) {
		this.username = username;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.emaiAddr = emaiAddr;
		this.phoneNo = phoneNo;
	}

	public User(String username, String userFirstName, String userLastName, String mailAddr, String phoneNo,
			String emaiAddr) {
		this(username, userFirstName, userLastName, phoneNo, emaiAddr);
		this.mailAddr = mailAddr;
	}

	public static ArrayList<User> GetUser() throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement(
				"SELECT `userName` AS `Username`, `userFirstName` AS `First Name`, `userLastName` AS `Last Name`, "
						+ "CONCAT_WS(', ', mailAddrSuitNo, mailAddrStreetNo, mailAddrStreet, mailAddrCity, mailAddrProv, mailAddrCountry, mailPostalCode) AS `Address`, "
						+ "email AS `Email`, `phoneNo` AS `Phone Number` FROM user;",

				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ResultSet rows = q.executeQuery();

		return ReadAll(rows);

	}
	

	public static ArrayList<String> GetUser(String username) throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement(
				"SELECT userName, userFirstName, userLastName, phoneNo, email FROM user WHERE userName= ?;",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		q.setString(1, username);

		ResultSet rows = q.executeQuery();

		ArrayList<String> userAttributes = new ArrayList<>();
		while (rows.next()) {
					
				userAttributes.add(rows.getString("userName"));
				userAttributes.add(rows.getString("userFirstName"));
				userAttributes.add(rows.getString("userLastName"));
				
				String phoneNo = "0";
				if (rows.getString("phoneNo") != null) {
					phoneNo = rows.getString("phoneNo");
				}
				userAttributes.add(phoneNo);
				userAttributes.add(rows.getString("email"));
			
			}
		
		return userAttributes;
		
		}
	
	
	protected static ArrayList<User> ReadAll(ResultSet rows) throws SQLException {
		ArrayList<User> users = new ArrayList<>();

		while (rows.next()) {

			String phoneNo = "";
			if (rows.getString("phoneNo") != null) {
				phoneNo = rows.getString("phoneNo");
			}
			
				users.add(new User(rows.getString("Username"), rows.getString("First Name"),
						rows.getString("Last Name"), rows.getString("Address"), phoneNo, rows.getString("Email")));

		}

		return users;
	}
	

	public static void Delete() throws SQLException {
		Connection conn = DBCon.getDbConn();
		PreparedStatement q = conn.prepareStatement("DELETE FROM `user` WHERE accountActivation= ?;");
		q.setInt(1, 0);

		q.execute();
	}

	public LinkedHashMap<String, String> updateAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("username", "Username");
		tableAttribute.put("userFirstName", "First Name");
		tableAttribute.put("userLastName", "Last Name");
		tableAttribute.put("phoneNo", "Phone Number");
		tableAttribute.put("emaiAddr", "Email Address");

		return tableAttribute;
	}

	@Override
	public LinkedHashMap<String, String> getTableAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("username", "Username");
		tableAttribute.put("userFirstName", "First Name");
		tableAttribute.put("userLastName", "Last Name");
		tableAttribute.put("mailAddr", "Address");
		tableAttribute.put("emaiAddr", "Email Address");
		tableAttribute.put("phoneNo", "Phone No");

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public String toString() {
		return String.format("%s, %s, %s, %s, %s", username, userFirstName, userLastName, phoneNo, emaiAddr);
	}

}
