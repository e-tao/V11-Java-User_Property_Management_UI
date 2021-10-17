package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

//	Booking booking = new Booking();
//	TableColumn<Booking, String> column;

	Queryable booking = new Booking();
	TableView<Queryable> bookingTable = new TableView<>();
	ViewGenerator view = new ViewGenerator();

	@Override
	public void start(Stage primaryStage) {
		try {

			int buttonW = 150;
			int buttonH = 100;
			int centerSpacing = 45;

//  Main UI layout
//  -------------------------------
//      EMPLOYEE NO ___________        root.top - vTop - hTop - Label, Text field
//     
//
//		  -----	     -----
//        |   |      |   |             root.center - vCenter - hCenterTop - Buttons
//		  |	  |      |   |
//        -----		 -----	
//		  -----      -----	
//		  |	  |      |   |             root.center - vCenter - hCenterBottom - Buttons
//		  |   |      |   |	
//		  -----      -----	
//	-------------------------------	   root.bottom - vBottom - Separator
//      FIRERNR COPYRIGHT INFO		   root.bottom - vBottom - Label 			
//  

			BorderPane root = new BorderPane();

			VBox vTop = new VBox();
			VBox vCenter = new VBox();
			VBox vBottom = new VBox();
			HBox hTop = new HBox();
			HBox hCenterTop = new HBox();
			HBox hCenterBottom = new HBox();

			hTop.setPrefHeight(20);
			vTop.setPrefHeight(40);

//=========================== Misc. components ============================================

			Label employee = new Label("Employee No.");
			TextField employeeNo = new TextField();
			employeeNo.setPromptText("8 digts Employee No.");

			Separator separator = new Separator();
			Label copyRight = new Label("FireRnR All Rights Reserved 2019 - 2021");

//======================= Buttons and Decorations Start ===================================	

			Button booking = new Button("_BOOKING");
			Button user = new Button("_USER");
			Button property = new Button("_PROPERTY");
			Button log = new Button("_LOG");

			ImageView bookingImg = new ImageView(new Image(getClass().getResourceAsStream("/rec/booking.png")));
			ImageView userImg = new ImageView(new Image(getClass().getResourceAsStream("/rec/user.png")));
			ImageView propertyImg = new ImageView(new Image(getClass().getResourceAsStream("/rec/property.png")));
			ImageView logImg = new ImageView(new Image(getClass().getResourceAsStream("/rec/log.png")));

			booking.setGraphic(bookingImg);
			user.setGraphic(userImg);
			property.setGraphic(propertyImg);
			log.setGraphic(logImg);

			Tooltip bookingTip = new Tooltip("Manage Bookings");
			bookingTip.setStyle("-fx-background-color: BurlyWood;");
			booking.setTooltip(bookingTip);

			Tooltip userTip = new Tooltip("Manage User Accounts");
			userTip.setStyle("-fx-background-color: BurlyWood;");
			user.setTooltip(userTip);

			Tooltip propertyTip = new Tooltip("Manage Available Properties");
			propertyTip.setStyle("-fx-background-color: BurlyWood;");
			property.setTooltip(propertyTip);

			Tooltip logTip = new Tooltip("System Logs");
			logTip.setStyle("-fx-background-color: BurlyWood;");
			log.setTooltip(logTip);

			booking.setPrefSize(buttonW, buttonH);
			user.setPrefSize(buttonW, buttonH);
			property.setPrefSize(buttonW, buttonH);
			log.setPrefSize(buttonW, buttonH);

//======================= Buttons and Decorations End ===================================			

//======================= Main UI Arrangement Starts ====================================

			hTop.getChildren().addAll(employee, employeeNo);
			hTop.setAlignment(Pos.CENTER);
			hTop.setSpacing(20);
			vTop.getChildren().add(hTop);
			vTop.setAlignment(Pos.BOTTOM_CENTER);

			hCenterTop.getChildren().addAll(booking, property);
			hCenterBottom.getChildren().addAll(user, log);
			vCenter.getChildren().addAll(hCenterTop, hCenterBottom);
			hCenterTop.setAlignment(Pos.CENTER);
			hCenterTop.setSpacing(centerSpacing);
			hCenterBottom.setAlignment(Pos.CENTER);
			hCenterBottom.setSpacing(centerSpacing);
			vCenter.setAlignment(Pos.CENTER);
			vCenter.setSpacing(centerSpacing);

			vBottom.getChildren().addAll(separator, copyRight);
			vBottom.setAlignment(Pos.CENTER);

			root.setTop(vTop);
			root.setCenter(vCenter);
			root.setBottom(vBottom);

			Scene scene = new Scene(root, 500, 400);

////======================= Main UI Arrangement Ends ====================================

//			bookingTable = view.getView(booking);
//			root.setCenter(bookingTable);

//			DBCon.init();
//			ArrayList<Booking> booking = Booking.GetBooking();
//
//			DBCon.close();

//			bookingTable.getItems().addAll(booking);

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
