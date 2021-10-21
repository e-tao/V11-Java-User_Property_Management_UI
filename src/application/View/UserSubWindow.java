package application.View;

import java.sql.SQLException;
import java.util.ArrayList;

import application.Model.User;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserSubWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;
	private SecLevelWindow parent;

	ArrayList<TextField> updateTxtFd = new ArrayList<>();
	ArrayList<User> userAttributes = new ArrayList<>();
	User user = new User();

	public UserSubWindow(SecLevelWindow parent) {

		int width = 280;
		int height = 350;
		this.parent = parent;

		stage = new Stage();

		HBox hBoxTop = new HBox();
		HBox hBoxBottom = new HBox();
		VBox vBoxLeft = new VBox();
		VBox vBoxRight = new VBox();
		VBox vBox = new VBox();

		TextField txtFd = null;

		root = vBox;

		for (String key : user.updateAttribute().keySet()) {
			Label lbl = new Label(user.updateAttribute().get(key));
			vBoxLeft.getChildren().add(lbl);
			vBoxLeft.setSpacing(39);
		}

		for (String key : user.updateAttribute().keySet()) {
			txtFd = new TextField();
			txtFd.setPromptText(key);
			txtFd.setFocusTraversable(false);
			updateTxtFd.add(txtFd);
			vBoxRight.getChildren().add(txtFd);
			vBoxRight.setSpacing(30);
		}

		Button update = new Button("UPDATE");
		Button query = new Button("QUERY");

		query.setOnAction((ae) -> {
			if (updateTxtFd.get(0).getText().isEmpty()) {
				MessageBox message = new MessageBox(AlertType.WARNING, "USERNAME IS EMPTY",
						"Enter the username you want to update", "");
			} else {
				try {
					userAttributes = User.GetUser(updateTxtFd.get(0).getText());
					for (int i = 0; i < userAttributes.size(); i++) {
						updateTxtFd.get(i).setText(userAttributes.get(i).toString());
					}

					for (User s : userAttributes) {
						System.out.println(s);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		update.setOnAction((ae) -> {

			System.out.println(updateTxtFd.get(0).getText());

			// updateTxtFd.stream().forEach(b -> System.out.println(b));

		});

		hBoxTop.getChildren().addAll(vBoxLeft, vBoxRight);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);

		hBoxBottom.getChildren().addAll(query, update);
		hBoxBottom.setSpacing(30);
		hBoxBottom.setAlignment(Pos.CENTER);

		vBox.getChildren().addAll(hBoxTop, hBoxBottom);
		vBox.setSpacing(30);
		vBox.setAlignment(Pos.CENTER);

		scene = new Scene(root, width, height);
		stage.setScene(scene);

	}

	public void close() {
		stage.close();
	}

	public void show() {

		stage.initOwner(parent.getStage());
		stage.initModality(Modality.WINDOW_MODAL);

		stage.showAndWait();
	}

}
