package application.View;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import application.Main;
import application.Model.Employee;
import application.Model.Log;
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

public class UserUpdateSubWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;
	private SecLevelWindow parent;
	private Log log;

	ArrayList<TextField> updateTxtFd = new ArrayList<>();
	ArrayList<String> userAttributes = new ArrayList<>();
	User user = new User();

	Employee op = new Employee(Main.getEmployeeNo());

	public UserUpdateSubWindow(SecLevelWindow parent) {

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

		Button update = new Button("_UPDATE");
		Button query = new Button("_QUERY");

		query.setOnAction((ae) -> {
			if (updateTxtFd.get(0).getText().isEmpty()) {
				MessageBox message = new MessageBox(AlertType.WARNING, "USERNAME IS EMPTY",
						"Enter the username you want to update", "");
			} else {
				userAttributes = User.GetUser(updateTxtFd.get(0).getText());
				if (userAttributes.size() != 0) {
					for (int i = 1; i < updateTxtFd.size(); i++) {
						updateTxtFd.get(i).setText(userAttributes.get(i));

					}
				} else {
					MessageBox message = new MessageBox(AlertType.WARNING, "USER DOES NOT EXIST",
							"The username entered does not exist in the database.", "");
				}

			}
		});

		update.setOnAction((ae) -> {
			User modifiedUser = new User(updateTxtFd.get(0).getText(), updateTxtFd.get(1).getText(),
					updateTxtFd.get(2).getText(), updateTxtFd.get(3).getText(), updateTxtFd.get(4).getText());

			if (!userAttributes.get(0).equals(updateTxtFd.get(0).getText())) {
				MessageBox message = new MessageBox(AlertType.WARNING, "CANNOT CHANGE USERNAME",
						"Changing username is not allowed", "");
			} else {
				User.Update(modifiedUser);

//				System.out.println(Main.getEmployeeNo());

				log = new Log(Main.getEmployeeNo(), getChanges(userAttributes, updateTxtFd).toString(), LocalDate.now(),
						LocalTime.now());
				int logID = Log.addLog(log);

				MessageBox message = new MessageBox(AlertType.INFORMATION, "USE RECORD UPDATED",
						"You have successfully updated user recored",
						getChanges(userAttributes, updateTxtFd).toString() + ", LogID: " + logID);
			}

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

	public static ArrayList<String> getChanges(ArrayList<String> before, ArrayList<TextField> after) {
		ArrayList<String> difference = new ArrayList<>();

		for (int i = 0; i < before.size(); i++) {
			if (!before.get(i).equals(after.get(i).getText())) {
				difference.add("Changed from " + before.get(i) + " to " + after.get(i).getText());
			}
		}

		return difference;

	}

}
