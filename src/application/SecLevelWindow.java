package application;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecLevelWindow {

	private BorderPane root;
	private Stage stage;
	private Scene scene;

	private TableView<Queryable> table;
	private ViewGenerator view;

	// TableView<Queryable> table, ViewGenerator view

	public SecLevelWindow() {

		root = new BorderPane();
		stage = new Stage();
		HBox hBoxTop = new HBox();
		HBox hBoxBottom = new HBox();
		VBox vBox = new VBox();

		vBox.getChildren().addAll(hBoxTop, hBoxBottom);
		root.getChildren().add(vBox);

		scene = new Scene(root, 800, 600);
		stage.setScene(scene);

	}

	public void show() {
		stage.show();
	}

}
