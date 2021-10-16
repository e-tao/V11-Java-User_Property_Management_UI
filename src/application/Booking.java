package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {

	String propertyName, reservedBy;
	LocalDate fromD, toD, reservedOn;

	public Booking(String propertyName, String reservedBy, LocalDate fromD, LocalDate toD, LocalDate reservedOn) {
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
		ResultSet result = q.executeQuery();

		ArrayList<Booking> bookings = new ArrayList<>();

		while (result.next()) {

			bookings.add(new Booking(result.getString("Property Name"), result.getString("Reserved By"),
					result.getDate("Reserved Until").toLocalDate(), result.getDate("Reserved From").toLocalDate(),
					result.getDate("Reserved On").toLocalDate()));
		}

		return bookings;

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
//		return name + " " + nationalDay + " " + cc2 + " " + cc3;
//
//	}

}
