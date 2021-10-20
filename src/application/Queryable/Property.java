package application.Queryable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import application.Connection.DBCon;
import application.UIGenerator.ViewGenerator;
import javafx.scene.control.TableView;

//SELECT `propertyName` AS `Property Name`, CONCAT_WS(',', propertyAddrSuitNo, propertyAddrNo, propertyAddrStreet, propertyAddrCity, propertyAddrProv, propertyAddrCountry, propertyPostalCode) AS `Address`, `noOfRooms` AS `Rooms` FROM property

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

	public static ArrayList<Property> GetProperty() throws SQLException {

		Connection conn = DBCon.getDbConn();

		PreparedStatement q = conn.prepareStatement("SELECT `propertyName` AS `Property Name`,"
				+ "CONCAT_WS(',', propertyAddrSuitNo, propertyAddrNo, propertyAddrStreet, propertyAddrCity, propertyAddrProv, propertyAddrCountry, propertyPostalCode) AS `Address`,"
				+ "`noOfRooms` AS `Rooms` FROM property;",

				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ResultSet result = q.executeQuery();

		ArrayList<Property> properties = new ArrayList<>();

		while (result.next()) {

			properties.add(new Property(result.getString("Property Name"), result.getString("Address"),
					result.getInt("Rooms")));
		}

		return properties;

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
	public TableView<Queryable> tableGenerator() throws SQLException {
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
