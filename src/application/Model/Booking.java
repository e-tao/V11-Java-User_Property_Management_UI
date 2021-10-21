package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.View.ViewGenerator;
import javafx.scene.control.TableView;

public class Booking implements Queryable {

	private String propertyName, reservedBy;
	private LocalDate fromD, toD, reservedOn;
	private LinkedHashMap<String, String> tableAttribute;

	private Queryable booking;
	private TableView<Queryable> bookingTable;

	public Booking() {

	}

	public Booking(String propertyName, LocalDate reservedOn, String reservedBy, LocalDate fromD, LocalDate toD) {
		this.propertyName = propertyName;
		this.reservedBy = reservedBy;
		this.fromD = fromD;
		this.toD = toD;
		this.reservedOn = reservedOn;
	}

	public static ArrayList<Booking> GetBooking() throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement(
				"select `property`.`propertyName` AS `Property Name`,`booking`.`bookedFrom` AS `Reserved From`,`booking`.`bookedUntil` AS `Reserved Until`,concat_ws(' ',`user`.`userFirstName`,`user`.`userLastName`) AS `Reserved By`,`booking`.`paymentDate` AS `Reserved On` from ((`booking` join `property` on(`property`.`propertyID` = `booking`.`propertyID`)) join `user` on(`user`.`userID` = `booking`.`UserID`)) where `booking`.`paymentDate` is not null;",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rows = q.executeQuery();

		return ReadAll(rows);

	}

	protected static ArrayList<Booking> ReadAll(ResultSet rows) throws SQLException {
		ArrayList<Booking> bookings = new ArrayList<>();

		while (rows.next()) {

			bookings.add(new Booking(rows.getString("Property Name"), rows.getDate("Reserved On").toLocalDate(),
					rows.getString("Reserved By"), rows.getDate("Reserved From").toLocalDate(),
					rows.getDate("Reserved Until").toLocalDate()));
		}

		return bookings;

	}

	public LinkedHashMap<String, String> getTableAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("propertyName", "Property Name");
		tableAttribute.put("reservedOn", "Reserved On");
		tableAttribute.put("reservedBy", "Reserved By");
		tableAttribute.put("fromD", "Reserved From");
		tableAttribute.put("toD", "Reserved Until");

		return tableAttribute;

	}

	public TableView<Queryable> tableGenerator() throws SQLException {
		booking = new Booking();
		bookingTable = new TableView<>();

		bookingTable = ViewGenerator.getView(booking);

		DBCon.init();
		ArrayList<Booking> bookingList = GetBooking();

		DBCon.close();

		bookingTable.getItems().addAll(bookingList);
		return bookingTable;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getReservedBy() {
		return reservedBy;
	}

	public LocalDate getFromD() {
		return fromD;
	}

	public LocalDate getToD() {
		return toD;
	}

	public LocalDate getReservedOn() {
		return reservedOn;
	}

//	public static void Add(Booking country) throws SQLException {
//		Connection conn = DBCon.getDbConn();
//		PreparedStatement q = conn.prepareStatement(
//				"INSERT INTO `nation`.`countries` (`name`, `area`, `national_day`, `country_code2`, `country_code3`, `region_id`)"
//						+ "VALUES (?,?,?,?,?,?);",
//				Statement.RETURN_GENERATED_KEYS);
//
//		q.setString(1, country.name);
//		q.setDouble(2, country.area);
//		q.setDate(3, Date.valueOf(country.nationalDay));
//		q.setString(4, country.cc2);
//		q.setString(5, country.cc3);
//		q.setInt(6, 1);
//
//		q.executeUpdate();
//		ResultSet genKey = q.getGeneratedKeys();
//		genKey.next();
//		int insertID = genKey.getInt(1);
//
//		country.countryID = insertID;
//
//	}
//
//	public static void Delete(Booking country) throws SQLException {
//		Delete(country.countryID);
//
//	}
//
//	public static void Delete(int id) throws SQLException {
//		Connection conn = DBCon.getDbConn();
//		PreparedStatement q = conn.prepareStatement("DELETE FROM `nation`.`countries` WHERE  `country_id`= ?;");
//		q.setInt(1, id);
//
//		q.execute();
//	}
//
//	public static int Update(Booking country) throws SQLException {
//		if (country.countryID != 0) {
//			Connection conn = DBCon.getDbConn();
//			PreparedStatement q = conn.prepareStatement("UPDATE `nation`.`countries` "
//					+ "SET `name`=?, `area`=?, `national_day`=?,`country_code2`=?, `country_code3` = ? "
//					+ "WHERE  `country_id`=?;");
//
//			q.setString(1, country.name);
//			q.setDouble(2, country.area);
//			q.setDate(3, Date.valueOf(country.nationalDay));
//			q.setString(4, country.cc2);
//			q.setString(5, country.cc3);
//			q.setInt(6, country.countryID);
//
//			return q.executeUpdate();
//		}
//
//		return 0;
//
//	}

//	@Override
//	public String toString() {
//		return propertyName + " " + reservedBy + " " + fromD + " " + toD + " " + reservedOn;
//
//	}
//
//	public static void main(String[] args) throws SQLException {
//
//		DBCon.init();
//		ArrayList<Booking> bookingList = Booking.GetBooking();
//		bookingList.stream().forEach(c -> System.out.println(c));
//		DBCon.close();
//
//	}

}
