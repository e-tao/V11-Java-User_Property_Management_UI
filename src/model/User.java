package model;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import application.Queryable;
import javafx.scene.control.TableView;

public class User implements Queryable {

	@Override
	public LinkedHashMap<String, String> getTableAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableView<Queryable> tableGenerator() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
