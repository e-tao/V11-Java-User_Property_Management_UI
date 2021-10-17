package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

//	Booking booking = new Booking();
//	TableColumn<Booking, String> column;

	Queryable booking = new Booking();
	TableView<Queryable> table = new TableView<>();
	ViewGenerator view = new ViewGenerator();

	@Override
	public void start(Stage primaryStage) {
		try {
			// Parent root = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));

			BorderPane root = new BorderPane();
			table = view.getView(booking);

//			TableView<Booking> table = new TableView<>();
//
//			for (String s : booking.getTableArrtibute().keySet()) {
//
//				column = new TableColumn<>(booking.getTableArrtibute().get(s));
//				column.setCellValueFactory(new PropertyValueFactory<Booking, String>(s));
//				table.getColumns().add(column);
//			}
//
////			TableColumn<Booking, String> colName = new TableColumn<>("Property");
////			colName.setCellValueFactory(new PropertyValueFactory<>("propertyName"));
////
////			TableColumn<Booking, String> colArea = new TableColumn<>("Reserved By");
////			colArea.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
////
////			table.getColumns().add(colName);
////			table.getColumns().add(colArea);
//
//			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			Scene scene = new Scene(root, 1024, 768);
			root.setCenter(table);

			DBCon.init();
			ArrayList<Booking> booking = Booking.GetBooking();

			DBCon.close();

			table.getItems().addAll(booking);

			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/rec/icon.png"));
			primaryStage.setTitle("FireRnR Database Query System");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
