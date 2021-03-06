package application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.View.ViewGenerator;
import javafx.scene.control.TableView;

public class Property implements Queryable {

	private String propertyName, address;
	private int rooms;

	private LinkedHashMap<String, String> tableAttribute;

	private Queryable property;
	private TableView<Queryable> propertyTable;

	public Property() {

	}

	public Property(String propertyName, String address, int rooms) {
		this.propertyName = propertyName;
		this.address = address;
		this.rooms = rooms;
	}

	public static ArrayList<Property> GetProperty() {
		try {
			Connection conn = DBCon.getDbConn();
			PreparedStatement q;
			q = conn.prepareStatement("SELECT `propertyName` AS `Property Name`,"
					+ "CONCAT_WS(',', propertyAddrSuitNo, propertyAddrNo, propertyAddrStreet, propertyAddrCity, propertyAddrProv, propertyAddrCountry, propertyPostalCode) AS `Address`,"
					+ "`noOfRooms` AS `Rooms` FROM property;", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rows = q.executeQuery();
			return ReadAll(rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static ArrayList<Property> ReadAll(ResultSet rows) {
		try {
			ArrayList<Property> properties = new ArrayList<>();
			while (rows.next()) {
				properties.add(
						new Property(rows.getString("Property Name"), rows.getString("Address"), rows.getInt("Rooms")));
			}
			return properties;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LinkedHashMap<String, String> getTableAttribute() {
		tableAttribute = new LinkedHashMap<>();
		tableAttribute.put("propertyName", "Property Name");
		tableAttribute.put("address", "Address");
		tableAttribute.put("rooms", "Rooms");

		return tableAttribute;
	}

	@Override
	public TableView<Queryable> tableGenerator() {
		property = new Property();
		propertyTable = new TableView<>();

		propertyTable = ViewGenerator.getView(property);

		DBCon.init();
		ArrayList<Property> propertyList = Property.GetProperty();

		DBCon.close();

		propertyTable.getItems().addAll(propertyList);
		return propertyTable;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getAddress() {
		return address;
	}

	public int getRooms() {
		return rooms;
	}

}
