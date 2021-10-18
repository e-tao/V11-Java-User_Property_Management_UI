package application;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewGenerator {

	TableColumn<Queryable, String> column;
	TableView<Queryable> table;

//	Booking booking = new Booking();
//	TableColumn<Booking, String> column;

	public TableView<Queryable> getView(Queryable item) {
		table = new TableView<>();

		for (String s : item.getTableArrtibute().keySet()) {

			column = new TableColumn<>(item.getTableArrtibute().get(s));
			column.setCellValueFactory(new PropertyValueFactory<Queryable, String>(s));
			table.getColumns().add(column);
		}

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

}
