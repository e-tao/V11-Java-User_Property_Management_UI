package application.UIComponent;


import application.Queryable.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserSubWindow {

	private Parent root;
	private Stage stage;
	private Scene scene;
	private SecLevelWindow parent;

	User user = new User();

	public UserSubWindow(SecLevelWindow parent) {

		int width = 800;
		int height = 600;

		stage = new Stage();
		VBox vBox = new VBox();
		
		root = vBox;
		
		
		for(String key: user.updateAttribute().keySet()) {
			Label lbl = new Label(user.updateAttribute().get(key));
			HBox hbox = new HBox();
			hbox.getChildren().add(lbl);
			vBox.getChildren().add(hbox);
		}
		
		
		scene = new Scene(root, width, height);
		stage.setScene(scene);
		
		stage.show();

	}
	




	
	
	
	
}
