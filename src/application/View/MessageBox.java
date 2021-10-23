package application.View;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MessageBox {

	public MessageBox(AlertType type, String title, String content, String detailMsg) {
		Alert message = new Alert(type);
		message.setTitle(title);
		message.setHeaderText(null);
		message.setContentText(content);
		if (!detailMsg.isEmpty()) {
			message.getDialogPane().setExpandableContent(DetailMessage(detailMsg));
		}

		message.showAndWait();
	}

	private Node DetailMessage(String detailMsg) {
		HBox details = new HBox();
		details.getChildren().add(new Text(detailMsg));

		return details;
	}

}
