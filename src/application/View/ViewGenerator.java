package application.View;

import application.Model.Queryable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewGenerator {

//	Booking booking = new Booking();
//	TableColumn<Booking, String> column;

	public static TableView<Queryable> getView(Queryable item) {
		TableColumn<Queryable, String> column;
		TableView<Queryable> table = new TableView<>();

		for (String s : item.getTableAttribute().keySet()) {

			column = new TableColumn<>(item.getTableAttribute().get(s));
			column.setCellValueFactory(new PropertyValueFactory<Queryable, String>(s));
			table.getColumns().add(column);
		}

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

}
