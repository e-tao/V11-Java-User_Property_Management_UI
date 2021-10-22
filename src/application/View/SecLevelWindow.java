package application.View;

import application.Model.Queryable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecLevelWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;

	UserUpdateSubWindow updateWindow;
	UserDelSubWindow deleteWindow;

	public SecLevelWindow(TableView<Queryable> table, boolean withButtons) {

		int width = 800;
		int height = 600;

		stage = new Stage();
		VBox vBox = new VBox();
		root = vBox;

		if (withButtons) {
			HBox hBox = new HBox();
			Button delete = new Button("DELETE");
			Button update = new Button("UPDATE");

			delete.setOnAction((ae) -> {
				// User.Delete();
				deleteWindow = new UserDelSubWindow(this);
				deleteWindow.show();
			});

			update.setOnAction((ae) -> {
				updateWindow = new UserUpdateSubWindow(this);
				updateWindow.show();
			});

			hBox.setSpacing(80);
			hBox.setPrefHeight(100);
			hBox.setAlignment(Pos.BOTTOM_CENTER);

			hBox.getChildren().addAll(delete, update);
			vBox.getChildren().addAll(table, hBox);
		} else {
			vBox.getChildren().add(table);
		}

		scene = new Scene(root, width, height);
		stage.setScene(scene);

	}

	public void show() {
		stage.show();
	}

	public void close() {
		stage.close();
	}

	public Stage getStage() {
		return stage;
	}

}
