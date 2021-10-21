package application.Model;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import javafx.scene.control.TableView;

public interface Queryable {

	public LinkedHashMap<String, String> getTableAttribute();

	// In order to preserve the Order of the HashMap, the LinkedHashMap is used.
	// The Map uses instant variables (usually those include in the constructor)
	// of the implementing Class as key and SQL query attributes as value;
	//
	// When the ViewGenerator.getView() is called, the getTableAttribute() inside
	// the method returns a LinkedHashMap, the values are used to generate
	// TableColumn titles, and keys are used for PropertyValueFactory;

	public TableView<Queryable> tableGenerator() throws SQLException;

	// This method calls the ViewGenerator.getView() method to generate a TableView
	// and query database to get all the results.
	// ActionEvents in the Main Class create SecLevelWindows to hold the TableView
	// results.

}
