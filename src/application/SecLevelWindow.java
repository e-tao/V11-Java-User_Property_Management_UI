package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecLevelWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;

	// private TableView<Queryable> table;
	// private ViewGenerator view;
	// TableView<Queryable> table, ViewGenerator view

	public SecLevelWindow(TableView<Queryable> table) {

		int width = 800;
		int height = 600;

		stage = new Stage();
		VBox vBox = new VBox();
		vBox.getChildren().add(table);
		root = vBox;

		scene = new Scene(root, width, height);
		stage.setScene(scene);

	}

	public void show() {
		stage.show();
	}

}
