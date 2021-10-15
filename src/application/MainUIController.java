package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MainUIController {

	@FXML
	private Button amenity;

	@FXML
	private TextField employeeNo;

	@FXML
	private Button property;

	@FXML
	private Button user;

	private void checkEmployeeNo() {
		if (employeeNo.getText().isEmpty()) {

			Alert warning = new Alert(AlertType.WARNING);
			warning.setTitle("Employee No Required.");
			warning.setHeaderText(null);
			warning.setContentText("Please enter your employee number to continue.");

			HBox layout = new HBox();
			layout.getChildren().add(new Text(
					"The company is tracking all activities on database. \nOperators need to input their employee no."));
			warning.getDialogPane().setExpandableContent(layout);

			warning.showAndWait();

			// System.out.println("Please Enter Your Employee ID");
		}
	}

	@FXML
	void AmenityUI(ActionEvent event) {
		checkEmployeeNo();
	}

	@FXML
	void PropertyUI(ActionEvent event) {
		checkEmployeeNo();
	}

	@FXML
	void userUI(ActionEvent event) {
		checkEmployeeNo();
	}

}
