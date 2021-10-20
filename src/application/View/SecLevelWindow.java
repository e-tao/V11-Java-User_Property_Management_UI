package application.View;

import java.sql.SQLException;

import application.Model.*;
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
	
	UserSubWindow updateWindow;

	// private TableView<Queryable> table;
	// private ViewGenerator view;
	// TableView<Queryable> table, ViewGenerator view

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
				try {
					User.Delete();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// System.out.println(Main.getEmployeeNnumber() + " deleted a record at " +
				// LocalDateTime.now());
			});
			
			update.setOnAction((ae)->{
				
				updateWindow = new UserSubWindow(this);

				
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

}
