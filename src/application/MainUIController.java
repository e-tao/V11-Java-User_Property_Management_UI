package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainUIController {

	// ============= FXML Variables ===============

	@FXML
	private Button amenity;

	@FXML
	private TextField employeeNo;

	@FXML
	private Button property;

	@FXML
	private Button user;

	// =====ADD FXML VARIABLES ABOVE THIS LINE=====

	private MessageBox message;

	private ArrayList<Booking> booking;

	private void checkEmployeeNo() {

		if (employeeNo.getText().isEmpty()) {

			message = new MessageBox(AlertType.WARNING, "Employee No Required",
					"Please ENTER your employee no to continue.",
					"The company is tracking all activities on database. \nOperators need to input their employee no.");

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
		DBCon.init();
		try {
			booking = Booking.GetBooking();
		} catch (SQLException e) {
			message = new MessageBox(AlertType.WARNING, "SQL Query Failer",
					"An Error occured while querying the database.", e.getMessage());
		}
	}

}