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

	public static ArrayList<Booking> GetBooking() {
		try {

			Connection conn = DBCon.getDbConn();
			PreparedStatement q;
			q = conn.prepareStatement(
					"select `property`.`propertyName` AS `Property Name`,`booking`.`bookedFrom` AS `Reserved From`,`booking`.`bookedUntil` AS `Reserved Until`,concat_ws(' ',`user`.`userFirstName`,`user`.`userLastName`) AS `Reserved By`,`booking`.`paymentDate` AS `Reserved On` from ((`booking` join `property` on(`property`.`propertyID` = `booking`.`propertyID`)) join `user` on(`user`.`userID` = `booking`.`UserID`)) where `booking`.`paymentDate` is not null;",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rows = q.executeQuery();
			return ReadAll(rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	protected static ArrayList<Booking> ReadAll(ResultSet rows) {
		try {
			ArrayList<Booking> bookings = new ArrayList<>();

			while (rows.next()) {

				bookings.add(new Booking(rows.getString("Property Name"), rows.getDate("Reserved On").toLocalDate(),
						rows.getString("Reserved By"), rows.getDate("Reserved From").toLocalDate(),
						rows.getDate("Reserved Until").toLocalDate()));
			}
			return bookings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

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

	public TableView<Queryable> tableGenerator() {
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

}
