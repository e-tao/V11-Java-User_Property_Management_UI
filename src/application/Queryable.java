package application;

import java.sql.SQLException;
import java.util.HashMap;

import javafx.scene.control.TableView;

public interface Queryable {

	public HashMap<String, String> getTableAttribute();

	// the getTableA

	public TableView<Queryable> tableGenerator() throws SQLException;

}
