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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserDelSubWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;
	private SecLevelWindow parent;
	private Log log;

	User user = new User();

	Employee op = new Employee(Main.getEmployeeNo());

	public UserDelSubWindow(SecLevelWindow parent) {

		int width = 280;
		int height = 200;
		this.parent = parent;

		stage = new Stage();
		HBox hBoxTop = new HBox();
		HBox hBoxBottom = new HBox();
		VBox vBox = new VBox();

		TextField txtFd = new TextField();
		txtFd.setPromptText("username you want to delete");
		txtFd.setFocusTraversable(false);
		txtFd.setPrefWidth(200);

		root = vBox;

		Button delete = new Button("_DELETE");
		Button purge = new Button("_PURGE");

		delete.setOnAction((ae) -> {

			String username = txtFd.getText();

			if (username.isEmpty()) {
				MessageBox message = new MessageBox(AlertType.WARNING, "USERNAME IS EMPTY",
						"Enter the username you want to delete", "");
			} else {

				if (User.GetUser(txtFd.getText()).size() != 0) {
					User.Delete(username);
					log = new Log(Main.getEmployeeNo(), "user " + username + " is deleted", LocalDate.now(),
							LocalTime.now());
					int logID = Log.addLog(log);

					MessageBox message = new MessageBox(AlertType.INFORMATION, "USE RECORDS DELETED",
							"You have successfully deleted user: " + username + ", LogID: " + logID, "");

				} else {
					MessageBox message = new MessageBox(AlertType.WARNING, "USER DOES NOT EXIST",
							"The username entered does not exist in the database.", "");
				}

			}
		});

		purge.setOnAction((ae) -> {
			User.Purge();
			log = new Log(Main.getEmployeeNo(), "Purge inactive user", LocalDate.now(), LocalTime.now());
			int logID = Log.addLog(log);

			MessageBox message = new MessageBox(AlertType.INFORMATION, "USE RECORDS DELETED",
					"You have successfully purged inactive users in the database, LogID: " + logID, "");

		});

		hBoxTop.getChildren().add(txtFd);
		hBoxTop.setAlignment(Pos.CENTER);

		hBoxBottom.getChildren().addAll(delete, purge);
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

	public static ArrayList<String> getEvent(ArrayList<String> before, ArrayList<TextField> after) {
		ArrayList<String> difference = new ArrayList<>();

		for (int i = 0; i < before.size(); i++) {
			if (!before.get(i).equals(after.get(i).getText())) {
				difference.add("Changed from " + before.get(i) + " to " + after.get(i).getText());
			}
		}

		return difference;

	}

}
